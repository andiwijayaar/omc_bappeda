package com.omcbappeda.sumsel.service;

import java.util.List;

import com.omcbappeda.sumsel.model.DepartementVO;

public interface DepartementService {
	void save(DepartementVO departementVO);
	void delete(DepartementVO departementVO);
	DepartementVO get(String code);
	List<DepartementVO> getAll(int limit, int offset);
	List<DepartementVO> getAll();
	List<DepartementVO> getDeptByName(String key, int limit, int offset);
	void update(DepartementVO departementVO);
	int countDept();
	int countDeptByName(String key);
}
