package com.slloan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.coborrowerSpouseDao;
import com.slloan.dao.personalProfileDao;
import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.UserLogin;
import com.slloan.service.inter.PersonalProfileService;

@Service(value = "personalprofileserviceimpl")
public class PersonalProfileServiceImpl implements PersonalProfileService {

	@Autowired
	private personalProfileDao personalProfiledao;

	@Override
	public boolean save(PersonalProfile personalProfile) {
		String product_Number = personalProfile.getProduct_Number();
		String name = personalProfile.getName(); // 共同借款人姓名
		String phoneticize = personalProfile.getPhoneticize(); // 拼音、英文姓名
		String id_type = personalProfile.getId_type(); // 身份证件类型
		String id_number = personalProfile.getId_number(); // 身份证件号码
		String relationship_with_borrower = personalProfile.getRelationship_with_borrower(); // 与借款人关系
		String country_and_region = personalProfile.getCountry_and_region(); // 国家及地区
		String sex = personalProfile.getSex(); // 性别: 0男 1女
		String Local_domicile = personalProfile.getLocal_domicile(); // 本地户籍
		String household_registration = personalProfile.getHousehold_registration(); // 户籍所在地
		String marital_status = personalProfile.getMarital_status(); // 婚姻情况0已婚，1未婚
		String housing_condition_now = personalProfile.getHousing_condition_now(); // 0房改/继承
																					// 1按揭自置2无按揭自置
																					// 3与父母同住
																					// 4租借//
																					// 5公司提供
																					// 6其他
		String birthday = personalProfile.getBirthday(); // 出生日期
		String home_address_now = personalProfile.getHome_address_now(); // 现住房地址
		String home_phone = personalProfile.getHome_phone(); // 住宅电话
		String mobile_phone = personalProfile.getMobile_phone(); // 移动电话
		String email = personalProfile.getEmail(); // E-mail
		String present_address_zip_code = personalProfile.getPresent_address_zip_code(); // 现住址邮编
		String vocation = personalProfile.getVocation(); // 职业
		String unit_industry = personalProfile.getUnit_industry(); // 现单位所处行业
		String uni_name = personalProfile.getUni_name(); // 现单位名称
		String unit_address = personalProfile.getUnit_address(); // 现单位地址
		String enterprise_scale = personalProfile.getEnterprise_scale(); // 就职企业规模
		double Revenue_in_the_previous_year = personalProfile.getRevenue_in_the_previous_year(); // 上年营收
		String asset_scale = personalProfile.getAsset_scale(); // 资产规模
		String unit_phone = personalProfile.getUnit_phone(); // 单位电话
		String postCode = personalProfile.getPostCode(); // 单位邮编
		String job_category = personalProfile.getJob_category(); // 职位类别
		String seniority = personalProfile.getSeniority(); // 现单位工龄
		String former_unit_name = personalProfile.getFormer_unit_name(); // 前单位名称
		String former_seniority = personalProfile.getFormer_seniority(); // 前单位工龄
		String source_of_income = personalProfile.getSource_of_income(); // 收入来源
		double monthly_income = personalProfile.getMonthly_income(); // 月收入
		double Income_from_investment = personalProfile.getIncome_from_investment(); // 投资收益
		double Rent_income = personalProfile.getRent_income(); // 租金收入
		double Other_income = personalProfile.getOther_income(); // 其他收入
		String family_number = personalProfile.getFamily_number(); // 供养人数
		double monthly_expenditure = personalProfile.getMonthly_expenditure(); // 月支出
		String postal_address = personalProfile.getPostal_address(); // 通讯地址
		String state = personalProfile.getState(); // 状态
													// 0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime = personalProfile.getCtime(); // 创建时间

		PersonalProfile joint = new PersonalProfile(product_Number,name, phoneticize, id_type, id_number, relationship_with_borrower,
				country_and_region, sex, Local_domicile, household_registration, marital_status, housing_condition_now,
				birthday, home_address_now, home_phone, mobile_phone, email, present_address_zip_code, vocation,
				unit_industry, uni_name, unit_address, enterprise_scale, Revenue_in_the_previous_year, asset_scale,
				unit_phone, postCode, job_category, seniority, former_unit_name, former_seniority, source_of_income,
				monthly_income, Income_from_investment, Rent_income, Other_income, family_number, monthly_expenditure,
				postal_address, state, ctime);
		return personalProfiledao.save(personalProfile);
	}

	@Override
	public boolean perupdate(PersonalProfile personalProfile) {

		return personalProfiledao.perupdate(personalProfile);

	}

	@Override
	public PersonalProfile SelectById(int id) {

		return personalProfiledao.SelectById(id);
	}






}
