package com.slloan.entity;

import java.io.Serializable;

/**
 * 借款申请人个人资料
 * 
 * @author xue
 *
 */
public class PersonalProfile implements Serializable {
	private Integer id;
	private String name; // '共同借款人姓名',
	private String phoneticize; // '拼音、英文姓名',
	private String id_type; // '身份证件类型',
	private String id_number;// '身份证件号码',
	private String relationship_with_borrower;// '与借款人关系',
	private String country_and_region; // '国家及地区',
	private String sex; // '性别: 0男 1女',
	private String Local_domicile; // '本地户籍',
	private String household_registration; // '户籍所在地',
	private String marital_status; // '婚姻情况,0已婚，1未婚',
	private String housing_condition_now; // '0房改/继承 1按揭自置2无按揭自置 3与父母同住 4租借// 5公司提供 6其他',
	private String birthday; // '出生日期',
	private String home_address_now; // '现住房地址',
	private String home_phone; // '住宅电话',
	private String mobile_phone; // '移动电话',
	private String email; // 'E-mail',
	private String present_address_zip_code; // '现住址邮编',
	private String vocation; // '职业',
	private String unit_industry; // '现单位所处行业',
	private String uni_name; // '现单位名称',
	private String unit_address; // '现单位地址',
	private String enterprise_scale; // '就职企业规模',
	private double Revenue_in_the_previous_year;// '上年营收'
	private String asset_scale; // '资产规模'
	private String unit_phone; // '单位电话',
	private String postCode; // '单位邮编',
	private String job_category; // '职位类别',
	private String seniority; // '现单位工龄',
	private String former_unit_name; // '前单位名称',
	private String former_seniority; // '前单位工龄',
	private String source_of_income; // '收入来源',
	private double monthly_income; // '月收入',
	private double Income_from_investment;// '投资收益',
	private double Rent_income; // '租金收入',
	private double Other_income;; // '其他收入',
	private String family_number; // '供养人数',
	private double monthly_expenditure; // '月支出',
	private String postal_address; // '通讯地址',
	private String start; // '状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清',
	private String ctime; // '创建时间',
	
	
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
	public String getPhoneticize() {
		return phoneticize;
	}
	public void setPhoneticize(String phoneticize) {
		this.phoneticize = phoneticize;
	}
	public String getId_type() {
		return id_type;
	}
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	public String getId_number() {
		return id_number;
	}
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	public String getRelationship_with_borrower() {
		return relationship_with_borrower;
	}
	public void setRelationship_with_borrower(String relationship_with_borrower) {
		this.relationship_with_borrower = relationship_with_borrower;
	}
	public String getCountry_and_region() {
		return country_and_region;
	}
	public void setCountry_and_region(String country_and_region) {
		this.country_and_region = country_and_region;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLocal_domicile() {
		return Local_domicile;
	}
	public void setLocal_domicile(String local_domicile) {
		Local_domicile = local_domicile;
	}
	public String getHousehold_registration() {
		return household_registration;
	}
	public void setHousehold_registration(String household_registration) {
		this.household_registration = household_registration;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getHousing_condition_now() {
		return housing_condition_now;
	}
	public void setHousing_condition_now(String housing_condition_now) {
		this.housing_condition_now = housing_condition_now;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getHome_address_now() {
		return home_address_now;
	}
	public void setHome_address_now(String home_address_now) {
		this.home_address_now = home_address_now;
	}
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPresent_address_zip_code() {
		return present_address_zip_code;
	}
	public void setPresent_address_zip_code(String present_address_zip_code) {
		this.present_address_zip_code = present_address_zip_code;
	}
	public String getVocation() {
		return vocation;
	}
	public void setVocation(String vocation) {
		this.vocation = vocation;
	}
	public String getUnit_industry() {
		return unit_industry;
	}
	public void setUnit_industry(String unit_industry) {
		this.unit_industry = unit_industry;
	}
	public String getUni_name() {
		return uni_name;
	}
	public void setUni_name(String uni_name) {
		this.uni_name = uni_name;
	}
	public String getUnit_address() {
		return unit_address;
	}
	public void setUnit_address(String unit_address) {
		this.unit_address = unit_address;
	}
	public String getEnterprise_scale() {
		return enterprise_scale;
	}
	public void setEnterprise_scale(String enterprise_scale) {
		this.enterprise_scale = enterprise_scale;
	}
	public double getRevenue_in_the_previous_year() {
		return Revenue_in_the_previous_year;
	}
	public void setRevenue_in_the_previous_year(double revenue_in_the_previous_year) {
		Revenue_in_the_previous_year = revenue_in_the_previous_year;
	}
	public String getAsset_scale() {
		return asset_scale;
	}
	public void setAsset_scale(String asset_scale) {
		asset_scale = asset_scale;
	}
	public String getUnit_phone() {
		return unit_phone;
	}
	public void setUnit_phone(String unit_phone) {
		this.unit_phone = unit_phone;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getJob_category() {
		return job_category;
	}
	public void setJob_category(String job_category) {
		this.job_category = job_category;
	}
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}
	public String getFormer_unit_name() {
		return former_unit_name;
	}
	public void setFormer_unit_name(String former_unit_name) {
		this.former_unit_name = former_unit_name;
	}
	public String getFormer_seniority() {
		return former_seniority;
	}
	public void setFormer_seniority(String former_seniority) {
		this.former_seniority = former_seniority;
	}
	public String getSource_of_income() {
		return source_of_income;
	}
	public void setSource_of_income(String source_of_income) {
		this.source_of_income = source_of_income;
	}
	public double getMonthly_income() {
		return monthly_income;
	}
	public void setMonthly_income(double monthly_income) {
		this.monthly_income = monthly_income;
	}
	public double getIncome_from_investment() {
		return Income_from_investment;
	}
	public void setIncome_from_investment(double income_from_investment) {
		Income_from_investment = income_from_investment;
	}
	public double getRent_income() {
		return Rent_income;
	}
	public void setRent_income(double rent_income) {
		Rent_income = rent_income;
	}
	public double getOther_income() {
		return Other_income;
	}
	public void setOther_income(double other_income) {
		Other_income = other_income;
	}
	public String getFamily_number() {
		return family_number;
	}
	public void setFamily_number(String family_number) {
		this.family_number = family_number;
	}
	public double getMonthly_expenditure() {
		return monthly_expenditure;
	}
	public void setMonthly_expenditure(double monthly_expenditure) {
		this.monthly_expenditure = monthly_expenditure;
	}
	public String getPostal_address() {
		return postal_address;
	}
	public void setPostal_address(String postal_address) {
		this.postal_address = postal_address;
	}
	public String getstart() {
		return start;
	}
	public void setstart(String start) {
		this.start = start;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
	
	public PersonalProfile(){}
	@Override
	public String toString() {
		return "PersonalProfile [id=" + id + ", name=" + name + ", phoneticize=" + phoneticize + ", id_type=" + id_type
				+ ", id_number=" + id_number + ", relationship_with_borrower=" + relationship_with_borrower
				+ ", country_and_region=" + country_and_region + ", sex=" + sex + ", Local_domicile=" + Local_domicile
				+ ", household_registration=" + household_registration + ", marital_status=" + marital_status
				+ ", housing_condition_now=" + housing_condition_now + ", birthday=" + birthday + ", home_address_now="
				+ home_address_now + ", home_phone=" + home_phone + ", mobile_phone=" + mobile_phone + ", email="
				+ email + ", present_address_zip_code=" + present_address_zip_code + ", vocation=" + vocation
				+ ", unit_industry=" + unit_industry + ", uni_name=" + uni_name + ", unit_address=" + unit_address
				+ ", enterprise_scale=" + enterprise_scale + ", Revenue_in_the_previous_year="
				+ Revenue_in_the_previous_year + ", Asset_scale=" + asset_scale + ", unit_phone=" + unit_phone
				+ ", postCode=" + postCode + ", job_category=" + job_category + ", seniority=" + seniority
				+ ", former_unit_name=" + former_unit_name + ", former_seniority=" + former_seniority
				+ ", source_of_income=" + source_of_income + ", monthly_income=" + monthly_income
				+ ", Income_from_investment=" + Income_from_investment + ", Rent_income=" + Rent_income
				+ ", Other_income=" + Other_income + ", family_number=" + family_number + ", monthly_expenditure="
				+ monthly_expenditure + ", postal_address=" + postal_address + ", start=" + start + ", ctime=" + ctime
				+ "]";
	}
	public PersonalProfile(String name, String phoneticize, String id_type, String id_number,
			String relationship_with_borrower, String country_and_region, String sex, String local_domicile,
			String household_registration, String marital_status, String housing_condition_now, String birthday,
			String home_address_now, String home_phone, String mobile_phone, String email,
			String present_address_zip_code, String vocation, String unit_industry, String uni_name,
			String unit_address, String enterprise_scale, double revenue_in_the_previous_year, String asset_scale,
			String unit_phone, String postCode, String job_category, String seniority, String former_unit_name,
			String former_seniority, String source_of_income, double monthly_income, double income_from_investment,
			double rent_income, double other_income, String family_number, double monthly_expenditure,
			String postal_address, String start, String ctime) {
		super();
		this.name = name;
		this.phoneticize = phoneticize;
		this.id_type = id_type;
		this.id_number = id_number;
		this.relationship_with_borrower = relationship_with_borrower;
		this.country_and_region = country_and_region;
		this.sex = sex;
		Local_domicile = local_domicile;
		this.household_registration = household_registration;
		this.marital_status = marital_status;
		this.housing_condition_now = housing_condition_now;
		this.birthday = birthday;
		this.home_address_now = home_address_now;
		this.home_phone = home_phone;
		this.mobile_phone = mobile_phone;
		this.email = email;
		this.present_address_zip_code = present_address_zip_code;
		this.vocation = vocation;
		this.unit_industry = unit_industry;
		this.uni_name = uni_name;
		this.unit_address = unit_address;
		this.enterprise_scale = enterprise_scale;
		Revenue_in_the_previous_year = revenue_in_the_previous_year;
		this.asset_scale = asset_scale;
		this.unit_phone = unit_phone;
		this.postCode = postCode;
		this.job_category = job_category;
		this.seniority = seniority;
		this.former_unit_name = former_unit_name;
		this.former_seniority = former_seniority;
		this.source_of_income = source_of_income;
		this.monthly_income = monthly_income;
		Income_from_investment = income_from_investment;
		Rent_income = rent_income;
		Other_income = other_income;
		this.family_number = family_number;
		this.monthly_expenditure = monthly_expenditure;
		this.postal_address = postal_address;
		this.start = start;
		this.ctime = ctime;
	}
	public PersonalProfile(Integer id, String name, String phoneticize, String id_type, String id_number,
			String relationship_with_borrower, String country_and_region, String sex, String local_domicile,
			String household_registration, String marital_status, String housing_condition_now, String birthday,
			String home_address_now, String home_phone, String mobile_phone, String email,
			String present_address_zip_code, String vocation, String unit_industry, String uni_name,
			String unit_address, String enterprise_scale, double revenue_in_the_previous_year, String asset_scale,
			String unit_phone, String postCode, String job_category, String seniority, String former_unit_name,
			String former_seniority, String source_of_income, double monthly_income, double income_from_investment,
			double rent_income, double other_income, String family_number, double monthly_expenditure,
			String postal_address, String start, String ctime) {
		super();
		this.id = id;
		this.name = name;
		this.phoneticize = phoneticize;
		this.id_type = id_type;
		this.id_number = id_number;
		this.relationship_with_borrower = relationship_with_borrower;
		this.country_and_region = country_and_region;
		this.sex = sex;
		Local_domicile = local_domicile;
		this.household_registration = household_registration;
		this.marital_status = marital_status;
		this.housing_condition_now = housing_condition_now;
		this.birthday = birthday;
		this.home_address_now = home_address_now;
		this.home_phone = home_phone;
		this.mobile_phone = mobile_phone;
		this.email = email;
		this.present_address_zip_code = present_address_zip_code;
		this.vocation = vocation;
		this.unit_industry = unit_industry;
		this.uni_name = uni_name;
		this.unit_address = unit_address;
		this.enterprise_scale = enterprise_scale;
		Revenue_in_the_previous_year = revenue_in_the_previous_year;
		this.asset_scale = asset_scale;
		this.unit_phone = unit_phone;
		this.postCode = postCode;
		this.job_category = job_category;
		this.seniority = seniority;
		this.former_unit_name = former_unit_name;
		this.former_seniority = former_seniority;
		this.source_of_income = source_of_income;
		this.monthly_income = monthly_income;
		Income_from_investment = income_from_investment;
		Rent_income = rent_income;
		Other_income = other_income;
		this.family_number = family_number;
		this.monthly_expenditure = monthly_expenditure;
		this.postal_address = postal_address;
		this.start = start;
		this.ctime = ctime;
	}

	
	
	
	
}
