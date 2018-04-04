package com.slloan.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import javax.servlet.http.HttpServletRequest;

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
import com.slloan.service.inter.ApplyForLoanInformationService;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

@Controller(value="applyforLoanInformationcontroll")
@RequestMapping("/loan")
public class ApplyForLoanInformationController {

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
	public String save(HttpServletRequest req) {
 
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
		String start = obj.getString("start");//状态0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime = obj.getString("ctime");//更新时间

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
	
	/**
	 * 修改参数
	 */
	@RequestMapping(value="/modifyuser",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String update(ApplyForLoanInformation applyForLoanInformation){
		boolean isResult = applyForLoanInformationservice.update(applyForLoanInformation);
		if (isResult == true) {
			return JSON.toJSONString(isResult);
		}else{
			return JSON.toJSONString("fail");
		}
		
	}
	
	/**
	 * 删除数据
	 */
	@RequestMapping(value="")
	@ResponseBody
	public String delete(@RequestParam("id")Integer id){
		boolean isResult = applyForLoanInformationservice.delete(id);
		if (isResult == true) {
			return JSON.toJSONString(isResult);
		}else {
			return JSON.toJSONString(isResult);
		}
 	}

}
