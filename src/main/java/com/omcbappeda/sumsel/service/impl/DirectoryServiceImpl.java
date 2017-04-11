package com.omcbappeda.sumsel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omcbappeda.sumsel.dao.DirectoryDAO;
import com.omcbappeda.sumsel.model.Directory;
import com.omcbappeda.sumsel.model.DirectoryVO;
import com.omcbappeda.sumsel.model.User;
import com.omcbappeda.sumsel.model.UserVO;
import com.omcbappeda.sumsel.service.DirectoryService;

public class DirectoryServiceImpl implements DirectoryService {

	private DirectoryDAO directoryDAO;
	
	public void setDirectoryDAO(DirectoryDAO directoryDAO) {
		this.directoryDAO = directoryDAO;
	}
	
	@Override
	public void save(DirectoryVO directoryVO) {
		this.directoryDAO.save(setDirFromDirVO(directoryVO));
	}

	@Override
	public DirectoryVO get(String id) {
		return setDirVOFromDir(this.directoryDAO.get(id));
	}

	@Override
	public void update(DirectoryVO directoryVO) {
		this.directoryDAO.update(setDirFromDirVO(directoryVO));
	}

	@Override
	public void delete(DirectoryVO directoryVO) {
		this.directoryDAO.delete(setDirFromDirVO(directoryVO));
	}

	@Override
	public List<DirectoryVO> getAll() {
		return setDirVOFromDir(this.directoryDAO.getAll());
	}

	private Directory setDirFromDirVO(DirectoryVO directoryVO){
//		Directory directory = new Directory();
		Directory directory = directoryDAO.get(directoryVO.getId()) == null ? new Directory() : directoryDAO.get(directoryVO.getId());
		BeanUtils.copyProperties(directoryVO, directory);
		
		User user = new User();
		
		BeanUtils.copyProperties(directoryVO.getCreatedBy() == null ? directory.getCreatedBy() : directoryVO.getCreatedBy(), user);
		directory.setCreatedBy(user);
		return directory;
	}
	
	private DirectoryVO setDirVOFromDir(Directory directory){
		DirectoryVO directoryVO = new DirectoryVO();
		BeanUtils.copyProperties(directory, directoryVO);
		
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(directory.getCreatedBy(), userVO);
		directoryVO.setCreatedBy(userVO);
		
		return directoryVO;
	}
	
	private List<DirectoryVO> setDirVOFromDir(List<Directory> directories){
		List<DirectoryVO> directoryVOs = new ArrayList<DirectoryVO>();
		for(int i = 0; i < directories.size(); i++){
			directoryVOs.add(setDirVOFromDir(directories.get(i)));
		}
		return directoryVOs;
	}

	@Override
	public List<DirectoryVO> getDirectoryByParent(String id) {
		return setDirVOFromDir(directoryDAO.getDirectoryByParent(id));
	}

	@Override
	public List<DirectoryVO> getDirectoryByName(String key) {
		return setDirVOFromDir(directoryDAO.getDirectoryByName(key));
	}
}
