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
 private String city;
 private String uploadFtpRoute;
 private String spare;
 private String sparetwo;
 public ImageDataUpdate( String city, String uploadtype,String Parentnode,String spare,String sparetwo,String createData,int id,String filepath) {
	super();
//	this.id = id;
	this.city = city;
	this.uploadtype = uploadtype;
	this.Parentnode = Parentnode;
	this.spare = spare;
	this.sparetwo = sparetwo;
	this.createData = createData;
	this.id = id;
	this.filepath = filepath;
	
}
 public ImageDataUpdate( String city, String uploadtype,String Parentnode,String spare,String sparetwo,String createData) {
		super();
//		this.id = id;
		this.city = city;
		this.uploadtype = uploadtype;
		this.Parentnode = Parentnode;
		this.spare = spare;
		this.sparetwo = sparetwo;
		this.createData = createData;
		
	}
public ImageDataUpdate(){}
 public ImageDataUpdate( String filepath, String note, String uploads,String originalfilename,String uploadtype,String city,String Subnode, String createData,String Parentnode,String spare,String sparetwo) {
	super();
	this.filepath = filepath;
	this.note = note;
	this.uploads = uploads;
	this.originalfilename = originalfilename;
	this.uploadtype=uploadtype;
	this.city=city;
	this.Subnode=Subnode;
	this.createData = createData;
	this.Parentnode = Parentnode;
	this.spare = spare;
	this.sparetwo = sparetwo;
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

public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getUploadFtpRoute() {
	return uploadFtpRoute;
}
public void setUploadFtpRoute(String uploadFtpRoute) {
	this.uploadFtpRoute = uploadFtpRoute;
}
public String getSpare() {
	return spare;
}
public void setSpare(String spare) {
	this.spare = spare;
}
public String getSparetwo() {
	return sparetwo;
}
public void setSparetwo(String sparetwo) {
	this.sparetwo = sparetwo;
}
@Override
public String toString() {
	return "ImageDataUpdate [id=" + id + ", filepath=" + filepath + ", note=" + note + ", uploads=" + uploads
			+ ", originalfilename=" + originalfilename + ", uploadtype=" + uploadtype + ", Parentnode=" + Parentnode
			+ ", Subnode=" + Subnode + ", createData=" + createData + ", city=" + city + ", uploadFtpRoute="
			+ uploadFtpRoute + ", spare=" + spare + ", sparetwo=" + sparetwo + "]";
}

}