package com.slloan.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.dao.FinaljudgmentDao;
import com.slloan.entity.NoteExplain;
import com.slloan.service.inter.ApplyForLoanInformationService;
import com.slloan.util.DateUtils;

import net.sf.json.JSONObject;

@Controller(value="publicCoroller")
@RequestMapping("/finaljudgmentcrollor")
public class FinaljudgmentCrollor {
	
	@Autowired
	FinaljudgmentDao dao;
	
	/**
	 * 修改用户保存
	 * @return
	 */
	@RequestMapping(value="/loannoteselectbyid",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateUser(HttpServletRequest req){
		String dataid = req.getParameter("datas");
		JSONObject json = new JSONObject().fromObject(dataid);
		String recordSingleNote = json.getString("recordSingleNote");//根据城市查备注
		String firstTrialNote = json.getString("firstTrialNote");
		String city = json.getString("city");//城市
		String parentnode = json.getString("parentnode");//父节点
//		String citydate = json.getString("citydate");
		String createDate = DateUtils.getInDateTime((new Date()));//日期
		
		NoteExplain noteExplain = new NoteExplain(recordSingleNote,firstTrialNote,city,parentnode,createDate);
		boolean isResult =dao.noteExplainAdd(noteExplain);
		if(isResult == true){
			return JSON.toJSONString(isResult);
		}else
			return JSON.toJSONString("fail");
	}
	
	/**
	 * 终审备注查看初审，按揭员
	 * @return
	 */
	@RequestMapping(value="/publicnote",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String publicnote(HttpServletRequest req){
		String dataid = req.getParameter("datas");
		JSONObject json = new JSONObject().fromObject(dataid);
//		String distribution_Role = json.getString("id");//根据ID查询
		String city = json.getString("city");//城市
//		String note =json.getString("note");
		boolean isResult =dao.selectByCityId(city);
		if(isResult == true){
			return JSON.toJSONString(isResult);
		}else
			return JSON.toJSONString("fail");
	}
	
}
