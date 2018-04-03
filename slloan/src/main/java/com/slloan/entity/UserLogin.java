package com.slloan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 添加用户
 * @author Administrator
 *
 */
		
public class UserLogin<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;//用户名
	private String passWord;//密码
	private String employeeis_Name;//员工姓名
	private String distribution_Role;//分配角色
	private String belongs_City;//所属城市
	private String note;//备注
	private Integer r_id;//角色ID
	private String createDate;//创建日期
	private List<T> userRole;//角色
	public UserLogin(){
		
	}
	
	public UserLogin(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	public UserLogin(String userName, String passWord,
			String employeeis_Name, String distribution_Role,
			String belongs_City, String note, Integer r_id,String createDate) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.employeeis_Name = employeeis_Name;
		this.distribution_Role = distribution_Role;
		this.belongs_City = belongs_City;
		this.note = note;
		this.r_id = r_id;
		this.createDate = createDate;
	}
	public UserLogin(String userName, String passWord,
			String employeeis_Name, String distribution_Role,
			String belongs_City, String note) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.employeeis_Name = employeeis_Name;
		this.distribution_Role = distribution_Role;
		this.belongs_City = belongs_City;
		this.note = note;
	}
	public UserLogin(Integer id, String userName, String passWord, String employeeis_Name, String distribution_Role,
			String belongs_City, String note, Integer r_id,String createDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.employeeis_Name = employeeis_Name;
		this.distribution_Role = distribution_Role;
		this.belongs_City = belongs_City;
		this.note = note;
		this.r_id = r_id;
		this.userRole = userRole;
		this.createDate = createDate;
	}

	public Integer getR_id() {
		return r_id;
	}

	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}

	public List<T> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<T> userRole) {
		this.userRole = userRole;
	}

	public String getBelongs_City() {
		return belongs_City;
	}

	public void setBelongs_City(String belongs_City) {
		this.belongs_City = belongs_City;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmployeeis_Name() {
		return employeeis_Name;
	}

	public void setEmployeeis_Name(String employeeis_Name) {
		this.employeeis_Name = employeeis_Name;
	}

	public String getDistribution_Role() {
		return distribution_Role;
	}

	public void setDistribution_Role(String distribution_Role) {
		this.distribution_Role = distribution_Role;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", employeeis_Name="
				+ employeeis_Name + ", distribution_Role=" + distribution_Role + ", belongs_City=" + belongs_City
				+ ", note=" + note + ", r_id=" + r_id + ", createDate=" + createDate + ", userRole=" + userRole + "]";
	}

}
