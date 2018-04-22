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
import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.NoteDescription;
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.service.inter.CoborrowerSpouseService;
import com.slloan.service.inter.NoteDescriptionService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

@Controller(value="notedescriptioncontroller")
@RequestMapping("/loan")
public class NoteDescriptionController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private NoteDescriptionService notedesc;
	
	@Autowired
	private CircuLationRecordSubmitService recordSubmitService;
	
	@ResponseBody
	@RequestMapping("/notedescription")
	public Json save(HttpServletRequest req) {
	
		
		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
		JSONObject obj = new JSONObject().fromObject(role_constant);
		 	String note_Description1 =obj.getString("note");
		 	String note_Description2 =obj.getString("note");
		 	String note_Description3 =obj.getString("note");
			String state=obj.getString("state");
			String ctime=obj.getString("ctime");
			String username = obj.getString("username");
			 String parentnodeId = obj.getString("parentnodeId");
			 String city = obj.getString("city");
			String rolename = obj.getString("rolename");
//			 PersonalProfile p = new PersonalProfile();
//			 	p.setUsername("张三");
//			 	p.setParentnodeId("27");
//			 	p.setCity("北京");
//			 	String username ="张三";
//				 String parentnodeId = "27";
//				 String city ="北京";
//				 String rolename ="按揭员";
			 NoteDescription note = new NoteDescription(note_Description1,note_Description2,note_Description3,state, ctime,username,parentnodeId,city,rolename);
			 boolean notes = notedesc.save(note);// 鎻掑叆瑙掕壊
		

				if (notes == true) {
					logger.info("数据插入成功!");
					return new Json(true,"success",notes ); 
				} else {
					logger.info("数据插入失败!");
					return new Json(false,"fail",notes); 
				}
//		
}
	
	
	/**
	 * 贷款创建备注提交到贷款初审
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loannotfirsts", method = RequestMethod.GET)
	public String loannotesSubmit(HttpServletRequest req) {
		CircuLationRecord circuLationRecord = new CircuLationRecord();
		circuLationRecord.setState(1);// 退回后状态改为1
		String createDate = DateUtils.getInDateTime((new Date()));
		circuLationRecord.setCreateDate(createDate);
		circuLationRecord.setFallbackname("提交到贷款初审------------------>");
		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
		if (isResultInsert == true) {
			System.out.println("插入流程表成功");
		} else {
			System.out.println("失败");
		}

		return "loanfirst/loanFirstTable";// 提交到贷款初审
	}

}