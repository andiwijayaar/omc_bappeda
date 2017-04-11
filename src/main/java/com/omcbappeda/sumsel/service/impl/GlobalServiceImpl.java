package com.omcbappeda.sumsel.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.controller.UploadController;
import com.omcbappeda.sumsel.form.LoginForm;
import com.omcbappeda.sumsel.model.ActivityLogVO;
import com.omcbappeda.sumsel.model.DepartementVO;
import com.omcbappeda.sumsel.model.DirectoryVO;
import com.omcbappeda.sumsel.model.FileVO;
import com.omcbappeda.sumsel.model.UserVO;
import com.omcbappeda.sumsel.service.ActivityLogService;
import com.omcbappeda.sumsel.service.DepartementService;
import com.omcbappeda.sumsel.service.DirectoryService;
import com.omcbappeda.sumsel.service.FileService;
import com.omcbappeda.sumsel.service.GlobalService;
import com.omcbappeda.sumsel.service.UserService;

public class GlobalServiceImpl implements GlobalService {

	private DepartementService departementService;
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(GlobalServiceImpl.class);
	public void setDirectoryService(DirectoryService directoryService) {
		this.directoryService = directoryService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	private ActivityLogService activityLogService;
	private DirectoryService directoryService;
	private FileService fileService;
	
	public void setDepartementService(DepartementService departementService) {
		this.departementService = departementService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}

	@Override
	public void saveNewUser(LoginForm form) throws Exception {
		if (newUserValidation(form.getUserId(), form.getEmail())) {
			throw new Exception();
		} else {
			UserVO userVO = new UserVO();
			BeanUtils.copyProperties(form, userVO);
			userVO.setId(UUID.randomUUID().toString());
			userVO.setGender(form.getJenisKelamin());
			userVO.setCreatedDate(new Date());
			userVO.setApproval(Constant.REQUEST.toString());
			userVO.setPassword(encryptPassword(userVO.getPassword()).toString());
			DepartementVO departementVO = getDeptByCode(form.getDepartement());
			userVO.setDepartementVO(departementVO);
			
			/* Still Hard Code in development */
			userVO.setIsAdmin(Constant.NO.toString());
			userVO.setIsActive(Constant.NO.toString());
			userService.save(userVO);
		}
	}

	@Override
	public List<DepartementVO> getAllDept() {
		return departementService.getAll();
	}

	@Override
	public Map<String, Object> getDataMap() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, String>> departementList = new ArrayList<Map<String,String>>();
		Map<String, String> genderMap = new HashMap<String, String>();
		
		for (DepartementVO dept : getAllDept()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(dept.getCode(),dept.getName() );
			
			departementList.add(map);
		}
		
		genderMap.put("L", "Laki - Laki");
		genderMap.put("P", "Perempuan");
		
		dataMap.put("departementList", departementList);
		dataMap.put("genderMap", genderMap);
		return dataMap;
	}

	@Override
	public void executeActLog(String userId, String ipAddress, String userAgent, String activity) {
		activityLogService.save(prepareActivityLogVO(userId, ipAddress, userAgent, activity));
		
	}
	
	@Override
	public UserVO getUserByUserIdAndPassword(String userId, String password) {
		String encryptPassword = encryptPassword(password);
		return userService.getByUserIdAndPassword(userId, encryptPassword);
	}

	@Override
	public List<UserVO> getAllUser() {
		return userService.getAllUser();		
	}
	
	/**
	 * validation for new user by email and user id
	 * @param userId, email
	 * @return {@link Boolean}
	 * 
	 */
	public boolean newUserValidation(String userId, String email) {
		return userService.isUserIdExist(userId) || userService.isEmailExist(email);
	}
	
	@Override
	public boolean isUserIdExist(String userId) {
		return userService.isUserIdExist(userId);
	}
	
	@Override
	public boolean isEmailExist(String email) {
		return userService.isEmailExist(email);
	}

	@Override
	public ActivityLogVO prepareActivityLogVO(String userId, String ipAddress, String userAgent, String activity) {
		ActivityLogVO activityLogVO = new ActivityLogVO();
		UserVO userVO = getUserById(userId);
		activityLogVO.setActivity(activity);
		activityLogVO.setCreatedBy(userVO);
		activityLogVO.setUserAgent(userAgent);
		activityLogVO.setIpAddress(ipAddress);
		
		return activityLogVO;
	}

	@Override
	public UserVO getUserById(String id) {
		return userService.get(id);
	}

	/*@Override
	public List<UserVO> getByUsername(String name) {
		return userService.getByName(name);
	}*/

	@Override
	public List<UserVO> getUserByPaging(String isActive, String approval, int limit, int offset) {
		return userService.getUserByPaging(isActive, approval, limit, offset);
	}
	
	@Override
	public List<DepartementVO> getDeptByPaging(int limit, int offset) {
		return departementService.getAll(limit, offset);
	}

	@Override
	public void updateUser(UserVO userVO) {
		userService.update(userVO);
	}

	@Override
	public void saveNewDepartement(DepartementVO departementVO) {
		departementService.save(departementVO);
	}
	
	private String encryptPassword(String password){
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("ENCRYPT", e);
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	@Override
	public String updateStatusUser(String adminId, String userId, String status) {
		UserVO newUserVO = getUserById(userId);
		
		newUserVO.setApproveBy(adminId);
		newUserVO.setApproveDate(new Date());
		
		if (status.equals("1")) {
			newUserVO.setApproval(Constant.APPROVED.toString());
			newUserVO.setIsActive(Constant.YES.toString());
		} else {
			newUserVO.setApproval(Constant.REJECT.toString());
		}
		
		userService.update(newUserVO);
		
		return newUserVO.getApproval();
	}

	@Override
	public DepartementVO getDeptByCode(String code) {
		return departementService.get(code);
	}

	@Override
	public List<DepartementVO> getDeptByName(String key, int limit, int offset) {
		return departementService.getDeptByName(key, limit, offset);
	}

	@Override
	public void executeApprove(String adminId, String ipAddress, String userAgent, String approval, String userId, String userName) {
		String activity = "";
		
		if(approval.equals(Constant.APPROVED.toString())){
			activity = "Approve user ";
		} else if(approval.equals(Constant.REJECT.toString())){
			activity = "Reject user ";			
		}
		activityLogService.save(prepareActivityLogVO(adminId, ipAddress, userAgent, activity+"ID: "+userId+" NAME: "+userName));
	}

	@Override
	public void updateDepartement(DepartementVO departementVO) {
		departementService.update(departementVO);
	}

	@Override
	public List<UserVO> getUserByUsername(String isActive, String approval, String key, int limit, int offset) {
		return userService.getUserByName(isActive, approval, key, limit, offset);
	}

	@Override
	public void saveNewDirectory(DirectoryVO directoryVO) {
		directoryService.save(directoryVO);
	}

	@Override
	public List<DirectoryVO> getDirectoryByParent(String id) {
		return directoryService.getDirectoryByParent(id);
	}

	@Override
	public List<FileVO> getFileByDir(String id) {
		return fileService.getFileByDir(id);
	}

	@Override
	public void updateDirectory(DirectoryVO directoryVO) {
		directoryService.update(directoryVO);
	}

	@Override
	public DirectoryVO getDirById(String id) {
		return directoryService.get(id);
	}

	@Override
	public void updateFile(FileVO fileVO) {
		fileService.update(fileVO);
	}

	@Override
	public void saveNewFile(FileVO fileVO) {
		fileService.save(fileVO);
		
	}

	@Override
	public List<DirectoryVO> getDirectoryByName(String key) {
		return directoryService.getDirectoryByName(key);
	}

	@Override
	public List<FileVO> getFileByName(String key) {
		return fileService.getFileByName(key);
	}
	
	@Override
	public void deleteUser(String id){
		userService.delete(id);
	}
	
	@Override
	public int countDept(){
		return departementService.countDept();
	}
	
	@Override
	public int countDeptByName(String key){
		return departementService.countDeptByName(key);
	}
	
	@Override
	public int countUser(String isActive, String approval){
		return userService.countUser(isActive, approval);
	}
	
	@Override
	public int countUserByName(String isActive, String approval, String key){
		return userService.countUserByName(isActive, approval, key);
	}
	
	@Override
	public int countActLog(String isAdmin){
		return 0;
//		return activityLogService.countActLog(isAdmin);
		
	}
	
	@Override
	public int countActLogByDate(String fromDate, String toDate, String id){
		return activityLogService.countActLogByDate(fromDate, toDate, id);
	}
	
	@Override
	public int countActLogByUser(String id, String fromDate, String toDate){
		return activityLogService.countActLogByUser(id, fromDate, toDate);
	}
	
	@Override
	public FileVO getFileById(String id) {
		return fileService.get(id);
	}

	@Override
	public ActivityLogVO get(String id) {
		return activityLogService.get(id);
	}

	@Override
	public List<ActivityLogVO> getAllActLog(String isAdmin, int limit, int offset) {
		return null;
//		return activityLogService.getAll(isAdmin, limit, offset);
	}

	@Override
	public List<ActivityLogVO> getAllActLogByUser(String id, String fromDate, String toDate, int limit, int offset) {
//		return null;
		return activityLogService.getAllByUser(id, fromDate, toDate, limit, offset);
	}

	@Override
	public List<ActivityLogVO> getActLogByDate(String fromDate, String toDate, String id, int limit, int offset) {
//		return null;
		return activityLogService.getActLogByDate(fromDate, toDate, id, limit, offset);
	}
	
}
