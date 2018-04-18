package com.slloan.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.Date;

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
import com.slloan.dao.ApplyForLoanInformationDao;
import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.Contacts;
import com.slloan.service.inter.ApplyForLoanInformationService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

@Controller(value="applyforLoanInformationcontroll")
@RequestMapping("/loan")
public class ApplyForLoanInformationController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ApplyForLoanInformationService applyForLoanInformationservice;
	
	@Autowired
	private ApplyForLoanInformationDao appdao;

	/**
	 * 申请借款信息
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ApplyLoaninformation")
	@ResponseBody
	public Json save(HttpServletRequest req) {
 
		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
		JSONObject obj = new JSONObject().fromObject(role_constant);
		String amount = obj.getString("amount"); // 金额
		String time_Limit = obj.getString("deadline"); //期限
		String borrowing_Variety = obj.getString("unit"); //借款品种,赎楼
		String repayment = obj.getString("variety"); // 还款方式 0到期一次性还本付息1按月付息，到期还本
		String receiving_Bank_Name = obj.getString("manner"); // 收款银行名称
		String receiving_Account_Name = obj.getString("bank"); // 收款账户名
		String receiving_Account = obj.getString("accountName"); //收款账号
		String repayment_Bank_Name = obj.getString("account"); // 还款银行名称
		String repayment_Account_Name = obj.getString("repayBank"); // 还款账户名
		String repayment_Account_Number = obj.getString("repayCcount"); //还款账号
		String state = obj.getString("state");//状态0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime = DateUtils.getInDateTime((new Date()));//日期

		
		ApplyForLoanInformation ap = new ApplyForLoanInformation(amount, time_Limit, borrowing_Variety, repayment,
				receiving_Bank_Name, receiving_Account_Name, receiving_Account, repayment_Bank_Name,
				repayment_Account_Name, repayment_Account_Number,state,ctime);
		boolean rt = applyForLoanInformationservice.save(ap);// 鎻掑叆瑙掕壊


		if (rt == true) {
			logger.info("数据插入成功!");
			return new Json(true,"success",rt ); 
		} else {
			logger.info("数据插入失败!");
			return new Json(false,"fail",rt); 
		}
	}
	



	
	/***
	 * 根据ID查所有联系人信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/loanlinkfsss",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req){
		String dataid = req.getParameter("datas");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		ApplyForLoanInformation isResult =applyForLoanInformationservice.SelectById(id);
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
	}

	/**
	 * 修改用户保存
	 * @return
	 */
	@RequestMapping(value="/modifyuser",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateUser(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String amount= json.getString("contacts");
		String time_Limit= json.getString("contacts");
		String borrowing_Variety= json.getString("contacts");
		String repayment= json.getString("contacts");
		String receiving_Bank_Name= json.getString("contacts");
		String receiving_Account_Name= json.getString("contacts");
		String receiving_Account= json.getString("contacts");
		String repayment_Bank_Name= json.getString("contacts");
		String repayment_Account_Name= json.getString("contacts");
		String repayment_Account_Number= json.getString("contacts");
		String state= json.getString("contacts");
		String ctime=DateUtils.getInDateTime((new Date()));
		ApplyForLoanInformation ap = new ApplyForLoanInformation(id,amount, time_Limit, borrowing_Variety, repayment,
				receiving_Bank_Name, receiving_Account_Name, receiving_Account, repayment_Bank_Name,
				repayment_Account_Name, repayment_Account_Number,state,ctime);
		boolean isResult =applyForLoanInformationservice.appUpdate(ap);
		if(isResult == true){
			return JSON.toJSONString(isResult);
		}else
			return JSON.toJSONString("fail");
	}
	

	/**
	 * 申请借款资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanapply")
	public String loanapply() {
		System.out.println("--------------------------");
		return "loan/loanInfo";
	}

	/**
	 * 贷款初审借款资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanapplys")
	public String loanapplys() {
		System.out.println("--------------------------");
		return "loanfirst/loanInfo";
	}
	/**
	 * 贷款终审贷款申请信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanapplyss")
	public String loanapplyss() {
		System.out.println("--------------------------");
		return "loanfinal/loanInfo";
	}
}
