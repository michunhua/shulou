package com.slloan.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户权限管理&用户列表
 * @author Administrator
 *
 */
public class UserRightsManagement implements Serializable{

	private Integer id; //编号
	private String userName;//用户名
	private String password;//密码
	private String assigning_Roles;//分配角色
	private String employeeis_name;//员工姓名
	private Date creation_date;//创建日期
	private String Note; //备注
	
	public UserRightsManagement(){
		
	}
	

	public UserRightsManagement(Integer id, String userName, String password, String assigning_Roles,
			String employeeis_name, Date creation_date, String note) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.assigning_Roles = assigning_Roles;
		this.employeeis_name = employeeis_name;
		this.creation_date = creation_date;
		Note = note;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAssigning_Roles() {
		return assigning_Roles;
	}
	public void setAssigning_Roles(String assigning_Roles) {
		this.assigning_Roles = assigning_Roles;
	}
	public String getEmployeeis_name() {
		return employeeis_name;
	}
	public void setEmployeeis_name(String employeeis_name) {
		this.employeeis_name = employeeis_name;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	public String getNote() {
		return Note;
	}


	public void setNote(String note) {
		Note = note;
	}

	@Override
	public String toString() {
		return "UserRightsManagement [id=" + id + ", userName=" + userName + ", password=" + password
				+ ", assigning_Roles=" + assigning_Roles + ", employeeis_name=" + employeeis_name + ", creation_date="
				+ creation_date + ", Note=" + Note + "]";
	}


}
