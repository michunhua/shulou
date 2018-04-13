package com.slloan.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

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
import com.slloan.entity.Contacts;
import com.slloan.entity.SpousesOfBorrowers;
import com.slloan.service.inter.PropertyInformationService;
import com.slloan.service.inter.SpousesOfBorrowersService;
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

		String name = obj.getString("cname"); // 借款人配偶姓名
		String id_Type = obj.getString("paperwork"); // 身份证件类型
		String id_Number = obj.getString("paperNumb"); // 身份证件号码
		String uni_Name = obj.getString("unitName"); // 单位名称
		String unit_Phone = obj.getString("residencePhone"); // 单位电话
		String home_Phone = obj.getString("unitPhone"); // 住宅电话
		String mobile_Phone = obj.getString("mobiePhone"); // 手机
		String monthly_Income = obj.getString("salary"); // 月收入
		String start =obj.getString("start");
		String ctime = obj.getString("ctime");
		

		SpousesOfBorrowers spouses = new SpousesOfBorrowers(name, id_Type, id_Number, uni_Name, unit_Phone, home_Phone,
				mobile_Phone, monthly_Income,start,ctime);

		boolean sp = spousesofborrowers.save(spouses);// 插入角色
		if (sp == true) {
			logger.info("数据插入成功!");
			return new Json(true,"success",sp ); 
		} else {
			logger.info("数据插入失败!");
			return new Json(false,"fail",sp); 
		}
	}
	
//	@RequestMapping(value="")	
//	@ResponseBody
//	
//	public String update(SpousesOfBorrowers spousesOfBorrowers){
//		boolean isResult = spousesofborrowers.update(spousesOfBorrowers);
//		if (isResult == true) {
//			return JSON.toJSONString(isResult);
//		}else {
//			return JSON.toJSONString(isResult);
//		}
//
//	}
//	
//	@RequestMapping(value = "")
//	@ResponseBody
//	
//	public String delete(@RequestParam("id")Integer id){
//		boolean isResult = spousesofborrowers.delete(id);
//		if (isResult == true) {
//			return JSON.toJSONString(isResult);
//		}else {
//			return JSON.toJSONString(isResult);
//		}
//	}
	
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
	
	@RequestMapping(value= "/spoupdate",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json updateadd(HttpServletRequest req,Contacts contactsparam){
		String name = req.getParameter("contacts");// 借款人配偶姓名
		String id_Type =req.getParameter("contacts"); // 身份证件类型
		String id_Number =req.getParameter("contacts"); // 身份证件号码
		String uni_Name = req.getParameter("contacts"); // 单位名称
		String unit_Phone = req.getParameter("contacts"); // 单位电话
		String home_Phone = req.getParameter("contacts"); // 住宅电话
		String mobile_Phone = req.getParameter("contacts"); // 手机
		String monthly_Income = req.getParameter("contacts"); // 月收入
		String start =req.getParameter("contacts");
		String ctime = req.getParameter("contacts");
		SpousesOfBorrowers spouses = new SpousesOfBorrowers(name, id_Type, id_Number, uni_Name, unit_Phone, home_Phone,
				mobile_Phone, monthly_Income,start,ctime);

			boolean isResult=	spousesofborrowers.spoupdate(spouses);
			if(isResult ==true){
				return  new Json(true,"success",isResult);
			}else{
				return  new Json(false,"fail",isResult);
			}
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
