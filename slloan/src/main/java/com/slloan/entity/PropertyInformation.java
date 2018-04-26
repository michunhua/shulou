package com.slloan.entity;

import java.io.Serializable;

/**
 * 房产资料
 * 
 * @author xue
 *
 */
public class PropertyInformation implements Serializable {

	private Integer id;
	private String name;
	private String ownership_And_percentage; // 权属人及占比
	private String property_Address; // 房产地址
	private String conStruction_Area; // 建筑面积
	private String inner_Area; // 套内面积
	private String sales_Contract_Number; // 买卖合同编号
	private String certificate_of_Title; // 产权证号
	private String proPerty_for; // 房产用于
	private String the_Assessed_Value; // 评估值
	private String original_Loan_Bank; // 原贷款银行
	private double original_Loan_Amount; // 原贷款金额
	private double loan_Outstanding_Balance; // 原贷款尚欠本息余额
	private String house_Account; // 供楼账号
	private double transaCtion_Price; // 买卖成交价
	private double purchase_Deposit; // 购房定金
	private String supervision_of_funds; // 资金监管
	private String new_loan_Bank; // 新贷款银行
	private double new_Loan_Approval_Amount; // 新贷款批复金额
	private String new_Loan_Bank_Account_Number; // 新贷款行账号
	private String note_DescriPtion; // 备注
	private String state;
	private String ctime;

	public PropertyInformation() {

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

	public String getOwnership_And_percentage() {
		return ownership_And_percentage;
	}

	public void setOwnership_And_percentage(String ownership_And_percentage) {
		this.ownership_And_percentage = ownership_And_percentage;
	}

	public String getProperty_Address() {
		return property_Address;
	}

	public void setProperty_Address(String property_Address) {
		this.property_Address = property_Address;
	}

	public String getConStruction_Area() {
		return conStruction_Area;
	}

	public void setConStruction_Area(String conStruction_Area) {
		this.conStruction_Area = conStruction_Area;
	}

	public String getInner_Area() {
		return inner_Area;
	}

	public void setInner_Area(String inner_Area) {
		this.inner_Area = inner_Area;
	}

	public String getSales_Contract_Number() {
		return sales_Contract_Number;
	}

	public void setSales_Contract_Number(String sales_Contract_Number) {
		this.sales_Contract_Number = sales_Contract_Number;
	}

	public String getCertificate_of_Title() {
		return certificate_of_Title;
	}

	public void setCertificate_of_Title(String certificate_of_Title) {
		this.certificate_of_Title = certificate_of_Title;
	}

	public String getProPerty_for() {
		return proPerty_for;
	}

	public void setProPerty_for(String proPerty_for) {
		this.proPerty_for = proPerty_for;
	}

	public String getThe_Assessed_Value() {
		return the_Assessed_Value;
	}

	public void setThe_Assessed_Value(String the_Assessed_Value) {
		this.the_Assessed_Value = the_Assessed_Value;
	}

	public String getOriginal_Loan_Bank() {
		return original_Loan_Bank;
	}

	public void setOriginal_Loan_Bank(String original_Loan_Bank) {
		this.original_Loan_Bank = original_Loan_Bank;
	}

	public double getOriginal_Loan_Amount() {
		return original_Loan_Amount;
	}

	public void setOriginal_Loan_Amount(double original_Loan_Amount) {
		this.original_Loan_Amount = original_Loan_Amount;
	}

	public double getLoan_Outstanding_Balance() {
		return loan_Outstanding_Balance;
	}

	public void setLoan_Outstanding_Balance(double loan_Outstanding_Balance) {
		this.loan_Outstanding_Balance = loan_Outstanding_Balance;
	}

	public String getHouse_Account() {
		return house_Account;
	}

	public void setHouse_Account(String house_Account) {
		this.house_Account = house_Account;
	}

	public double getTransaCtion_Price() {
		return transaCtion_Price;
	}

	public void setTransaCtion_Price(double transaCtion_Price) {
		this.transaCtion_Price = transaCtion_Price;
	}

	public double getPurchase_Deposit() {
		return purchase_Deposit;
	}

	public void setPurchase_Deposit(double purchase_Deposit) {
		this.purchase_Deposit = purchase_Deposit;
	}

	public String getSupervision_of_funds() {
		return supervision_of_funds;
	}

	public void setSupervision_of_funds(String supervision_of_funds) {
		this.supervision_of_funds = supervision_of_funds;
	}

	public String getNew_loan_Bank() {
		return new_loan_Bank;
	}

	public void setNew_loan_Bank(String new_loan_Bank) {
		this.new_loan_Bank = new_loan_Bank;
	}

	public double getNew_Loan_Approval_Amount() {
		return new_Loan_Approval_Amount;
	}

	public void setNew_Loan_Approval_Amount(double new_Loan_Approval_Amount) {
		this.new_Loan_Approval_Amount = new_Loan_Approval_Amount;
	}

	public String getNew_Loan_Bank_Account_Number() {
		return new_Loan_Bank_Account_Number;
	}

	public void setNew_Loan_Bank_Account_Number(String new_Loan_Bank_Account_Number) {
		this.new_Loan_Bank_Account_Number = new_Loan_Bank_Account_Number;
	}

	public String getNote_DescriPtion() {
		return note_DescriPtion;
	}

	public void setNote_DescriPtion(String note_DescriPtion) {
		this.note_DescriPtion = note_DescriPtion;
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

	public PropertyInformation(Integer id, String name, String ownership_And_percentage, String property_Address,
			String conStruction_Area, String inner_Area, String sales_Contract_Number, String certificate_of_Title,
			String proPerty_for, String the_Assessed_Value, String original_Loan_Bank, double original_Loan_Amount,
			double loan_Outstanding_Balance, String house_Account, double transaCtion_Price, double purchase_Deposit,
			String supervision_of_funds, String new_loan_Bank, double new_Loan_Approval_Amount,
			String new_Loan_Bank_Account_Number, String note_DescriPtion, String state, String ctime) {
		super();
		this.id = id;
		this.name = name;
		this.ownership_And_percentage = ownership_And_percentage;
		this.property_Address = property_Address;
		this.conStruction_Area = conStruction_Area;
		this.inner_Area = inner_Area;
		this.sales_Contract_Number = sales_Contract_Number;
		this.certificate_of_Title = certificate_of_Title;
		this.proPerty_for = proPerty_for;
		this.the_Assessed_Value = the_Assessed_Value;
		this.original_Loan_Bank = original_Loan_Bank;
		this.original_Loan_Amount = original_Loan_Amount;
		this.loan_Outstanding_Balance = loan_Outstanding_Balance;
		this.house_Account = house_Account;
		this.transaCtion_Price = transaCtion_Price;
		this.purchase_Deposit = purchase_Deposit;
		this.supervision_of_funds = supervision_of_funds;
		this.new_loan_Bank = new_loan_Bank;
		this.new_Loan_Approval_Amount = new_Loan_Approval_Amount;
		this.new_Loan_Bank_Account_Number = new_Loan_Bank_Account_Number;
		this.note_DescriPtion = note_DescriPtion;
		this.state = state;
		this.ctime = ctime;
	}

	public PropertyInformation(String name, String ownership_And_percentage, String property_Address,
			String conStruction_Area, String inner_Area, String sales_Contract_Number, String certificate_of_Title,
			String proPerty_for, String the_Assessed_Value, String original_Loan_Bank, double original_Loan_Amount,
			double loan_Outstanding_Balance, String house_Account, double transaCtion_Price, double purchase_Deposit,
			String supervision_of_funds, String new_loan_Bank, double new_Loan_Approval_Amount,
			String new_Loan_Bank_Account_Number, String note_DescriPtion, String state, String ctime) {
		super();
		this.name = name;
		this.ownership_And_percentage = ownership_And_percentage;
		this.property_Address = property_Address;
		this.conStruction_Area = conStruction_Area;
		this.inner_Area = inner_Area;
		this.sales_Contract_Number = sales_Contract_Number;
		this.certificate_of_Title = certificate_of_Title;
		this.proPerty_for = proPerty_for;
		this.the_Assessed_Value = the_Assessed_Value;
		this.original_Loan_Bank = original_Loan_Bank;
		this.original_Loan_Amount = original_Loan_Amount;
		this.loan_Outstanding_Balance = loan_Outstanding_Balance;
		this.house_Account = house_Account;
		this.transaCtion_Price = transaCtion_Price;
		this.purchase_Deposit = purchase_Deposit;
		this.supervision_of_funds = supervision_of_funds;
		this.new_loan_Bank = new_loan_Bank;
		this.new_Loan_Approval_Amount = new_Loan_Approval_Amount;
		this.new_Loan_Bank_Account_Number = new_Loan_Bank_Account_Number;
		this.note_DescriPtion = note_DescriPtion;
		this.state = state;
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "PropertyInformation [id=" + id + ", name=" + name + ", ownership_And_percentage="
				+ ownership_And_percentage + ", property_Address=" + property_Address + ", conStruction_Area="
				+ conStruction_Area + ", inner_Area=" + inner_Area + ", sales_Contract_Number=" + sales_Contract_Number
				+ ", certificate_of_Title=" + certificate_of_Title + ", proPerty_for=" + proPerty_for
				+ ", the_Assessed_Value=" + the_Assessed_Value + ", original_Loan_Bank=" + original_Loan_Bank
				+ ", original_Loan_Amount=" + original_Loan_Amount + ", loan_Outstanding_Balance="
				+ loan_Outstanding_Balance + ", house_Account=" + house_Account + ", transaCtion_Price="
				+ transaCtion_Price + ", purchase_Deposit=" + purchase_Deposit + ", supervision_of_funds="
				+ supervision_of_funds + ", new_loan_Bank=" + new_loan_Bank + ", new_Loan_Approval_Amount="
				+ new_Loan_Approval_Amount + ", new_Loan_Bank_Account_Number=" + new_Loan_Bank_Account_Number
				+ ", note_DescriPtion=" + note_DescriPtion + ", state=" + state + ", ctime=" + ctime + "]";
	}
	
	
}
