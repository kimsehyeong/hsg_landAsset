package hsgLandAsset.admin.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
public class MinwonVO {

	String startDate;
	String endDate;
	
	String registerNo;
	String in_mw_take_no; //접수번호
	String mw_afr_shtnm; //민원명
	String in_mw_aplr_nm; //민원인
	String mw_aplct_hpno; //민원인 휴대폰
	String user_name; //담당자
	String deal_se; //처리사항 코드
	String deal_code_cn; //처리사항
	
	String progress;
	String progress2;
	String answer;
	

	
	int page = 1;
	int listSize = 20;
	PageVO pageVO;
	
	boolean temp = false;
	
}
