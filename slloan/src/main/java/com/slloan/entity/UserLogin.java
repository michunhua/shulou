package com.slloan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 添加用户
 * @author Administrator
 *
 */
		
public class UserLogin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;//用户名
	private String passWord;//密码
	private String employeeis_Name;//员工姓名
	private String distribution_Role;//分配角色
	private String belongs_City;//所属城市
	private String note;//备注
	private Integer r_id;//角色ID
	private String create_Date;//创建日期
	private String updateDate;//修改日期
	
	public UserLogin(){
		
	}

	public UserLogin(String userName, String passWord, String employeeis_Name, String distribution_Role,
			String belongs_City, String note,String updateDate, Integer id) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.employeeis_Name = employeeis_Name;
		this.distribution_Role = distribution_Role;
		this.belongs_City = belongs_City;
		this.note = note;
		this.id = id;
		this.updateDate = updateDate;
	}
	

	public UserLogin(String userName, String passWord,String employeeis_Name, String distribution_Role,
			String belongs_City, String note, Integer r_id,String create_Date) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.employeeis_Name = employeeis_Name;
		this.distribution_Role = distribution_Role;
		this.belongs_City = belongs_City;
		this.note = note;
		this.r_id = r_id;
		this.create_Date = create_Date;
	}
	
	public UserLogin(String userName, String passWord, String employeeis_Name, String distribution_Role,
		String belongs_City, String note, String updateDate) {
	super();
	this.userName = userName;
	this.passWord = passWord;
	this.employeeis_Name = employeeis_Name;
	this.distribution_Role = distribution_Role;
	this.belongs_City = belongs_City;
	this.note = note;
	this.updateDate = updateDate;
}

	public UserLogin(Integer id,String userName, String employeeis_Name, String distribution_Role, String belongs_City,
			String note, String create_Date) {
		super();
		this.userName = userName;
		this.employeeis_Name = employeeis_Name;
		this.distribution_Role = distribution_Role;
		this.belongs_City = belongs_City;
		this.note = note;
		this.create_Date = create_Date;
	}
	
	public UserLogin(Integer id,String userName, String passWord,
			String employeeis_Name, String distribution_Role,
			String belongs_City, String note, String create_Date) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.employeeis_Name = employeeis_Name;
		this.distribution_Role = distribution_Role;
		this.belongs_City = belongs_City;
		this.note = note;
		this.id = id;
	}
	
	public UserLogin(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	
	
	public UserLogin(Integer id, String userName, String passWord, String employeeis_Name, String distribution_Role,
			String belongs_City, String note, Integer r_id,String create_Date) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.employeeis_Name = employeeis_Name;
		this.distribution_Role = distribution_Role;
		this.belongs_City = belongs_City;
		this.note = note;
		this.r_id = r_id;
		this.create_Date = create_Date;
	}

	
	
	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getR_id() {
		return r_id;
	}

	public void setR_id(Integer r_id) {
		this.r_id = r_id;
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


	public String getCreate_Date() {
		return create_Date;
	}

	public void setCreate_Date(String create_Date) {
		this.create_Date = create_Date;
	}
	

	public String getUpdatedate() {
		return updateDate;
	}

	public void setUpdatedate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", employeeis_Name="
				+ employeeis_Name + ", distribution_Role=" + distribution_Role + ", belongs_City=" + belongs_City
				+ ", note=" + note + ", r_id=" + r_id + ", create_Date=" + create_Date + ", updateDate=" + updateDate
				+ "]";
	}


}
