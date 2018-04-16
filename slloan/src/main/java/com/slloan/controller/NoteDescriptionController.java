package com.slloan.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.NoteDescription;
import com.slloan.service.inter.NoteDescriptionService;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

@Controller(value="notedescriptioncontroller")
@RequestMapping("/loan")
public class NoteDescriptionController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private NoteDescriptionService notedesc;
	
	@ResponseBody
	@RequestMapping("/notedescription")
	public Json save(HttpServletRequest req) {
		
		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
		JSONObject obj = new JSONObject().fromObject(role_constant);
		
		 	String note_Description1 =obj.getString("note");
		 	
			String state=obj.getString("state");
			String ctime=obj.getString("ctime");
			 NoteDescription note = new NoteDescription(note_Description1, state, ctime);
			 boolean notes = notedesc.save(note);// 鎻掑叆瑙掕壊
		

				if (notes == true) {
					logger.info("数据插入成功!");
					return new Json(true,"success",notes ); 
				} else {
					logger.info("数据插入失败!");
					return new Json(false,"fail",notes); 
				}
		
}
	
	
	
}