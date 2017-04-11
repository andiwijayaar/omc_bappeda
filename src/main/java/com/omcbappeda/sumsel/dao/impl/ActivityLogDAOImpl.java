package com.omcbappeda.sumsel.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;

import com.omcbappeda.sumsel.dao.ActivityLogDAO;
import com.omcbappeda.sumsel.model.ActivityLog;
import com.omcbappeda.sumsel.utilities.AbstractHibernate4Dao;

public class ActivityLogDAOImpl extends AbstractHibernate4Dao<ActivityLog> implements ActivityLogDAO {

	@Override
	public List<ActivityLog> getAllActivityLog(String isAdmin, int limit, int offset) {
		Query q = getSession().getNamedQuery("SQL_GET_ACTIVITY_LOG_PAGING");
		q.setParameter(0, isAdmin);
		q.setParameter(1, limit);
		q.setParameter(2, offset);
		return (List<ActivityLog>) q.list();
	}

	@Override
	public int countActivityLog(String isAdmin) {
		Query q = getSession().getNamedQuery("SQL_COUNT_ACTIVITY_LOG");
		q.setParameter(0, isAdmin);
		return ((BigInteger) q.uniqueResult()).intValue();
	}

	@Override
	public List<ActivityLog> getActivityLogByDate(String fromDate, String toDate, String id, int limit, int offset) {
		Query q = getSession().getNamedQuery("SQL_GET_ACTIVITY_LOG_BY_DATE_PAGING");
		q.setParameter(0, fromDate);
		q.setParameter(1, toDate);
		q.setParameter(2, id);
		q.setParameter(3, limit);
		q.setParameter(4, offset);
		return (List<ActivityLog>) q.list();
	}
	
	@Override
	public int countActivityLogByDate(String fromDate, String toDate, String id) {
		Query q = getSession().getNamedQuery("SQL_COUNT_ACTIVITY_LOG_BY_DATE");
		q.setParameter(0, fromDate);
		q.setParameter(1, toDate);
		q.setParameter(2, id);
		return ((BigInteger) q.uniqueResult()).intValue();
	}

	@Override
	public List<ActivityLog> getAllActivityLogByUser(String id, String fromDate, String toDate, int limit, int offset) {
		Query q = getSession().getNamedQuery("SQL_GET_USER_ACTIVITY_LOG_PAGING");
		q.setParameter(0, id);
		q.setParameter(1, fromDate);
		q.setParameter(2, toDate);
		q.setParameter(3, limit);
		q.setParameter(4, offset);
		return (List<ActivityLog>) q.list();
	}
	
	@Override
	public int countActivityLogByUser(String id, String fromDate, String toDate) {
		Query q = getSession().getNamedQuery("SQL_COUNT_ACTIVITY_LOG_USER");
		q.setParameter(0, id);
		q.setParameter(1, fromDate);
		q.setParameter(2, toDate);
		return ((BigInteger) q.uniqueResult()).intValue();
	}
	
}
