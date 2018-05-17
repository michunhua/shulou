package com.slloan.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.Contacts;
import com.slloan.entity.PropertyInformation;
import com.slloan.service.inter.PersonalProfileService;
import com.slloan.service.inter.PropertyInformationService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;
/**
 * 房产资料
 * @author Administrator
 *
 */
@Controller(value="propertyinformationcontroller")
@RequestMapping("/loan")
public class PropertyInformationController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private PropertyInformationService propertyinformationservice;
	@ResponseBody
	@RequestMapping("/housepropertydata")
	public Json save(HttpServletRequest req) {
		String role_constant = req.getParameter("data"); // 例如按揭员名
		JSONObject obj = new JSONObject().fromObject(role_constant);
		String name = obj.getString("owner");
		String ownership_And_percentage = obj.getString("owner"); // 权属人及占比
		String property_Address = obj.getString("accounting"); // 房产地址
		String conStruction_Area = obj.getString("propertyAddress"); // 建筑面积
		String inner_Area = obj.getString("building"); // 套内面积
		String sales_Contract_Number = obj.getString("innerArea"); // 买卖合同编号
		String certificate_of_Title = obj.getString("contract"); // 产权证号
		String proPerty_for = obj.getString("certificate"); // 房产用于
		String the_Assessed_Value = obj.getString("evaluation"); // 评估值
		String original_Loan_Bank = obj.getString("property"); // 原贷款银行
		String originalBank = obj.getString("originalBank"); // 原贷款金额
		String originalBanks = obj.getString("originalBank"); // 原贷款尚欠本息余额
		String house_Account = obj.getString("houseAccount"); // 供楼账号
		String originalOwed = obj.getString("originalOwed"); // 买卖成交价
		String bidPrice = obj.getString("bidPrice"); // 购房定金
		String supervision_of_funds = obj.getString("deposit"); // 资金监管
		String new_loan_Bank = obj.getString("newBank"); // 新贷款银行
		String funds = obj.getString("funds"); // 新贷款批复金额
		String new_Loan_Bank_Account_Number = obj.getString("newAccount"); // 新贷款行账号
		String note_DescriPtion = obj.getString("newApproved"); // 备注
		String state=obj.getString("temporaryId"); //状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime=DateUtils.getInDateTime(new Date());
		Double original_Loan_Amount = 0.0;
		if (originalBank.length() > 0) {
			original_Loan_Amount = Double.parseDouble(originalBank);
		}
		Double loan_Outstanding_Balance = 0.0;
		if (originalBanks.length() > 0) {
			loan_Outstanding_Balance = Double.parseDouble(originalBanks);
		}
		Double transaCtion_Price = 0.0;
		if (originalOwed.length() > 0) {
			transaCtion_Price = Double.parseDouble(originalOwed);
		}
		Double purchase_Deposit = 0.0;
		if (bidPrice.length() > 0) {
			purchase_Deposit = Double.parseDouble(bidPrice);
		}	
		Double new_Loan_Approval_Amount = 0.0;
		if (funds.length() > 0) {
			new_Loan_Approval_Amount = Double.parseDouble(funds);
		}
		PropertyInformation preperty = new PropertyInformation(name,ownership_And_percentage, property_Address,
				conStruction_Area, inner_Area, sales_Contract_Number, certificate_of_Title, proPerty_for,
				the_Assessed_Value, original_Loan_Bank, original_Loan_Amount, loan_Outstanding_Balance, house_Account,
				transaCtion_Price, purchase_Deposit, supervision_of_funds, new_loan_Bank, new_Loan_Approval_Amount,
				new_Loan_Bank_Account_Number, note_DescriPtion,state,ctime);
		boolean pr = propertyinformationservice.save(preperty);// 插入角色
		if (pr == true) {
			logger.info("数据插入成功!");
			return new Json(true,"success",pr ); 
		} else {
			logger.info("数据插入失败!");
			return new Json(false,"fail",pr); 
		}
		
	}


	/***
	 * 初审根据ID查所有联系人信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/pper",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req){
		System.out.println("+++++++++++++++++++++++++++++");
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		PropertyInformation isResult =propertyinformationservice.SelectById(id);
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
	}
	

/**
 * 初审修改
 * @param request
 * @param contactsparam
 * @return
 */
	@RequestMapping(value= "/proupdate",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json proupdate(HttpServletRequest request,Contacts contactsparam){
		String dataid = request.getParameter("data");
		JSONObject req = new JSONObject().fromObject(dataid);
		Integer id = req.getInt("id");
		String name = req.getString("owner");
		String ownership_And_percentage = req.getString("owner"); // 权属人及占比
		String property_Address = req.getString("accounting"); // 房产地址
		String conStruction_Area = req.getString("propertyAddress"); // 建筑面积
		String inner_Area = req.getString("building"); // 套内面积
		String sales_Contract_Number = req.getString("innerArea"); // 买卖合同编号
		String certificate_of_Title = req.getString("contract"); // 产权证号
		String proPerty_for = req.getString("certificate"); // 房产用于
		String the_Assessed_Value = req.getString("evaluation"); // 评估值
		String original_Loan_Bank = req.getString("property"); // 原贷款银行
		String originalBank = req.getString("originalBank"); // 原贷款金额
		String originalBanks = req.getString("originalBank"); // 原贷款尚欠本息余额
		String house_Account = req.getString("houseAccount"); // 供楼账号
		String originalOwed = req.getString("originalOwed"); // 买卖成交价
		String bidPrice = req.getString("bidPrice"); // 购房定金
		String supervision_of_funds = req.getString("deposit"); // 资金监管
		String new_loan_Bank = req.getString("newBank"); // 新贷款银行
		String funds = req.getString("funds"); // 新贷款批复金额
		String new_Loan_Bank_Account_Number = req.getString("newAccount"); // 新贷款行账号
		String note_DescriPtion = req.getString("newApproved"); // 备注
		String state=String.valueOf(id); //状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		int stateid = Integer.parseInt(state);
		String ctime = DateUtils.getInDateTime(new Date());
		Double original_Loan_Amount = 0.0;
		if (originalBank.length() > 0) {
			original_Loan_Amount = Double.parseDouble(originalBank);
		}
		Double loan_Outstanding_Balance = 0.0;
		if (originalBanks.length() > 0) {
			loan_Outstanding_Balance = Double.parseDouble(originalBanks);
		}
		Double transaCtion_Price = 0.0;
		if (originalOwed.length() > 0) {
			transaCtion_Price = Double.parseDouble(originalOwed);
		}
		Double purchase_Deposit = 0.0;
		if (bidPrice.length() > 0) {
			purchase_Deposit = Double.parseDouble(bidPrice);
		}	
		Double new_Loan_Approval_Amount = 0.0;
		if (funds.length() > 0) {
			new_Loan_Approval_Amount = Double.parseDouble(funds);
		}
		
		PropertyInformation preperty = new PropertyInformation(id,name,ownership_And_percentage, property_Address,
				conStruction_Area, inner_Area, sales_Contract_Number, certificate_of_Title, proPerty_for,
				the_Assessed_Value, original_Loan_Bank, original_Loan_Amount, loan_Outstanding_Balance, house_Account,
				transaCtion_Price, purchase_Deposit, supervision_of_funds, new_loan_Bank, new_Loan_Approval_Amount,
				new_Loan_Bank_Account_Number, note_DescriPtion,state,ctime);
			
			PropertyInformation pro= propertyinformationservice.SelectById(stateid);
			
			if(pro !=null){
				boolean isResult=	propertyinformationservice.proupdate(preperty);
				if(isResult ==true){
					return  new Json(true,"success",isResult);
				}else{
					return  new Json(false,"fail",isResult);
				}
			}else{
				PropertyInformation pre = new PropertyInformation(name,ownership_And_percentage, property_Address,
						conStruction_Area, inner_Area, sales_Contract_Number, certificate_of_Title, proPerty_for,
						the_Assessed_Value, original_Loan_Bank, original_Loan_Amount, loan_Outstanding_Balance, house_Account,
						transaCtion_Price, purchase_Deposit, supervision_of_funds, new_loan_Bank, new_Loan_Approval_Amount,
						new_Loan_Bank_Account_Number, note_DescriPtion,state,ctime);
				boolean pr = propertyinformationservice.save(pre);// 插入角色
				if(pr == true){
					return  new Json(true,"success",pr);
				}else{
					return  new Json(false,"fail",pr);
				}
			}
			
	}
	

	
	/**
	 * 贷款终审房产资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanestass")
	public String loanestass() {
		System.out.println("--------------------------");
		return "loanfinal/estateInfo";
	}
	
	/**
	 * 贷款初审房产资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanestas")
	public String loanestas() {
		System.out.println("--------------------------");
		return "loanfirst/estateInfo";
	}
	
	/**
	 * 房产资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanesta")
	public String loanesta() {
		System.out.println("--------------------------");
		return "loan/estateInfo";
	}

}
