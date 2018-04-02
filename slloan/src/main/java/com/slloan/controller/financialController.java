package com.slloan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 财务审批
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="financial")
public class financialController {
	
	/**
	 * 结算凭证
	 */
	@RequestMapping(value="/settlementvoucher")
	public String settlementvoucher(){
		
		return "financial/clearProof";
	}
	/**
	 * 转账凭证
	 */
	@RequestMapping(value="/transfervoucher")
	public String transfervoucher(){
		
		return "financial/transferProof";
	}
	/**
	 * 审批列表
	 */
	@RequestMapping(value="/financeapproval")
	public String financeApproval(){
		
		return "financial/financeApproval/financeApproval";
	}
	/**
	 * 财务备注
	 */
	@RequestMapping(value="/financenote")
	public String financeNote(){
		
		return "financial/financeApproval/financeNote";
	}
}
