package com.slloan.entity;

import java.io.Serializable;

/**
 * 借款人配偶
 * @author xue
 *
 */
public class SpousesOfBorrowers implements Serializable{
	
	private Integer id;
	private String name;						//共同借款人配偶姓名
	private String id_Type;          			//身份证件类型
	private String id_Other;                     //其他
	private String id_Number;					//身份证件号码
	private String uni_Name;					//单位名称
	private String unit_Phone;					//单位电话
	private String home_Phone;					//住宅电话
	private String mobile_Phone;				//手机
	private double monthly_Income;				//月收入
	private String state;
	private String ctime;
	
	
	
	public SpousesOfBorrowers(){}



	public SpousesOfBorrowers(String name, String id_Type, String id_Other, String id_Number, String uni_Name,
			String unit_Phone, String home_Phone, String mobile_Phone, double monthly_Income, String state,
			String ctime) {
		super();
		this.name = name;
		this.id_Type = id_Type;
		this.id_Other = id_Other;
		this.id_Number = id_Number;
		this.uni_Name = uni_Name;
		this.unit_Phone = unit_Phone;
		this.home_Phone = home_Phone;
		this.mobile_Phone = mobile_Phone;
		this.monthly_Income = monthly_Income;
		this.state = state;
		this.ctime = ctime;
	}



	public SpousesOfBorrowers(Integer id, String name, String id_Type, String id_Other, String id_Number,
			String uni_Name, String unit_Phone, String home_Phone, String mobile_Phone, double monthly_Income,
			String state, String ctime) {
		super();
		this.id = id;
		this.name = name;
		this.id_Type = id_Type;
		this.id_Other = id_Other;
		this.id_Number = id_Number;
		this.uni_Name = uni_Name;
		this.unit_Phone = unit_Phone;
		this.home_Phone = home_Phone;
		this.mobile_Phone = mobile_Phone;
		this.monthly_Income = monthly_Income;
		this.state = state;
		this.ctime = ctime;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getId_Type() {
		return id_Type;
	}



	public void setId_Type(String id_Type) {
		this.id_Type = id_Type;
	}



	public String getId_Other() {
		return id_Other;
	}



	public void setId_Other(String id_Other) {
		this.id_Other = id_Other;
	}



	public String getId_Number() {
		return id_Number;
	}



	public void setId_Number(String id_Number) {
		this.id_Number = id_Number;
	}



	public String getUni_Name() {
		return uni_Name;
	}



	public void setUni_Name(String uni_Name) {
		this.uni_Name = uni_Name;
	}



	public String getUnit_Phone() {
		return unit_Phone;
	}



	public void setUnit_Phone(String unit_Phone) {
		this.unit_Phone = unit_Phone;
	}



	public String getHome_Phone() {
		return home_Phone;
	}



	public void setHome_Phone(String home_Phone) {
		this.home_Phone = home_Phone;
	}



	public String getMobile_Phone() {
		return mobile_Phone;
	}



	public void setMobile_Phone(String mobile_Phone) {
		this.mobile_Phone = mobile_Phone;
	}



	public double getMonthly_Income() {
		return monthly_Income;
	}



	public void setMonthly_Income(double monthly_Income) {
		this.monthly_Income = monthly_Income;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCtime() {
		return ctime;
	}



	public void setCtime(String ctime) {
		this.ctime = ctime;
	}




	
}
