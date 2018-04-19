package com.slloan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.service.inter.PersonalProfileService;


@RequestMapping("/loan")
public class Loan {
	

	@Autowired
	private PersonalProfileService personalprofileservice;
	

	/**
	 * 贷款信息查看列表
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/rolemanagement", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String rolemanagements(HttpServletRequest req) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int startPos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// startPos, int pageSize
		// roleAddService.getRolePage(startPos);
		// JSON.toJSONString(user)
		return JSON.toJSONString(personalprofileservice.getPersonalProfilePage(startPos));
	}
}
