package com.omcbappeda.sumsel.service;

import java.util.List;

import com.omcbappeda.sumsel.model.DirectoryVO;

public interface DirectoryService {

	void save(DirectoryVO directoryVO);
	void update(DirectoryVO directoryVO);
	void delete(DirectoryVO directoryVO);
	List<DirectoryVO> getAll();
	DirectoryVO get(String id);
	List<DirectoryVO> getDirectoryByParent(String id);
	List<DirectoryVO> getDirectoryByName(String key);
}
