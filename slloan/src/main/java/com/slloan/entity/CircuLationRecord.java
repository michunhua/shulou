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
	private String username;//存入用户名
	private String ParentnodeId;//存入登录用户名ID
	private String city;//城市
	private String rolename;//角色名
	private String updatedate;
	private String marked;
	
	public CircuLationRecord(){}

	public CircuLationRecord(String fallbackname, int state,String updatedate, int id) {
//		super();
		this.fallbackname = fallbackname;
		this.state = state;
		this.updatedate = updatedate;
		this.id = id;
	}

	public CircuLationRecord(String fallbackname, int state, String createDate,
			String username, String parentnodeId, String city, String rolename) {
		super();
		this.fallbackname = fallbackname;
		this.state = state;
		this.createDate = createDate;
		this.username = username;
		ParentnodeId = parentnodeId;
		this.city = city;
		this.rolename = rolename;
	}
	

	public CircuLationRecord(String fallbackname,String submit, int state, String createDate,
			String username, String parentnodeId, String city, String rolename,String spare1) {
		super();
		this.fallbackname = fallbackname;
		this.submit = submit;
		this.state = state;
		this.createDate = createDate;
		this.username = username;
		ParentnodeId = parentnodeId;
		this.city = city;
		this.rolename = rolename;
		this.spare1 = spare1;
	}
	public CircuLationRecord(String fallbackname, int state, String createDate,
			String username, String parentnodeId, String city, String rolename,int id) {
		super();
		this.fallbackname = fallbackname;
		this.state = state;
		this.createDate = createDate;
		this.username = username;
		ParentnodeId = parentnodeId;
		this.city = city;
		this.rolename = rolename;
		this.id = id;
	}

	public CircuLationRecord(String fallbackname, int state, String createDate,
			String username, String parentnodeId, String city, String rolename,String spare1,int id) {
		super();
		this.fallbackname = fallbackname;
		this.state = state;
		this.createDate = createDate;
		this.username = username;
		ParentnodeId = parentnodeId;
		this.city = city;
		this.rolename = rolename;
		this.spare1 = spare1;
		this.id = id;
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

	public CircuLationRecord(String submit, String city, int state) {
		super();
		this.submit = submit;
		this.city = city;
		this.state = state;
	}


	public CircuLationRecord(String fallbackname, int state,String updatedate,String username,String ParentnodeId,String rolename, int id) {
//		super();
		this.fallbackname = fallbackname;
//		this.city = city;
		this.state = state;
		this.updatedate = updatedate;
		this.username = username;
		this.ParentnodeId = ParentnodeId;
		this.rolename = rolename;
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getParentnodeId() {
		return ParentnodeId;
	}


	public void setParentnodeId(String parentnodeId) {
		ParentnodeId = parentnodeId;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getRolename() {
		return rolename;
	}


	public void setRolename(String rolename) {
		this.rolename = rolename;
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



	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}


	public String getMarked() {
		return marked;
	}

	public void setMarked(String marked) {
		this.marked = marked;
	}

	@Override
	public String toString() {
		return "CircuLationRecord [id=" + id + ", fallbackname=" + fallbackname + ", submit=" + submit + ", state="
				+ state + ", spare1=" + spare1 + ", createDate=" + createDate + ", username=" + username
				+ ", ParentnodeId=" + ParentnodeId + ", city=" + city + ", rolename=" + rolename + ", updatedate="
				+ updatedate + ", marked=" + marked + "]";
	}

}
