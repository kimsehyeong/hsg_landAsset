package hsgLandAsset.admin.message.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hsgLandAsset.admin.vo.MessageVO;
import hsgLandAsset.admin.vo.PageVO;

@Service
public class AdminMessageService {

	@Autowired
	AdminMessageMapper mapper;
	
	@Transactional
	public int insertSendMessageLog(Map<String, Object> params) throws Exception {
		return mapper.insertSendMessageLog(params);
		
	}

	public List<MessageVO> selectSendMessageList(MessageVO vo) throws Exception {
		vo.setPageVO(new PageVO(vo.getListSize(), vo.getPage(), mapper.selectSendMessageListCount(vo)));
		return mapper.selectSendMessageList(vo);
	}


}
