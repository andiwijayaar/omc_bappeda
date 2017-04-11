package com.omcbappeda.sumsel.model;

import java.util.Date;

public class FileVO {

	private String id;
	private String name;
	private DirectoryVO directoryVO;
	private String extension;
	private UserVO createdBy;
	private Date createdDate;
	private String isActive;
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DirectoryVO getDirectoryVO() {
		return directoryVO;
	}

	public void setDirectoryVO(DirectoryVO directoryVO) {
		this.directoryVO = directoryVO;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public UserVO getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserVO createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
