package com.omcbappeda.sumsel.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.dao.DepartementDAO;
import com.omcbappeda.sumsel.model.Departement;
import com.omcbappeda.sumsel.utilities.AbstractHibernate4Dao;

public class DepartementDAOImpl extends AbstractHibernate4Dao<Departement> implements DepartementDAO {
	@Override
	public List<Departement> getDeptByPaging(int limit, int offset) {
		Query q = getSession().getNamedQuery("SQL_GET_ALL_DEPARTEMENT_PAGING");
		q.setParameter(0, Constant.YES.toString());
		q.setParameter(1, limit);
		q.setParameter(2, offset);
		
		return (List<Departement>) q.list();
	}

	@Override
	public List<Departement> getDeptByName(String key, int limit, int offset) {
		Query q = getSession().getNamedQuery("SQL_GET_DEPARTEMENT_BY_NAME_PAGING");
		q.setParameter(0, '%' + key+ '%');
		q.setParameter(1, '%' + key+ '%');
		q.setParameter(2, Constant.YES.toString());
		q.setParameter(3, limit);
		q.setParameter(4, offset);
		
		return (List<Departement>) q.list();
	}

	@Override
	public int countDept() {
		Query q = getSession().getNamedQuery("SQL_COUNT_DEPARTEMENT");
		q.setParameter(0, Constant.YES.toString());
		return ((BigInteger) q.uniqueResult()).intValue();
	}
	
	@Override
	public int countDeptByName(String key) {
		Query q = getSession().getNamedQuery("SQL_COUNT_DEPARTEMENT_BY_NAME");
		q.setParameter(0, Constant.YES.toString());
		q.setParameter(1, '%' + key+ '%');
		q.setParameter(2, '%' + key+ '%');
		return ((BigInteger) q.uniqueResult()).intValue();
	}
}
