package com.slloan.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jfree.data.DataUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.ObjectSeq;
import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.ResultList;
import com.slloan.entity.circulation;
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.service.inter.ImagedataService;
import com.slloan.service.inter.JointApplicantService;
import com.slloan.service.inter.PersonalProfileService;
import com.slloan.service.inter.circulationService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

/**
 * 借款申请人资料
 * 
 * @author Administrator
 *
 */
@Controller(value = "personalprofilecontroller")
@RequestMapping("/loan")
public class PersonalProfileController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private PersonalProfileService personalprofileservice;
	
	@Autowired
	private ImagedataService imagedataservice;
	
	@Autowired
	private CircuLationRecordSubmitService recordSubmitService;
	
	@Autowired
	private	PersonalProfileService personalproFileService;

	@Autowired
	private circulationService circulationservice;
	@ResponseBody
	@RequestMapping("/loanApplypersonaldata")
	public Json save(HttpServletRequest req) {

		String role_constant = req.getParameter("data"); // 例如按揭员名
		JSONObject obj = new JSONObject().fromObject(role_constant);
		String product_Number=obj.getString("hi");// 编号
		String name=obj.getString("cname"); // 借款人姓名
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
		String ctime=DateUtils.getInDateTime(new Date());
		String username = obj.getString("username"); //用户名
		 String parentnodeId = obj.getString("parentnodeId"); //用户名id
		 String city = obj.getString("city"); //城市
		String rolename = obj.getString("rolename"); //角色
		
		
		
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
		ObjectSeq objd = new ObjectSeq();
		ObjectSeq addSeq = imagedataservice.addSeq();
		System.out.println(addSeq);
			ObjectSeq seq = imagedataservice.listSeq();
			System.out.println(seq.getStart_value());
			int idseq= seq.getStart_value();
			System.out.println(idseq);
			Long l = (long) seq.getStart_value();
			String seqid = String.valueOf(idseq);
			System.out.println(seqid);
			String applicationnumber = caseNo(l);
//			String applicationnumber = DateUtils.getInDateTime(new Date());
			
		    		PersonalProfile person = new PersonalProfile(product_Number, name, phoneticize, id_type, Other_identity_types,
		    				id_number, country_and_region, other_Countries, sex, Local_domicile, household_registration,
		    				marital_status, housing_condition_now, otherCensus, birthday, home_address_now, home_phone,
		    				mobile_phone, email, present_address_zip_code, vocation, unit_industry, uni_name, unit_address,
		    				enterprise_scale, Revenue_previous_year, asset_scale, unit_phone, postCode, job_category, seniority,
		    				former_unit_name, former_seniority, source_of_income, monthly_income, Income_from_investment,
		    				supportPeople, Other_income, family_number, monthly_expenditure, postal_address, state, ctime,username,parentnodeId,city,rolename,applicationnumber);
		    		
		    		boolean pe = personalprofileservice.save(person);// 插入角色
		    		String fallbackname = "创建贷款完成";
//		    		String spare1 = obj.getString("note");//备注
		    		int stateid =0;
		    		String createDate =  DateUtils.getInDateTime2((new Date()));//日期
		    		PersonalProfile param = new PersonalProfile(city,name);
		    		PersonalProfile p =personalproFileService.getSelectById(param);
		    		int pid = p.getId();
		    		String submit = String.valueOf(pid);
		    		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,submit,stateid,createDate,username,parentnodeId,city,rolename,null);
		    		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
//		    		String createdata = DateUtils.getInDateTime2(new Date());
		    		circulation record = new  circulation("0",fallbackname,createDate,username,parentnodeId,city,rolename,createDate,submit);
//		    		record.setCirculation("------"+username+"------------>"+"贷款创建提交到贷款初审");
		    		boolean coan = circulationservice.save(record);// 鎻掑叆瑙掕壊
		    		if (pe == true) {
		    			logger.info("数据插入成功!");
		    			return new Json(true, "success", pe,submit);
		    		} else {
		    			logger.info("数据插入失败!");
		    			return new Json(false, "fail", pe,submit);
		}
	

	}

	/**
	 * 按揭员查询Id
	 * 
	 * @param req
	 * @return
	 */
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
	 * 按揭员查询Id
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/personalpmake", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectByIds(HttpServletRequest req) {
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		PersonalProfile isResult = personalprofileservice.SelectByIdMarital(id);
		if (isResult != null) {
			return new Json(true, "success", isResult);
		} else
			return new Json(false, "fail", isResult);
	}

	/**
	 * 初审借款申请人个人资料
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
		String product_Number=json.getString("hi");// 编号
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
		String state = String.valueOf(id);
		int stateid = Integer.parseInt(state);
//		String state= strid; // 状态;//
							// 0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结
		String ctime=DateUtils.getInDateTime(new Date());

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

		PersonalProfile jointss = new PersonalProfile(id,product_Number, name, phoneticize, id_type, Other_identity_types,
				id_number, country_and_region, other_Countries, sex, Local_domicile, household_registration,
				marital_status, housing_condition_now, otherCensus, birthday, home_address_now, home_phone,
				mobile_phone, email, present_address_zip_code, vocation, unit_industry, uni_name, unit_address,
				enterprise_scale, Revenue_previous_year, asset_scale, unit_phone, postCode, job_category, seniority,
				former_unit_name, former_seniority, source_of_income, monthly_income, Income_from_investment,
				supportPeople, Other_income, family_number, monthly_expenditure, postal_address, state, ctime, null, null);
		PersonalProfile person = personalprofileservice.SelectById(stateid);
		if(person !=null){
			boolean isResult = personalprofileservice.perupdate(jointss);
			if (isResult == true) {
				return new Json(true, "success", isResult);
			} else {
				return new Json(false, "fail", isResult);
			}
		}else{
			PersonalProfile person2 = new PersonalProfile(product_Number, name, phoneticize, id_type, Other_identity_types,
    				id_number, country_and_region, other_Countries, sex, Local_domicile, household_registration,
    				marital_status, housing_condition_now, otherCensus, birthday, home_address_now, home_phone,
    				mobile_phone, email, present_address_zip_code, vocation, unit_industry, uni_name, unit_address,
    				enterprise_scale, Revenue_previous_year, asset_scale, unit_phone, postCode, job_category, seniority,
    				former_unit_name, former_seniority, source_of_income, monthly_income, Income_from_investment,
    				supportPeople, Other_income, family_number, monthly_expenditure, postal_address, state, ctime,"","","","","");
			boolean pe = personalprofileservice.save(person2);
    			if(pe ==true){
    				return new Json(true, "success", pe);
    			} else {
    				return new Json(false, "fail", pe);
    			}
    		
		}
		
	}
	
	

	

	/**
	 * 终审申请人个人资料
	 * 
	 * @param req
	 * @param contactsparam
	 * @return
	 */
	
	/**
	 * 初审借款申请人个人资料
	 * 
	 * @param req
	 * @param contactsparam
	 * @return
	 */
	@RequestMapping(value = "/perupdates", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateads(HttpServletRequest req) {

		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String product_Number=json.getString("hi");// 编号
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
		String ctime=DateUtils.getInDateTime(new Date()); // 日期
		
//		String datatime = DateUtils.getToDateTime((new Date()));
//		String appnumber = applicationnumber+seqid;
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

		PersonalProfile jointss = new PersonalProfile(id,product_Number, name, phoneticize, id_type, Other_identity_types,
				id_number, country_and_region, other_Countries, sex, Local_domicile, household_registration,
				marital_status, housing_condition_now, otherCensus, birthday, home_address_now, home_phone,
				mobile_phone, email, present_address_zip_code, vocation, unit_industry, uni_name, unit_address,
				enterprise_scale, Revenue_previous_year, asset_scale, unit_phone, postCode, job_category, seniority,
				former_unit_name, former_seniority, source_of_income, monthly_income, Income_from_investment,
				supportPeople, Other_income, family_number, monthly_expenditure, postal_address, state, ctime, null, null);
		boolean isResult = personalprofileservice.perupdate(jointss);
		if (isResult == true) {
			return new Json(true, "success", isResult);
		} else {
			return new Json(false, "fail", isResult);
		}
	}
	
	
	/**
	 * 创建列表模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/vaguelikeselectcreate", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> vaguelikeselectcreate(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName");
		String mobilephone = obj.getString("iphone");
		String idnumber = obj.getString("IDcard");
		String numbering = obj.getString("numbering");
		String ctim1 = obj.getString("start");
		String ctim2 = obj.getString("end");
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state");
		String rolename = obj.getString("rolename");
		String username = obj.getString("username");
		String city = obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
		System.out.println("a: "+name+" b: "+idnumber+" c: "+mobilephone+" d: "+ctim1+" e: "+ ctim2+" f: "+amount.trim()+" g: "+amount2.trim());
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
		
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
		return personalprofileservice.vaguelikeSelectCreatetwo(map);
	}
	
	/**
	 * 初审列表模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/firsttriallikeselect", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> cs(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName");
		String mobilephone = obj.getString("iphone");
		String idnumber = obj.getString("IDcard");
		String numbering = obj.getString("numbering");
		String ctim1 = obj.getString("start");
		String ctim2 = obj.getString("end");
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state");
		String rolename = obj.getString("rolename");
		String username = obj.getString("username");
		String city = obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
		System.out.println("a: "+name+" b: "+idnumber+" c: "+mobilephone+" d: "+ctim1+" e: "+ ctim2+" f: "+amount.trim()+" g: "+amount2.trim());
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
		
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
		
		return personalprofileservice.vaguelikeSelectCreatetwo2(map);
	}
	
	/**
	 * 终审列表模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/finalreviewselect", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> finalreview(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName");
		String mobilephone = obj.getString("iphone");
		String idnumber = obj.getString("IDcard");
		String numbering = obj.getString("numbering");
		String ctim1 = obj.getString("start");
		String ctim2 = obj.getString("end");
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state");
		String rolename = obj.getString("rolename");
		String username = obj.getString("username");
		String city = obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
		System.out.println("a: "+name+" b: "+idnumber+" c: "+mobilephone+" d: "+ctim1+" e: "+ ctim2+" f: "+amount.trim()+" g: "+amount2.trim());
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
		
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
		return personalprofileservice.vaguelikeSelectCreatetwo2(map);
	}

	
	/**
	 * 财务列表模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/financeselect", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> financeselect(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName");
		String mobilephone = obj.getString("iphone");
		String idnumber = obj.getString("IDcard");
		String numbering = obj.getString("numbering");
		String ctim1 = obj.getString("start");
		String ctim2 = obj.getString("end");
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state");
		String rolename = obj.getString("rolename");
		String username = obj.getString("username");
		String city = obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
		System.out.println("a: "+name+" b: "+idnumber+" c: "+mobilephone+" d: "+ctim1+" e: "+ ctim2+" f: "+amount.trim()+" g: "+amount2.trim());
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
		
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
		return personalprofileservice.vaguelikeSelectCreatetwo2(map);
	}
	
	/**
	 * 转账凭证列表模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/transferdocument", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> transferdocument(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName");
		String mobilephone = obj.getString("iphone");
		String idnumber = obj.getString("IDcard");
		String numbering = obj.getString("numbering");
		String ctim1 = obj.getString("start");
		String ctim2 = obj.getString("end");
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state");
		String rolename = obj.getString("rolename");
		String username = obj.getString("username");
		String city = obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
		System.out.println("a: "+name+" b: "+idnumber+" c: "+mobilephone+" d: "+ctim1+" e: "+ ctim2+" f: "+amount.trim()+" g: "+amount2.trim());
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
		
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
		return personalprofileservice.vaguelikeSelectCreatetwo2(map);
	}
	
	/**
	 * 取证凭证列表模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/forensicdocuments", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> forensicdocuments(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName");
		String mobilephone = obj.getString("iphone");
		String idnumber = obj.getString("IDcard");
		String numbering = obj.getString("numbering");
		String ctim1 = obj.getString("start");
		String ctim2 = obj.getString("end");
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state");
		String rolename = obj.getString("rolename");
		String username = obj.getString("username");
		String city = obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
		System.out.println("a: "+name+" b: "+idnumber+" c: "+mobilephone+" d: "+ctim1+" e: "+ ctim2+" f: "+amount.trim()+" g: "+amount2.trim());
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
		
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
		return personalprofileservice.vaguelikeSelectCreatetwo2(map);
	}

	
	/**
	 * 解押凭证列表模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/revocationcertificate", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> revocationcertificate(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName");
		String mobilephone = obj.getString("iphone");
		String idnumber = obj.getString("IDcard");
		String numbering = obj.getString("numbering");
		String ctim1 = obj.getString("start");
		String ctim2 = obj.getString("end");
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state");
		String rolename = obj.getString("rolename");
		String username = obj.getString("username");
		String city = obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
		System.out.println("a: "+name+" b: "+idnumber+" c: "+mobilephone+" d: "+ctim1+" e: "+ ctim2+" f: "+amount.trim()+" g: "+amount2.trim());
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
		
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
		return personalproFileService.vaguelikeSelectCreatetwo2(map);
	}
	/**
	 * 进押凭证列表模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/receivingcertificate", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> receivingcertificate(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName");
		String mobilephone = obj.getString("iphone");
		String idnumber = obj.getString("IDcard");
		String numbering = obj.getString("numbering");
		String ctim1 = obj.getString("start");
		String ctim2 = obj.getString("end");
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state");
		String rolename = obj.getString("rolename");
		String username = obj.getString("username");
		String city = obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
		System.out.println("a: "+name+" b: "+idnumber+" c: "+mobilephone+" d: "+ctim1+" e: "+ ctim2+" f: "+amount.trim()+" g: "+amount2.trim());
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
		
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
		return personalprofileservice.vaguelikeSelectCreatetwo2(map);
	}
	/**
	 * 回款确认列表模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/refundconfirmation", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> refundconfirmation(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName");
		String mobilephone = obj.getString("iphone");
		String idnumber = obj.getString("IDcard");
		String numbering = obj.getString("numbering");
		String ctim1 = obj.getString("start");
		String ctim2 = obj.getString("end");
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state");
		String rolename = obj.getString("rolename");
		String username = obj.getString("username");
		String city = obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
		System.out.println("a: "+name+" b: "+idnumber+" c: "+mobilephone+" d: "+ctim1+" e: "+ ctim2+" f: "+amount.trim()+" g: "+amount2.trim());
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
		
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
		return personalprofileservice.vaguelikeSelectCreatetwo2(map);
	}
	
	
	/**
	 * 结算凭证列表模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/settlementdocument", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> settlementdocument(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName");
		String mobilephone = obj.getString("iphone");
		String idnumber = obj.getString("IDcard");
		String numbering = obj.getString("numbering");
		String ctim1 = obj.getString("start");
		String ctim2 = obj.getString("end");
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state");
		String rolename = obj.getString("rolename");
		String username = obj.getString("username");
		String city = obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
		System.out.println("a: "+name+" b: "+idnumber+" c: "+mobilephone+" d: "+ctim1+" e: "+ ctim2+" f: "+amount.trim()+" g: "+amount2.trim());
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
		
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
		return personalproFileService.vaguelikeSelectCreatetwo2(map);
	}
	
	
	/**
	 * 贷款信息模糊查询
	 * @return json
	 */
	@RequestMapping(value = "/loaninformationselect", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Page<PersonalProfile> loaninformation(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		Page<PersonalProfile> resulit = new Page<PersonalProfile>();
		 String data = req.getParameter("data");
		 JSONObject obj = new JSONObject().fromObject(data);
		String name = obj.getString("userName").trim();
		String mobilephone = obj.getString("iphone").trim();
		String idnumber = obj.getString("IDcard").trim();
		String numbering = obj.getString("numbering").trim();
		String ctim1 = obj.getString("start").trim();
		String ctim2 = obj.getString("end").trim();
		String amount = obj.getString("min").trim();
		String amount2 = obj.getString("max").trim();
		String state = obj.getString("state").trim();
		String rolename = obj.getString("rolename").trim();
		String username = obj.getString("username").trim();
		String city = obj.getString("city").trim();
		String parentnodeId = obj.getString("parentnodeId").trim();
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("name", name);//姓名
		map.put("mobile_phone", mobilephone);//手机号码
		map.put("id_number", idnumber);//证件号码
		map.put("applicationnumber", numbering);
		map.put("ctime", ctim1);//申请时间1
		map.put("ctime2", ctim2);//申请时间2
		map.put("amount", amount.trim());//金额
		map.put("amount2", amount2.trim());//金额2
		map.put("state", state);//金额2
		map.put("rolename", rolename);//申请时间2
		map.put("username", username);//金额
		map.put("city", city);//金额2
		map.put("parentnodeId", parentnodeId);//金额2
		map.put("page", page);
		map.put("limit", limit);
//		map.put("state", state);//状态
//		PersonalProfile result = personalprofileservice.vaguelikeSelectCreate(map);
//		resultlist.setLists(result);
//		Page<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo(map);
//		 resulit.setLists(p);
		
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
//		List<PersonalProfile> p =	personalprofileservice.vaguelikeSelectCreatetwo2(map);
//		 resulit.setLists(p);
		if(state.contains("全部") && username.equals("admin")){
			return personalprofileservice.vaguelikeSelectCreatetwoAll(map);
		}else{
			return personalprofileservice.vaguelikeSelectCreatethere(map);
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
	 private String caseNo(Long id) {
	        String strId = id.toString();
	        int idLenth = 9 - strId.length();
	        StringBuilder builder = new StringBuilder();
//	        Calendar cal = Calendar.getInstance();
	        builder.append("(").append(DateUtils.getToDateTime((new Date()))).append(")");
	        builder.append("赎楼第");
	        for (int i = 0; i < idLenth; i++) {
	            builder.append("0");
	        }
	        builder.append(id);
	        builder.append("号");
	        return builder.toString();
	    }
}
