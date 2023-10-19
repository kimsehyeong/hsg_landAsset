package hsgLandAsset.admin.vo;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("messageVO")
public class MessageVO extends CommonVO {
	
	
	private int idx;
	
	private String registerNo;
	
	private String receiver;
	
	private String sender;
	
	private String template;
	
	private String progress;
	
	private int refer;
	
	private String kind;
	
	private String resultCode;
	
	private String sendResult;
	
	private String reqId;
	
	private String content;
	
	private String response;
	
	private Timestamp insertDate;
	
	private String error;

	private String mwAfrShtnm;
	
	private String success;
}
