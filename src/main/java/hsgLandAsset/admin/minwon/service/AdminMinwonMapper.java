package hsgLandAsset.admin.minwon.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import hsgLandAsset.admin.vo.FileVO;
import hsgLandAsset.admin.vo.LandLocationVO;
import hsgLandAsset.admin.vo.MinwonVO;

@Mapper
public interface AdminMinwonMapper {

	int minwonFileUpload(Map<String, Object> map) throws Exception;
	
	List<FileVO> minwonFileList(MinwonVO minwonVO) throws Exception;

	int minwonFiledelete(FileVO fileVO) throws Exception;

	int insertLandLocation(LandLocationVO vo) throws Exception;

	LandLocationVO selectLandLocation(MinwonVO minwonVO) throws Exception;

	boolean isLandIdExists(LandLocationVO vo) throws Exception;

	int updateLandLocation(LandLocationVO vo) throws Exception;
	
	int insertMinwon(Map<String, String> map) throws Exception;

	Map<String, Object> getCommentStatus(Map<String, String> map) throws Exception;

	Map<String, Object> selectOneMinwon(Map<String, Object> param) throws Exception;

	int selectProgressMsgCount(Map<String, String> map) throws Exception;
	
	List<Map<String, Object>> getRegisterNoBySearch(Map<String, String> map) throws Exception;
	
	
}
