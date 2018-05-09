package com.slloan.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.NoteDescription;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.circulation;
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.service.inter.NoteDescriptionService;
import com.slloan.service.inter.PersonalProfileService;
import com.slloan.service.inter.circulationService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

@Controller(value = "notedescriptioncontroller")
@RequestMapping("/loan")
public class NoteDescriptionController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private NoteDescriptionService notedesc;
	
	@Autowired
	private CircuLationRecordSubmitService c;
	
	@Autowired
	private CircuLationRecordSubmitService recordSubmitService;
	
	@Autowired
	private circulationService circulationservice;
	@Autowired
	private	PersonalProfileService personalproFileService;

	@ResponseBody
	@RequestMapping("/notedescription")
	public Json save(HttpServletRequest req) {

		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
		JSONObject obj = new JSONObject().fromObject(role_constant);
		String note_Description1 = obj.getString("note");

		String state = obj.getString("state");
		String ctime =  DateUtils.getInDateTime((new Date()));//日期
		String username = obj.getString("username");
		String parentnodeId = obj.getString("parentnodeId");
		String city = obj.getString("city");
		String rolename = obj.getString("rolename");
		String submit = obj.getString("temporaryId");
		int stateid =0;
		NoteDescription note = new NoteDescription(note_Description1, state,ctime, username, parentnodeId, city, rolename,submit);
		boolean notes = notedesc.save(note);// 鎻掑叆瑙掕壊

		if (notes == true) {
			logger.info("数据插入成功!");
			return new Json(true, "success", notes);
		} else {
			logger.info("数据插入失败!");
			return new Json(false, "fail", notes);
		}
		
		//
	}
	


	
	
//	/**
//	 * 贷款创建备注提交到贷款初审
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/notedescription", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
//	public String loannotesSubmitss(HttpServletRequest req) {
//		String data = req.getParameter("data");
//		JSONObject obj = new JSONObject().fromObject(data);
//		String note_Description1 = obj.getString("note");
//		String note_Description2 = obj.getString("note");
//		String note_Description3 = obj.getString("note");
//		String state = obj.getString("state");
//		String ctime = obj.getString("ctime");
//		String username = obj.getString("username");
//		String parentnodeId = obj.getString("parentnodeId");
//		String city = obj.getString("city");
//		String rolename = obj.getString("rolename");
//		String fallbackname = obj.getString("note");
////		String username = obj.getString("username");
////		String rolename =obj.getString("rolename");
////		String city =obj.getString("city");
////		 String parentnodeId = obj.getString("parentnodeId");
//		int stateid =0;
//		String createDate =  DateUtils.getInDateTime((new Date()));//日期
//		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename);
//		boolean isResultInsert = recordSubmitService.fallbackinserts(circuLationRecord);
//		if (isResultInsert == true) {
//			System.out.println("插入流程表成功"); 
//		} else {
//			System.out.println("失败");
//		}
// 
//		return "loan/loanCreateTable";// 提交到贷款初审
//	}

	/***
	 * 根据ID查所有联系人信息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/notedescripti", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req) {

		System.out.println("======================================");
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);

		
		NoteDescription contactcd = notedesc.findById(id);

		if (contactcd != null) {
			return new Json(true, "success", contactcd);
		} else
			return new Json(false, "fail", contactcd);
	}

	/**
	 * 修改用户保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatenote", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateUser(HttpServletRequest req) {

		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
//		String id = json.getString("id");
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		String note_Description1 =json.getString("note");
		String note_Description2 =json.getString("recordFirst");
		String note_Description3 =json.getString("recorFinal");
		String note_Description4 =json.getString("recorfore");
		String state = json.getString("state");
		String ctime = DateUtils.getInDateTime((new Date()));//日期
		NoteDescription contact = new NoteDescription(id, note_Description1,note_Description2,note_Description3,note_Description4, state,ctime);
		boolean isResult = notedesc.update(contact);
		if (isResult == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResult,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResult,"");
		}
//		if (isResult == true) {
//			return JSON.toJSONString(isResult);
//		} else
//			return JSON.toJSONString("fail");
	}
	
	
	
	/**
	 * 初审用户保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatenotetwo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateUsertwo(HttpServletRequest req) {

		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
//		String id = json.getString("id");
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		String note_Description1 =json.getString("note");
		String note_Description2 =json.getString("recordFirst");
		String note_Description3 =json.getString("recorFinal");
		String note_Description4 =json.getString("recorfore");
		String state = json.getString("state");
		String ctime = DateUtils.getInDateTime((new Date()));//日期
		NoteDescription contact = new NoteDescription(id, note_Description1,note_Description2,note_Description3,note_Description4, state,ctime);
		boolean isResult = notedesc.updatetwo(contact);
		if (isResult == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResult,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResult,"");
		}
//		if (isResult == true) {
//			return JSON.toJSONString(isResult);
//		} else
//			return JSON.toJSONString("fail");
	}
	
	/**
	 * 初审用户保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatenotethere", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateUserthere(HttpServletRequest req) {

		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
//		String id = json.getString("id");
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		String note_Description1 =json.getString("note");
		String note_Description2 =json.getString("recordFirst");
		String note_Description3 =json.getString("recorFinal");
		String note_Description4 =json.getString("recorfore");
		String state = json.getString("state");
		String ctime = DateUtils.getInDateTime((new Date()));//日期
		NoteDescription contact = new NoteDescription(id, note_Description1,note_Description2,note_Description3,note_Description4, state,ctime);
		boolean isResult = notedesc.updatethere(contact);
		if (isResult == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResult,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResult,"");
		}
//		if (isResult == true) {
//			return JSON.toJSONString(isResult);
//		} else
//			return JSON.toJSONString("fail");
	}
	
	
	/**
	 * 初审用户保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatenotefore", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateUserfore(HttpServletRequest req) {

		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
//		String id = json.getString("id");
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		String note_Description1 =json.getString("note");
		String note_Description2 =json.getString("recordFirst");
		String note_Description3 =json.getString("recorFinal");
		String note_Description4 =json.getString("recorfore");
		String state = json.getString("state");
		String ctime = DateUtils.getInDateTime((new Date()));//日期
		NoteDescription contact = new NoteDescription(id, note_Description1,note_Description2,note_Description3,note_Description4, state,ctime);
		boolean isResult = notedesc.updatefore(contact);
		if (isResult == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResult,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResult,"");
		}
//		if (isResult == true) {
//			return JSON.toJSONString(isResult);
//		} else
//			return JSON.toJSONString("fail");
	}
	
	/**
	 * 按揭员保存
	 * 保存s
	 * @return
	 */
	@RequestMapping(value = "/notedescription", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json loannotesSubmita(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String fallbackname = "创建贷款完成";
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		String spare1 = obj.getString("note");//备注
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =0;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,null,stateid,createDate,username,parentnodeId,city,rolename,spare1);
		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
 
//		return "loan/loanCreateTable";// 提交到贷款初审
	}

	
	/**
	 * 贷款初审保存
	 * 保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/notedescriptionassc", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json loannotesSubmitabb(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String fallbackname = "待终审审批中";
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		String spare1 = obj.getString("note");//备注
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =1;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,null,stateid,createDate,username,parentnodeId,city,rolename,spare1);
		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
 
//		return "loan/loanCreateTable";// 提交到贷款初审
	}
	
	/**
	 * 贷款终审备注保存
	 * 保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/notedescriptionaweqc", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json loannotesSubmitabbqew(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String fallbackname = "待出账确认";
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		String spare1 = obj.getString("recorFinal");//备注
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid = 3;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,null,stateid,createDate,username,parentnodeId,city,rolename,spare1);
		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
 
//		return "loan/loanCreateTable";// 提交到贷款初审
	}

	/**
	 * 贷款创建备注提交到贷款初审
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotfirsts", method = RequestMethod.GET)
	@ResponseBody
	public Json loannotesSubmit(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = "待初审审批中";
		String spare1 = obj.getString("note");//备注
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =1;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,spare1,id);
		circulation record = new  circulation("1",fallbackname,createDate,username,parentnodeId,city,rolename);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		boolean coan = circulationservice.save(record);
		if (isResultInsert == true && coan == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
 
//		return "redirect:/loan/loancrea";// 提交到贷款初审
	}

	/**
	 * 贷款初审备注提交到贷款终审
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotsubmit", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json loannotesSubmits(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = "待终审审批中";
		String spare1 = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =2;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,spare1,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation("2",fallbackname,createDate,username,parentnodeId,city,rolename);
		boolean coan = circulationservice.save(record);
		
		if (isResultInsert == true && coan == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
 
//		return "loan/loanCreateTable";// 提交到贷款初审
	}

	/**
	 * 贷款终审备注提交到财务审批
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanfinance", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json loanfinance(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = "待出账确认";
		String spare1 = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =3;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,spare1,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation("3",fallbackname,createDate,username,parentnodeId,city,rolename);
		boolean coan = circulationservice.save(record);
		if (isResultInsert == true && coan == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
 
//		return "loan/loanCreateTable";// 提交到贷款初审
	}
	
	/**
	 * 财务审批提交到转账凭证
	 * 
	 * @return
	 */
	@RequestMapping(value = "/transferaccounts", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json TransferAccounts(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = "待放款";
		String spare1 = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =4;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,spare1,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation("4",fallbackname,createDate,username,parentnodeId,city,rolename);
		boolean coan = circulationservice.save(record);
		if (isResultInsert == true && coan == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
//		return "loan/loanCreateTable";// 提交到贷款初审
	}
	
	/**
	 * 转账凭证提交到取证凭证
	 * 
	 * @return
	 */
	@RequestMapping(value = "/obtainevidence", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json ObtainEvidence(HttpServletRequest req) {		
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =5;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation("5",fallbackname,createDate,username,parentnodeId,city,rolename);
		boolean coan = circulationservice.save(record);
	if (isResultInsert == true && coan == true) {
		System.out.println("插入流程表成功"); 
		return new Json(true,"success",isResultInsert,"");
	} else {
		System.out.println("失败");
		return new Json(false,"fail",isResultInsert,"");
	}
 
	}
	
	/**
	 * 取证凭证提交到解压
	 * 
	 * @return
	 */
	@RequestMapping(value = "/decompression", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json decompression(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =6;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation("6",fallbackname,createDate,username,parentnodeId,city,rolename);
		boolean coan = circulationservice.save(record);
		if (isResultInsert == true && coan == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
	}
	
	
	/**
	 * 解压提交到进押
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enterintocustody", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json EnterIntoCustody(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =7;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation("7",fallbackname,createDate,username,parentnodeId,city,rolename);
		boolean coan = circulationservice.save(record);
		if (isResultInsert == true && coan == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
	}
	
	/**
	 * 进押提交到确认回款
	 * 
	 * @return
	 */
	@RequestMapping(value = "/confirmation", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json Confirmation(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =8;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation("8",fallbackname,createDate,username,parentnodeId,city,rolename);
		boolean coan = circulationservice.save(record);
		if (isResultInsert == true && coan == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
	}
	
	/**
	 * 确认回款提交到待结算
	 * 
	 * @return
	 */
	@RequestMapping(value = "/settlementsettled", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json settlementsettled (HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =9;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation("9",fallbackname,createDate,username,parentnodeId,city,rolename);
		boolean coan = circulationservice.save(record);
		if (isResultInsert == true && coan == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
 
	}
	/**
	 * 待结算提交到已结算
	 * 
	 * @return
	 */
	@RequestMapping(value = "/settlementsettleds", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json settlementsettleds(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =10;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation("10",fallbackname,createDate,username,parentnodeId,city,rolename);
		boolean coan = circulationservice.save(record);
		if (isResultInsert == true && coan == true) {
			System.out.println("插入流程表成功"); 
			return new Json(true,"success",isResultInsert,"");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}
 
	}
	
	/**
	 * 贷款初审备注回退
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotFallback", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json loannotesFallback(HttpServletRequest req, HttpServletResponse response) {
		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
		JSONObject obj = new JSONObject().fromObject(role_constant);
		Integer id = obj.getInt("id");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		CircuLationRecord circuLationRecord = new CircuLationRecord();
		circuLationRecord.setFallbackname("初审审批回退");
		circuLationRecord.setState(0);// 退回后状态改为1
		circuLationRecord.setId(id);
		String updatedate = DateUtils.getInDateTime2((new Date()));
		circuLationRecord.setUpdatedate(updatedate);
		
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation();
			record.setState("0");
			record.setCirculation("初审审批回退");
			record.setUsername(username);
			record.setParentnodeId(parentnodeId);
			record.setCity(city);
			record.setRolename(rolename);
			record.setUpdatedata(updatedate);
		boolean coan = circulationservice.save2(record);
		if (isResultInsert == true && coan == true) {
			return new Json(true,"success",isResultInsert,"");//System.out.println("插入流程表成功");
		} else {
			System.out.println("失败");
			return new Json(false,"fail",isResultInsert,"");
		}

	}

	/**
	 * 贷款终审备注回退到初审
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotllback", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json FallbackFirst(HttpServletRequest req, HttpServletResponse response) {
		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
		JSONObject obj = new JSONObject().fromObject(role_constant);
		Integer id = obj.getInt("id");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		CircuLationRecord circuLationRecord = new CircuLationRecord();
		circuLationRecord.setFallbackname("初审审批回退");
		circuLationRecord.setState(1);// 退回后状态改为1
		circuLationRecord.setId(id);
		String updatedate = DateUtils.getInDateTime2((new Date()));
		circuLationRecord.setUpdatedate(updatedate);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		circulation record = new  circulation();
		record.setState("1");
		record.setCirculation("初审审批回退");
		record.setUsername(username);
		record.setParentnodeId(parentnodeId);
		record.setCity(city);
		record.setRolename(rolename);
		record.setUpdatedata(updatedate);
	boolean coan = circulationservice.save2(record);
		if (isResultInsert == true && coan == true) {
			return new Json(true,"success",isResultInsert,""); //System.out.println("插入流程表成功");
		} else {
			return new Json(false,"fail",isResultInsert,"");//System.out.println("失败");
		}
//		return "loanfirst/loanFirstTable";

	}

}