package com.omcbappeda.sumsel.service;

import java.util.List;

import com.omcbappeda.sumsel.model.UserVO;

public interface UserService {

	void save(UserVO userVO);
	void update(UserVO userVO);
	void delete(String id);
	UserVO get(String id);
	//List<UserVO> getByName(String name);
	List<UserVO> getAllUser();
	UserVO getByUserIdAndPassword(String userId, String password);
	boolean isEmailExist(String email);
	boolean isUserIdExist(String userId);
	List<UserVO> getUserByPaging(String isActive, String approval, int limit, int offset);
	List<UserVO> getUserByName(String isActive, String approval, String key, int limit, int offset);
	int countUser(String isActive, String approval);
	int countUserByName(String isActive, String approval, String key);
}
