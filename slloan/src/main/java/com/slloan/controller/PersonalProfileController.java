package com.slloan.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.PersonalProfile;
import com.slloan.service.inter.JointApplicantService;
import com.slloan.service.inter.PersonalProfileService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;



/**
 * 借款申请人资料
 * @author Administrator
 *
 */
@Controller(value = "personalprofilecontroller")
@RequestMapping("/loan")
public class PersonalProfileController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private PersonalProfileService personalprofileservice;

	@ResponseBody
	@RequestMapping("/loanApplypersonaldata")
	public Json save(HttpServletRequest req) {

		String role_constant = req.getParameter("data"); // 例如按揭员名
		JSONObject obj = new JSONObject().fromObject(role_constant);

		 String name=obj.getString("cname");  // 共同借款人姓名
		 String phoneticize=obj.getString("ename");  // 拼音、英文姓名
		 String id_type=obj.getString("paperwork");  // 身份证件类型
		 String id_number=obj.getString("paperNumb"); // 身份证件号码
		 String relationship_with_borrower=obj.getString("country"); // 与借款人关系
		 String country_and_region=obj.getString("gender");  // 国家及地区
		 String sex=obj.getString("gender");  // 性别: 0男 1女
		 String Local_domicile=obj.getString("census");  // 本地户籍
		 String household_registration=obj.getString("census");  // 户籍所在地
		 String marital_status=obj.getString("marriage");  // 婚姻情况0已婚，1未婚
		 String housing_condition_now=obj.getString("housing");  // 0房改/继承 1按揭自置2无按揭自置 3与父母同住 4租借// 5公司提供 6其他
		 String birthday=obj.getString("birthday");  // 出生日期
		 String home_address_now=obj.getString("currentAddress");  // 现住房地址
		 String home_phone=obj.getString("residencePhone");  // 住宅电话
		 String mobile_phone=obj.getString("mobilePhone");  // 移动电话
		 String email=obj.getString("email");  // E-mail
		 String present_address_zip_code=obj.getString("code");  // 现住址邮编
		 String vocation=obj.getString("career");  // 职业
		 String unit_industry=obj.getString("unit");  // 现单位所处行业
		 String uni_name=obj.getString("unitName");  // 现单位名称
		 String unit_address=obj.getString("unitAddress");  // 现单位地址
		 String enterprise_scale=obj.getString("assetSize");  // 就职企业规模
		 String unitPhone=obj.getString("lastyearIncome"); //上年营收
		 String asset_scale=obj.getString("unitCode");  // 资产规模
		 String unit_phone=obj.getString("unitPhone");  // 单位电话
		 String postCode=obj.getString("unitCode");  // 单位邮编
		 String job_category=obj.getString("jobsType");  // 职位类别
		 String seniority=obj.getString("unitTime");  // 现单位工龄
		 String former_unit_name=obj.getString("lastunitName");  // 前单位名称
		 String former_seniority=obj.getString("lastunitTime");  // 前单位工龄
		 String source_of_income=obj.getString("incomeSource");  // 收入来源
		 String rent = obj.getString("salary");  // 月收入
		 String added =obj.getString("investment");// 投资收益
		 String supportPeople =obj.getString("rent");// 租金收入
		 String expenses =obj.getString("added");// 其他收入
		 String family_number=obj.getString("supportPeople");  // 供养人数
//		 double monthly_expenditure = Double.valueOf(obj.getString("a"));// 月支出
		 String mon = obj.getString("expenses");// 月支出
		 String postal_address=obj.getString("communication");  // 通讯地址
		 String start=obj.getString("start");  // 状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		 String ctime=obj.getString("ctime");//日期

		 Double Revenue_in_the_previous_year = 0.0;
		 if(unitPhone.length()>0){
			 Revenue_in_the_previous_year = Double.parseDouble(unitPhone);
		 }
		 Double monthly_income = 0.0;
		 if (rent.length()>0) {
			 monthly_income = Double.parseDouble(rent);
		}
		 Double Income_from_investment=0.0;
		 if (added.length()>0) {
			 monthly_income = Double.parseDouble(added);
		}
		 Double Rent_income=0.0;
		 if (supportPeople.length()>0) {
			monthly_income = Double.parseDouble(supportPeople);
		} 
		Double Other_income = 0.0;
		if (expenses.length() > 0) {
			monthly_income = Double.parseDouble(expenses);
		} 
		Double monthly_expenditure = 0.0;
		if (mon.length() > 0) {
			monthly_income = Double.parseDouble(mon);
		} 

		PersonalProfile person = new PersonalProfile(name, phoneticize, id_type, id_number, relationship_with_borrower,
				country_and_region, sex, Local_domicile, household_registration, marital_status, housing_condition_now,
				birthday, home_address_now, home_phone, mobile_phone, email, present_address_zip_code, vocation,
				unit_industry, uni_name, unit_address, enterprise_scale, Revenue_in_the_previous_year, asset_scale,
				unit_phone, postCode, job_category, seniority, former_unit_name, former_seniority, source_of_income,
				monthly_income, Income_from_investment, Rent_income, Other_income, family_number, monthly_expenditure,
				postal_address, start, ctime);
		boolean pe = personalprofileservice.save(person);// 插入角色

		if (pe == true) {
			logger.info("数据插入成功!");
			return new Json(true, "success", pe);
		} else {
			logger.info("数据插入失败!");
			return new Json(false, "fail", pe);
		}

	}

	@RequestMapping(value = "/personalp", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req) {
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		PersonalProfile isResult = personalprofileservice.SelectById(id);
		if (isResult != null) {
			return new Json(true, "success", isResult);
		} else
			return new Json(false, "fail", isResult);
	}

	/**
	 * 借款申请人个人资料
	 * 
	 * @param req
	 * @param contactsparam
	 * @return
	 */
	@RequestMapping(value = "/perupdate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateadd(HttpServletRequest req) {

		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		 String name=json.getString("cname");  // 共同借款人姓名
		 String phoneticize=json.getString("ename");  // 拼音、英文姓名
		 String id_type=json.getString("paperwork");  // 身份证件类型
		 String id_number=json.getString("paperNumb"); // 身份证件号码
		 String relationship_with_borrower=json.getString("country"); // 与借款人关系
		 String country_and_region=json.getString("gender");  // 国家及地区
		 String sex=json.getString("gender");  // 性别: 0男 1女
		 String Local_domicile=json.getString("census");  // 本地户籍
		 String household_registration=json.getString("census");  // 户籍所在地
		 String marital_status=json.getString("marriage");  // 婚姻情况0已婚，1未婚
		 String housing_condition_now=json.getString("housing");  // 0房改/继承 1按揭自置2无按揭自置 3与父母同住 4租借// 5公司提供 6其他
		 String birthday=json.getString("birthday");  // 出生日期
		 String home_address_now=json.getString("currentAddress");  // 现住房地址
		 String home_phone=json.getString("residencePhone");  // 住宅电话
		 String mobile_phone=json.getString("mobilePhone");  // 移动电话
		 String email=json.getString("email");  // E-mail
		 String present_address_zip_code=json.getString("code");  // 现住址邮编
		 String vocation=json.getString("career");  // 职业
		 String unit_industry=json.getString("unit");  // 现单位所处行业
		 String uni_name=json.getString("unitName");  // 现单位名称
		 String unit_address=json.getString("unitAddress");  // 现单位地址
		 String enterprise_scale=json.getString("assetSize");  // 就职企业规模
		 String unitPhone=json.getString("lastyearIncome"); //上年营收
		 String asset_scale=json.getString("unitCode");  // 资产规模
		 String unit_phone=json.getString("unitPhone");  // 单位电话
		 String postCode=json.getString("unitCode");  // 单位邮编
		 String job_category=json.getString("jobsType");  // 职位类别
		 String seniority=json.getString("unitTime");  // 现单位工龄
		 String former_unit_name=json.getString("lastunitName");  // 前单位名称
		 String former_seniority=json.getString("lastunitTime");  // 前单位工龄
		 String source_of_income=json.getString("incomeSource");  // 收入来源
		 String rent = json.getString("salary");  // 月收入
		 String added =json.getString("investment");// 投资收益
		 String supportPeople =json.getString("rent");// 租金收入
		 String expenses =json.getString("added");// 其他收入
		 String family_number=json.getString("supportPeople");  // 供养人数
//		 double monthly_expenditure = Double.valueOf(json.getString("a"));// 月支出
		 String mon = json.getString("expenses");// 月支出
		 String postal_address=json.getString("communication");  // 通讯地址
		 String start=json.getString("start");  // 状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		 String ctime=json.getString("ctime");//日期
	    
		Double Revenue_in_the_previous_year = 0.0;
		if (unit_phone.length() > 0) {
			Revenue_in_the_previous_year = Double.parseDouble(unit_phone);
		}
		Double monthly_income = 0.0;
		if (rent.length() > 0) {
			monthly_income = Double.parseDouble(rent);
		}
		Double Income_from_investment = 0.0;
		if (added.length() > 0) {
			monthly_income = Double.parseDouble(added);
		}
		Double Rent_income = 0.0;
		if (supportPeople.length() > 0) {
			monthly_income = Double.parseDouble(supportPeople);
		}
		Double Other_income = 0.0;
		if (expenses.length() > 0) {
			monthly_income = Double.parseDouble(expenses);
		}
		Double monthly_expenditure = 0.0;
		if (mon.length() > 0) {
			monthly_income = Double.parseDouble(mon);
		}

		PersonalProfile jointss = new PersonalProfile(id, name, phoneticize, id_type, id_number,
				relationship_with_borrower, country_and_region, sex, Local_domicile, household_registration,
				marital_status, housing_condition_now, birthday, home_address_now, home_phone, mobile_phone, email,
				present_address_zip_code, vocation, unit_industry, uni_name, unit_address, enterprise_scale,
				Revenue_in_the_previous_year, asset_scale, unit_phone, postCode, job_category, seniority,
				former_unit_name, former_seniority, source_of_income, monthly_income, Income_from_investment,
				Rent_income, Other_income, family_number, monthly_expenditure, postal_address, start, ctime);
		boolean isResult = personalprofileservice.perupdate(jointss);
		if (isResult == true) {
			return new Json(true, "success", isResult);
		} else {
			return new Json(false, "fail", isResult);
		}
	}

	/**
	 * 申请人资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanjoin")
	public String loanjoin() {
		System.out.println("--------------------------");
		return "loan/loanerInfo";
	}

	/**
	 * 贷款初审借款人资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanjoins")
	public String loanjoins() {
		System.out.println("--------------------------");
		return "loanfirst/loanerInfo";
	}

	/**
	 * 贷款终审申请人资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanjoinss")
	public String loanjoinss() {
		System.out.println("--------------------------");
		return "loanfinal/loanerInfo";
	}
}
