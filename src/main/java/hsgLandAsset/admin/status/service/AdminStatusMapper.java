package hsgLandAsset.admin.status.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hsgLandAsset.admin.vo.StatusVO;

@Mapper
public interface AdminStatusMapper {

	List<StatusVO> selectStatsList(StatusVO vo) throws Exception;

	int getStatusListCount(StatusVO vo)  throws Exception;

}
