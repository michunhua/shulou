package com.slloan.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.Contacts;
import com.slloan.entity.Firstfilla;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.NoteDescription;
import com.slloan.entity.NoteExplain;
import com.slloan.entity.PersonalProfile;
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.service.inter.CoborrowerSpouseService;
import com.slloan.service.inter.ContactsService;
import com.slloan.service.inter.JointApplicantService;
import com.slloan.service.inter.PersonalProfileService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

/**
 * 共同申请人个人资料
 * 
 * @author Administrator
 *
 */
@Controller(value = "jointapplicantcontroll")
@RequestMapping("/loan")
public class JointApplicantController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JointApplicantService jointapplicant;

	@Autowired
	private PersonalProfileService personalprofileservice;

	@Autowired
	private CircuLationRecordSubmitService recordSubmitService;
	
	
	@Autowired
	private CoborrowerSpouseService notedesc;

	@ResponseBody
	@RequestMapping(value = "/commonApplydata")
	public Json save(HttpServletRequest req) {

		String role_constant = req.getParameter("data"); // 例如按揭员名
		JSONObject obj = new JSONObject().fromObject(role_constant);

		String name=obj.getString("cname"); // 共同借款人姓名
		String phoneticize=obj.getString("ename"); // 拼音、英文姓名
		String id_type=obj.getString("paperwork"); // 身份证件类型
		String Other_identity_types=obj.getString("otherPaperwork"); // 输入身份证类型
		String id_number=obj.getString("paperNumb"); // 身份证件号码
		String country_and_region=obj.getString("country"); // 国家及地区
		String other_Countries=obj.getString("otherCountry");// 其他国家
		String sex=obj.getString("gender"); // 性别: 0男 1女
		String Local_domicile=obj.getString("census"); // 本地户籍
		String household_registration=obj.getString("otherCensus");// 其他户籍所在地
		String marital_status=obj.getString("marriage");// 婚姻情况0已婚，1未婚
		String housing_condition_now=obj.getString("housing"); // 0房改/继承
		String otherCensus=obj.getString("otherHousing");// 其他
		String birthday=obj.getString("birthday"); // 出生日期
		String home_address_now=obj.getString("currentAddress"); // 现住房地址
		String home_phone=obj.getString("residencePhone"); // 住宅电话
		String mobile_phone=obj.getString("mobilePhone");// 移动电话
		String email=obj.getString("email"); // E-mail
		String present_address_zip_code=obj.getString("code"); // 现住址邮编
		String vocation=obj.getString("career");// 职业
		String unit_industry=obj.getString("unit"); // 现单位所处行业
		String uni_name=obj.getString("unitName"); // 现单位名称
		String unit_address=obj.getString("unitAddress");// 现单位地址
		String enterprise_scale=obj.getString("companyNumber");// 企业人数
		String lastyearIncome=obj.getString("lastyearIncome");// 上年营收
		String asset_scale=obj.getString("assetSize"); // 资产规模
		String unit_phone=obj.getString("unitPhone"); // 单位电话
		String postCode=obj.getString("unitCode"); // 单位邮编
		String job_category=obj.getString("jobsType"); // 职位类别
		String seniority=obj.getString("unitTime"); // 现单位工龄
		String former_unit_name=obj.getString("lastunitName"); // 前单位名称
		String former_seniority=obj.getString("lastunitTime");// 前单位工龄
		String source_of_income=obj.getString("incomeSource");// 收入来源
		String salary=obj.getString("salary");// 月收入
		String investment=obj.getString("investment");// 投资收益
		String rent=obj.getString("rent"); // 租金收入
		String added=obj.getString("added"); // 其他收入
		String family_number=obj.getString("supportPeople"); // 供养人数
		String expenses=obj.getString("expenses");// 月支出
		String postal_address=obj.getString("communication"); // 通讯地址
		String state=obj.getString("state"); // 状态;//
							// 0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结
		String ctime=obj.getString("ctime"); // 日期


		Double Revenue_previous_year = 0.0;
		if (lastyearIncome.length() > 0) {
			Revenue_previous_year = Double.parseDouble(lastyearIncome);
		}
		Double monthly_income = 0.0;
		if (salary.length() > 0) {
			monthly_income = Double.parseDouble(salary);
		}
		Double Income_from_investment = 0.0;
		if (investment.length() > 0) {
			Income_from_investment = Double.parseDouble(investment);
		}
		Double supportPeople = 0.0;
		if (rent.length() > 0) {
			supportPeople = Double.parseDouble(rent);
		}
		Double Other_income = 0.0;
		if (added.length() > 0) {
			Other_income = Double.parseDouble(added);
		}
		Double monthly_expenditure = 0.0;
		if (expenses.length() > 0) {
			monthly_expenditure = Double.parseDouble(expenses);
		}
		JointApplicant joint= new JointApplicant(name, phoneticize, id_type, Other_identity_types,
				id_number, country_and_region, other_Countries, sex, Local_domicile, household_registration,
				marital_status, housing_condition_now, otherCensus, birthday, home_address_now, home_phone,
				mobile_phone, email, present_address_zip_code, vocation, unit_industry, uni_name, unit_address,
				enterprise_scale, Revenue_previous_year, asset_scale, unit_phone, postCode, job_category, seniority,
				former_unit_name, former_seniority, source_of_income, monthly_income, Income_from_investment,
				supportPeople, Other_income, family_number, monthly_expenditure, postal_address, state, ctime);
		boolean jo = jointapplicant.save(joint);// 插入角色

		if (jo == true) {
			logger.info("数据插入成功!");
			return new Json(true,"success",jo ); 
		} else {
			logger.info("数据插入失败!");
			return new Json(false,"fail",jo); 
		}

	}

/**
 * 初审
 * @param req
 * @return
 */

	@RequestMapping(value = "/joinupdate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateadd(HttpServletRequest req) {
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String name=json.getString("cname"); // 共同借款人姓名
		String phoneticize=json.getString("ename"); // 拼音、英文姓名
		String id_type=json.getString("paperwork"); // 身份证件类型
		String Other_identity_types=json.getString("otherPaperwork"); // 输入身份证类型
		String id_number=json.getString("paperNumb"); // 身份证件号码
		String country_and_region=json.getString("country"); // 国家及地区
		String other_Countries=json.getString("otherCountry");// 其他国家
		String sex=json.getString("gender"); // 性别: 0男 1女
		String Local_domicile=json.getString("census"); // 本地户籍
		String household_registration=json.getString("otherCensus");// 其他户籍所在地
		String marital_status=json.getString("marriage");// 婚姻情况0已婚，1未婚
		String housing_condition_now=json.getString("housing"); // 0房改/继承
		String otherCensus=json.getString("otherHousing");// 其他
		String birthday=json.getString("birthday"); // 出生日期
		String home_address_now=json.getString("currentAddress"); // 现住房地址
		String home_phone=json.getString("residencePhone"); // 住宅电话
		String mobile_phone=json.getString("mobilePhone");// 移动电话
		String email=json.getString("email"); // E-mail
		String present_address_zip_code=json.getString("code"); // 现住址邮编
		String vocation=json.getString("career");// 职业
		String unit_industry=json.getString("unit"); // 现单位所处行业
		String uni_name=json.getString("unitName"); // 现单位名称
		String unit_address=json.getString("unitAddress");// 现单位地址
		String enterprise_scale=json.getString("companyNumber");// 企业人数
		String lastyearIncome=json.getString("lastyearIncome");// 上年营收
		String asset_scale=json.getString("assetSize"); // 资产规模
		String unit_phone=json.getString("unitPhone"); // 单位电话
		String postCode=json.getString("unitCode"); // 单位邮编
		String job_category=json.getString("jobsType"); // 职位类别
		String seniority=json.getString("unitTime"); // 现单位工龄
		String former_unit_name=json.getString("lastunitName"); // 前单位名称
		String former_seniority=json.getString("lastunitTime");// 前单位工龄
		String source_of_income=json.getString("incomeSource");// 收入来源
		String salary=json.getString("salary");// 月收入
		String investment=json.getString("investment");// 投资收益
		String rent=json.getString("rent"); // 租金收入
		String added=json.getString("added"); // 其他收入
		String family_number=json.getString("supportPeople"); // 供养人数
		String expenses=json.getString("expenses");// 月支出
		String postal_address=json.getString("communication"); // 通讯地址
		String state=json.getString("state"); // 状态;//
							// 0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结
		String ctime=json.getString("ctime"); // 日期
		
		Double Revenue_previous_year = 0.0;
		if (lastyearIncome.length() > 0) {
			Revenue_previous_year = Double.parseDouble(lastyearIncome);
		}
		Double monthly_income = 0.0;
		if (salary.length() > 0) {
			monthly_income = Double.parseDouble(salary);
		}
		Double Income_from_investment = 0.0;
		if (investment.length() > 0) {
			Income_from_investment = Double.parseDouble(investment);
		}
		Double supportPeople = 0.0;
		if (rent.length() > 0) {
			supportPeople = Double.parseDouble(rent);
		}
		Double Other_income = 0.0;
		if (added.length() > 0) {
			Other_income = Double.parseDouble(added);
		}
		Double monthly_expenditure = 0.0;
		if (expenses.length() > 0) {
			monthly_expenditure = Double.parseDouble(expenses);
		}
		JointApplicant joints= new JointApplicant(id,name, phoneticize, id_type, Other_identity_types,
				id_number, country_and_region, other_Countries, sex, Local_domicile, household_registration,
				marital_status, housing_condition_now, otherCensus, birthday, home_address_now, home_phone,
				mobile_phone, email, present_address_zip_code, vocation, unit_industry, uni_name, unit_address,
				enterprise_scale, Revenue_previous_year, asset_scale, unit_phone, postCode, job_category, seniority,
				former_unit_name, former_seniority, source_of_income, monthly_income, Income_from_investment,
				supportPeople, Other_income, family_number, monthly_expenditure, postal_address, state, ctime);
		boolean isResult = jointapplicant.update(joints);
		if (isResult == true) {
			logger.info("数据插入成功!");
			return new Json(true,"success",isResult ); 
		} else {
			logger.info("数据插入失败!");
			return new Json(false,"fail",isResult); 
		}
	}

	/***
	 * 根据ID查所有联系人信息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/jointappli", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req) {

		System.out.println("======================================");
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		JointApplicant isResult = jointapplicant.SelectById(id);

		if (isResult != null) {
			return new Json(true, "success", isResult);
		} else
			return new Json(false, "fail", isResult);
	}

	/**
	 * 删除
	 */
	// @RequestMapping(value = "")
	// @ResponseBody
	// public String delete(@RequestParam("id") Integer id) {
	// boolean isReslt = jointapplicant.delete(id);
	// if (isReslt == true) {
	// return JSON.toJSONString(isReslt);
	// } else {
	// return JSON.toJSONString(isReslt);
	// }
	// }

	/**
	 * 终审
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/joinupdates", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateadds(HttpServletRequest req) {
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String name=json.getString("cname"); // 共同借款人姓名
		String phoneticize=json.getString("ename"); // 拼音、英文姓名
		String id_type=json.getString("paperwork"); // 身份证件类型
		String Other_identity_types=json.getString("otherPaperwork"); // 输入身份证类型
		String id_number=json.getString("paperNumb"); // 身份证件号码
		String country_and_region=json.getString("country"); // 国家及地区
		String other_Countries=json.getString("otherCountry");// 其他国家
		String sex=json.getString("gender"); // 性别: 0男 1女
		String Local_domicile=json.getString("census"); // 本地户籍
		String household_registration=json.getString("otherCensus");// 其他户籍所在地
		String marital_status=json.getString("marriage");// 婚姻情况0已婚，1未婚
		String housing_condition_now=json.getString("housing"); // 0房改/继承
		String otherCensus=json.getString("otherHousing");// 其他
		String birthday=json.getString("birthday"); // 出生日期
		String home_address_now=json.getString("currentAddress"); // 现住房地址
		String home_phone=json.getString("residencePhone"); // 住宅电话
		String mobile_phone=json.getString("mobilePhone");// 移动电话
		String email=json.getString("email"); // E-mail
		String present_address_zip_code=json.getString("code"); // 现住址邮编
		String vocation=json.getString("career");// 职业
		String unit_industry=json.getString("unit"); // 现单位所处行业
		String uni_name=json.getString("unitName"); // 现单位名称
		String unit_address=json.getString("unitAddress");// 现单位地址
		String enterprise_scale=json.getString("companyNumber");// 企业人数
		String lastyearIncome=json.getString("lastyearIncome");// 上年营收
		String asset_scale=json.getString("assetSize"); // 资产规模
		String unit_phone=json.getString("unitPhone"); // 单位电话
		String postCode=json.getString("unitCode"); // 单位邮编
		String job_category=json.getString("jobsType"); // 职位类别
		String seniority=json.getString("unitTime"); // 现单位工龄
		String former_unit_name=json.getString("lastunitName"); // 前单位名称
		String former_seniority=json.getString("lastunitTime");// 前单位工龄
		String source_of_income=json.getString("incomeSource");// 收入来源
		String salary=json.getString("salary");// 月收入
		String investment=json.getString("investment");// 投资收益
		String rent=json.getString("rent"); // 租金收入
		String added=json.getString("added"); // 其他收入
		String family_number=json.getString("supportPeople"); // 供养人数
		String expenses=json.getString("expenses");// 月支出
		String postal_address=json.getString("communication"); // 通讯地址
		String state=json.getString("state"); // 状态;//
							// 0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结
		String ctime=json.getString("ctime"); // 日期
		
		Double Revenue_previous_year = 0.0;
		if (lastyearIncome.length() > 0) {
			Revenue_previous_year = Double.parseDouble(lastyearIncome);
		}
		Double monthly_income = 0.0;
		if (salary.length() > 0) {
			monthly_income = Double.parseDouble(salary);
		}
		Double Income_from_investment = 0.0;
		if (investment.length() > 0) {
			Income_from_investment = Double.parseDouble(investment);
		}
		Double supportPeople = 0.0;
		if (rent.length() > 0) {
			supportPeople = Double.parseDouble(rent);
		}
		Double Other_income = 0.0;
		if (added.length() > 0) {
			Other_income = Double.parseDouble(added);
		}
		Double monthly_expenditure = 0.0;
		if (expenses.length() > 0) {
			monthly_expenditure = Double.parseDouble(expenses);
		}
		JointApplicant joints= new JointApplicant(id,name, phoneticize, id_type, Other_identity_types,
				id_number, country_and_region, other_Countries, sex, Local_domicile, household_registration,
				marital_status, housing_condition_now, otherCensus, birthday, home_address_now, home_phone,
				mobile_phone, email, present_address_zip_code, vocation, unit_industry, uni_name, unit_address,
				enterprise_scale, Revenue_previous_year, asset_scale, unit_phone, postCode, job_category, seniority,
				former_unit_name, former_seniority, source_of_income, monthly_income, Income_from_investment,
				supportPeople, Other_income, family_number, monthly_expenditure, postal_address, state, ctime);
		boolean isResult = jointapplicant.update(joints);
		if (isResult == true) {
			return new Json(true, "success", isResult);
		} else {
			return new Json(false, "fail", isResult);
		}
	}

	

	/**
	 * 贷款创建列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loancrea")
	public String loancrea() {
		System.out.println("--------------------------");
		return "loan/loanCreateTable";
	}

	/**
	 * 共同申请人资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loancom")
	public String loancom() {
		System.out.println("--------------------------");
		return "loan/commomLoanerInfo";
	}

	/**
	 * 影像资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanimag")
	public String loanimag() {
		System.out.println("--------------------------");
		return "loan/imageInfo";
	}

	/**
	 * 备注说明
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannote")
	public String loannote() {
		System.out.println("--------------------------");
		return "loan/noteInfo";
	}

	/**
	 * 贷款初审列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loancreas")
	public String loancreas() {
		System.out.println("--------------------------");
		return "loanfirst/loanFirstTable";
	}

	/**
	 * 贷款信息查看列表
	 * 
	 * @return json
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/rolemanagement", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String rolemanagements(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		
		int startPos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// startPos, int pageSize
		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		System.out.println(username+" "+ role);
		return JSON.toJSONString(personalprofileservice.getPersonalProfilePage(startPos,username,role,city,parentnodeId));
	}
	
	
	
	/**
	 * 贷款初审列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/loanlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loanlist(HttpServletRequest req) throws UnsupportedEncodingException {
		System.out.println("-----------初审列表---------------");
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
//		String id = req.getParameter("id");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		return JSON.toJSONString(personalprofileservice.getFirsttrialPage(statePos,username,role,city,parentnodeId));
	}
	/**
	 * 贷款终审列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/loanfinalreviewlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loanFinalreviewlist(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		System.out.println("-----------贷款终审列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getFinalreviewPage(statePos,username,role,city,parentnodeId));
	}

	
	/**
	 * 贷款财务列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/loanfinancelist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loanfinancelist(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getFinancePagePage(statePos,username,role,city,parentnodeId));
	}
	
	/**
	 * 转账凭证列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/loantransferlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loantransferlist(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		System.out.println("-----------转账凭证列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getTransferloanPage(statePos,username,role,city,parentnodeId));
	}
	
	
	/**
	 * 结算凭证列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/loanjslist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loanjslist(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		return JSON.toJSONString(personalprofileservice.getjsloanPage(statePos,username,role,city,parentnodeId));
	}
	
	/**
	 * 贷款信息查看列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/LoanInformation", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String LoanInformation(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int startPos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getLoanInformation(startPos,username,role,city,parentnodeId));
	}
	
	
	/**
	 * 回款确认列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	
	@RequestMapping(value = "/ReturnMoney", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String ReturnMoney(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int startPos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getReturnMoney(startPos,username,role,city,parentnodeId));
	}
	/**
	 * 取证凭证列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/ObtainEvidence", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String ObtainEvidence(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int startPos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getObtainEvidence(startPos,username,role,city,parentnodeId));
	}
	/**
	 * 解压列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/Decompression", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String Decompression(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int startPos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);

		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getDecompression(startPos,username,role,city,parentnodeId));
	}
	/**
	 * 进压列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/LoanPressure", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String LoanPressure(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int startPos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);

		String username = obj.getString("username");
		String role = obj.getString("role").toString();
		String city = obj.getString("city").toString();
		String parentnodeId = obj.getString("parentnodeId");
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getLoanPressure(startPos,username,role,city,parentnodeId));
	}
	
	
	/**
	 * 贷款初审共同借款人资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loancoms")
	public String loancoms() {
		System.out.println("--------------------------");
		return "loanfirst/commomLoanerInfo";
	}

	/**
	 * 贷款初审影像资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanimags")
	public String loanimags() {
		System.out.println("--------------------------");
		return "loanfirst/imageInfo";
	}

	/**
	 * 贷款初审备注
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotes")
	public String loannotes() {
		System.out.println("--------------------------");
		return "loanfirst/noteInfo";
	}
	

//	/**
//	 * 贷款初审备注回退
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/loannotFallback", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
//	public String loannotesFallback(HttpServletRequest req, HttpServletResponse response) {
//		
//		CircuLationRecord circuLationRecord = new CircuLationRecord();
//		circuLationRecord.setState(0);// 退回后状态改为1
//		String createDate = DateUtils.getInDateTime((new Date()));
//		circuLationRecord.setCreateDate(createDate);
//		circuLationRecord.setFallbackname("退回到按揭员------------------>");
//		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
//		if (isResultInsert == true) {
//			System.out.println("插入流程表成功");
//		} else {
//			System.out.println("失败");
//		}
//		return "loanfirst/loanFirstTable";
//
//
//	}
//	
//	/**
//	 * 贷款终审备注回退
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/loannotllback", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
//	public String FallbackFirst(HttpServletRequest req, HttpServletResponse response) {
//		
//		CircuLationRecord circuLationRecord = new CircuLationRecord();
//		circuLationRecord.setState(1);// 退回后状态改为1
//		String createDate = DateUtils.getInDateTime((new Date()));
//		circuLationRecord.setCreateDate(createDate);
//		circuLationRecord.setFallbackname("退回到初审------------------>");
//		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
//		if (isResultInsert == true) {
//			System.out.println("插入流程表成功");
//		} else {
//			System.out.println("失败");
//		}
//		return "loanfirst/loanFirstTable";
//
//
//	}
//
//	/**
//	 * 贷款初审备注提交到贷款终审
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/loannotsubmit", method = RequestMethod.GET)
//	public String loannotesSubmit(HttpServletRequest req) {
//
//		CircuLationRecord circuLationRecord = new CircuLationRecord();
//		circuLationRecord.setState(2);// 状态改为2
//		String createDate = DateUtils.getInDateTime((new Date()));
//		circuLationRecord.setCreateDate(createDate);
//		circuLationRecord.setFallbackname("提交到贷款初审------------------>");
//		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
//		if (isResultInsert == true) {
//			System.out.println("插入流程表成功");
//		} else {
//			System.out.println("失败");
//		}
//
//		return "loanfinal/loanFinalTable";// 提交到贷款终审
//	}
//	
	
//	/**
//	 * 按揭员备注提交到贷款初审
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/loannotfile", method = RequestMethod.GET)
//	public String loannotesFile(HttpServletRequest req) {
//
//		CircuLationRecord circuLationRecord = new CircuLationRecord();
//		circuLationRecord.setState(1);// 退回后状态改为1
//		String createDate = DateUtils.getInDateTime((new Date()));
//		circuLationRecord.setCreateDate(createDate);
//		circuLationRecord.setFallbackname("贷款终审跳转财务------------------>");
//		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
//		if (isResultInsert == true) {
//			System.out.println("插入流程表成功");
//		} else {
//			System.out.println("失败");
//		}
//
//		return "financeApproval/financeApproval";// 提交到贷款初审
//	}
//	
//	/**
//	 * 贷款初审备注说明保存
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/loannoteadd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//	@ResponseBody
//	public Json loannoteadd(HttpServletRequest req) {
//
//		String recordSingleNote = req.getParameter("recordsinglenote");// 录单备注
//
//		String firstTrialNote = req.getParameter("firsttrialnote");// 初审备注
//
//		NoteExplain note = new NoteExplain(recordSingleNote, firstTrialNote);
//		boolean isResultInsert = recordSubmitService.firstTrial(note);
//		if (isResultInsert == true) {
//			System.out.println("插入流程表成功");
//			return new Json(true, "success", isResultInsert);
//		} else {
//			System.out.println("失败");
//			return new Json(false, "fail", isResultInsert);
//		}
//
//		// return "loanfinal/loanFinalTable";//提交到贷款终审
//	}

	/**
	 * 贷款终审贷款列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loancreass")
	public String loancreass() {
		System.out.println("--------------------------");
		return "loanfinal/loanFinalTable";
	}

	/**
	 * 贷款终审共同借款人资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loancomss")
	public String loancomss() {
		System.out.println("--------------------------");
		return "loanfinal/commomLoanerInfo";
	}

	/**
	 * 贷款终审影像资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanimagss")
	public String loanimagss() {
		System.out.println("--------------------------");
		return "loanfinal/imageInfo";
	}

	/**
	 * 贷款终审备注
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotess")
	public String loannotess() {
		System.out.println("--------------------------");
		return "loanfinal/noteInfo";
	}

	/**
	 * 贷款信息查看
	 * 
	 * @return
	 */
	@RequestMapping(value = "/anin")
	public String anin() {
		System.out.println("--------------------------");
		return "/loanInfoRead";
	}

	/**
	 * 回款确认
	 * 
	 * @return
	 */
	@RequestMapping(value = "/repa")
	public String repa() {
		System.out.println("--------------------------");
		return "/repayConfirm";
	}

	/**
	 * 取证凭证
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gain")
	public String gain() {
		System.out.println("--------------------------");
		return "/gainProof";
	}

	/**
	 * 解压凭证
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reli")
	public String reli() {
		System.out.println("--------------------------");
		return "/reliefProof";
	}

	/**
	 * 进押凭证
	 * 
	 * @return
	 */
	@RequestMapping(value = "/inca")
	public String inca() {
		System.out.println("--------------------------");
		return "/incareProof";
	}

	/**
	 * 首页登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/happy")
	public String happy() {
		System.out.println("--------------------------");
		return "index/firstPage";
	}
}
