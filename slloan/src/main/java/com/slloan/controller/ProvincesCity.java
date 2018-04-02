package com.slloan.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slloan.entity.AddRole;
import com.slloan.service.inter.DistrictService;
import com.slloan.service.inter.RoleAddService;
import com.slloan.util.Json;

/**
 * 
 * @author asus-省市获取
 *
 */
@Controller("provinces")
//@RequestMapping(value="/provinces")
public class ProvincesCity {
	
	 @Resource
	    private DistrictService districtService;
	 
	 @Resource
	 private RoleAddService roleaddservice;
	 
	 /**
	  * 获取省列表
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value = "getProvinces")
	 @ResponseBody
	 public Object getProvinces() throws Exception {
	    return districtService.getProvinces();
	 }
	 
	 /**
	  * 获取市列表
	  * @param province
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value = "getCities")
	 @ResponseBody
	 public Object getCities(@RequestParam(value = "province") String province) throws Exception {
	    return districtService.getCities(province);
	 }
	 
	 
	 @RequestMapping(value="getRole")
	 @ResponseBody
	 public Json getRole(){
		 List<AddRole> rolelist = roleaddservice.list();
		 	if(rolelist !=null){
		 		return new Json(true,"success",true);
		 	}else
		 		return  new Json(false,"fail",null);
	 }
}
