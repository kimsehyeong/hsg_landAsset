package hsgLandAsset.admin.message.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import hsgLandAsset.admin.vo.MessageVO;

@Mapper
public interface AdminMessageMapper {

	int insertSendMessageLog(Map<String, Object> params) throws Exception;

	int selectSendMessageListCount(MessageVO vo) throws Exception;

	List<MessageVO> selectSendMessageList(MessageVO vo) throws Exception;


}
