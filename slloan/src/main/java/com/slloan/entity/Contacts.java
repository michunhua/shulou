package com.slloan.entity;

import java.io.Serializable;

/**
 * 联系人
 * @author Administrator
 *
 */

public class Contacts implements Serializable{
	
	private Integer id;
	private String contacts;  //1联系人姓名
	private String contacts1; //2联系人姓名
	private String contacts2;//3联系人姓名
	private String relationship;//1联系人关系
	private String relationship1;//2联系人关系
	private String relationship2;//3联系人关系
	private String c_Telephone;//1联系人电话
	private String c_Telephone1;//2联系人电话
	private String c_Telephone2;//3联系人电话
	private String state;
	private String ctime;
	
	public Contacts(){}
	
	
	public Contacts(Integer id, String contacts, String contacts1, String contacts2, String relationship,
			String relationship1, String relationship2, String c_Telephone, String c_Telephone1, String c_Telephone2,
			String state, String ctime) {
		super();
		this.id = id;
		this.contacts = contacts;
		this.contacts1 = contacts1;
		this.contacts2 = contacts2;
		this.relationship = relationship;
		this.relationship1 = relationship1;
		this.relationship2 = relationship2;
		this.c_Telephone = c_Telephone;
		this.c_Telephone1 = c_Telephone1;
		this.c_Telephone2 = c_Telephone2;
		this.state = state;
		this.ctime = ctime;
	}


	public Contacts(String contacts, String contacts1, String contacts2, String relationship, String relationship1,
			String relationship2, String c_Telephone, String c_Telephone1, String c_Telephone2) {
		super();
		this.contacts = contacts;
		this.contacts1 = contacts1;
		this.contacts2 = contacts2;
		this.relationship = relationship;
		this.relationship1 = relationship1;
		this.relationship2 = relationship2;
		this.c_Telephone = c_Telephone;
		this.c_Telephone1 = c_Telephone1;
		this.c_Telephone2 = c_Telephone2;
	}
	public Contacts(String contacts, String contacts1, String contacts2, String relationship, String relationship1,
			String relationship2, String c_Telephone, String c_Telephone1, String c_Telephone2, String state,
			String ctime) {
		super();
		this.contacts = contacts;
		this.contacts1 = contacts1;
		this.contacts2 = contacts2;
		this.relationship = relationship;
		this.relationship1 = relationship1;
		this.relationship2 = relationship2;
		this.c_Telephone = c_Telephone;
		this.c_Telephone1 = c_Telephone1;
		this.c_Telephone2 = c_Telephone2;
		this.state = state;
		this.ctime = ctime;
	}
	public String getstate() {
		return state;
	}
	public void setstate(String state) {
		this.state = state;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getContacts1() {
		return contacts1;
	}
	public void setContacts1(String contacts1) {
		this.contacts1 = contacts1;
	}
	public String getContacts2() {
		return contacts2;
	}
	public void setContacts2(String contacts2) {
		this.contacts2 = contacts2;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getRelationship1() {
		return relationship1;
	}
	public void setRelationship1(String relationship1) {
		this.relationship1 = relationship1;
	}
	public String getRelationship2() {
		return relationship2;
	}
	public void setRelationship2(String relationship2) {
		this.relationship2 = relationship2;
	}
	public String getC_Telephone() {
		return c_Telephone;
	}
	public void setC_Telephone(String c_Telephone) {
		this.c_Telephone = c_Telephone;
	}
	public String getC_Telephone1() {
		return c_Telephone1;
	}
	public void setC_Telephone1(String c_Telephone1) {
		this.c_Telephone1 = c_Telephone1;
	}
	public String getC_Telephone2() {
		return c_Telephone2;
	}
	public void setC_Telephone2(String c_Telephone2) {
		this.c_Telephone2 = c_Telephone2;
	}
	@Override
	public String toString() {
		return "Contacts [id=" + id + ", contacts=" + contacts + ", contacts1=" + contacts1 + ", contacts2=" + contacts2
				+ ", relationship=" + relationship + ", relationship1=" + relationship1 + ", relationship2="
				+ relationship2 + ", c_Telephone=" + c_Telephone + ", c_Telephone1=" + c_Telephone1 + ", c_Telephone2="
				+ c_Telephone2 + ", state=" + state + ", ctime=" + ctime + "]";
	}
	
	
	
}
