package com.omcbappeda.sumsel.service;

import java.util.List;

import com.omcbappeda.sumsel.model.FileVO;

public interface FileService {

	void save(FileVO fileVO);
	void update(FileVO fileVO);
	List<FileVO> getAll();
	FileVO get(String id);
	List<FileVO> getFileByDir(String id);
	List<FileVO> getFileByName(String key);
}
