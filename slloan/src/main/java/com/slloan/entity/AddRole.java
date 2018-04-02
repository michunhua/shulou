package com.slloan.entity;

import java.io.Serializable;
import java.util.Date;

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
	
	/*private String loan_management;//贷款管理
	private String repayment_confirmation;//回款确认
	private String loan_Final_review;//贷款终审
	private String loan_First_trial;//贷款初审
	private String loan_information;//贷款信息查看
	private String loan_Create;//贷款创建
	private String qz_certificate;//取证凭证上传
	private String Incarceration_certificate;//进押凭证上传
	private String jy_certificate;//解押凭证上传
	private String finance_Management;//财务管理
	private String finance_sp;//财务审批
	private String loan_Sp;//放款审批
	private String setTlement_Pz;//结算凭证上传
	private String transfer_Accounts_Pz;//转账凭证上传
	private String personal_information_sz;//个人信息设置
	private String personal_information_xg;//个人信息修改
	private String password_Modify;//密码修改	
	private String role_Setup;//角色设置
	private String add_Role;//添加角色
	private String modify_Role;//修改角色信息
	private String del_Role;//删除角色
	private String qx__Setup;//权限设置
	private String fp_Jurisdiction;//分配权限
	private String del_Jurisdiction;//删除权限
	private String user_Administration;//用户管理
	private String add_User;//添加用户
	private String fp_Role;//分配角色
	private String del_User;//删除用户
	private String modify_user;//修改用户信息
	private String parent_id;//父ID
	private String url;//地址
	private String method;//请求方式
	private String city;//市
	private String county;//省
//	private String name;//栏目名称
//	private String orderline;//排序值
 * */
	public AddRole(){}
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
	public AddRole(String roleName, String descriPtion,String belongs_City, String note,String configuration, String createDate) {
		super();
		this.roleName = roleName;
		this.descriPtion = descriPtion;
		this.note = note;
		this.createDate = createDate;
		this.belongs_City = belongs_City;
		this.configuration = configuration;
	}
	/*public AddRole(Integer id, String roleName, String descriPtion, String note, Date createDate, String belongs_City,
			String configuration, String loan_management, String repayment_confirmation, String loan_Final_review,
			String loan_First_trial, String loan_information, String loan_Create, String qz_certificate,
			String incarceration_certificate, String jy_certificate, String finance_Management, String finance_sp,
			String loan_Sp, String setTlement_Pz, String transfer_Accounts_Pz, String personal_information_sz,
			String personal_information_xg, String password_Modify, String role_Setup, String add_Role,
			String modify_Role, String del_Role, String qx__Setup, String fp_Jurisdiction, String del_Jurisdiction,
			String user_Administration, String add_User, String fp_Role, String del_User, String modify_user,
			String url, String method, String city, String county) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.descriPtion = descriPtion;
		this.note = note;
		this.createDate = createDate;
		this.belongs_City = belongs_City;
		this.configuration = configuration;
		this.loan_management = loan_management;
		this.repayment_confirmation = repayment_confirmation;
		this.loan_Final_review = loan_Final_review;
		this.loan_First_trial = loan_First_trial;
		this.loan_information = loan_information;
		this.loan_Create = loan_Create;
		this.qz_certificate = qz_certificate;
		Incarceration_certificate = incarceration_certificate;
		this.jy_certificate = jy_certificate;
		this.finance_Management = finance_Management;
		this.finance_sp = finance_sp;
		this.loan_Sp = loan_Sp;
		this.setTlement_Pz = setTlement_Pz;
		this.transfer_Accounts_Pz = transfer_Accounts_Pz;
		this.personal_information_sz = personal_information_sz;
		this.personal_information_xg = personal_information_xg;
		this.password_Modify = password_Modify;
		this.role_Setup = role_Setup;
		this.add_Role = add_Role;
		this.modify_Role = modify_Role;
		this.del_Role = del_Role;
		this.qx__Setup = qx__Setup;
		this.fp_Jurisdiction = fp_Jurisdiction;
		this.del_Jurisdiction = del_Jurisdiction;
		this.user_Administration = user_Administration;
		this.add_User = add_User;
		this.fp_Role = fp_Role;
		this.del_User = del_User;
		this.modify_user = modify_user;
		this.url = url;
		this.method = method;
		this.city = city;
		this.county = county;
	}*/
	public Integer getId() {
		return id;
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
	@Override
	public String toString() {
		return "AddRole [roleName=" + roleName + ", descriPtion=" + descriPtion + ", note=" + note
				+ ", createDate=" + createDate + ", belongs_City=" + belongs_City + ", configuration=" + configuration
				+ "]";
	}

}
