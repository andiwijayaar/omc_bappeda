package com.omcbappeda.sumsel.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.dao.UserDAO;
import com.omcbappeda.sumsel.model.User;
import com.omcbappeda.sumsel.utilities.AbstractHibernate4Dao;

public class UserDAOImpl extends AbstractHibernate4Dao<User> implements UserDAO  {

	@Override
	public User getUserByUserIdAndPassword(String userId, String password) {
		Criteria criterions = getSession().createCriteria(User.class);
		criterions.add(Restrictions.eq("userId", userId).ignoreCase());
		criterions.add(Restrictions.eq("password",password).ignoreCase());
		criterions.add(Restrictions.eq("isActive",Constant.YES.toString()).ignoreCase());
		
		return (User) criterions.uniqueResult();
	}

	@Override
	public User getUserByUserId(String userId) {
		Criteria criterions = getSession().createCriteria(User.class);
		criterions.add(Restrictions.eq("userId", userId).ignoreCase());
		return (User) criterions.uniqueResult();
	}

	@Override
	public User getUserByEmail(String email) {
		Criteria criterions = getSession().createCriteria(User.class);
		criterions.add(Restrictions.eq("email", email).ignoreCase());
		return (User) criterions.uniqueResult();
	}

	/*@Override
	public List<User> getUserByUsername(String name) {
		Criteria criterions = getSession().createCriteria(User.class);
		criterions.add(Restrictions.eq("name", name).ignoreCase());
		return (List<User>) criterions.list();
	}*/
	
	@Override
	public List<User> getUserByUsername(String isActive, String approval, String key, int limit, int offset) {
		Query q = getSession().getNamedQuery("SQL_GET_USER_BY_NAME_PAGING");
		q.setParameter(0, approval);
		q.setParameter(1, isActive);
		q.setParameter(2, '%' + key+ '%');
		q.setParameter(3, limit);
		q.setParameter(4, offset);
		
		return (List<User>) q.list();
	}

	@Override
	public List<User> getUserByPaging(String isActive, String approval, int limit, int offset) {
		Query q = getSession().getNamedQuery("SQL_GET_ALL_USER_PAGING");
		q.setParameter(0, approval);
		q.setParameter(1, isActive);
		q.setParameter(2, limit);
		q.setParameter(3, offset);
		
		return (List<User>) q.list();
	}
	
	@Override
	public int countUser(String isActive, String approval) {
		Query q = getSession().getNamedQuery("SQL_COUNT_USERS");
		q.setParameter(0, approval);
		q.setParameter(1, isActive);
		return ((BigInteger) q.uniqueResult()).intValue();
	}
	
	@Override
	public int countUserByName(String isActive, String approval, String key) {
		Query q = getSession().getNamedQuery("SQL_COUNT_USER_BY_NAME");
		q.setParameter(0, isActive);
		q.setParameter(1, approval);
		q.setParameter(2, '%' + key+ '%');
		
		System.out.println(q.getQueryString());
		return ((BigInteger) q.uniqueResult()).intValue();
	}

}
