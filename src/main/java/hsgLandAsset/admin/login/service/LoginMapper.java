package hsgLandAsset.admin.login.service;

import org.apache.ibatis.annotations.Mapper;

import hsgLandAsset.admin.vo.UserVO;

@Mapper
public interface LoginMapper {
	
	UserVO loginSelect(UserVO vo) throws Exception;

}
