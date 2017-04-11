package com.omcbappeda.sumsel.service;

import java.util.List;

import com.omcbappeda.sumsel.model.ActivityLogVO;

public interface ActivityLogService {
	void save(ActivityLogVO activityLogVO);
	ActivityLogVO get(String id);
	List<ActivityLogVO> getAll(String isAdmin, int limit, int offset);
	List<ActivityLogVO> getAllByUser(String id, String fromDate, String toDate, int limit, int offset);
	List<ActivityLogVO> getActLogByDate(String fromDate, String toDate, String id, int limit, int offset);
	int countActLog(String isAdmin);
	int countActLogByDate(String fromDate, String toDate, String id);
	int countActLogByUser(String id, String fromDate, String toDate);
	
}
