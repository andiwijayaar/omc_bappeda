package com.omcbappeda.sumsel.service;

import java.util.List;
import java.util.Map;

import com.omcbappeda.sumsel.form.LoginForm;
import com.omcbappeda.sumsel.model.ActivityLogVO;
import com.omcbappeda.sumsel.model.DepartementVO;
import com.omcbappeda.sumsel.model.DirectoryVO;
import com.omcbappeda.sumsel.model.FileVO;
import com.omcbappeda.sumsel.model.UserVO;

public interface GlobalService extends BaseService {
	void saveNewUser(LoginForm form) throws Exception;
	UserVO getUserByUserIdAndPassword(String userId, String password);
	List<UserVO> getAllUser();
	//List<UserVO> getByUsername(String name);
	List<UserVO> getUserByPaging(String isActive, String approval, int limit, int offset);
	void updateUser(UserVO userVO);
	String updateStatusUser(String adminId, String userId, String status);
	UserVO getUserById(String id);
	List<UserVO> getUserByUsername(String isActive, String approval, String key, int limit, int offset);
	void deleteUser(String id);
	int countUser(String isActive, String approval);
	int countUserByName(String isActive, String approval, String key);
	boolean isUserIdExist(String userId);
	boolean isEmailExist(String email);
	
	DepartementVO getDeptByCode(String code);
	void saveNewDepartement(DepartementVO departementVO);
	List<DepartementVO> getDeptByPaging(int limit, int offset);
	List<DepartementVO> getAllDept();
	List<DepartementVO> getDeptByName(String key, int limit, int offset);
	void updateDepartement(DepartementVO departementVO);
	int countDept();
	int countDeptByName(String key);

	void executeApprove(String adminId, String ipAddress, String userAgent, String approval, String userId, String userName);
	Map<String, Object> getDataMap();
	

	void saveNewDirectory(DirectoryVO directoryVO);
	List<DirectoryVO> getDirectoryByParent(String id);
	List<FileVO> getFileByDir(String id);
	void updateDirectory(DirectoryVO directoryVO);
	DirectoryVO getDirById(String id);
	FileVO getFileById(String id);
	void updateFile(FileVO fileVO);
	void saveNewFile(FileVO fileVO);
	List<DirectoryVO> getDirectoryByName(String key);
	List<FileVO> getFileByName(String key);	

	void executeActLog(String userId, String ipAddress, String userAgent, String activity);
	ActivityLogVO get(String id);
	List<ActivityLogVO> getAllActLog(String isAdmin, int limit, int offset);
	List<ActivityLogVO> getAllActLogByUser(String id, String fromDate, String toDate,int limit, int offset);
	List<ActivityLogVO> getActLogByDate(String fromDate, String toDate, String id, int limit, int offset);
	int countActLog(String isAdmin);
	int countActLogByDate(String fromDate, String toDate, String isAdmin);
	int countActLogByUser(String id,String fromDate, String toDate);
}
