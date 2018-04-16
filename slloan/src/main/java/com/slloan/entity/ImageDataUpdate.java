package com.slloan.entity;

import java.io.Serializable;

/**
 * 影像资料上传
 * @author Administrator
 *
 */
public class ImageDataUpdate implements Serializable{
 private int id;
 private String filepath;//上传文件
 private String note;//备注
 private String uploads;//上传者
 private String originalfilename;//原文件名
 private String uploadtype;//上传类型
 private String Parentnode;//父节点
 private String Subnode;//子节点
 private String createData;//创建日期
 
 
 public ImageDataUpdate(String filepath, String parentnode, String uploadtype) {
	super();
	this.filepath = filepath;
	this.uploadtype = uploadtype;
	Parentnode = parentnode;
}
public ImageDataUpdate(){}
 public ImageDataUpdate( String filepath, String note, String uploads,String originalfilename,String uploadtype,String Parentnode,String Subnode, String createData) {
	super();
	this.filepath = filepath;
	this.note = note;
	this.uploads = uploads;
	this.originalfilename = originalfilename;
	this.uploadtype=uploadtype;
	this.Parentnode=Parentnode;
	this.Subnode=Subnode;
	this.createData = createData;
}
 public ImageDataUpdate(int id, String filepath, String note, String uploads,String originalfilename,String uploadtype,String Parentnode,String Subnode) {
	super();
	this.id = id;
	this.filepath = filepath;
	this.note = note;
	this.uploads = uploads;
	this.originalfilename = originalfilename;
	this.uploadtype=uploadtype;
	this.Parentnode=Parentnode;
	this.Subnode=Subnode;
}




public String getCreateData() {
	return createData;
}

public void setCreateData(String createData) {
	this.createData = createData;
}

public String getParentnode() {
	return Parentnode;
}

public void setParentnode(String parentnode) {
	Parentnode = parentnode;
}

public String getSubnode() {
	return Subnode;
}

public void setSubnode(String subnode) {
	Subnode = subnode;
}

public String getUploadtype() {
	return uploadtype;
}

public void setUploadtype(String uploadtype) {
	this.uploadtype = uploadtype;
}

public String getOriginalfilename() {
	return originalfilename;
}

public void setOriginalfilename(String originalfilename) {
	this.originalfilename = originalfilename;
}

public String getUploads() {
	return uploads;
}


public void setUploads(String uploads) {
	this.uploads = uploads;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFilepath() {
	return filepath;
}
public void setFilepath(String filepath) {
	this.filepath = filepath;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}

@Override
public String toString() {
	return "ImageDataUpdate [id=" + id + ", filepath=" + filepath + ", note=" + note + ", uploads=" + uploads
			+ ", originalfilename=" + originalfilename + ", uploadtype=" + uploadtype + ", Parentnode=" + Parentnode
			+ ", Subnode=" + Subnode + ", createData=" + createData + "]";
}


}
