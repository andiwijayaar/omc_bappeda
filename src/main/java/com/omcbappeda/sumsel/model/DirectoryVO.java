package com.omcbappeda.sumsel.model;

import java.util.Date;

public class DirectoryVO {

	private String id;
	private String name;
	private String parent;
	private UserVO createdBy;
	private String isActive;
	private Date createdDate;

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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
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
