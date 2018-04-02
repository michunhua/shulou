package com.slloan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.SpousesOfBorrowers;
import com.slloan.service.inter.PropertyInformationService;
import com.slloan.service.inter.SpousesOfBorrowersService;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

@Controller(value="spousesOfborrowerscontroller")
@RequestMapping("loan")
public class SpousesOfBorrowersController {

	@Autowired
	private SpousesOfBorrowersService spousesofborrowers;
	@ResponseBody
	@RequestMapping("/savewe.do")
	public String save(HttpServletRequest req) {

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
		String start =obj.getString("a");
		String ctime = obj.getString("b");
		

		SpousesOfBorrowers spouses = new SpousesOfBorrowers(name, id_Type, id_Number, uni_Name, unit_Phone, home_Phone,
				mobile_Phone, monthly_Income,start,ctime);

		boolean sp = spousesofborrowers.save(spouses);// 插入角色
		if (sp == true) {
			return JSON.toJSONString("success");
		} else {
			return JSON.toJSONString("fail");
		}
	}
}
