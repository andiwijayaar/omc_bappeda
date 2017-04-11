package com.omcbappeda.sumsel.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.dao.DirectoryDAO;
import com.omcbappeda.sumsel.model.Directory;
import com.omcbappeda.sumsel.utilities.AbstractHibernate4Dao;

public class DirectoryDAOImpl extends AbstractHibernate4Dao<Directory> implements DirectoryDAO {

	@Override
	public List<Directory> getDirectoryByParent(String id) {
		Query q = getSession().getNamedQuery("SQL_GET_DIRECTORY_BY_PARENT");
		q.setParameter(0, id);
		q.setParameter(1, Constant.YES.toString());
		return (List<Directory>) q.list();
	}

	@Override
	public List<Directory> getDirectoryByName(String name) {
		Query q = getSession().getNamedQuery("SQL_GET_DIRECTORY_BY_NAME");
		q.setParameter(0, '%' + name+ '%');
		q.setParameter(1, Constant.YES.toString());
		return (List<Directory>) q.list();
	}

}
