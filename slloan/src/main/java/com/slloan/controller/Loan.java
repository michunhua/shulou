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
	
	/**
	 * 贷款初审列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loanlist(HttpServletRequest req) {
		System.out.println("-----------初审列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getFirsttrialPage(statePos));
	}
	/**
	 * 贷款终审列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanfinalreviewlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loanFinalreviewlist(HttpServletRequest req) {
		System.out.println("-----------贷款终审列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getFinalreviewPage(statePos));
	}

	
	/**
	 * 贷款财务列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanfinancelist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loanfinancelist(HttpServletRequest req) {
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getFinancePagePage(statePos));
	}
	
	/**
	 * 转账凭证列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loantransferlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loantransferlist(HttpServletRequest req) {
		System.out.println("-----------转账凭证列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getTransferloanPage(statePos));
	}
	
	
	/**
	 * 结算凭证列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanjslist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String loanjslist(HttpServletRequest req) {
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getFinancePagePage(statePos));
	}
	
	/**
	 * 贷款信息查看列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/LoanInformation", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String LoanInformation(HttpServletRequest req) {
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getLoanInformation(statePos));
	}
	
	
	/**
	 * 回款确认列表
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/ReturnMoney", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String ReturnMoney(HttpServletRequest req) {
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getReturnMoney(statePos));
	}
	/**
	 * 取证凭证列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ObtainEvidence", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String ObtainEvidence(HttpServletRequest req) {
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getObtainEvidence(statePos));
	}
	/**
	 * 解压列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Decompression", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String Decompression(HttpServletRequest req) {
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getDecompression(statePos));
	}
	/**
	 * 进压列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/LoanPressure", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String LoanPressure(HttpServletRequest req) {
		System.out.println("-----------财务列表---------------");
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int statePos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);// statePos, int pageSize
		return JSON.toJSONString(personalprofileservice.getLoanPressure(statePos));
	}
}
