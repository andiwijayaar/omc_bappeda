package com.omcbappeda.sumsel.dao;

import java.util.List;

import com.omcbappeda.sumsel.model.Directory;
import com.omcbappeda.sumsel.model.DirectoryVO;
import com.omcbappeda.sumsel.utilities.Dao;

public interface DirectoryDAO extends Dao<Directory> {

	List<Directory> getDirectoryByParent(String id);
	List<Directory> getDirectoryByName(String name);
	

}
