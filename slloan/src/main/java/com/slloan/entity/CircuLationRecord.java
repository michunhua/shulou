package com.slloan.entity;

import java.io.Serializable;

/**
 * 流转记录&状态改动
 * @author Administrator
 *
 */
public class CircuLationRecord implements Serializable{

	private int id;//ID
	private String fallbackname;//退回名
	private String submit;//提前
	private int state;// 状态
	private String spare1;//备用字段
	private String createDate;//创建时间
	public CircuLationRecord(){}


	public CircuLationRecord(String fallbackname, String submit, int state, String spare1,String createDate) {
		super();
		this.fallbackname = fallbackname;
		this.submit = submit;
		this.state = state;
		this.spare1 = spare1;
		this.createDate = createDate;
	}


	public CircuLationRecord(int id, String fallbackname, String submit, int state, String spare1 ,String createDate) {
		super();
		this.id = id;
		this.fallbackname = fallbackname;
		this.submit = submit;
		this.state = state;
		this.spare1 = spare1;
		this.createDate = createDate;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSpare1() {
		return spare1;
	}

	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}


	public String getFallbackname() {
		return fallbackname;
	}

	public void setFallbackname(String fallbackname) {
		this.fallbackname = fallbackname;
	}



	@Override
	public String toString() {
		return "CircuLationRecord [id=" + id + ", fallbackname=" + fallbackname + ", submit=" + submit + ", state="
				+ state + ", spare1=" + spare1 + ", createDate=" + createDate + "]";
	}
}
