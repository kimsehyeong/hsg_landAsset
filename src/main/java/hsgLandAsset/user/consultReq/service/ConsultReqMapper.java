package hsgLandAsset.user.consultReq.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hsgLandAsset.admin.vo.ConsultVO;

@Mapper
public interface ConsultReqMapper {

	int insertConsultReq(ConsultVO consultVO) throws Exception;

	List<ConsultVO> selectListConsultReq(String registerNo) throws Exception;

	boolean isConsultExists(ConsultVO consultVO) throws Exception;

	int updateConsultReq(ConsultVO consultVO) throws Exception;

	int deleteConsultReq(ConsultVO consultVO) throws Exception ;

	ConsultVO consultSelectOne(ConsultVO consultVO) throws Exception;

	int updateComments(ConsultVO consultVO) throws Exception;

	int updateConsultInfo(ConsultVO consultVO) throws Exception;

	int getConsultCount(ConsultVO vo) throws Exception;

	List<ConsultVO> selectListConsultAdmin(ConsultVO vo) throws Exception;

}
