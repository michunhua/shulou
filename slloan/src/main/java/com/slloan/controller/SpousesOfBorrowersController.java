package com.slloan.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

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
import com.slloan.entity.SpousesOfBorrowers;
import com.slloan.service.inter.PropertyInformationService;
import com.slloan.service.inter.SpousesOfBorrowersService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;


/**
 * 借款人配偶
 * @author Administrator
 *
 */
@Controller(value="spousesOfborrowerscontroller")
@RequestMapping("/loan")
public class SpousesOfBorrowersController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private SpousesOfBorrowersService spousesofborrowers;
	@ResponseBody
	@RequestMapping("/loanApplyspouse")
	public Json save(HttpServletRequest req) {

		String role_constant = req.getParameter("data"); // 例如按揭员名
		JSONObject obj = new JSONObject().fromObject(role_constant);

		  String name = obj.getString("cname"); // 共同借款人配偶姓名戦
			String id_Type = obj.getString("certificate"); //身份证件类型
			String id_Other = obj.getString("certificateType"); //其他
			String id_Number =obj.getString("document"); // 身份证件号码
			String uni_Name = obj.getString("untilName"); // 工作单位名称
			String unit_Phone =obj.getString("untilPhone");// 单位电话
			String home_Phone =obj.getString("residence"); // 住宅电话
			String mobile_Phone = obj.getString("mobile"); // 移动电话
			String salary =obj.getString("salary");// 月薪（人民币）
		String state = obj.getString("temporaryId");//状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime = DateUtils.getInDateTime((new Date()));//日期
		Double monthly_Income = 0.0;
		if (salary.length() > 0) {
			monthly_Income = Double.parseDouble(salary);
		}

		SpousesOfBorrowers spouses = new SpousesOfBorrowers(name, id_Type, id_Other,id_Number, uni_Name, unit_Phone, home_Phone,
				mobile_Phone, monthly_Income,state,ctime);

		boolean sp = spousesofborrowers.save(spouses);// 插入角色
		if (sp == true) {
			logger.info("数据插入成功!");
			return new Json(true,"success",sp ); 
		} else {
			logger.info("数据插入失败!");
			return new Json(false,"fail",sp); 
		}
	}
	

	/***
	 * 根据ID查所有联系人信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/spouses",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req){
		
		
		System.out.println("======================================");
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		SpousesOfBorrowers isResult =spousesofborrowers.SelectById(id);
		
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
	}
	
	
	/**
	 * 初审修改用户保存
	 * @return
	 */
	@RequestMapping(value="/spoupdatea",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json spoupdate(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String name = json.getString("cname");  // 共同借款人配偶姓名戦
		String id_Type =  json.getString("certificate"); //身份证件类型
		String id_Other = json.getString("certificateType");
		String id_Number =json.getString("document");  // 身份证件号码
		String uni_Name =  json.getString("untilName"); // 工作单位名称
		String unit_Phone =json.getString("untilPhone"); // 单位电话
		String home_Phone =json.getString("residence"); // 住宅电话
		String mobile_Phone = json.getString("mobile"); // 移动电话
		String salary =json.getString("salary"); // 月薪（人民币）
		String state =String.valueOf(id); //状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		int stateid = Integer.parseInt(state);
		String ctime=DateUtils.getInDateTime(new Date());
		Double monthly_Income = 0.0;
		if (salary.length() > 0) {
			monthly_Income = Double.parseDouble(salary);
		}

		SpousesOfBorrowers coborrow = new SpousesOfBorrowers(id,name, id_Type,id_Other, id_Number, uni_Name,
				unit_Phone, home_Phone, mobile_Phone, monthly_Income,state,ctime );
		SpousesOfBorrowers sp = spousesofborrowers.SelectById(stateid);
		if(sp !=null){
			boolean isResult =spousesofborrowers.spoupdate(coborrow);
			if(isResult == true){
				return new Json(true,"success",isResult,"");//JSON.toJSONString(isResult);
			}else
				return new Json(true,"success",isResult,"");//JSON.toJSONString("fail");
		}else{
			SpousesOfBorrowers spouses = new SpousesOfBorrowers(name, id_Type, id_Other,id_Number, uni_Name, unit_Phone, home_Phone,
					mobile_Phone, monthly_Income,state,ctime);
			boolean spp = spousesofborrowers.save(spouses);// 插入角色
			if (spp == true) {
				logger.info("数据插入成功!");
				return new Json(true,"success",spp); 
			} else {
				logger.info("数据插入失败!");
				return new Json(false,"fail",spp); 
			}
		}
			
		
	} 
	
	/**
	 * 初审修改用户保存
	 * @return
	 */
	@RequestMapping(value="/spoupdates",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String spoupdates(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String name = json.getString("cname");  // 共同借款人配偶姓名戦
		String id_Type =  json.getString("certificate"); //身份证件类型
		String id_Other = json.getString("certificateType");
		String id_Number =json.getString("document");  // 身份证件号码
		String uni_Name =  json.getString("untilName"); // 工作单位名称
		String unit_Phone =json.getString("untilPhone"); // 单位电话
		String home_Phone =json.getString("residence"); // 住宅电话
		String mobile_Phone = json.getString("mobile"); // 移动电话
		String salary =json.getString("salary"); // 月薪（人民币）
		String state =json.getString("state"); //状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime=DateUtils.getInDateTime(new Date());
		Double monthly_Income = 0.0;
		if (salary.length() > 0) {
			monthly_Income = Double.parseDouble(salary);
		}
		SpousesOfBorrowers coborrow = new SpousesOfBorrowers(id,name, id_Type,id_Other, id_Number, uni_Name,
				unit_Phone, home_Phone, mobile_Phone, monthly_Income,state,ctime );
		boolean isResult =spousesofborrowers.spoupdate(coborrow);
		if(isResult == true){
			return JSON.toJSONString(isResult);
		}else
			return JSON.toJSONString("fail");
	} 
	/**
	 * 申请人配偶
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanerma")
	public String loanerma() {
		System.out.println("--------------------------");
		return "loan/loanerMate";
	}
	
	/**
	 * 贷款初审借款人配偶信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanermas")
	public String loanermas() {
		System.out.println("--------------------------");
		return "loanfirst/loanerMate";
	}

	
	/**
	 * 贷款终审申请人配偶
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanermass")
	public String loanermass() {
		System.out.println("--------------------------");
		return "loanfinal/loanerMate";
	}

}
