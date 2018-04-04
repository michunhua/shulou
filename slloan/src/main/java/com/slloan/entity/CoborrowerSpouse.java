package com.slloan.entity;

import java.io.Serializable;

/**
 * 共同借款人配偶
 * @author xue
 *
 */
public class CoborrowerSpouse implements Serializable{
	
	private Integer id;
	private String name;						//共同借款人配偶姓名
	private String id_Type;          			//身份证件类型
	private String id_Number;					//身份证件号码
	private String uni_Name;					//单位名称
	private String unit_Phone;					//单位电话
	private String home_Phone;					//住宅电话
	private String mobile_Phone;				//手机
	private String monthly_Income;				//月收入
	private String start;
	private String ctime;
	
	
	
	public CoborrowerSpouse(String name, String id_Type, String id_Number, String uni_Name, String unit_Phone,
			String home_Phone, String mobile_Phone, String monthly_Income, String start, String ctime) {
		super();
		this.name = name;
		this.id_Type = id_Type;
		this.id_Number = id_Number;
		this.uni_Name = uni_Name;
		this.unit_Phone = unit_Phone;
		this.home_Phone = home_Phone;
		this.mobile_Phone = mobile_Phone;
		this.monthly_Income = monthly_Income;
		this.start = start;
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
	public String getMonthly_Income() {
		return monthly_Income;
	}
	public void setMonthly_Income(String monthly_Income) {
		this.monthly_Income = monthly_Income;
	}
	
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	@Override
	public String toString() {
		return "CoborrowerSpouse [id=" + id + ", name=" + name + ", id_Type=" + id_Type + ", id_Number=" + id_Number
				+ ", uni_Name=" + uni_Name + ", unit_Phone=" + unit_Phone + ", home_Phone=" + home_Phone
				+ ", mobile_Phone=" + mobile_Phone + ", monthly_Income=" + monthly_Income + ", start=" + start
				+ ", ctime=" + ctime + "]";
	}
	
	
}
