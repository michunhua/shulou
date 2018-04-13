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
 * @author xue
 *
 */
@Controller(value="contactscont")
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
		
		String contacts = obj.getString("linkfName"); // 共同借款人配偶姓名戦
		String contacts1 = obj.getString("linksName"); //身份证件类型
		String contacts2 =obj.getString("linksName"); // 身份证件号码
		String relationship = obj.getString("linkfRelationship"); // 工作单位名称
		String relationship1 =obj.getString("linksRelationship");// 单位电话
		String relationship2 =obj.getString("linksRelationship"); // 住宅电话
		String c_Telephone = obj.getString("linkfPhone"); // 移动电话
		String c_Telephone1 =obj.getString("linkfPhone");// 月薪（人民币）
		String c_Telephone2= obj.getString("linkfPhone");//状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String start = obj.getString("start");
		String ctime = obj.getString("ctime"); //更新时间
		Contacts coa = new Contacts(contacts, contacts1, contacts2, relationship, relationship1, relationship2, c_Telephone, c_Telephone1, c_Telephone2, ctime, ctime);
		boolean coan = contactsservice.save(coa);// 鎻掑叆瑙掕壊
		
		if (coan == true) {
			logger.info("数据插入成功!");
			return new Json(true,"success",coan ); 
		} else {
			logger.info("数据插入失败!");
			return new Json(false,"fail",coan); 
		}
		
	}
	



	
	
	/***
	 * 根据ID查所有联系人信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/sssss",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req){
		
		
		System.out.println("======================================");
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		Contacts isResult =contactsservice.SelectById(id);
		
		if(isResult !=null){
			return new Json(true,"success",isResult); 
		}else
			return new Json(false,"fail",isResult); 
	}
	
//	/**
//	 * 修改用户保存
//	 * @return
//	 */
//	@RequestMapping(value="/modifyuser",method=RequestMethod.POST,produces="application/json;charset=utf-8")
//	@ResponseBody
//	public String updateUser(HttpServletRequest req){
//		String dataid = req.getParameter("datas");
//		JSONObject json = new JSONObject().fromObject(dataid);
//		String username = json.getString("username");//用户名
//		String employeeis_Name = json.getString("employeeisname");//员工姓名
//		String distribution_Role = json.getString("distributionrole");//分配角色
//		String belongs_City = json.getString("belongscity");//所属城市
//		String createDate = DateUtils.getInDateTime((new Date()));//日期
//		String note =json.getString("note");
//		UserLogin userlogin = new UserLogin(username,employeeis_Name,distribution_Role,belongs_City,note,createDate);
//		boolean isResult =userservice.updateaddUser(userlogin);
//		if(isResult == true){
//			return JSON.toJSONString(isResult);
//		}else
//			return JSON.toJSONString("fail");
//	}



	/**
	 * 修改用户保存
	 * @return
	 */
	@RequestMapping(value="/modifyusers",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateUser(HttpServletRequest req){
		
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");
		String contacts = json.getString("linkfName"); //联系人姓名
		String contacts1=json.getString("linkfName");//联系人姓名
		String contacts2=json.getString("linkfName");//联系人姓名
		String relationship=json.getString("linkfRelationship");//联系人关系
		String relationship1=json.getString("linkfRelationship");//联系人关系
		String relationship2=json.getString("linkfRelationship");//联系人关系
		String c_Telephone=json.getString("linkfPhone");//联系人电话
		String c_Telephone1=json.getString("linkfPhone");//联系人电话
		String c_Telephone2=json.getString("linkfPhone");//联系人电话
		String start=json.getString("start");
		String ctime=DateUtils.getInDateTime((new Date()));
		Contacts contact = new Contacts(id,contacts, contacts1, contacts2, relationship, relationship1, relationship2, c_Telephone, c_Telephone1, c_Telephone2, start, ctime);
		boolean isResult =contactsservice.updateadd(contact);
		if(isResult == true){
			return JSON.toJSONString(isResult);
		}else
			return JSON.toJSONString("fail");
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
