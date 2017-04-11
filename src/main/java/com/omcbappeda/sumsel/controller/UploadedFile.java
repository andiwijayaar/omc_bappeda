package com.omcbappeda.sumsel.controller;

import org.springframework.web.multipart.MultipartFile;

public class UploadedFile {
	private MultipartFile multipartFile;
	private String idFolder;
	private String idUser;

	public String getIdFolder() {
		return idFolder;
	}

	public void setIdFolder(String idFolder) {
		this.idFolder = idFolder;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
}