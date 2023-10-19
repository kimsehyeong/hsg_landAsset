package hsgLandAsset.admin.status.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hsgLandAsset.admin.vo.PageVO;
import hsgLandAsset.admin.vo.StatusVO;

@Service
public class AdminStatusService {

	@Autowired
	AdminStatusMapper mapper;

	public List<StatusVO> selectStatsList(StatusVO vo) throws Exception {
		vo.setPageVO(new PageVO(vo.getListSize(), vo.getPage(),  mapper.getStatusListCount(vo)));
		return mapper.selectStatsList(vo);
	}
	
	
	
}
