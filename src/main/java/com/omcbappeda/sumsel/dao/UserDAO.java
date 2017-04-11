package com.omcbappeda.sumsel.dao;

import java.util.List;

import com.omcbappeda.sumsel.model.User;
import com.omcbappeda.sumsel.utilities.Dao;

public interface UserDAO extends Dao<User>{
	User getUserByUserIdAndPassword(String userId, String password);
	User getUserByUserId(String userId);
	User getUserByEmail(String email);
	//List<User> getUserByUsername(String name);
	List<User> getUserByPaging(String isActive, String approval, int limit, int offset);
	List<User> getUserByUsername(String isActive, String approval, String key, int limit, int offset);
	int countUser(String isActive, String approval);
	int countUserByName(String isActive, String approval, String key);
}
