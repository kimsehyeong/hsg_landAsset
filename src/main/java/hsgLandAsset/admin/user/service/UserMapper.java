package hsgLandAsset.admin.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hsgLandAsset.admin.vo.UserVO;

@Mapper
public interface UserMapper {

	int insertUser(UserVO vo) throws Exception;

	List<UserVO> selectList(UserVO vo) throws Exception;

	int getUserListCount(UserVO vo) throws Exception;

	int checkId(UserVO vo) throws Exception;
	
	UserVO selectOneUser(UserVO vo) throws Exception;

	int updateUser(UserVO vo) throws Exception;

	int deleteUser(UserVO vo) throws Exception;

	UserVO getUserById(String id) throws Exception;
}
