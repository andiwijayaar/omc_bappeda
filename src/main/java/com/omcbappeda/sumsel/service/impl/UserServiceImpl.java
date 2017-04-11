package com.omcbappeda.sumsel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.dao.UserDAO;
import com.omcbappeda.sumsel.model.Departement;
import com.omcbappeda.sumsel.model.DepartementVO;
import com.omcbappeda.sumsel.model.User;
import com.omcbappeda.sumsel.model.UserVO;
import com.omcbappeda.sumsel.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void save(UserVO userVO) {
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		
		Departement departement = new Departement();
		
		BeanUtils.copyProperties(userVO.getDepartementVO(), departement);
		user.setDepartement(departement);
		
		this.userDAO.save(user);
	}
	
	@Override
	public UserVO get(String id) {
		User user = userDAO.get(id);
		return setUserVOByUserModel(user);
	}

	@Override
	public void update(UserVO userVO) {
		User user = userDAO.get(userVO.getId());
		BeanUtils.copyProperties(userVO, user);
		
		Departement departement = new Departement();
		
		BeanUtils.copyProperties(userVO.getDepartementVO(), departement);
		user.setDepartement(departement);
		this.userDAO.update(user);
	}

	@Override
	public void delete(String id) {
		User user = userDAO.get(id);
		this.userDAO.delete(user);
	}

	@Override
	public UserVO getByUserIdAndPassword(String userId, String password) {
		User user = userDAO.getUserByUserIdAndPassword(userId, password);
		return setUserVOByUserModel(user);
	}
	
	private UserVO setUserVOByUserModel(User user) {
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(user, userVO);
		
//		if (!Constant.YES.toString().equals(userVO.getIsAdmin())) {
			DepartementVO departementVO = new DepartementVO();
			BeanUtils.copyProperties(user.getDepartement(), departementVO);
			userVO.setDepartementVO(departementVO);
//		}
		
		return userVO;
	}
	
	private List<UserVO> setUserVOListByUserModel(List<User> users){
		List<UserVO> userVOs = new ArrayList<UserVO>();
		for (User user : users){
			userVOs.add(setUserVOByUserModel(user));
		}
		return userVOs;
	}

	@Override
	public List<UserVO> getAllUser() {
		List<User> userList = userDAO.getAll();
		List<UserVO> userVOList = new ArrayList<UserVO>();
		
		for (User user : userList) {
			UserVO userVO = setUserVOByUserModel(user);
			userVOList.add(userVO);
		}

		return userVOList;
	}

	/** 
	 * Checking email exist or not
	 * @param: String email
	 * @return: {@link Boolean}
	 */
	@Override
	public boolean isEmailExist(String email) {
		boolean isExist = false;
		
		User user = userDAO.getUserByEmail(email);
		if (user != null) {
			isExist = true;
		}
		
		return isExist;
	}
	
	/** 
	 * Checking email exist or not
	 * @param: String userId
	 * @return: {@link Boolean}
	 */
	@Override
	public boolean isUserIdExist(String userId) {
		boolean isExist = false;
		
		User user = userDAO.getUserByUserId(userId);
		if (user != null) {
			isExist = true;
		}
		
		return isExist;
	}

	/*@Override
	public List<UserVO> getByName(String name) {
		List<User> userList = userDAO.getUserByUsername(name);
		return setUserVOListByUserModel(userList);
	}*/

	@Override
	public List<UserVO> getUserByPaging(String isActive, String approval, int limit, int offset) {
		List<User> userList = userDAO.getUserByPaging(isActive, approval, limit, offset);		
		return setUserVOListByUserModel(userList);
	}

	@Override
	public List<UserVO> getUserByName(String isActive, String approval, String key, int limit, int offset) {
		return setUserVOListByUserModel(userDAO.getUserByUsername(isActive, approval, key, limit, offset));
	}
	
	private User setUserByUserVO(UserVO userVO) {
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		
		Departement departement = new Departement();
		BeanUtils.copyProperties(departement, userVO.getDepartementVO());
		
		user.setDepartement(departement);
		
		return user;
	}
	
	@Override
	public int countUser(String isActive, String approval){
		return userDAO.countUser(isActive, approval);
	}
	
	@Override
	public int countUserByName(String isActive, String approval, String key){
		return userDAO.countUserByName(isActive, approval, key);
	}
}