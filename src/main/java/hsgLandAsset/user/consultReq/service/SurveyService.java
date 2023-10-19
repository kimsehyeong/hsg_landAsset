package hsgLandAsset.user.consultReq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hsgLandAsset.admin.vo.PageVO;
import hsgLandAsset.admin.vo.SurveyVO;

@Service
public class SurveyService {

	@Autowired
	SurveyMapper surveyMapper;
	
	@Transactional
	public Map<String, Object> insertSurvey(SurveyVO surveyVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(surveyVO);
		int result = surveyMapper.insertSurvey(surveyVO);
		
		if (result > 0) {
			resultMap.put("result", true);
			resultMap.put("msg", "저장되었습니다.");
		}else {
			resultMap.put("result", false);
			resultMap.put("msg", "오류가 발생했습니다.");
		}
		
		
		return resultMap;
	}

	public SurveyVO selectSurvey(String registerNo) throws Exception {
		return surveyMapper.selectSurvey(registerNo);
	}

	public List<SurveyVO> selectListSurvey(SurveyVO vo) throws Exception {
		vo.setPageVO(new PageVO(vo.getListSize(), vo.getPage(), surveyMapper.getSatisfyListCount(vo)));
//		if(vo.isPaging()) vo.setPageVO(new PageVO(vo.getListSize(), vo.getPage(), surveyMapper.getSatisfyListCount(vo)));
		return surveyMapper.selectListSurvey(vo);
	}

	public Map<String, Object> getSurveyStats(SurveyVO surveyVO) throws Exception {
		return surveyMapper.getSurveyStats(surveyVO);
	}

}
