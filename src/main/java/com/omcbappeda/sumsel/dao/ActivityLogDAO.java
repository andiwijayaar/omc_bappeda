package com.omcbappeda.sumsel.dao;

import java.util.List;

import com.omcbappeda.sumsel.model.ActivityLog;
import com.omcbappeda.sumsel.utilities.Dao;

public interface ActivityLogDAO extends Dao<ActivityLog> {
	List<ActivityLog> getAllActivityLog(String isAdmin, int limit, int offset);
	List<ActivityLog> getAllActivityLogByUser(String id, String fromDate, String toDate, int limit, int offset);
	List<ActivityLog> getActivityLogByDate(String fromDate, String toDate, String id, int limit, int offset);
	int countActivityLog(String isAdmin);
	int countActivityLogByDate(String fromDate, String toDate, String id);
	int countActivityLogByUser(String id, String fromDate, String toDate);
}
