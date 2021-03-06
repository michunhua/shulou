package com.slloan.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slloan.entity.PersonalProfile;
import com.slloan.entity.circulation;
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.service.inter.PersonalProfileService;
import com.slloan.service.inter.circulationService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

@Controller(value="circulationcontroller")
@RequestMapping("/sumiteregresses")
public class CirculationContRoller {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	

	@Autowired
	private circulationService circulationservice;
	@Autowired 
	private CircuLationRecordSubmitService circuLationRecordSubmitService;
	
	@Autowired
	private	PersonalProfileService personalproFileService;
	
	
	@Value("${admin}")
	public String admin;
	/**
	 * 提交插入一条记录
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sumitadd",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public Json sumitadd(HttpServletRequest req) {

//		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
//		JSONObject obj = new JSONObject().fromObject(role_constant);
//		Integer id = obj.getInt("");
//		String state = obj.getString("");
//		String circulation = obj.getString("");
//		String ctime = obj.getString("");
//		String endTime = obj.getString("");
		
		String state = req.getParameter("state");
//		String circulation = req.getParameter("circulation");
		String ctime = DateUtils.getInDateTime2((new Date()));//日期
//		String ctime = DateUtils.getInDateTime2((new Date()));//日期
		String city= req.getParameter("city");
		String username = req.getParameter("username");
		String rolename = req.getParameter("rolename");
		String parentnodeId = req.getParameter("parentnodeId");
//		circulation record = new  circulation( state,circulation,ctime,username, parentnodeId,city,rolename);
		circulation record = new  circulation();
		record.setCity(city);
		record.setRolename(rolename);
		record.setParentnodeId(parentnodeId);
		record.setUsername(username);
		record.setCreatedata(ctime);
		record.setState(state);
//		record.setCirculation("------"+username+"------------>"+"贷款创建提交到贷款初审");
		boolean coan = circulationservice.save(record);// 鎻掑叆瑙掕壊
		if (coan == true) {
			logger.info("数据插入成功!");
			return new Json(true, "success", coan);
		} else {
			logger.info("数据插入失败!");
			return new Json(false, "fail", coan);
		}

	}
	
	/**
	 * 回退记录
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/regresses",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public Json regresses(HttpServletRequest req) {

//		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
//		JSONObject obj = new JSONObject().fromObject(role_constant);
//		Integer id = obj.getInt("");
//		String state = obj.getString("");
//		String circulation = obj.getString("");
//		String ctime = obj.getString("");
//		String endTime = obj.getString("");
		
		String state = req.getParameter("state");
//		String circulation = req.getParameter("circulation");
		String ctime = DateUtils.getInDateTime2((new Date()));//日期
//		String ctime = DateUtils.getInDateTime2((new Date()));//日期
		String city= req.getParameter("city");
		String username = req.getParameter("username");
		String rolename = req.getParameter("rolename");
		String parentnodeId = req.getParameter("parentnodeId");
//		circulation record = new  circulation( state,circulation,ctime,username, parentnodeId,city,rolename);
		circulation record = new  circulation();
		record.setCity(city);
		record.setRolename(rolename);
		record.setParentnodeId(parentnodeId);
		record.setUsername(username);
		record.setCreatedata(ctime);
		record.setState(state);
		record.setCirculation("----------"+username+"-------->"+"初审回退创建提交");
		boolean coan = circulationservice.save2(record);// 鎻掑叆瑙掕壊
		if (coan == true) {
			logger.info("数据插入成功!");
			return new Json(true, "success", coan);
		} else {
			logger.info("数据插入失败!");
			return new Json(false, "fail", coan);
		}

	}
	
	/**
	 * 查询记录
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectwhole",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public Json selectwhole(HttpServletRequest req) {

		String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
		JSONObject obj = new JSONObject().fromObject(role_constant);
//		Integer id = obj.getInt("");
//		String state = obj.getString("");
//		String circulation = obj.getString("");
//		String ctime = obj.getString("");
//		String endTime = obj.getString("");
		
//		String state = req.getParameter("state");
//		String circulation = req.getParameter("circulation");
//		String ctime = DateUtils.getInDateTime2((new Date()));//日期
//		String ctime = DateUtils.getInDateTime2((new Date()));//日期
		String city= obj.getString("city");
		String parentnodeId = obj.getString("parentnodeId");
		String username = obj.getString("username");
		String state = obj.getString("state");
		String id = obj.getString("id");//根据一条记录的ID查一条记录流转记录状态
		int uid = Integer.parseInt(id);
//		int stateid = Integer.parseInt(state);
		List<circulation> coan;
		List<String> coan2 = null;
		PersonalProfile p;
		if(username.contains(admin)){
//			circulation record = new  circulation( state,circulation,ctime,username, parentnodeId,city,rolename);
			circulation record = new  circulation();
			record.setSpare(id);
//			record.setCity(city);
			record.setParentnodeId(parentnodeId);
			record.setUsername(username);
			record.setId(uid);
			record.setState(state);
			
//			record.setCirculation("----------"+username+"-------->"+"初审回退创建提交");
			 coan = circulationservice.findById(record);// 鎻掑叆瑙掕壊
			List<circulation> selectAdminData = circulationservice.findById(record);// 鎻掑叆瑙掕壊
//			PersonalProfile param = new PersonalProfile(uid,username,city,"",parentnodeId,"")C;
			PersonalProfile param = new PersonalProfile();
			param.setId(uid);
			 p =personalproFileService.getSelectByIdAdmin(param);
		}else{
//			circulation record = new  circulation( state,circulation,ctime,username, parentnodeId,city,rolename);
			circulation record = new  circulation();
			record.setSpare(id);
			record.setCity(city);
			record.setParentnodeId(parentnodeId);
			record.setUsername(username);
			record.setId(uid);
			record.setState(state);
			
//			record.setCirculation("----------"+username+"-------->"+"初审回退创建提交");
			coan = circulationservice.findById(record);// 鎻掑叆瑙掕壊
			List<circulation> selectAdminData = circulationservice.findById(record);// 鎻掑叆瑙掕壊
//			PersonalProfile param = new PersonalProfile(uid,username,city,"",parentnodeId,"")C;
			PersonalProfile param = new PersonalProfile();
			param.setCity(city);
//    		param.setName(name);
    		param.setId(uid);
			 p=personalproFileService.getSelectById(param);

		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("a", coan);
		map.put("b", p);
		if (coan !=null) {
			if(coan.size()<1 || coan == null){
				logger.info("查询成功!");
//				map.put("a","更好的3");
//				coan2.add("更好的3");
//				coan2.add("更好的3");
//				coan2.add("更好的3");
//				coan2.add("更好的3");
//				map.put("a", coan2);
				return new Json(true, "success", map ,"--");
			}else{
				logger.info("查询成功!");
				return new Json(true, "success", map ,"--");
			}
			
		} else {
			logger.info("查询失败!");
			return new Json(false, "fail", "--");
		}

	}
}
