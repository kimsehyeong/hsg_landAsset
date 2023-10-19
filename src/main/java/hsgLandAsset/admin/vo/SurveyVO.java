package hsgLandAsset.admin.vo;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("surveyVO")
public class SurveyVO extends CommonVO {

	int surveyId;
	String registerNo;
	String surveyTitle;
	Timestamp insertDate;
	int age;
	String gender;
	String qsatisfy;
	String qkindness;
	String qquickness;
	String qprofessional;
	String qservice;
	String suggestion;
	String startDate;
	String endDate;
	
	
	
	
	
	String mwAfrShtnm; // 민원명
}
