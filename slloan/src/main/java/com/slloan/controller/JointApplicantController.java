package com.slloan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.JointApplicant;
import com.slloan.service.inter.CoborrowerSpouseService;
import com.slloan.service.inter.JointApplicantService;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

/**
 * 共同申请人个人资料
 * 
 * @author Administrator
 *
 */
@Controller(value="jointapplicantcontroll")
@RequestMapping("loan")
public class JointApplicantController {

	@Autowired
	private JointApplicantService jointapplicant;

	@ResponseBody
	@RequestMapping(value="/commonApplydata",method=(RequestMethod.GET),produces="application/json;charset=utf-8")
	public String save(HttpServletRequest req) {

		String role_constant = req.getParameter("data"); // 例如按揭员名
		JSONObject obj = new JSONObject().fromObject(role_constant);

		String name=obj.getString("cname");  // 共同借款人姓名
		 String phoneticize=obj.getString("ename");  // 拼音、英文姓名
		 String id_type=obj.getString("paperwork");  // 身份证件类型
		 String id_number=obj.getString("paperNumb"); // 身份证件号码
		 String relationship_with_borrower=obj.getString("country"); // 与借款人关系
		 String country_and_region=obj.getString("gender");  // 国家及地区
		 String sex=obj.getString("census");  // 性别: 0男 1女
		 String Local_domicile=obj.getString("marriage");  // 本地户籍
		 String household_registration=obj.getString("housing");  // 户籍所在地
		 String marital_status=obj.getString("birthday");  // 婚姻情况0已婚，1未婚
		 String housing_condition_now=obj.getString("currentAddress");  // 0房改/继承 1按揭自置2无按揭自置 3与父母同住 4租借// 5公司提供 6其他
		 String birthday=obj.getString("residencePhone");  // 出生日期
		 String home_address_now=obj.getString("mobilePhone");  // 现住房地址
		 String home_phone=obj.getString("email");  // 住宅电话
		 String mobile_phone=obj.getString("code");  // 移动电话
		 String email=obj.getString("career");  // E-mail
		 String present_address_zip_code=obj.getString("unit");  // 现住址邮编
		 String vocation=obj.getString("unitName");  // 职业
		 String unit_industry=obj.getString("unitAddress");  // 现单位所处行业
		 String uni_name=obj.getString("companyNumber");  // 现单位名称
		 String unit_address=obj.getString("lastyearIncome");  // 现单位地址
		 String enterprise_scale=obj.getString("assetSize");  // 就职企业规模
		 double Revenue_in_the_previous_year=Double.valueOf(obj.getString("unitPhone"));
		 String asset_scale=obj.getString("unitCode");  // 资产规模
		 String unit_phone=obj.getString("jobsType");  // 单位电话
		 String postCode=obj.getString("unitTime");  // 单位邮编
		 String job_category=obj.getString("lastunitName");  // 职位类别
		 String seniority=obj.getString("lastunitTime");  // 现单位工龄
		 String former_unit_name=obj.getString("incomeSource");  // 前单位名称
		 String former_seniority=obj.getString("salary");  // 前单位工龄
		 String source_of_income=obj.getString("investment");  // 收入来源
		 double monthly_income = Double.valueOf(obj.getString("rent"));  // 月收入
		 double Income_from_investment = Double.valueOf(obj.getString("added"));// 投资收益
		 double Rent_income = Double.valueOf(obj.getString("supportPeople"));// 租金收入
		 double Other_income = Double.valueOf(obj.getString("expenses"));// 其他收入
		 String family_number=obj.getString("communication");  // 供养人数
		 double monthly_expenditure = Double.valueOf(obj.getString("a"));// 月支出
		 String postal_address=obj.getString("b");  // 通讯地址
		 String start=obj.getString("c");  // 状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		 String ctime=obj.getString("d");  // 创建时间

	
	
		JointApplicant joint = new JointApplicant(name, phoneticize, id_type,id_number,
				relationship_with_borrower,  country_and_region,  sex, Local_domicile,
				household_registration, marital_status,  housing_condition_now, birthday,
				home_address_now,  home_phone, mobile_phone,email,
				present_address_zip_code,vocation,unit_industry, uni_name,
				 unit_address, enterprise_scale, Revenue_in_the_previous_year,asset_scale,
				 unit_phone, postCode, job_category, seniority, former_unit_name,
				former_seniority,source_of_income, monthly_income, Income_from_investment,
				Rent_income,  Other_income, family_number,monthly_expenditure,
				 postal_address,  start, ctime);
		boolean jo = jointapplicant.save(joint);// 插入角色

		if (jo == true) {
			return JSON.toJSONString("success");
		} else {
			return JSON.toJSONString("fail");
		}

	}
	
	
	@RequestMapping(value = "loanjoin")
	public String rolelist(){
		System.out.println("--------------------------");
		return "loan/loanInfo";
	}
	
	/***
	 * 修改角色权限
	 * @return
	 */
	@RequestMapping(value = "loanapply")
	public String updateRole(){
		System.out.println("--------------------------");
		return "loan/loanInfo";
	}
	

}
