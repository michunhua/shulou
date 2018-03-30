package com.slloan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.service.inter.ApplyForLoanInformationService;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

@Controller(value="applyforLoanInformationcontroll")
@RequestMapping("/loan")
public class ApplyForLoanInformationController {

	@Autowired
	private ApplyForLoanInformationService applyForLoanInformationservice;

	/**
	 * 鐢宠鍊熸淇℃伅
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ApplyLoaninformation" ,method=(RequestMethod.GET),produces="application/json;charset=utf-8")
	@ResponseBody
	public String save(HttpServletRequest req) {
 
		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
		JSONObject obj = new JSONObject().fromObject(role_constant);
		String amount = obj.getString("amount"); // 閲戦
		String time_Limit = obj.getString("deadline"); // 鏈熼檺
		String borrowing_Variety = obj.getString("unit"); // 鍊熸鍝佺
		String repayment = obj.getString("variety"); // 杩樻鏂瑰紡
		String receiving_Bank_Name = obj.getString("manner"); // 鏀舵閾惰鍚嶇О
		String receiving_Account_Name = obj.getString("bank"); // 鏀舵璐︽埛鍚�
		String receiving_Account = obj.getString("accountName"); // 鏀舵璐﹀彿
		String repayment_Bank_Name = obj.getString("account"); // 杩樻閾惰鍚嶇О
		String repayment_Account_Name = obj.getString("repayBank"); // 杩樻璐︽埛鍚�
		String repayment_Account_Number = obj.getString("repayCcount"); // 杩樻璐﹀彿
		String start = obj.getString("start");
		String ctime = obj.getString("ctime");

		ApplyForLoanInformation ap = new ApplyForLoanInformation(amount, time_Limit, borrowing_Variety, repayment,
				receiving_Bank_Name, receiving_Account_Name, receiving_Account, repayment_Bank_Name,
				repayment_Account_Name, repayment_Account_Number,start,ctime);
		boolean rt = applyForLoanInformationservice.save(ap);// 鎻掑叆瑙掕壊

		if (rt == true) {
			return JSON.toJSONString("success");
		} else {
			return JSON.toJSONString("fail");
		}

	}
	


}
