package com.slloan.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 添加角色
 * @author Administrator
 *
 */
public class AddRole{
	
	private Integer id;
	private String roleName;//角色名
	private String descriPtion;//角色描述
	private String belongs_City;//所属城市
	private String note;//备注
	private String configuration;//权限配置
	private String createDate;//创建日期
	private String updatedate;//修改日期
	private String parentid;
//	private UserLogin userid;
	
	public AddRole(){}
	
	public AddRole(String roleName, String descriPtion, String belongs_City, String note, String createDate) {
		super();
		this.roleName = roleName;
		this.descriPtion = descriPtion;
		this.belongs_City = belongs_City;
		this.note = note;
		this.createDate = createDate;
	}

	public AddRole(Integer id, String roleName, String descriPtion, String belongs_City, String note,
			String configuration, String createDate) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.descriPtion = descriPtion;
		this.belongs_City = belongs_City;
		this.note = note;
		this.configuration = configuration;
		this.createDate = createDate;
	}
	public AddRole(String roleName, String descriPtion,String belongs_City, String note,String configuration, String createDate,String parentid) {
		super();
		this.roleName = roleName;
		this.descriPtion = descriPtion;
		this.note = note;
		this.createDate = createDate;
		this.belongs_City = belongs_City;
		this.configuration = configuration;
		this.parentid = parentid;
	}
	
	
	public AddRole(String roleName, String descriPtion, String belongs_City, String note, String configuration,
			String updatedate, Integer id) {
		super();
		this.roleName = roleName;
		this.descriPtion = descriPtion;
		this.belongs_City = belongs_City;
		this.note = note;
		this.configuration = configuration;
		this.updatedate = updatedate;
		this.id = id;
	}

//	public UserLogin getUserid() {
//		return userid;
//	}
//
//	public void setUserid(UserLogin userid) {
//		this.userid = userid;
//	}

	public Integer getId() {
		return id;
	}
	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescriPtion() {
		return descriPtion;
	}
	public void setDescriPtion(String descriPtion) {
		this.descriPtion = descriPtion;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getBelongs_City() {
		return belongs_City;
	}
	public void setBelongs_City(String belongs_City) {
		this.belongs_City = belongs_City;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	
	/*public String getLoan_management() {
		return loan_management;
	}
	public void setLoan_management(String loan_management) {
		this.loan_management = loan_management;
	}
	public String getRepayment_confirmation() {
		return repayment_confirmation;
	}
	public void setRepayment_confirmation(String repayment_confirmation) {
		this.repayment_confirmation = repayment_confirmation;
	}
	public String getLoan_Final_review() {
		return loan_Final_review;
	}
	public void setLoan_Final_review(String loan_Final_review) {
		this.loan_Final_review = loan_Final_review;
	}
	public String getLoan_First_trial() {
		return loan_First_trial;
	}
	public void setLoan_First_trial(String loan_First_trial) {
		this.loan_First_trial = loan_First_trial;
	}
	public String getLoan_information() {
		return loan_information;
	}
	public void setLoan_information(String loan_information) {
		this.loan_information = loan_information;
	}
	public String getLoan_Create() {
		return loan_Create;
	}
	public void setLoan_Create(String loan_Create) {
		this.loan_Create = loan_Create;
	}
	public String getQz_certificate() {
		return qz_certificate;
	}
	public void setQz_certificate(String qz_certificate) {
		this.qz_certificate = qz_certificate;
	}
	public String getIncarceration_certificate() {
		return Incarceration_certificate;
	}
	public void setIncarceration_certificate(String incarceration_certificate) {
		Incarceration_certificate = incarceration_certificate;
	}
	public String getJy_certificate() {
		return jy_certificate;
	}
	public void setJy_certificate(String jy_certificate) {
		this.jy_certificate = jy_certificate;
	}
	public String getFinance_Management() {
		return finance_Management;
	}
	public void setFinance_Management(String finance_Management) {
		this.finance_Management = finance_Management;
	}
	public String getFinance_sp() {
		return finance_sp;
	}
	public void setFinance_sp(String finance_sp) {
		this.finance_sp = finance_sp;
	}
	public String getLoan_Sp() {
		return loan_Sp;
	}
	public void setLoan_Sp(String loan_Sp) {
		this.loan_Sp = loan_Sp;
	}
	public String getSetTlement_Pz() {
		return setTlement_Pz;
	}
	public void setSetTlement_Pz(String setTlement_Pz) {
		this.setTlement_Pz = setTlement_Pz;
	}
	public String getTransfer_Accounts_Pz() {
		return transfer_Accounts_Pz;
	}
	public void setTransfer_Accounts_Pz(String transfer_Accounts_Pz) {
		this.transfer_Accounts_Pz = transfer_Accounts_Pz;
	}
	public String getPersonal_information_sz() {
		return personal_information_sz;
	}
	public void setPersonal_information_sz(String personal_information_sz) {
		this.personal_information_sz = personal_information_sz;
	}
	public String getPersonal_information_xg() {
		return personal_information_xg;
	}
	public void setPersonal_information_xg(String personal_information_xg) {
		this.personal_information_xg = personal_information_xg;
	}
	public String getPassword_Modify() {
		return password_Modify;
	}
	public void setPassword_Modify(String password_Modify) {
		this.password_Modify = password_Modify;
	}
	public String getRole_Setup() {
		return role_Setup;
	}
	public void setRole_Setup(String role_Setup) {
		this.role_Setup = role_Setup;
	}
	public String getAdd_Role() {
		return add_Role;
	}
	public void setAdd_Role(String add_Role) {
		this.add_Role = add_Role;
	}
	public String getModify_Role() {
		return modify_Role;
	}
	public void setModify_Role(String modify_Role) {
		this.modify_Role = modify_Role;
	}
	public String getDel_Role() {
		return del_Role;
	}
	public void setDel_Role(String del_Role) {
		this.del_Role = del_Role;
	}
	public String getQx__Setup() {
		return qx__Setup;
	}
	public void setQx__Setup(String qx__Setup) {
		this.qx__Setup = qx__Setup;
	}
	public String getFp_Jurisdiction() {
		return fp_Jurisdiction;
	}
	public void setFp_Jurisdiction(String fp_Jurisdiction) {
		this.fp_Jurisdiction = fp_Jurisdiction;
	}
	public String getDel_Jurisdiction() {
		return del_Jurisdiction;
	}
	public void setDel_Jurisdiction(String del_Jurisdiction) {
		this.del_Jurisdiction = del_Jurisdiction;
	}
	public String getUser_Administration() {
		return user_Administration;
	}
	public void setUser_Administration(String user_Administration) {
		this.user_Administration = user_Administration;
	}
	public String getAdd_User() {
		return add_User;
	}
	public void setAdd_User(String add_User) {
		this.add_User = add_User;
	}
	public String getFp_Role() {
		return fp_Role;
	}
	public void setFp_Role(String fp_Role) {
		this.fp_Role = fp_Role;
	}
	public String getDel_User() {
		return del_User;
	}
	public void setDel_User(String del_User) {
		this.del_User = del_User;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}*/
	/*@Override
	public String toString() {
		return "AddRole [id=" + id + ", roleName=" + roleName + ", descriPtion=" + descriPtion + ", note=" + note
				+ ", createDate=" + createDate + ", belongs_City=" + belongs_City + ", configuration=" + configuration
				+ ", loan_management=" + loan_management + ", repayment_confirmation=" + repayment_confirmation
				+ ", loan_Final_review=" + loan_Final_review + ", loan_First_trial=" + loan_First_trial
				+ ", loan_information=" + loan_information + ", loan_Create=" + loan_Create + ", qz_certificate="
				+ qz_certificate + ", Incarceration_certificate=" + Incarceration_certificate + ", jy_certificate="
				+ jy_certificate + ", finance_Management=" + finance_Management + ", finance_sp=" + finance_sp
				+ ", loan_Sp=" + loan_Sp + ", setTlement_Pz=" + setTlement_Pz + ", transfer_Accounts_Pz="
				+ transfer_Accounts_Pz + ", personal_information_sz=" + personal_information_sz
				+ ", personal_information_xg=" + personal_information_xg + ", password_Modify=" + password_Modify
				+ ", role_Setup=" + role_Setup + ", add_Role=" + add_Role + ", modify_Role=" + modify_Role
				+ ", del_Role=" + del_Role + ", qx__Setup=" + qx__Setup + ", fp_Jurisdiction=" + fp_Jurisdiction
				+ ", del_Jurisdiction=" + del_Jurisdiction + ", user_Administration=" + user_Administration
				+ ", add_User=" + add_User + ", fp_Role=" + fp_Role + ", del_User=" + del_User + ", modify_user="
				+ modify_user + ", url=" + url + ", method=" + method + ", city=" + city + ", county=" + county + "]";
	}*/

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

//	@Override
//	public String toString() {
//		return "AddRole [id=" + id + ", roleName=" + roleName + ", descriPtion=" + descriPtion + ", belongs_City="
//				+ belongs_City + ", note=" + note + ", configuration=" + configuration + ", createDate=" + createDate
//				+ ", updatedate=" + updatedate + ", parentid=" + parentid + ", userid=" + userid + "]";
//	}

	@Override
	public String toString() {
		return "AddRole [id=" + id + ", roleName=" + roleName + ", descriPtion=" + descriPtion + ", belongs_City="
				+ belongs_City + ", note=" + note + ", configuration=" + configuration + ", createDate=" + createDate
				+ ", updatedate=" + updatedate + ", parentid=" + parentid + "]";
	}

}
