package com.slloan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 借款申请人个人资料
 * 
 * @author xue
 *
 */
public class PersonalProfile implements Serializable {
	private Integer id;

	private String product_Number;// 编号
	private String name;// 共同借款人姓名
	private String phoneticize;// 拼音、英文姓名
	private String id_type;// 身份证件类型
	private String Other_identity_types;// 输入身份证类型
	private String id_number;// 身份证件号码
	private String country_and_region;// 国家及地区
	private String other_Countries;// 其他国家
	private String sex;// 性别: 0男 1女
	private String Local_domicile;// 本地户籍
	private String household_registration;// 其他户籍所在地
	private String marital_status;// 婚姻情况0已婚，1未婚
	private String housing_condition_now;// 0房改/继承
	private String otherCensus;// 其他
	private String birthday;// 出生日期
	private String home_address_now;// 现住房地址
	private String home_phone;// 住宅电话
	private String mobile_phone;// 移动电话
	private String email;// E-mail
	private String present_address_zip_code;// 现住址邮编
	private String vocation;// 职业
	private String unit_industry;// 现单位所处行业
	private String uni_name;// 现单位名称
	private String unit_address;// 现单位地址
	private String enterprise_scale;// 企业人数
	private double Revenue_previous_year;// 上年营收
	private String asset_scale;// 资产规模
	private String unit_phone;// 单位电话
	private String postCode;// 单位邮编
	private String job_category;// 职位类别
	private String seniority;// 现单位工龄
	private String former_unit_name;// 前单位名称
	private String former_seniority;// 前单位工龄
	private String source_of_income;// 收入来源
	private double monthly_income;// 月收入
	private double Income_from_investment;// 投资收益
	private double supportPeople;// 租金收入
	private double Other_income;// 其他收入
	private String family_number;// 供养人数
	private double monthly_expenditure;// 月支出
	private String postal_address;// 通讯地址
	private String state;// 状态;//
							// 0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
	private String ctime;// 日期

	private List<CircuLationRecord> notes;

	private List<ApplyForLoanInformation> applyfor;
	
	private PersonalProfile(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProduct_Number() {
		return product_Number;
	}

	public void setProduct_Number(String product_Number) {
		this.product_Number = product_Number;
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

	public String getOther_identity_types() {
		return Other_identity_types;
	}

	public void setOther_identity_types(String other_identity_types) {
		Other_identity_types = other_identity_types;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getCountry_and_region() {
		return country_and_region;
	}

	public void setCountry_and_region(String country_and_region) {
		this.country_and_region = country_and_region;
	}

	public String getOther_Countries() {
		return other_Countries;
	}

	public void setOther_Countries(String other_Countries) {
		this.other_Countries = other_Countries;
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

	public String getOtherCensus() {
		return otherCensus;
	}

	public void setOtherCensus(String otherCensus) {
		this.otherCensus = otherCensus;
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

	public double getRevenue_previous_year() {
		return Revenue_previous_year;
	}

	public void setRevenue_previous_year(double revenue_previous_year) {
		Revenue_previous_year = revenue_previous_year;
	}

	public String getAsset_scale() {
		return asset_scale;
	}

	public void setAsset_scale(String asset_scale) {
		this.asset_scale = asset_scale;
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

	public double getSupportPeople() {
		return supportPeople;
	}

	public void setSupportPeople(double supportPeople) {
		this.supportPeople = supportPeople;
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

	public List<CircuLationRecord> getNotes() {
		return notes;
	}

	public void setNotes(List<CircuLationRecord> notes) {
		this.notes = notes;
	}

	public List<ApplyForLoanInformation> getApplyfor() {
		return applyfor;
	}

	public void setApplyfor(List<ApplyForLoanInformation> applyfor) {
		this.applyfor = applyfor;
	}
	
	

	public PersonalProfile(Integer id, String product_Number, String name, String phoneticize, String id_type,
			String other_identity_types, String id_number, String country_and_region, String other_Countries,
			String sex, String local_domicile, String household_registration, String marital_status,
			String housing_condition_now, String otherCensus, String birthday, String home_address_now,
			String home_phone, String mobile_phone, String email, String present_address_zip_code, String vocation,
			String unit_industry, String uni_name, String unit_address, String enterprise_scale,
			double revenue_previous_year, String asset_scale, String unit_phone, String postCode, String job_category,
			String seniority, String former_unit_name, String former_seniority, String source_of_income,
			double monthly_income, double income_from_investment, double supportPeople, double other_income,
			String family_number, double monthly_expenditure, String postal_address, String state, String ctime,
			List<CircuLationRecord> notes, List<ApplyForLoanInformation> applyfor) {
		super();
		this.id = id;
		this.product_Number = product_Number;
		this.name = name;
		this.phoneticize = phoneticize;
		this.id_type = id_type;
		Other_identity_types = other_identity_types;
		this.id_number = id_number;
		this.country_and_region = country_and_region;
		this.other_Countries = other_Countries;
		this.sex = sex;
		Local_domicile = local_domicile;
		this.household_registration = household_registration;
		this.marital_status = marital_status;
		this.housing_condition_now = housing_condition_now;
		this.otherCensus = otherCensus;
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
		Revenue_previous_year = revenue_previous_year;
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
		this.supportPeople = supportPeople;
		Other_income = other_income;
		this.family_number = family_number;
		this.monthly_expenditure = monthly_expenditure;
		this.postal_address = postal_address;
		this.state = state;
		this.ctime = ctime;
		this.notes = notes;
		this.applyfor = applyfor;
	}

	public PersonalProfile(String product_Number, String name, String phoneticize, String id_type,
			String other_identity_types, String id_number, String country_and_region, String other_Countries,
			String sex, String local_domicile, String household_registration, String marital_status,
			String housing_condition_now, String otherCensus, String birthday, String home_address_now,
			String home_phone, String mobile_phone, String email, String present_address_zip_code, String vocation,
			String unit_industry, String uni_name, String unit_address, String enterprise_scale,
			double revenue_previous_year, String asset_scale, String unit_phone, String postCode, String job_category,
			String seniority, String former_unit_name, String former_seniority, String source_of_income,
			double monthly_income, double income_from_investment, double supportPeople, double other_income,
			String family_number, double monthly_expenditure, String postal_address, String state, String ctime) {
		super();
		this.product_Number = product_Number;
		this.name = name;
		this.phoneticize = phoneticize;
		this.id_type = id_type;
		Other_identity_types = other_identity_types;
		this.id_number = id_number;
		this.country_and_region = country_and_region;
		this.other_Countries = other_Countries;
		this.sex = sex;
		Local_domicile = local_domicile;
		this.household_registration = household_registration;
		this.marital_status = marital_status;
		this.housing_condition_now = housing_condition_now;
		this.otherCensus = otherCensus;
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
		Revenue_previous_year = revenue_previous_year;
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
		this.supportPeople = supportPeople;
		Other_income = other_income;
		this.family_number = family_number;
		this.monthly_expenditure = monthly_expenditure;
		this.postal_address = postal_address;
		this.state = state;
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "PersonalProfile [id=" + id + ", product_Number=" + product_Number + ", name=" + name + ", phoneticize="
				+ phoneticize + ", id_type=" + id_type + ", Other_identity_types=" + Other_identity_types
				+ ", id_number=" + id_number + ", country_and_region=" + country_and_region + ", other_Countries="
				+ other_Countries + ", sex=" + sex + ", Local_domicile=" + Local_domicile + ", household_registration="
				+ household_registration + ", marital_status=" + marital_status + ", housing_condition_now="
				+ housing_condition_now + ", otherCensus=" + otherCensus + ", birthday=" + birthday
				+ ", home_address_now=" + home_address_now + ", home_phone=" + home_phone + ", mobile_phone="
				+ mobile_phone + ", email=" + email + ", present_address_zip_code=" + present_address_zip_code
				+ ", vocation=" + vocation + ", unit_industry=" + unit_industry + ", uni_name=" + uni_name
				+ ", unit_address=" + unit_address + ", enterprise_scale=" + enterprise_scale
				+ ", Revenue_previous_year=" + Revenue_previous_year + ", asset_scale=" + asset_scale + ", unit_phone="
				+ unit_phone + ", postCode=" + postCode + ", job_category=" + job_category + ", seniority=" + seniority
				+ ", former_unit_name=" + former_unit_name + ", former_seniority=" + former_seniority
				+ ", source_of_income=" + source_of_income + ", monthly_income=" + monthly_income
				+ ", Income_from_investment=" + Income_from_investment + ", supportPeople=" + supportPeople
				+ ", Other_income=" + Other_income + ", family_number=" + family_number + ", monthly_expenditure="
				+ monthly_expenditure + ", postal_address=" + postal_address + ", state=" + state + ", ctime=" + ctime
				+ ", notes=" + notes + ", applyfor=" + applyfor + "]";
	}



	
}
