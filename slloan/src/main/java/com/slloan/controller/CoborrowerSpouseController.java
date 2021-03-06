 package com.slloan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.Contacts;
import com.slloan.service.inter.CoborrowerSpouseService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;


/**
 * 共同借款人配偶信息
 * @author xue
 *
 */
@Controller(value="coborrowers")
@RequestMapping("/loan")
public class CoborrowerSpouseController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private CoborrowerSpouseService coborrowerSpouseService;
	@ResponseBody
	@RequestMapping("/commonApplyspouse")
	public Json save(HttpServletRequest req) {
		

	String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
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
		int sid = Integer.parseInt(state);
		Double monthly_Income = 0.0;
		if (salary.length() > 0) {
			monthly_Income = Double.parseDouble(salary);
		}

CoborrowerSpouse isResult =coborrowerSpouseService.SelectById(sid);
	if(isResult != null){
		CoborrowerSpouse coborrow = new CoborrowerSpouse(sid,name, id_Type,id_Other, id_Number, uni_Name,
				unit_Phone, home_Phone, mobile_Phone, monthly_Income,state,ctime );
				boolean isResults =coborrowerSpouseService.update(coborrow);
				if(isResults == true){
					return new Json(true,"success",isResults,"");//JSON.toJSONString(isResult);
				}else
					return new Json(false,"fail",isResults,"");
	}else{
		CoborrowerSpouse coborrows = new CoborrowerSpouse(name, id_Type,id_Other,id_Number, uni_Name,
				unit_Phone, home_Phone, mobile_Phone, monthly_Income,state,ctime);
		boolean co = coborrowerSpouseService.save(coborrows);// 鎻掑叆瑙掕壊

		if (co == true) {
			logger.info("数据插入成功!");
			return new Json(true,"success",co,state ); 
		} else {
			logger.info("数据插入失败!");
			return new Json(false,"fail",co); 
		}
	}
	}

	
	/***
	 * 根据ID查所有联系人信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/coborrowers",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req){
		System.out.println("+++++++++++++++++++++++++++++");
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		CoborrowerSpouse isResult =coborrowerSpouseService.SelectById(id);
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(true,"success",isResult); 
	}

	/**
	 * 初审修改用户保存
	 * @return
	 */
	@RequestMapping(value="/cobupdate",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json update(HttpServletRequest req){
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
		CoborrowerSpouse coborrow = new CoborrowerSpouse(id,name, id_Type,id_Other, id_Number, uni_Name,
				unit_Phone, home_Phone, mobile_Phone, monthly_Income,state,ctime );
		CoborrowerSpouse cob= coborrowerSpouseService.SelectById(stateid);
			if(cob !=null){
				boolean isResult =coborrowerSpouseService.update(coborrow);
				if(isResult == true){
					return new Json(true,"success",isResult,"");//JSON.toJSONString(isResult);
				}else
					return new Json(false,"fail",isResult,"");
			}else{
				CoborrowerSpouse coborrow2 = new CoborrowerSpouse(name, id_Type,id_Other,id_Number, uni_Name,
						unit_Phone, home_Phone, mobile_Phone, monthly_Income,state,ctime);
				boolean co = coborrowerSpouseService.save(coborrow2);
				if(co == true){
					return new Json(true,"success",co,"");//JSON.toJSONString(isResult);
				}else
					return new Json(false,"fail",co,"");
			}
		
		
	} 
	
	
	
	

	
	/**
	 * 共同申请人配偶
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loancomm")
	public String loancomm() {
		System.out.println("--------------------------");
		return "loan/commomMate";
	}


	/**
	 * 贷款初审共同借款人配偶
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loancomms")
	public String loancomms() {
		System.out.println("--------------------------");
		return "loanfirst/commomMate";
	}
	
	/**
	 * 贷款终审共同申请人配偶
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loancommss")
	public String loancommss() {
		System.out.println("--------------------------");
		return "loanfinal/commomMate";
	}

}
