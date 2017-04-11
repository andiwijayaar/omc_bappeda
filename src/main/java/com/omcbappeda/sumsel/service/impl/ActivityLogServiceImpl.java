package com.omcbappeda.sumsel.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.omcbappeda.sumsel.controller.UploadController;
import com.omcbappeda.sumsel.dao.ActivityLogDAO;
import com.omcbappeda.sumsel.model.ActivityLog;
import com.omcbappeda.sumsel.model.ActivityLogVO;
import com.omcbappeda.sumsel.model.User;
import com.omcbappeda.sumsel.model.UserVO;
import com.omcbappeda.sumsel.service.ActivityLogService;
import com.omcbappeda.sumsel.service.GlobalService;
import com.omcbappeda.sumsel.service.UserService;

public class ActivityLogServiceImpl implements ActivityLogService {

	private ActivityLogDAO activityLogDAO;
	private static final Logger logger = LoggerFactory.getLogger(ActivityLogServiceImpl.class);

	public void setActivityLogDAO(ActivityLogDAO activityLogDAO) {
		this.activityLogDAO = activityLogDAO;
	}

	@Override
	public void save(ActivityLogVO activityLogVO) {
		try {
			ActivityLog activityLog = new ActivityLog();
			BeanUtils.copyProperties(activityLogVO, activityLog);
			
			User user = new User();
			BeanUtils.copyProperties(activityLogVO.getCreatedBy(), user);
			
			activityLog.setId(UUID.randomUUID().toString());
			activityLog.setCreatedDate(new Date());
			activityLog.setIpAddress(activityLogVO.getIpAddress() == null ? InetAddress.getLocalHost().getHostAddress() : activityLogVO.getIpAddress().toString());
			activityLog.setCreatedBy(user);
			
			
			activityLogDAO.save(activityLog);
		} catch (UnknownHostException e) {
			logger.error("SAVE",e);
			e.printStackTrace();
		}
	}

	@Override
	public List<ActivityLogVO> getAll(String isAdmin, int limit, int offset) {
		List<ActivityLogVO> activityLogVOs = new ArrayList<ActivityLogVO>();
		List<ActivityLog> activityLogs = activityLogDAO.getAllActivityLog(isAdmin, limit, offset);
		
		for(int i = 0; i < activityLogs.size(); i++){
			ActivityLogVO activityLogVO = new ActivityLogVO();
			BeanUtils.copyProperties(activityLogs.get(i), activityLogVO);
			activityLogVOs.add(activityLogVO);
		}
		
		return activityLogVOs;
	}

	@Override
	public ActivityLogVO get(String id) {
		return setActLogVoFromActLog(activityLogDAO.get(id));
	}

	private ActivityLogVO setActLogVoFromActLog(ActivityLog activityLog){
		ActivityLogVO activityLogVO = new ActivityLogVO();
		BeanUtils.copyProperties(activityLog, activityLogVO);
		return activityLogVO;
	}

	@Override
	public List<ActivityLogVO> getActLogByDate(String fromDate, String toDate, String id, int limit, int offset) {
		List<ActivityLogVO> activityLogVOs = new ArrayList<ActivityLogVO>();
		List<ActivityLog> activityLogs = activityLogDAO.getActivityLogByDate(fromDate, toDate, id, limit, offset);
		
		for(int i = 0; i < activityLogs.size(); i++){
			ActivityLogVO activityLogVO = new ActivityLogVO();
			BeanUtils.copyProperties(activityLogs.get(i), activityLogVO);
			
			UserVO userVO = new UserVO();
			BeanUtils.copyProperties(activityLogs.get(i).getCreatedBy(), userVO);
			activityLogVO.setCreatedBy(userVO);
			activityLogVOs.add(activityLogVO);
		}
		
		return activityLogVOs;
	}

	@Override
	public List<ActivityLogVO> getAllByUser(String id, String fromDate, String toDate, int limit, int offset) {
		List<ActivityLogVO> activityLogVOs = new ArrayList<ActivityLogVO>();
		List<ActivityLog> activityLogs = activityLogDAO.getAllActivityLogByUser(id, fromDate, toDate, limit, offset);
		
		for(int i = 0; i < activityLogs.size(); i++){
			ActivityLogVO activityLogVO = new ActivityLogVO();
			BeanUtils.copyProperties(activityLogs.get(i), activityLogVO);
			activityLogVOs.add(activityLogVO);
		}
		
		return activityLogVOs;
	}
	
	@Override
	public int countActLog(String isAdmin){
		return activityLogDAO.countActivityLog(isAdmin);
	}
	
	@Override
	public int countActLogByDate(String fromDate, String toDate, String id){
		return activityLogDAO.countActivityLogByDate(fromDate, toDate, id);
	}
	
	@Override
	public int countActLogByUser(String id, String fromDate, String toDate){
		return activityLogDAO.countActivityLogByUser(id, fromDate, toDate);
	}

}
