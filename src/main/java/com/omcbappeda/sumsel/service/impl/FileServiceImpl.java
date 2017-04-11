package com.omcbappeda.sumsel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.omcbappeda.sumsel.dao.FileDAO;
import com.omcbappeda.sumsel.model.Directory;
import com.omcbappeda.sumsel.model.DirectoryVO;
import com.omcbappeda.sumsel.model.File;
import com.omcbappeda.sumsel.model.FileVO;
import com.omcbappeda.sumsel.model.User;
import com.omcbappeda.sumsel.model.UserVO;
import com.omcbappeda.sumsel.service.FileService;

public class FileServiceImpl implements FileService {

	private FileDAO fileDAO;

	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	@Override
	public void save(FileVO fileVO) {
		this.fileDAO.save(setFileFromFileVO(fileVO));
	}

	@Override
	public FileVO get(String id) {
		return setFileVOFromFile(this.fileDAO.get(id));
	}

	@Override
	public void update(FileVO fileVO) {
		this.fileDAO.update(setFileFromFileVO(fileVO));
	}

	@Override
	public List<FileVO> getAll() {
		return setFileVOFromFile(this.fileDAO.getAll());
	}

	@Override
	public List<FileVO> getFileByDir(String id) {
		return setFileVOFromFile(fileDAO.getFileByDir(id));
	}
	
	private File setFileFromFileVO(FileVO fileVO){
		File file = fileDAO.get(fileVO.getId()) == null ? new File() : fileDAO.get(fileVO.getId());
		BeanUtils.copyProperties(fileVO, file);
		
		Directory directory = new Directory();		
		BeanUtils.copyProperties(fileVO.getDirectoryVO(), directory);
		
		User user = new User();
		
		BeanUtils.copyProperties(fileVO.getCreatedBy() == null ? file.getCreatedBy() : fileVO.getCreatedBy(), user);
		file.setCreatedBy(user);
		
		file.setDirectory(directory);
		return file;
	}
	
	private FileVO setFileVOFromFile(File file){
		FileVO fileVO = null;
		if(file != null){
			fileVO = new FileVO();
			BeanUtils.copyProperties(file, fileVO);
			
			DirectoryVO directoryVO = new DirectoryVO();		
			BeanUtils.copyProperties(file.getDirectory(), directoryVO);
			
			UserVO userVO = new UserVO();
			BeanUtils.copyProperties(file.getCreatedBy(), userVO);
			
			fileVO.setDirectoryVO(directoryVO);
			fileVO.setCreatedBy(userVO);
		}
		
		return fileVO;
	}
	
	private List<FileVO> setFileVOFromFile(List<File> files){
		List<FileVO> fileVOs = new ArrayList<FileVO>();
		for(int i = 0; i < files.size(); i++){
			fileVOs.add(setFileVOFromFile(files.get(i)));
		}
		return fileVOs;
	}

	@Override
	public List<FileVO> getFileByName(String key) {
		return setFileVOFromFile(fileDAO.getFileByName(key));
	}

}
