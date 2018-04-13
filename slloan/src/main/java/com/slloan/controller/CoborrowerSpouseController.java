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
		String id_Type = obj.getString("paperwork"); //身份证件类型
		String id_Other = obj.getString("other");
		String id_Number =obj.getString("paperNumb"); // 身份证件号码
		String uni_Name = obj.getString("mobilePhone"); // 工作单位名称
		String unit_Phone =obj.getString("unitName");// 单位电话
		String home_Phone =obj.getString("unitPhone"); // 住宅电话
		String mobile_Phone = obj.getString("housePhone"); // 移动电话
		String monthly_Income =obj.getString("salary");// 月薪（人民币）
		String start = obj.getString("start");//状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime = obj.getString("catime"); //更新时间
		CoborrowerSpouse coborrow = new CoborrowerSpouse(name, id_Type,id_Other,id_Number, uni_Name,
				unit_Phone, home_Phone, mobile_Phone, monthly_Income,start,ctime);
		boolean co = coborrowerSpouseService.save(coborrow);// 鎻掑叆瑙掕壊

		if (co == true) {
			logger.info("数据插入成功!");
			return new Json(true,"success",co ); 
		} else {
			logger.info("数据插入失败!");
			return new Json(false,"fail",co); 
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
			return new Json(false,"fail",isResult); 
	}

	/**
	 * 修改用户保存
	 * @return
	 */
	@RequestMapping(value="/cobupdate",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String update(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		
		String name = req.getParameter("cname");  // 共同借款人配偶姓名戦
		String id_Type =  req.getParameter("paperwork"); //身份证件类型
		String id_Number =req.getParameter("paperNumb");  // 身份证件号码
		String uni_Name =  req.getParameter("mobilePhone"); // 工作单位名称
		String unit_Phone =req.getParameter("unitName"); // 单位电话
		String home_Phone =req.getParameter("unitPhone"); // 住宅电话
		String mobile_Phone = req.getParameter("housePhone"); // 移动电话
		String monthly_Income =req.getParameter("salary"); // 月薪（人民币）
		String start =req.getParameter("start"); //状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime=DateUtils.getInDateTime((new Date()));
		CoborrowerSpouse coborrow = new CoborrowerSpouse(name, id_Type, id_Number, uni_Name,
				unit_Phone, home_Phone, mobile_Phone, monthly_Income,start,ctime );
		boolean isResult =coborrowerSpouseService.update(coborrow);
		if(isResult == true){
			return JSON.toJSONString(isResult);
		}else
			return JSON.toJSONString("fail");
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
