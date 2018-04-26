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
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.service.inter.NoteDescriptionService;
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
	private CircuLationRecordSubmitService recordSubmitService;

//	@ResponseBody
//	@RequestMapping("/notedescription")
//	public Json save(HttpServletRequest req) {
//
//		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
//		JSONObject obj = new JSONObject().fromObject(role_constant);
//		String note_Description1 = obj.getString("note");
//		String note_Description2 = obj.getString("recordFirst");
//		String note_Description3 = obj.getString("recorFinal");
//		String state = obj.getString("state");
//		String ctime = obj.getString("ctime");
//		String username = obj.getString("username");
//		String parentnodeId = obj.getString("parentnodeId");
//		String city = obj.getString("city");
//		String rolename = obj.getString("rolename");
//		int stateid =0;
//		NoteDescription note = new NoteDescription(note_Description1, note_Description2, note_Description3, state,
//				ctime, username, parentnodeId, city, rolename);
//		boolean notes = notedesc.save(note);// 鎻掑叆瑙掕壊
//
//		if (notes == true) {
//			logger.info("数据插入成功!");
//			return new Json(true, "success", notes);
//		} else {
//			logger.info("数据插入失败!");
//			return new Json(false, "fail", notes);
//		}
//		
//		//
//	}
	
	
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
		NoteDescription isResult = notedesc.findById(id);

		if (isResult != null) {
			return new Json(true, "success", isResult);
		} else
			return new Json(false, "fail", isResult);
	}

	/**
	 * 修改用户保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/NoteDescriptions", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String updateUser(HttpServletRequest req) {

		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		Integer id = json.getInt("id");

		String note_Description1 = json.getString("note");
		String note_Description2 = json.getString("recordFirst");
		String note_Description3 = json.getString("recorFinal");
		String state = json.getString("state");
		String ctime = json.getString("ctime");

		NoteDescription contact = new NoteDescription(id, note_Description1, note_Description2, note_Description3,
				state, ctime);
		boolean isResult = notedesc.update(contact);
		if (isResult == true) {
			return JSON.toJSONString(isResult);
		} else
			return JSON.toJSONString("fail");
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/notedescription", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String loannotesSubmita(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =0;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename);
		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审
	}

	/**
	 * 贷款创建备注提交到贷款初审
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotfirsts", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String loannotesSubmit(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =1;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审
	}

	/**
	 * 贷款初审备注提交到贷款终审
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotsubmit", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String loannotesSubmits(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =2;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审
	}

	/**
	 * 贷款终审备注提交到财务审批
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loanfinance", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String loanfinance(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =3;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审
	}
	
	/**
	 * 财务审批提交到转账凭证
	 * 
	 * @return
	 */
	@RequestMapping(value = "/transferaccounts", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String TransferAccounts(HttpServletRequest req) {
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String sid = obj.getString("id");
		int id = Integer.parseInt(sid);
		String fallbackname = obj.getString("note");
		String username = obj.getString("username");
		String rolename =obj.getString("rolename");
		String city =obj.getString("city");
		 String parentnodeId = obj.getString("parentnodeId");
		int stateid =4;
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename,id);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审
	}
	
	/**
	 * 转账凭证提交到取证凭证
	 * 
	 * @return
	 */
	@RequestMapping(value = "/obtainevidence", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String ObtainEvidence(HttpServletRequest req) {		
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
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审	
	}
	
	/**
	 * 取证凭证提交到解压
	 * 
	 * @return
	 */
	@RequestMapping(value = "/decompression", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String decompression(HttpServletRequest req) {
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
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审
	}
	
	
	/**
	 * 解压提交到进押
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enterintocustody", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String EnterIntoCustody(HttpServletRequest req) {
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
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审
	}
	
	/**
	 * 进押提交到确认回款
	 * 
	 * @return
	 */
	@RequestMapping(value = "/confirmation", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String Confirmation(HttpServletRequest req) {
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
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审
	}
	
	/**
	 * 确认回款提交到待结算
	 * 
	 * @return
	 */
	@RequestMapping(value = "/settlementsettled", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String settlementsettled (HttpServletRequest req) {
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
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审
	}
	/**
	 * 待结算提交到已结算
	 * 
	 * @return
	 */
	@RequestMapping(value = "/settlementsettleds", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String settlementsettleds(HttpServletRequest req) {
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
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename);
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功"); 
		} else {
			System.out.println("失败");
		}
 
		return "loan/loanCreateTable";// 提交到贷款初审
	}
	
	/**
	 * 贷款初审备注回退
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotFallback", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String loannotesFallback(HttpServletRequest req, HttpServletResponse response) {

		CircuLationRecord circuLationRecord = new CircuLationRecord();
		circuLationRecord.setState(0);// 退回后状态改为1
		String createDate = DateUtils.getInDateTime((new Date()));
		circuLationRecord.setCreateDate(createDate);
		circuLationRecord.setFallbackname("退回到按揭员------------------>");
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功");
		} else {
			System.out.println("失败");
		}
		return "loanfirst/loanFirstTable";

	}

	/**
	 * 贷款终审备注回退
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotllback", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String FallbackFirst(HttpServletRequest req, HttpServletResponse response) {

		CircuLationRecord circuLationRecord = new CircuLationRecord();
		circuLationRecord.setState(1);// 退回后状态改为1
		String createDate = DateUtils.getInDateTime((new Date()));
		circuLationRecord.setCreateDate(createDate);
		circuLationRecord.setFallbackname("退回到初审------------------>");
		boolean isResultInsert = recordSubmitService.updatefallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功");
		} else {
			System.out.println("失败");
		}
		return "loanfirst/loanFirstTable";

	}

}