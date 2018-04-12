package com.slloan.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slloan.entity.CircuLationRecord;
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

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
		int startid = Integer.parseInt(state);
		String spare1 = req.getParameter("spare1");
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		CircuLationRecord circuLationRecord = new CircuLationRecord(fallbackname,submit,startid,spare1,createDate);
		
		boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
		if(isResultInsert == true){
			return new Json(true,"success",isResultInsert,"");
		}else{
			return new Json(true,"fail",isResultInsert,"");
		}
		
		
	}
	
	
}
