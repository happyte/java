package com.zs.javaweb.beans;

public class FileUploadBean {
	private Integer id;
	//文件名
	private String fileName;
	//文件路径 
	private String filePath;
	//文件描述
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public FileUploadBean(String fileName, String filePath, String description) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.description = description;
	}
	public FileUploadBean() {
		
	}

}
