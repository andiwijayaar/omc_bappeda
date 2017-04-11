package com.omcbappeda.sumsel.dao;

import java.util.List;

import com.omcbappeda.sumsel.model.File;
import com.omcbappeda.sumsel.model.FileVO;
import com.omcbappeda.sumsel.utilities.Dao;

public interface FileDAO extends Dao<File> {

	List<File> getFileByDir(String id);
	List<File> getFileByName(String name);

}
