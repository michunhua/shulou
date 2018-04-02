package com.slloan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.PropertyInformation;
import com.slloan.service.inter.PersonalProfileService;
import com.slloan.service.inter.PropertyInformationService;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

@Controller(value="propertyinformationcontroller")
@RequestMapping("loan")
public class PropertyInformationController {

	@Autowired
	private PropertyInformationService propertyinformationservice;
	@ResponseBody
	@RequestMapping("/saveq.do")
	public String save(HttpServletRequest req) {

		String role_constant = req.getParameter("data"); // 例如按揭员名
		JSONObject obj = new JSONObject().fromObject(role_constant);

		String ownership_And_percentage = obj.getString("owner"); // 权属人及占比
		String property_Address = obj.getString("accounting"); // 房产地址
		String conStruction_Area = obj.getString("propertyAddress"); // 建筑面积
		String inner_Area = obj.getString("building"); // 套内面积
		String sales_Contract_Number = obj.getString("innerArea"); // 买卖合同编号
		String certificate_of_Title = obj.getString("contract"); // 产权证号
		String proPerty_for = obj.getString("certificate"); // 房产用于
		String the_Assessed_Value = obj.getString("evaluation"); // 评估值
		String original_Loan_Bank = obj.getString("property"); // 原贷款银行
		String original_Loan_Amount = obj.getString("originalBank"); // 原贷款金额
		String loan_Outstanding_Balance = obj.getString("originalBank"); // 原贷款尚欠本息余额
		String house_Account = obj.getString("houseAccount"); // 供楼账号
		String transaCtion_Price = obj.getString("originalOwed"); // 买卖成交价
		String purchase_Deposit = obj.getString("bidPrice"); // 购房定金
		String supervision_of_funds = obj.getString("deposit"); // 资金监管
		String new_loan_Bank = obj.getString("newBank"); // 新贷款银行
		String new_Loan_Approval_Amount = obj.getString("funds"); // 新贷款批复金额
		String new_Loan_Bank_Account_Number = obj.getString("newAccount"); // 新贷款行账号
		String note_DescriPtion = obj.getString("newApproved"); // 备注
		String start=obj.getString("a");
		String ctime = obj.getString("b");

		PropertyInformation preperty = new PropertyInformation(ownership_And_percentage, property_Address,
				conStruction_Area, inner_Area, sales_Contract_Number, certificate_of_Title, proPerty_for,
				the_Assessed_Value, original_Loan_Bank, original_Loan_Amount, loan_Outstanding_Balance, house_Account,
				transaCtion_Price, purchase_Deposit, supervision_of_funds, new_loan_Bank, new_Loan_Approval_Amount,
				new_Loan_Bank_Account_Number, note_DescriPtion,start,ctime);

		boolean pr = propertyinformationservice.save(preperty);// 插入角色
		if (pr == true) {
			return JSON.toJSONString("success");
		} else {
			return JSON.toJSONString("fail");
		}

	}

}
