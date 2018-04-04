 package com.slloan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.service.inter.CoborrowerSpouseService;
import com.slloan.util.Json;

import net.sf.json.JSONObject;


/**
 * 共同借款人配偶信息
 * @author xue
 *
 */
@Controller(value="coborrowers")
@RequestMapping("loan")
public class CoborrowerSpouseController {
	
	@Autowired
	private CoborrowerSpouseService coborrowerSpouseService;
	@ResponseBody
	@RequestMapping("/commonApplyspouse")
	public String save(HttpServletRequest req) {
		

	String role_constant = req.getParameter("data"); // 渚嬪鎸夋彮鍛樺悕
	JSONObject obj = new JSONObject().fromObject(role_constant);

	 	Date now = new Date( );
     	SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd.hh:mm:ss");
		String name = obj.getString("cname"); // 共同借款人配偶姓名戦
		String id_Type = obj.getString("paperwork"); //身份证件类型
		String id_Number =obj.getString("paperNumb"); // 身份证件号码
		String uni_Name = obj.getString("unitName"); // 工作单位名称
		String unit_Phone =obj.getString("unitPhone");// 单位电话
		String home_Phone =obj.getString("housePhone"); // 住宅电话
		String mobile_Phone = obj.getString("mobilePhone"); // 移动电话
		String monthly_Income =obj.getString("salary");// 月薪（人民币）
		String start = obj.getString("b");//状态  0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime = obj.getString("a"); //更新时间
		CoborrowerSpouse coborrow = new CoborrowerSpouse(name, id_Type, id_Number, uni_Name,
				unit_Phone, home_Phone, mobile_Phone, monthly_Income,start,ctime );
		boolean co = coborrowerSpouseService.save(coborrow);// 鎻掑叆瑙掕壊

		if (co == true) {
			return JSON.toJSONString("success");
		} else {
			return JSON.toJSONString("fail");
		}

	}
	
	
	/**
	 * 根据id 删除用户
	 * @param req
	 * @return
	 */
	
//	@ResponseBody
//	@RequestMapping("/commonApplyspouse")
//	public String delete(@RequestParam("id")Integer id){
//		boolean isResult = coborrowerSpouseService.delete(id);
//		if (isResult == true) {
//			return JSON.toJSONString(isResult);
//		}else {
//			return JSON.toJSONString(isResult);
//		}
//		
//	}
//	
//	 /**  
//     * 获取所有用户列表  
//     * @param request  
//     * @return  
//     */  
//	@ResponseBody
//    @RequestMapping("/commonApplyspouse")  
//    public String getAllUser1(HttpServletRequest req,Model model){  
//		List<CoborrowerSpouse>  cobrr = coborrowerSpouseService.findAll();  
//        model.addAttribute("userList", cobrr);  
//        req.setAttribute("userList", cobrr);  
//        return "/loancreate";  
//    }  
//    
    
   
	
}
