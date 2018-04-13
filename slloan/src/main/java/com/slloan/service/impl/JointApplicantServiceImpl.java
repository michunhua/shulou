package com.slloan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.jointApplicantDao;
import com.slloan.entity.AddRole;
import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.Page;
import com.slloan.service.inter.JointApplicantService;

@Service(value = "Jointapplicantserviceimpl")
public class JointApplicantServiceImpl implements JointApplicantService {

	@Autowired
	private jointApplicantDao jointApplicantdao;

	@Override
	public boolean save(JointApplicant jointApplicant) {

		String name = jointApplicant.getName(); // 共同借款人姓名
		String phoneticize = jointApplicant.getPhoneticize(); // 拼音、英文姓名
		String id_type = jointApplicant.getId_type(); // 身份证件类型
		String id_number = jointApplicant.getId_number(); // 身份证件号码
		String relationship_with_borrower = jointApplicant.getRelationship_with_borrower(); // 与借款人关系
		String country_and_region = jointApplicant.getCountry_and_region(); // 国家及地区
		String sex = jointApplicant.getSex(); // 性别: 0男 1女
		String Local_domicile = jointApplicant.getLocal_domicile(); // 本地户籍
		String household_registration = jointApplicant.getHousehold_registration(); // 户籍所在地
		String marital_status = jointApplicant.getMarital_status(); // 婚姻情况0已婚，1未婚
		String housing_condition_now = jointApplicant.getHousing_condition_now(); // 0房改/继承
																					// 1按揭自置2无按揭自置
																					// 3与父母同住
																					// 4租借//
																					// 5公司提供
																					// 6其他
		String birthday = jointApplicant.getBirthday(); // 出生日期
		String home_address_now = jointApplicant.getHome_address_now(); // 现住房地址
		String home_phone = jointApplicant.getHome_phone(); // 住宅电话
		String mobile_phone = jointApplicant.getMobile_phone(); // 移动电话
		String email = jointApplicant.getEmail(); // E-mail
		String present_address_zip_code = jointApplicant.getPresent_address_zip_code(); // 现住址邮编
		String vocation = jointApplicant.getVocation(); // 职业
		String unit_industry = jointApplicant.getUnit_industry(); // 现单位所处行业
		String uni_name = jointApplicant.getUni_name(); // 现单位名称
		String unit_address = jointApplicant.getUnit_address(); // 现单位地址
		String enterprise_scale = jointApplicant.getEnterprise_scale(); // 就职企业规模
		double Revenue_in_the_previous_year = jointApplicant.getRevenue_in_the_previous_year(); // 上年营收
		String asset_scale = jointApplicant.getAsset_scale(); // 资产规模
		String unit_phone = jointApplicant.getUnit_phone(); // 单位电话
		String postCode = jointApplicant.getPostCode(); // 单位邮编
		String job_category = jointApplicant.getJob_category(); // 职位类别
		String seniority = jointApplicant.getSeniority(); // 现单位工龄
		String former_unit_name = jointApplicant.getFormer_unit_name(); // 前单位名称
		String former_seniority = jointApplicant.getFormer_seniority(); // 前单位工龄
		String source_of_income = jointApplicant.getSource_of_income(); // 收入来源
		double monthly_income = jointApplicant.getMonthly_income(); // 月收入
		double Income_from_investment = jointApplicant.getIncome_from_investment(); // 投资收益
		double Rent_income = jointApplicant.getRent_income(); // 租金收入
		double Other_income = jointApplicant.getOther_income(); // 其他收入
		String family_number = jointApplicant.getFamily_number(); // 供养人数
		double monthly_expenditure = jointApplicant.getMonthly_expenditure(); // 月支出
		String postal_address = jointApplicant.getPostal_address(); // 通讯地址
		String start = jointApplicant.getstart(); // 状态
													// 0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime = jointApplicant.getCtime(); // 创建时间

		JointApplicant joint = new JointApplicant(name, phoneticize, id_type, id_number, relationship_with_borrower,
				country_and_region, sex, Local_domicile, household_registration, marital_status, housing_condition_now,
				birthday, home_address_now, home_phone, mobile_phone, email, present_address_zip_code, vocation,
				unit_industry, uni_name, unit_address, enterprise_scale, Revenue_in_the_previous_year, asset_scale,
				unit_phone, postCode, job_category, seniority, former_unit_name, former_seniority, source_of_income,
				monthly_income, Income_from_investment, Rent_income, Other_income, family_number, monthly_expenditure,
				postal_address, start, ctime);
		return jointApplicantdao.save(joint);

	}

	@Override
	public boolean update(JointApplicant jointApplicant) {
		Integer id = jointApplicant.getId();
		String name = jointApplicant.getName(); // 共同借款人姓名
		String phoneticize = jointApplicant.getPhoneticize(); // 拼音、英文姓名
		String id_type = jointApplicant.getId_type(); // 身份证件类型
		String id_number = jointApplicant.getId_number(); // 身份证件号码
		String relationship_with_borrower = jointApplicant.getRelationship_with_borrower(); // 与借款人关系
		String country_and_region = jointApplicant.getCountry_and_region(); // 国家及地区
		String sex = jointApplicant.getSex(); // 性别: 0男 1女
		String Local_domicile = jointApplicant.getLocal_domicile(); // 本地户籍
		String household_registration = jointApplicant.getHousehold_registration(); // 户籍所在地
		String marital_status = jointApplicant.getMarital_status(); // 婚姻情况0已婚，1未婚
		String housing_condition_now = jointApplicant.getHousing_condition_now(); // 0房改/继承
																					// 1按揭自置2无按揭自置
																					// 3与父母同住
																					// 4租借//
																					// 5公司提供
																					// 6其他
		String birthday = jointApplicant.getBirthday(); // 出生日期
		String home_address_now = jointApplicant.getHome_address_now(); // 现住房地址
		String home_phone = jointApplicant.getHome_phone(); // 住宅电话
		String mobile_phone = jointApplicant.getMobile_phone(); // 移动电话
		String email = jointApplicant.getEmail(); // E-mail
		String present_address_zip_code = jointApplicant.getPresent_address_zip_code(); // 现住址邮编
		String vocation = jointApplicant.getVocation(); // 职业
		String unit_industry = jointApplicant.getUnit_industry(); // 现单位所处行业
		String uni_name = jointApplicant.getUni_name(); // 现单位名称
		String unit_address = jointApplicant.getUnit_address(); // 现单位地址
		String enterprise_scale = jointApplicant.getEnterprise_scale(); // 就职企业规模
		double Revenue_in_the_previous_year = jointApplicant.getRevenue_in_the_previous_year(); // 上年营收
		String asset_scale = jointApplicant.getAsset_scale(); // 资产规模
		String unit_phone = jointApplicant.getUnit_phone(); // 单位电话
		String postCode = jointApplicant.getPostCode(); // 单位邮编
		String job_category = jointApplicant.getJob_category(); // 职位类别
		String seniority = jointApplicant.getSeniority(); // 现单位工龄
		String former_unit_name = jointApplicant.getFormer_unit_name(); // 前单位名称
		String former_seniority = jointApplicant.getFormer_seniority(); // 前单位工龄
		String source_of_income = jointApplicant.getSource_of_income(); // 收入来源
		double monthly_income = jointApplicant.getMonthly_income(); // 月收入
		double Income_from_investment = jointApplicant.getIncome_from_investment(); // 投资收益
		double Rent_income = jointApplicant.getRent_income(); // 租金收入
		double Other_income = jointApplicant.getOther_income(); // 其他收入
		String family_number = jointApplicant.getFamily_number(); // 供养人数
		double monthly_expenditure = jointApplicant.getMonthly_expenditure(); // 月支出
		String postal_address = jointApplicant.getPostal_address(); // 通讯地址
		String start = jointApplicant.getstart(); // 状态
													// 0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime = jointApplicant.getCtime(); // 创建时间

		JointApplicant joint = new JointApplicant(id,name, phoneticize, id_type, id_number, relationship_with_borrower,
				country_and_region, sex, Local_domicile, household_registration, marital_status, housing_condition_now,
				birthday, home_address_now, home_phone, mobile_phone, email, present_address_zip_code, vocation,
				unit_industry, uni_name, unit_address, enterprise_scale, Revenue_in_the_previous_year, asset_scale,
				unit_phone, postCode, job_category, seniority, former_unit_name, former_seniority, source_of_income,
				monthly_income, Income_from_investment, Rent_income, Other_income, family_number, monthly_expenditure,
				postal_address, start, ctime);
		return jointApplicantdao.update(joint);
		
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JointApplicant findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JointApplicant> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<JointApplicant> getRolePage(int currentPage) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<JointApplicant> pageBean = new Page<JointApplicant>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;//每页显示数据
		pageBean.setPageSize(pageSize);
		int totalCount = jointApplicantdao.getCount();
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());//向上取整
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		//封装每页显示的数据
		List<JointApplicant> lists = jointApplicantdao.getUserByPage(map);
		pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int selectCount() {
		return 0;
	}

	@Override
	public JointApplicant SelectById(int reqid) {
		return jointApplicantdao.SelectById(reqid);
	}
	
	

}
