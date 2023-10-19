package hsgLandAsset.admin.minwon.service;

import java.util.Enumeration;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gpki.gpkiapi_jni;

import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPEntry;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPSearchConstraints;
import netscape.ldap.LDAPSearchResults;

@Service
public class GPKIService {
	
	private gpkiapi_jni gpkiAPI = null;

	
	@Value("${gpkiPath}")
	String gpkiPath;
	
	@Value("${LIC_PATH}")
	String LIC_PATH;


	public void setup() throws Exception {
		synchronized (this) {
			if (gpkiAPI == null) {
				gpkiAPI = new gpkiapi_jni();
				
			}
		}
	}


	/**
	 * LDAP에서 인증서 얻기.
	 *
	 * @param code
	 * @return
	 * @throws Exception
	 */
	
	public byte[] getCertFromLDAP(String code) throws Exception {
		//-----------------------------------------
		// @PostConstruct 미사용 방식
		//-----------------------------------------
		if (gpkiAPI == null) {
			setup();
		}
		////---------------------------------------

		//--------------------------------
		// LDAP 관련 정보 얻기
		//--------------------------------
		String serverIp = "152.99.56.86";
		String serverPort = "389";
		String basedn = "ou=Group of Server,o=Government of Korea,c=kr";
		String readEntry = "cn=SVR" + code;
		String attribute = "usercertificate;binary";

		String pwd = null;

		//--------------------------------
		// LDAP 연결
		//--------------------------------
		byte[] cert = null;
		LDAPEntry entry = null;
		Enumeration<?> enumerator = null;
		LDAPSearchConstraints cons = null;
		LDAPSearchResults res = null;
		LDAPConnection ld = null;
		LDAPSearchConstraints constraints = null;

		try {
			ld = new LDAPConnection();
			constraints = new LDAPSearchConstraints();
			constraints.setTimeLimit(5000);
			ld.setConnectTimeout(3);
			ld.setConstraints(constraints);

			ld.connect(serverIp, Integer.parseInt(serverPort), basedn, pwd);

			cons = ld.getSearchConstraints();
			cons.setBatchSize(1);
			res = ld.search(basedn, 2, readEntry, null, false, cons);
			entry = (LDAPEntry) res.nextElement();
			enumerator = entry.getAttribute(attribute).getByteValues();
			cert = (byte[]) enumerator.nextElement();
		} finally {
			if (ld != null) {
				try {
					ld.disconnect();
				} catch (LDAPException ignore) {
					//LOGGER.debug("Ignored Exception (LDAP Disconnect)", ignore);
				}
			}
		}

		return cert;
	}

	/**
	 * 데이터 암호화 처리.
	 *
	 * @see GPKIService.com.sec.pki.service.EgovGPKIService#encrypt(byte[], java.lang.String)
	 */
	
	public byte[] encrypt(byte[] message, String target) throws Exception {
		//-----------------------------------------
		// @PostConstruct 미사용 방식
		//-----------------------------------------
		if (gpkiAPI == null) {
			setup();
		}
		////---------------------------------------

		byte[] cert = getCertFromLDAP(target);

		byte[] encryptedData = null;

		try {
			gpkiAPI.API_Init(LIC_PATH);
			int returnCode = 0;

			returnCode = gpkiAPI.API_SetOption(gpkiapi_jni.API_OPT_RSA_ENC_V20);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}

			//returnCode = gpkiAPI.CMS_MakeEnvelopedData(cert, message, gpkiapi_jni.SYM_ALG_SEED_CBC);
			returnCode = gpkiAPI.CMS_MakeEnvelopedData(cert, message, gpkiapi_jni.SYM_ALG_NEAT_CBC);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			encryptedData = gpkiAPI.baReturnArray;

		} finally {
			if (gpkiAPI != null) {
				gpkiAPI.API_Finish();
			}
		}

		return encryptedData;
	}

	/**
	 * 복호화 처리.
	 *
	 * @see GPKIService.com.sec.pki.service.EgovGPKIService#decrypt(byte[])
	 */
	
	public byte[] decrypt(byte[] data) throws Exception {
		//-----------------------------------------
		// @PostConstruct 미사용 방식
		//-----------------------------------------
		if (gpkiAPI == null) {
			setup();
		}
		////---------------------------------------

		//----------------------------------
		// 설정 정보 (암호화용 인증서 정보 필요)
		//----------------------------------
		String certForEnvFile = gpkiPath + "/SVR" + "4260086002" + "_env.cer";
		String keyForEnvFile = gpkiPath + "/SVR" + "4260086002" + "_env.key";
		String pinForEnv = "ghldtjd!2";

		//----------------------------------
		// 복호화 처리
		//----------------------------------
		byte[] plainData = null;

		try {
			gpkiAPI.API_Init(LIC_PATH);
			int returnCode = 0;

			byte[] baPriKey = null;
			byte[] certificate = null;

			returnCode = gpkiAPI.STORAGE_ReadPriKey(gpkiapi_jni.MEDIA_TYPE_FILE_PATH, keyForEnvFile, pinForEnv, gpkiapi_jni.DATA_TYPE_OTHER);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			baPriKey = gpkiAPI.baReturnArray;

			returnCode = gpkiAPI.STORAGE_ReadCert(gpkiapi_jni.MEDIA_TYPE_FILE_PATH, certForEnvFile, gpkiapi_jni.DATA_TYPE_OTHER);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			certificate = gpkiAPI.baReturnArray;

			returnCode = gpkiAPI.CMS_ProcessEnvelopedData(certificate, baPriKey, data);
			if (returnCode != 0) {
				throw new IllegalAccessException((new StringBuffer(String.valueOf(returnCode))).toString() + " : " + gpkiAPI.sDetailErrorString);
			}
			plainData = gpkiAPI.baReturnArray;

		} finally {
			if (gpkiAPI != null) {
				gpkiAPI.API_Finish();
			}
		}

		return plainData;
	}


	/**
	 * BASE64 encoding 처리.
	 *
	 * @see GPKIService.com.sec.pki.service.EgovGPKIService#getBASE64String(byte[])
	 */
	
	public String getBASE64String(byte[] data) throws Exception {
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * BASE64 decoding 처리.
	 *
	 * @see GPKIService.com.sec.pki.service.EgovGPKIService#getDataFromBASE64(java.lang.String)
	 */
	
	public byte[] getDataFromBASE64(String base64) throws Exception {
		return Base64.decodeBase64(base64.getBytes());
	}
}
