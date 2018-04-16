package com.slloan.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.NoteExplain;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.PropertyInformation;
import com.slloan.entity.SpousesOfBorrowers;
import com.slloan.service.inter.ApplyForLoanInformationService;
import com.slloan.service.inter.ContactsService;
import com.slloan.service.inter.JointApplicantService;
import com.slloan.service.inter.PersonalProfileService;
import com.slloan.service.inter.PropertyInformationService;
import com.slloan.service.inter.SpousesOfBorrowersService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

/**
 * 贷款终审查询以及修改保存
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="loanfinaljudgment")
public class LoanFinaljudgmentController {

	
	@Autowired
	private JointApplicantService applicantDao;//共同申请人个人资料
	
	@Autowired
	private SpousesOfBorrowersService spousesOfBorrowersService;//借款申请人配偶
	
	@Autowired
	private PersonalProfileService personalprofileservice;//共同申请人个人资料
	
	@Autowired
	private ApplyForLoanInformationService applyForLoanInformationService;//申请借款信息
	
	@Autowired
	private PropertyInformationService propertyinformationservice;//房产资料
	
	@Autowired
	private ContactsService contactsservice;//联系人信息
	
	
	/**
	 * 共同申请人资料
	 * 终审根据ID查共同申请人资料所以
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/finaljudgmentselectid",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json loanFinaljudgmentSelectById(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		JointApplicant isResult =applicantDao.SelectById(id);
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
	}
	
	/**
	 * 同申请人资料
	 * 终审根据ID保存共同申请人资料
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/finaljudgmentupdateadd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json loanFinaljudgmentupdateAdd(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		
		Integer id = json.getInt("id");
		 String name=json.getString("cname");  // 共同借款人姓名
		 String phoneticize=json.getString("ename");  // 拼音、英文姓名
		 String idtype=json.getString("paperwork");  // 身份证件类型
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
		 String state=json.getString("state");  // 状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
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


		JointApplicant joints = new JointApplicant(id,name, phoneticize, idtype, id_number, relationship_with_borrower,
				country_and_region, sex, Local_domicile, household_registration, marital_status, housing_condition_now,
				birthday, home_address_now, home_phone, mobile_phone, email, present_address_zip_code, vocation,
				unit_industry, uni_name, unit_address, enterprise_scale, Revenue_in_the_previous_year, asset_scale,
				unit_phone, postCode, job_category, seniority, former_unit_name, former_seniority, source_of_income,
				monthly_income, Income_from_investment, Rent_income, Other_income, family_number, monthly_expenditure,
				postal_address, state, ctime);
		boolean isResult = applicantDao.update(joints);
		if (isResult == true) {
			return new Json(true, "success", isResult);
		} else {
			return new Json(false, "fail", isResult);
		}
	}
	
	
	/**
	 * 终审借款申请人配偶
	 * 终审根据ID查共同申请人配偶所以
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/finaljudgmentspouseselectbyid",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json finaljudgmentspouseselectbyid(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		SpousesOfBorrowers isResult =spousesOfBorrowersService.SelectById(id);
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
	}
	
	/**
	 * 
	 * 终审借款申请人配偶
	 * 借款申请人保存共同申请人配偶
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/finaljudgmentspouseadd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json ddd(HttpServletRequest req){
	String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
	JSONObject obj = new JSONObject().fromObject(role_constant);
	Integer id = obj.getInt("id");
	String name = req.getParameter("contacts");// 借款人配偶姓名
	String id_Type =req.getParameter("contacts"); // 身份证件类型
	String id_Number =req.getParameter("contacts"); // 身份证件号码
	String uni_Name = req.getParameter("contacts"); // 单位名称
	String unit_Phone = req.getParameter("contacts"); // 单位电话
	String home_Phone = req.getParameter("contacts"); // 住宅电话
	String mobile_Phone = req.getParameter("contacts"); // 手机
	String monthly_Income = req.getParameter("contacts"); // 月收入
	String state =req.getParameter("contacts");
	String ctime = req.getParameter("contacts");
	SpousesOfBorrowers spouses = new SpousesOfBorrowers(id,name, id_Type, id_Number, uni_Name, unit_Phone, home_Phone,
			mobile_Phone, monthly_Income,state,ctime);

		boolean isResult=	spousesOfBorrowersService.spoupdate(spouses);
			if(isResult ==true){
			return  new Json(true,"success",isResult);
		}else{
			return  new Json(false,"fail",isResult);
		}
	}
	
	/**
	 * 借款申请人个人资料
	 * 	借款申请人个人资料查询
	 */
	@RequestMapping(value="/applicantisborrowinginformation",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json  applicantisborrowinginformation(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		PersonalProfile isResult =personalprofileservice.SelectById(id);
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
	}
	/**
	 * 借款申请人个人资料
	 * 借款申请人个人资料保存
	 * @param req
	 * @param contactsparam
	 * @return
	 */
	@RequestMapping(value= "/applicantisloanadd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json updateadd(HttpServletRequest request){
		
		String dataid = request.getParameter("data");
		JSONObject req = new JSONObject().fromObject(dataid);
		Integer id = req.getInt("id");
		String product_Number = req.getString("");
		String name =req.getString("contacts"); // 共同借款人姓名
		String phoneticize = req.getString("contacts");// 拼音、英文姓名
		String id_type =  req.getString("contacts");// 身份证件类型
		String id_number = req.getString("contacts");// 身份证件号码
		String relationship_with_borrower = req.getString("contacts");// 与借款人关系
		String country_and_region = req.getString("contacts");// 国家及地区
		String sex =req.getString("contacts"); // 性别: 0男 1女
		String Local_domicile = req.getString("contacts"); // 本地户籍
		String household_registration = req.getString("contacts"); // 户籍所在地
		String marital_status = req.getString("contacts"); // 婚姻情况0已婚，1未婚
		String housing_condition_now =req.getString("contacts"); // 0房改/继承
																		// 1按揭自置2无按揭自置
																		// 3与父母同住
																		// 4租借//
																		// 5公司提供
																		// 6其他
		String birthday = req.getString("contacts"); // 出生日期
		String home_address_now = req.getString("contacts");// 现住房地址
		String home_phone = req.getString("contacts");// 住宅电话
		String mobile_phone = req.getString("contacts");// 移动电话
		String email = req.getString("contacts"); // E-mail
		String present_address_zip_code =  req.getString("contacts");// 现住址邮编
		String vocation =  req.getString("contacts");// 职业
		String unit_industry =  req.getString("contacts");// 现单位所处行业
		String uni_name = req.getString("contacts"); // 现单位名称
		String unit_address =  req.getString("contacts");// 现单位地址
		String enterprise_scale = req.getString("contacts");// 就职企业规模
		double Revenue_in_the_previous_year = Double.valueOf(req.getString("contacts"));
		String asset_scale = req.getString("contacts");// 资产规模
		String unit_phone = req.getString("contacts");// 单位电话
		String postCode = req.getString("contacts");// 单位邮编
		String job_category =  req.getString("contacts");// 职位类别
		String seniority = req.getString("contacts");// 现单位工龄
		String former_unit_name = req.getString("contacts"); // 前单位名称
		String former_seniority =  req.getString("contacts");// 前单位工龄
		String source_of_income =  req.getString("contacts");// 收入来源
		double monthly_income = Double.valueOf(req.getString("contacts")); // 月收入
		double Income_from_investment = Double.valueOf(req.getString("contacts"));// 投资收益
		double Rent_income = Double.valueOf(req.getString("contacts"));// 租金收入
		double Other_income = Double.valueOf(req.getString("contacts"));// 其他收入
		String family_number = req.getString("contacts");// 供养人数
		double monthly_expenditure = Double.valueOf(req.getString("contacts"));// 月支出
		String postal_address = req.getString("contacts");// 通讯地址
		String state =  req.getString("contacts");// 状态
												// 0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime =  req.getString("contacts");// 创建时间

		PersonalProfile jointss = new PersonalProfile(id,product_Number,name, phoneticize, id_type, id_number, relationship_with_borrower,
				country_and_region, sex, Local_domicile, household_registration, marital_status, housing_condition_now,
				birthday, home_address_now, home_phone, mobile_phone, email, present_address_zip_code, vocation,
				unit_industry, uni_name, unit_address, enterprise_scale, Revenue_in_the_previous_year, asset_scale,
				unit_phone, postCode, job_category, seniority, former_unit_name, former_seniority, source_of_income,
				monthly_income, Income_from_investment, Rent_income, Other_income, family_number, monthly_expenditure,
				postal_address, state, ctime);
			boolean isResult=	personalprofileservice.perupdate(jointss);
			if(isResult ==true){
				return  new Json(true,"success",isResult);
			}else{
				return  new Json(false,"fail",isResult);
			}
	}
	
	/***
	 * 申请借款信息(终审申请借款信息)
	 * 根据ID查所有联系人信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/loaninformationbyid",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req){
		String dataid = req.getParameter("datas");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		ApplyForLoanInformation isResult =applyForLoanInformationService.SelectById(id);
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
	}

	/**
	 * (终审申请借款信息)
	 * 修改用户保存
	 * @return
	 */
	@RequestMapping(value="/modifyloanadd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateUser(HttpServletRequest req){
		String dataid = req.getParameter("datas");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String amount= json.getString("contacts");
		String time_Limit= json.getString("contacts");
		String borrowing_Variety= json.getString("contacts");
		String repayment= json.getString("contacts");
		String receiving_Bank_Name= json.getString("contacts");
		String receiving_Account_Name= json.getString("contacts");
		String receiving_Account= json.getString("contacts");
		String repayment_Bank_Name= json.getString("contacts");
		String repayment_Account_Name= json.getString("contacts");
		String repayment_Account_Number= json.getString("contacts");
		String state= json.getString("contacts");
		String ctime=DateUtils.getInDateTime((new Date()));
		ApplyForLoanInformation ap = new ApplyForLoanInformation(id,amount, time_Limit, borrowing_Variety, repayment,
				receiving_Bank_Name, receiving_Account_Name, receiving_Account, repayment_Bank_Name,
				repayment_Account_Name, repayment_Account_Number,state,ctime);
		boolean isResult =applyForLoanInformationService.appUpdate(ap);
		if(isResult == true){
			return JSON.toJSONString(isResult);
		}else
			return JSON.toJSONString("fail");
	}
	
	
	/***
	 * 房产资料(终审房产资料)
	 * 根据ID查所有联系人信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/propertydataselectid",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json propertydata(HttpServletRequest req){
		
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		PropertyInformation isResult =propertyinformationservice.SelectById(id);
		
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
	}
	
	/**
	 *  房产资料(终审房产资料)
	 * @param req
	 * @param contactsparam
	 * @return
	 */
	@RequestMapping(value= "/propertydatadd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json updateadd(HttpServletRequest request,Contacts contactsparam){
		String dataid = request.getParameter("data");
		JSONObject req = new JSONObject().fromObject(dataid);
		Integer id = req.getInt("id");
		String ownership_And_percentage = req.getString("contacts"); // 权属人及占比
		String property_Address = req.getString("contacts"); // 房产地址
		String conStruction_Area =req.getString("contacts");// 建筑面积
		String inner_Area = req.getString("contacts"); // 套内面积
		String sales_Contract_Number =req.getString("contacts"); // 买卖合同编号
		String certificate_of_Title = req.getString("contacts"); // 产权证号
		String proPerty_for =req.getString("contacts");// 房产用于
		String the_Assessed_Value =req.getString("contacts"); // 评估值
		String original_Loan_Bank = req.getString("contacts"); // 原贷款银行
		String original_Loan_Amount = req.getString("contacts");// 原贷款金额
		String loan_Outstanding_Balance = req.getString("contacts"); // 原贷款尚欠本息余额
		String house_Account = req.getString("contacts"); // 供楼账号
		String transaCtion_Price =req.getString("contacts"); // 买卖成交价
		String purchase_Deposit = req.getString("contacts"); // 购房定金
		String supervision_of_funds =req.getString("contacts"); // 资金监管
		String new_loan_Bank =req.getString("contacts");// 新贷款银行
		String new_Loan_Approval_Amount =req.getString("contacts"); // 新贷款批复金额
		String new_Loan_Bank_Account_Number = req.getString("contacts"); // 新贷款行账号
		String note_DescriPtion = req.getString("contacts"); // 备注
		String state=req.getString("contacts"); //状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime = req.getString("contacts");//更新时间
		PropertyInformation preperty = new PropertyInformation(id,ownership_And_percentage, property_Address,
				conStruction_Area, inner_Area, sales_Contract_Number, certificate_of_Title, proPerty_for,
				the_Assessed_Value, original_Loan_Bank, original_Loan_Amount, loan_Outstanding_Balance, house_Account,
				transaCtion_Price, purchase_Deposit, supervision_of_funds, new_loan_Bank, new_Loan_Approval_Amount,
				new_Loan_Bank_Account_Number, note_DescriPtion,state,ctime);
			boolean isResult=	propertyinformationservice.proupdate(preperty);
			if(isResult ==true){
				return  new Json(true,"success",isResult);
			}else{
				return  new Json(false,"fail",isResult);
			}
	}
	
	/***
	 * 联系人信息
	 * 终审联系人信息（查询）
	 * 根据ID查所有联系人信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/contactsinformation",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json contactsinformation(HttpServletRequest req){
		
		
		System.out.println("======================================");
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		Contacts isResult =contactsservice.SelectById(id);
		
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
	}
	
	/**
	 * 联系人信息
	 * 终审联系人信息（修改保存）
	 * 修改用户保存
	 * @return
	 */
	@RequestMapping(value="/modifycontactsadd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String modifycontactsadd(HttpServletRequest req){
		
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String contacts = json.getString("linkfName"); //联系人姓名
		String contacts1=json.getString("linkfName");//联系人姓名
		String contacts2=json.getString("linkfName");//联系人姓名
		String relationship=json.getString("linkfRelationship");//联系人关系
		String relationship1=json.getString("linkfRelationship");//联系人关系
		String relationship2=json.getString("linkfRelationship");//联系人关系
		String c_Telephone=json.getString("linkfPhone");//联系人电话
		String c_Telephone1=json.getString("linkfPhone");//联系人电话
		String c_Telephone2=json.getString("linkfPhone");//联系人电话
		String state=json.getString("state");
		String ctime=DateUtils.getInDateTime((new Date()));
		Contacts contact = new Contacts(id,contacts, contacts1, contacts2, relationship, relationship1, relationship2, c_Telephone, c_Telephone1, c_Telephone2, state, ctime);
		boolean isResult =contactsservice.updateadd(contact);
		if(isResult == true){
			return JSON.toJSONString(isResult);
		}else
			return JSON.toJSONString("fail");
	}
	
	/**
	 * 备注说明（终审备注说明查询）
	 * 查询初审&录单备注
	 * @return
	 */
	@RequestMapping(value="/selectBynote",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json NoteSelectById(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String city = json.getString("city");
		NoteExplain note = new NoteExplain(id,city);
		NoteExplain isResult =contactsservice.SelectBynote(note);
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
		
	}
	
	/**
	 * 备注说明（终审备注说明保存）
	 * 查询初审&录单备注
	 * @return
	 */
	@RequestMapping(value="/noteloanadd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json NoteSelectAdd(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String city = json.getString("city");
		NoteExplain note = new NoteExplain(id,city);
		boolean isResult =contactsservice.firstTrial(note);
		if(isResult ==true){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
		
	}
	
	/**
	 * 贷款信息查看查询
	 */
	/*@RequestMapping(value="/loaninFormationselect",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json loaninFormationSelect(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String name = json.getString("name");//姓名
		String phone = json.getString("phone");//手机号码
		String id_Number = json.getString("idnumber");
		String Application_number = json.getString("applicationnumber");//申请编号
		//申请时间开始时间
		//申请结束时间
		//贷款金额范围1
		//贷款金额范围2
	}*/
	
	
	/**
	 * 共用的(糊糊)状态查询
	 */
	/*@RequestMapping(value="/loaninFormationselect",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json vagueSelect(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		
	}*/
	
	
	/**
	 * 共用的(贷款创建，贷款初审，贷款终审)状态查询
	 */
	/*@RequestMapping(value="/loaninFormationselect",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json loaninFormationSelect(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String name = json.getString("name");//姓名
		String phone = json.getString("phone");//手机号码
		String id_Number = json.getString("idnumber");
		String Application_number = json.getString("applicationnumber");//申请编号
		String createdate1 = json.getString("createdata1");//申请时间1
		String createdata2 = json.getString("createdata2");//申请时间2
		String loanmoney1 = json.getString("money1");//贷款金额范围1
		String loanmoney2 = json.getString("money2");//贷款金额范围2
		
	}*/
	
}
