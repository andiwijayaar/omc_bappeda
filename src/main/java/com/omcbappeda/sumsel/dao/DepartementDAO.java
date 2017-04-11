package com.omcbappeda.sumsel.dao;

import java.util.List;

import com.omcbappeda.sumsel.model.Departement;
import com.omcbappeda.sumsel.utilities.Dao;

public interface DepartementDAO extends Dao<Departement> {

	List<Departement> getDeptByPaging(int limit, int offset);
	List<Departement> getDeptByName(String key, int limit, int offset);
	int countDept();
	int countDeptByName(String key);
}
