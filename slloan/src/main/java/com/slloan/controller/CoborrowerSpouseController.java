 package com.slloan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.service.inter.CoborrowerSpouseService;
import com.slloan.util.Json;

import net.sf.json.JSONObject;


/**
 * 鍏卞悓鍊熸浜洪厤鍋�
 * @author xue
 *
 */
@Controller(value="coborrowerspousecontroller")
@RequestMapping("loan")
public class CoborrowerSpouseController {
	
	@Autowired
	private CoborrowerSpouseService coborrowerSpouseService;
	@ResponseBody
	@RequestMapping("/save.do")
	public String save(HttpServletRequest req) {
		

	String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
	JSONObject obj = new JSONObject().fromObject(role_constant);
//		String name = req.getParameter("cname"); // 閲戦
//		String id_Type = req.getParameter("paperwork"); // 鏈熼檺
//		String id_Number = req.getParameter("paperNumb"); // 鍊熸鍝佺 
//		String uni_Name = req.getParameter("unitName"); // 杩樻鏂瑰紡
//		String unit_Phone = req.getParameter("unitPhone"); // 鏀舵閾惰鍚嶇О
//		String home_Phone = req.getParameter("housePhone"); // 鏀舵璐︽埛鍚�
//		String mobile_Phone = req.getParameter("mobilePhone"); // 鏀舵璐﹀彿
//		String monthly_Income = req.getParameter("salary"); // 杩樻閾惰鍚嶇О
//		
////		String monthly_Income = String.valueOf( req.getParameter("tatle"));
//		String start = req.getParameter("1");
//		String ctime = req.getParameter("2");
		
		

	 Date now = new Date( );
     SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd.hh:mm:ss");
		String name = obj.getString("cname"); // 閲戦
		String id_Type = obj.getString("paperwork"); // 鏈熼檺
		String id_Number =obj.getString("paperNumb"); // 鍊熸鍝佺
		String uni_Name = obj.getString("unitName"); // 杩樻鏂瑰紡
		String unit_Phone =obj.getString("unitPhone");// 鏀舵閾惰鍚嶇О
		String home_Phone =obj.getString("housePhone"); // 鏀舵璐︽埛鍚�
		String mobile_Phone = obj.getString("mobilePhone"); // 鏀舵璐﹀彿
		String monthly_Income =obj.getString("salary");// 杩樻閾惰鍚嶇О
		
//		String monthly_Income = String.valueOf( req.getParameter("tatle"));
		String start = obj.getString("b");
		String ctime = obj.getString("a");
		CoborrowerSpouse coborrow = new CoborrowerSpouse(name, id_Type, id_Number, uni_Name,
				unit_Phone, home_Phone, mobile_Phone, monthly_Income,start,ctime );
		boolean co = coborrowerSpouseService.save(coborrow);// 鎻掑叆瑙掕壊

		if (co == true) {
			return JSON.toJSONString("success");
		} else {
			return JSON.toJSONString("fail");
		}

	}
//	@RequestMapping("/aa.do")
//	public String t(){
//		
//		return "tjuser";
//		
//	}
}
