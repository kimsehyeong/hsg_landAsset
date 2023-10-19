package hsgLandAsset.admin.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("statusVO")
public class StatusVO extends CommonVO {

	String registerNo;
	String takeYmd;
	String mwAfrShtnm;
	String mwAplctNm;
	String mwAplctHpno;
	String dppUsrNm;
	String dealPlanYmd;
	
	
	
	
	
	
	
	
	int progressCnt;
	int compromiseCnt;
	
	
	String startDate;
	String endDate;

}
