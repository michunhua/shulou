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
import com.slloan.entity.Contacts;
import com.slloan.entity.PersonalProfile;
import com.slloan.service.inter.ContactsService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

/**
 * 联系人信息
 * 
 * @author xue
 *
 */
@Controller(value = "contactscont")
@RequestMapping("/loan")
public class ContactsController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ContactsService contactsservice;

	@ResponseBody
	@RequestMapping("/contactinformation")
	public Json save(HttpServletRequest req) {
		
		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
		JSONObject obj = new JSONObject().fromObject(role_constant);
		
		String contacts = obj.getString("linkf"); // 共同借款人配偶姓名戦
		String contacts1 = obj.getString("links"); //身份证件类型
		String contacts2 =obj.getString("linkt"); // 身份证件号码
		String relationship = obj.getString("linkfMate"); // 工作单位名称
		String relationship1 =obj.getString("linksMate");// 单位电话
		String relationship2 =obj.getString("linktMate"); // 住宅电话
		String c_Telephone = obj.getString("linkfPhone"); // 移动电话
		String c_Telephone1 =obj.getString("linksPhone");// 月薪（人民币）
		String c_Telephone2= obj.getString("linktPhone");//状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String state = obj.getString("temporaryId");
		String ctime=DateUtils.getInDateTime(new Date());
		int sid = Integer.parseInt(state);
		Contacts isResult =contactsservice.SelectById(sid);
		if(isResult != null){
			Contacts contact = new Contacts(sid,contacts, contacts1, contacts2, relationship, relationship1, relationship2, c_Telephone, c_Telephone1, c_Telephone2, state, ctime);
			
					boolean isResults =contactsservice.updateadd(contact);
					if(isResults == true){
						return new Json(true,"success",isResults,"");//JSON.toJSONString(isResult);
					}else{
						return new Json(false,"fail",isResults,"");
					}
		}else{
			Contacts coa = new Contacts(contacts, contacts1, contacts2, relationship, relationship1, relationship2, c_Telephone, c_Telephone1, c_Telephone2, state, ctime);
			boolean coan = contactsservice.save(coa);// 鎻掑叆瑙掕壊
			
			if (coan == true) {
				logger.info("数据插入成功!");
				return new Json(true,"success",coan,state ); 
			} else {
				logger.info("数据插入失败!");
				return new Json(false,"fail",coan); 
			}
		}

		
		
	}

	/***
	 * 根据ID查所有联系人信息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/contactasss", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req) {

		System.out.println("======================================");
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		Contacts isResult = contactsservice.SelectById(id);

		if (isResult != null) {
			return new Json(true, "success", isResult);
		} else
			return new Json(true, "success", isResult);
	}

	/**
	 * 修改用户保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/contaupdate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateUser(HttpServletRequest req) {

		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String contacts = json.getString("linkf"); // 联系人姓名
		String contacts1 = json.getString("links");// 联系人姓名
		String contacts2 = json.getString("linkt");// 联系人姓名
		String relationship = json.getString("linkfMate");// 联系人关系
		String relationship1 = json.getString("linksMate");// 联系人关系
		String relationship2 = json.getString("linktMate");// 联系人关系
		String c_Telephone = json.getString("linkfPhone");// 联系人电话
		String c_Telephone1 = json.getString("linksPhone");// 联系人电话
		String c_Telephone2 = json.getString("linktPhone");// 联系人电话
		String state = String.valueOf(id);
		int stateid = Integer.parseInt(state);
		String ctime = DateUtils.getInDateTime(new Date());
		Contacts contact = new Contacts(id, contacts, contacts1, contacts2, relationship, relationship1, relationship2,
				c_Telephone, c_Telephone1, c_Telephone2, state, ctime);
		Contacts con = contactsservice.SelectById(stateid);

		if (con != null) {
			boolean isResult = contactsservice.updateadd(contact);
			if (isResult == true) {
				return new Json(true, "success", isResult, "");// JSON.toJSONString(isResult);
			} else {
				return new Json(true, "success", isResult, "");
			}
		} else {
			Contacts coa = new Contacts(contacts, contacts1, contacts2, relationship, relationship1, relationship2,
					c_Telephone, c_Telephone1, c_Telephone2, state, ctime);
			boolean coan = contactsservice.save(coa);
			if (coan == true) {
				return new Json(true, "success", coan);
			} else {
				return new Json(false, "fail", coan);
			}
		}

	}

	/**
	 * 联系人信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanlink")
	public String loanlink() {
		System.out.println("--------------------------");
		return "loan/linkInfo";
	}

	/**
	 * 贷款初审联系人信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanlinks")
	public String loanlinks() {
		System.out.println("--------------------------");
		return "loanfirst/linkInfo";
	}

	/**
	 * 贷款终审联系人信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanlinkss")
	public String loanlinkss() {
		System.out.println("--------------------------");
		return "loanfinal/linkInfo";
	}

}
