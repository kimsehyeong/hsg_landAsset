package hsgLandAsset.admin.minwon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import hsgLandAsset.admin.fileUpload.service.FileServiceImpl;
import hsgLandAsset.admin.vo.FileVO;
import hsgLandAsset.admin.vo.LandLocationVO;
import hsgLandAsset.admin.vo.MinwonVO;
import hsgLandAsset.util.SessionUtil;

@Service
public class AdminMinwonService {

	//@Value("${file.upload-dir}")
	//String uploadPath;
	
	String uploadFolder = "minwon";
	
	@Autowired
	FileServiceImpl fileService;
	@Autowired
	AdminMinwonMapper mapper;
	
	@Transactional
	public Map<String, Object> MinwonfileUpload(Map<String, Object> param, MultipartHttpServletRequest req) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		MultipartFile file = req.getFile("fileName");
		Map<String, Object> map = fileService.saveFile(file, uploadFolder);
		map.put("fileComments", param.get("comments"));
		map.put("registerNo", param.get("registerNo"));
		map.put("insertId", SessionUtil.getId());
		mapper.minwonFileUpload(map);
		
		return resultMap;
	}
	
	public List<FileVO> minwonFileList(MinwonVO minwonVO) throws Exception{
		return mapper.minwonFileList(minwonVO);
	}

	@Transactional
	public Map<String, Object> minwonFiledelete(FileVO fileVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		fileVO.setDeleteId(SessionUtil.getId());
		int result = mapper.minwonFiledelete(fileVO);
		if (result > 0) {
			resultMap.put("result", true);
			resultMap.put("msg", "삭제되었습니다");
		}else {
			resultMap.put("result", false);
			resultMap.put("msg", "오류가 발생했습니다.");
		}
		
		return resultMap;
	}
	@Transactional
	public void minwonFileDownload(HttpServletResponse response, FileVO fileVO, HttpServletRequest request) throws Exception {
		fileService.downloadFile(response,fileVO,request);
	}
	
	@Transactional
	public Map<String, Object> insertLandLocation(LandLocationVO vo) throws Exception {
		   Map<String, Object> resultMap = new HashMap<String, Object>();
		    int result;

		    if (mapper.isLandIdExists(vo)) {
		        vo.setUpdateId(SessionUtil.getId());
		        result = mapper.updateLandLocation(vo);
		        resultMap.put("msg", result > 0 ? "수정되었습니다" : "수정 중 오류가 발생했습니다.");
		    } else {
		        vo.setInsertId(SessionUtil.getId());
		        result = mapper.insertLandLocation(vo);
		        resultMap.put("msg", result > 0 ? "저장되었습니다" : "저장 중 오류가 발생했습니다.");
		    }

		    resultMap.put("result", result > 0);
		    return resultMap;
	}

	public LandLocationVO selectLandLocation(MinwonVO minwonVO) throws Exception {
		return mapper.selectLandLocation(minwonVO);
	}
	
	public Map<String, Object> selectOneMinwon(Map<String, Object> param) throws Exception{
		return mapper.selectOneMinwon(param);

	}
	
	
	public int selectProgressMsgCount(Map<String, String> map) throws Exception {
		return mapper.selectProgressMsgCount(map);
	}
	
}
