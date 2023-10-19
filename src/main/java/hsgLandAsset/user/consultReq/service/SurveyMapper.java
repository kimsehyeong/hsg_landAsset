package hsgLandAsset.user.consultReq.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import hsgLandAsset.admin.vo.SurveyVO;

@Mapper
public interface SurveyMapper {

	int insertSurvey(SurveyVO surveyVO) throws Exception;

	SurveyVO selectSurvey(String registerNo) throws Exception;

	List<SurveyVO> selectListSurvey(SurveyVO surveyVO) throws Exception;

	Map<String, Object> getSurveyStats(SurveyVO surveyVO) throws Exception;

	int getSatisfyListCount(SurveyVO vo) throws Exception ;

}
