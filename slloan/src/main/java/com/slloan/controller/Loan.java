package com.slloan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author asus- 贷款管理
 *
 */
@Controller
@RequestMapping(value="loans")
public class Loan {
	
	/**
	 * 获取贷款数据
	 * @return
	 */
	@RequestMapping(value="/ApplyLoaninformation")
	public String commonApplydata(){
		
		return "loan/loanInfo";
	}
	
	@RequestMapping(value="/commonApplydata")
	public String commonApplydataw(){
		
		return "loan/loanerInfo";
	}
	
	
}
