package com.slloan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 申请借款信息
 * @author xue
 *
 */
public class ApplyForLoanInformation implements Serializable{
	
	private Integer id;
	private String amount;						//金额
	private String time_Limit;					//期限
	private String time_Limits;					//天月年
	private String borrowing_Variety;    		//借款品种
	private String repayment;					//还款方式
	private String receiving_Bank_Name;  		//收款银行名称
	private String receiving_Account_Name;	    //收款账户名
	private String receiving_Account;    		//收款账号
	private String repayment_Bank_Name;  		//还款银行名称
	private String repayment_Account_Name; 		//还款账户名
	private String repayment_Account_Number;		//还款账号
	private String state;
	private String ctime;
	private String username;//存入用户名
	private String ParentnodeId;//存入登录用户名ID
	private String city;//城市
	private String rolename;//角色名
	
	public ApplyForLoanInformation(){
		
	}
	
	private List<Contacts> contacts;

	private List<PropertyInformation> propert;
	

	
	

	public ApplyForLoanInformation(String amount, String time_Limit, String time_Limits, String borrowing_Variety,
			String repayment, String receiving_Bank_Name, String receiving_Account_Name, String receiving_Account,
			String repayment_Bank_Name, String repayment_Account_Name, String repayment_Account_Number, String state,
			String ctime, String username, String parentnodeId, String city, String rolename) {
		super();
		this.amount = amount;
		this.time_Limit = time_Limit;
		this.time_Limits = time_Limits;
		this.borrowing_Variety = borrowing_Variety;
		this.repayment = repayment;
		this.receiving_Bank_Name = receiving_Bank_Name;
		this.receiving_Account_Name = receiving_Account_Name;
		this.receiving_Account = receiving_Account;
		this.repayment_Bank_Name = repayment_Bank_Name;
		this.repayment_Account_Name = repayment_Account_Name;
		this.repayment_Account_Number = repayment_Account_Number;
		this.state = state;
		this.ctime = ctime;
		this.username = username;
		ParentnodeId = parentnodeId;
		this.city = city;
		this.rolename = rolename;
	}



	public ApplyForLoanInformation(String amount, String time_Limit, String time_Limits, String borrowing_Variety,
			String repayment, String receiving_Bank_Name, String receiving_Account_Name, String receiving_Account,
			String repayment_Bank_Name, String repayment_Account_Name, String repayment_Account_Number) {
		super();
		this.amount = amount;
		this.time_Limit = time_Limit;
		this.time_Limits = time_Limits;
		this.borrowing_Variety = borrowing_Variety;
		this.repayment = repayment;
		this.receiving_Bank_Name = receiving_Bank_Name;
		this.receiving_Account_Name = receiving_Account_Name;
		this.receiving_Account = receiving_Account;
		this.repayment_Bank_Name = repayment_Bank_Name;
		this.repayment_Account_Name = repayment_Account_Name;
		this.repayment_Account_Number = repayment_Account_Number;
	}



	public ApplyForLoanInformation(String amount, String time_Limit, String time_Limits, String borrowing_Variety,
			String repayment, String receiving_Bank_Name, String receiving_Account_Name, String receiving_Account,
			String repayment_Bank_Name, String repayment_Account_Name, String repayment_Account_Number, String state,
			String ctime) {
		super();
		this.amount = amount;
		this.time_Limit = time_Limit;
		this.time_Limits = time_Limits;
		this.borrowing_Variety = borrowing_Variety;
		this.repayment = repayment;
		this.receiving_Bank_Name = receiving_Bank_Name;
		this.receiving_Account_Name = receiving_Account_Name;
		this.receiving_Account = receiving_Account;
		this.repayment_Bank_Name = repayment_Bank_Name;
		this.repayment_Account_Name = repayment_Account_Name;
		this.repayment_Account_Number = repayment_Account_Number;
		this.state = state;
		this.ctime = ctime;
	}



	public ApplyForLoanInformation(Integer id, String amount, String time_Limit, String time_Limits,
			String borrowing_Variety, String repayment, String receiving_Bank_Name, String receiving_Account_Name,
			String receiving_Account, String repayment_Bank_Name, String repayment_Account_Name,
			String repayment_Account_Number, String state, String ctime) {
		super();
		this.id = id;
		this.amount = amount;
		this.time_Limit = time_Limit;
		this.time_Limits = time_Limits;
		this.borrowing_Variety = borrowing_Variety;
		this.repayment = repayment;
		this.receiving_Bank_Name = receiving_Bank_Name;
		this.receiving_Account_Name = receiving_Account_Name;
		this.receiving_Account = receiving_Account;
		this.repayment_Bank_Name = repayment_Bank_Name;
		this.repayment_Account_Name = repayment_Account_Name;
		this.repayment_Account_Number = repayment_Account_Number;
		this.state = state;
		this.ctime = ctime;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTime_Limit() {
		return time_Limit;
	}

	public void setTime_Limit(String time_Limit) {
		this.time_Limit = time_Limit;
	}

	public String getTime_Limits() {
		return time_Limits;
	}

	public void setTime_Limits(String time_Limits) {
		this.time_Limits = time_Limits;
	}

	public String getBorrowing_Variety() {
		return borrowing_Variety;
	}

	public void setBorrowing_Variety(String borrowing_Variety) {
		this.borrowing_Variety = borrowing_Variety;
	}

	public String getRepayment() {
		return repayment;
	}

	public void setRepayment(String repayment) {
		this.repayment = repayment;
	}

	public String getReceiving_Bank_Name() {
		return receiving_Bank_Name;
	}

	public void setReceiving_Bank_Name(String receiving_Bank_Name) {
		this.receiving_Bank_Name = receiving_Bank_Name;
	}

	public String getReceiving_Account_Name() {
		return receiving_Account_Name;
	}

	public void setReceiving_Account_Name(String receiving_Account_Name) {
		this.receiving_Account_Name = receiving_Account_Name;
	}

	public String getReceiving_Account() {
		return receiving_Account;
	}

	public void setReceiving_Account(String receiving_Account) {
		this.receiving_Account = receiving_Account;
	}

	public String getRepayment_Bank_Name() {
		return repayment_Bank_Name;
	}

	public void setRepayment_Bank_Name(String repayment_Bank_Name) {
		this.repayment_Bank_Name = repayment_Bank_Name;
	}

	public String getRepayment_Account_Name() {
		return repayment_Account_Name;
	}

	public void setRepayment_Account_Name(String repayment_Account_Name) {
		this.repayment_Account_Name = repayment_Account_Name;
	}

	public String getRepayment_Account_Number() {
		return repayment_Account_Number;
	}

	public void setRepayment_Account_Number(String repayment_Account_Number) {
		this.repayment_Account_Number = repayment_Account_Number;
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

	@Override
	public String toString() {
		return "ApplyForLoanInformation [id=" + id + ", amount=" + amount + ", time_Limit=" + time_Limit
				+ ", time_Limits=" + time_Limits + ", borrowing_Variety=" + borrowing_Variety + ", repayment="
				+ repayment + ", receiving_Bank_Name=" + receiving_Bank_Name + ", receiving_Account_Name="
				+ receiving_Account_Name + ", receiving_Account=" + receiving_Account + ", repayment_Bank_Name="
				+ repayment_Bank_Name + ", repayment_Account_Name=" + repayment_Account_Name
				+ ", repayment_Account_Number=" + repayment_Account_Number + ", state=" + state + ", ctime=" + ctime
				+ ", username=" + username + ", ParentnodeId=" + ParentnodeId + ", city=" + city + ", rolename="
				+ rolename + "]";
	}
	
	
	
	
}
