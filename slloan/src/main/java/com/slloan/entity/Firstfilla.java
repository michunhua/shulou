package com.slloan.entity;

import java.io.Serializable;

import com.sun.mail.imap.protocol.SaslAuthenticator;

public class Firstfilla implements Serializable{
	
	
	private int id;//ID
	private String firstnamese;//退回名
	private String submit;//提前
	private int state;// 状态
	private String spare1;//备用字段
	private String createDate;//创建时间
	
	
	
	public Firstfilla(String firstname, String submit, int state, String spare1, String createDate) {
		super();
		this.firstnamese = firstname;
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
	public String getFirstname() {
		return firstnamese;
	}
	public void setFirstname(String firstname) {
		this.firstnamese = firstname;
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
	public String getSpare1() {
		return spare1;
	}
	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Firstfilla [id=" + id + ", firstname=" + firstnamese + ", submit=" + submit + ", state=" + state
				+ ", spare1=" + spare1 + ", createDate=" + createDate + "]";
	}
	
	
}
