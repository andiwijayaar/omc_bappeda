package com.omcbappeda.sumsel.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.omcbappeda.sumsel.constant.Constant;
import com.omcbappeda.sumsel.dao.FileDAO;
import com.omcbappeda.sumsel.model.File;
import com.omcbappeda.sumsel.utilities.AbstractHibernate4Dao;

public class FileDAOImpl extends AbstractHibernate4Dao<File> implements FileDAO {

	@Override
	public List<File> getFileByDir(String id) {
		Query q = getSession().getNamedQuery("SQL_GET_FILE_BY_DIRECTORY");
		q.setParameter(0, id);
		q.setParameter(1, Constant.YES.toString());
		
		return (List<File>) q.list();
	}

	@Override
	public List<File> getFileByName(String name) {
		Query q = getSession().getNamedQuery("SQL_GET_FILE_BY_NAME");
		q.setParameter(0, '%' + name+ '%');
		q.setParameter(1, '%' + name+ '%');
		q.setParameter(2, Constant.YES.toString());
		return (List<File>) q.list();
	}
}
