package com.slloan.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

@Controller(value="circuLationrecordsubmitscontroller")
@RequestMapping(value="record")
public class CircuLationRecordSubmitSController {

	
	@Autowired
	private CircuLationRecordSubmitService recordSubmitService;
	
	@RequestMapping(value="/recordstateinsert",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json RecordSubmitInsert(HttpServletRequest req){
		String fallbackname = req.getParameter("fallbackname");
		String submit = req.getParameter("submit");
		String state = req.getParameter("state");
		int stateid = Integer.parseInt(state);
		String spare1 = req.getParameter("spare1");
		String username = req.getParameter("username");
		 String parentnodeId = req.getParameter("parentnodeId");
		 String city = req.getParameter("city");
		String rolename = req.getParameter("rolename");
		String createDate =  DateUtils.getInDateTime2((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename);
		
		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
		if(isResultInsert == true){
			return new Json(true,"success",isResultInsert,"");
		}else{
			return new Json(true,"fail",isResultInsert,"");
		}
		
		
	}

//	/**
//	 * 初审修改用户保存
//	 * @return
//	 */
//	@RequestMapping(value="/updatefallbackinsert",method=RequestMethod.POST,produces="application/json;charset=utf-8")
//	@ResponseBody
//	public String updatefallbackinsert(HttpServletRequest req){
//		String dataid = req.getParameter("data");
//		JSONObject json = new JSONObject().fromObject(dataid);
//		String fallbackname = json.getString("fallbackname");
//		String submit = json.getString("submit");
//		String state = json.getString("state");
//		int stateid = Integer.parseInt(state);
//		String spare1 = json.getString("spare1");
//		String username = json.getString("username");
//		 String parentnodeId = json.getString("parentnodeId");
//		 String city = json.getString("city");
//		String rolename = json.getString("rolename");
//		String createDate =  DateUtils.getInDateTime((new Date()));//日期
//		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,stateid,createDate,username,parentnodeId,city,rolename);
//		boolean isResult =recordSubmitService.updatefallbackinsert(circuLationRecord);
//		if(isResult == true){
//			return JSON.toJSONString(isResult);
//		}else
//			return JSON.toJSONString("fail");
//	} 
//	
}
