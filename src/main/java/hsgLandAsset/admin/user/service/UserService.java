package hsgLandAsset.admin.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hsgLandAsset.admin.vo.PageVO;
import hsgLandAsset.admin.vo.UserVO;
import hsgLandAsset.util.BaseUtil;
import hsgLandAsset.util.SHAUtil;
import hsgLandAsset.util.SessionUtil;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	
	@Transactional
	public Map<String, Object> insertUser(UserVO vo) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!vo.getPwd().equals("")) {
			String password = BaseUtil.getString(vo.getPwd()); 
			String hashedPassword = SHAUtil.getSHA512(password);
			vo.setPwd(hashedPassword);
			System.out.println(vo);
		}
		

		
		if (vo.getMode().equals("U")) {
			vo.setUpdateId(SessionUtil.getId());
			int result = userMapper.updateUser(vo);
			if (result > 0) {
				resultMap.put("result", true);
				resultMap.put("msg", "수정되었습니다");
			}else {
				resultMap.put("result", false);
				resultMap.put("msg", "오류가 발생했습니다.");
			}
		}else {
			int checkId = userMapper.checkId(vo);
			vo.setInsertId(SessionUtil.getId());
			int result = userMapper.insertUser(vo);
			
			if (checkId > 0) {
				resultMap.put("result", false);
				resultMap.put("msg", "아이디가 이미 존재합니다.");
				return resultMap;
			}
			
			if (result > 0) {
				resultMap.put("result", true);
				resultMap.put("msg", "저장되었습니다");
			}else {
				resultMap.put("result", false);
				resultMap.put("msg", "오류가 발생했습니다.");
			}
		}
		
		
		
		return resultMap;
	}

	public List<UserVO> selectList(UserVO vo) throws Exception {
		vo.setPageVO(new PageVO(vo.getListSize(), vo.getPage(), userMapper.getUserListCount(vo)));
		return userMapper.selectList(vo);
	}
	public UserVO selectOneUser(UserVO vo) throws Exception{
		UserVO userVO = userMapper.selectOneUser(vo);
		return userVO;
	}
	
	@Transactional
	public Map<String, Object> deleteUser(UserVO vo) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		vo.setDeleteId(SessionUtil.getId());
		System.out.println(vo);
		int result = userMapper.deleteUser(vo);
		if (result > 0) {
			resultMap.put("result", true);
			resultMap.put("msg", "삭제되었습니다");
		}else {
			resultMap.put("result", false);
			resultMap.put("msg", "오류가 발생했습니다.");
		}
		return resultMap;
	}

		public UserVO getUserById(String id) throws Exception {
			return userMapper.getUserById(id);
	}
	
}
