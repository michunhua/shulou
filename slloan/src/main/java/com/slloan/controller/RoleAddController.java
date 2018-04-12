package com.slloan.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.AddRole;
import com.slloan.entity.Page;
import com.slloan.entity.PermissionEntity;
import com.slloan.entity.UserLogin;
import com.slloan.entity.UtilCity;
import com.slloan.service.inter.AddPermissionService;
import com.slloan.service.inter.DistrictService;
import com.slloan.service.inter.RoleAddService;
import com.slloan.service.inter.UserService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import io.netty.handler.codec.http.HttpResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.Iterator;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
/**
 * 角色添加
 * @author Administrator
 *
 */
@Controller(value="roleaddcontroller")
@RequestMapping("role")
public class RoleAddController {
	private static Logger logger = LoggerFactory.getLogger(RoleAddController.class);

	@Autowired
	public RoleAddService roleAddService;//添加角色

	@Autowired
	public DistrictService districtService;//得到城市
	
	@Autowired
	public UserService userservice;
	
	@Autowired
	public AddPermissionService addpermissionservice;//添加权限
	
	/***
	 * 得到所有城市
	 * @return
	 */
	@RequestMapping(value="getallcity",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getrole(){
		List<UtilCity> rolelist = districtService.getProvinces();
		if(rolelist.size()>0){
			 return JSON.toJSONString(rolelist);
		}else{
			logger.debug("获取城市失败:"+rolelist);
			 return JSON.toJSONString("fail");
		}
	}
	
	/**
	 *  角色列表
	 * @return json
	 */
	@RequestMapping(value="/rolemanagement",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String rolemanagement(HttpServletRequest req){
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int startPos= Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		System.out.println(page);
		System.out.println(limit);//startPos, int pageSize
//		roleAddService.getRolePage(startPos);
		//JSON.toJSONString(user)
		return JSON.toJSONString(roleAddService.getRolePage(startPos));
	}
	/**
	 * 添加角色
	 * @param addRole 参数
	 * @return json
	 *//*
	@RequestMapping(value="/addrole")
	public Json addrolethe(AddRole addRole){
		boolean rt = roleAddService.addRoleUser(addRole);
		if(rt ==true){
			
			return new Json(true,"success",rt);
		}else{
			return new Json(false,"fail",null);
		}
	}*/
	
	/**
	 * 批量删除角色
	 * @param addRole 参数
	 * @return json
	 */
	@RequestMapping(value="/batchdelrole",method=RequestMethod.POST)
	@ResponseBody
	public String delRole(HttpServletRequest request,HttpServletResponse response){
		String items = request.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(items);
		String id = obj.getString("id");
		String dt = id.replace("[\"", " ").replace("\"]", " ").replace("\"", "");
		List<String> delList = new ArrayList<String>();
		String[] strs = dt.split(",");
		for(String str:strs){
			System.out.println(str);
			delList.add(str);
		}
	boolean isResult = roleAddService.batchDeletes(delList);
			if(isResult == true){
				 return JSON.toJSONString("success");
			}else{
				logger.error("删除失败:"+isResult);
				 return JSON.toJSONString("fail");
			}
	}
	/**
	 * 页面跳转ID查询
	 * @param id 参数
	 * @return
	 */
	@RequestMapping(value="/selectbyid",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String selectById(@RequestParam("data") String id,HttpServletResponse response){
		int tzid = Integer.parseInt(id);
		AddRole selectid = roleAddService.updateselectId(tzid);
			if(selectid.getId() >0 || selectid.getId()!=null){
				logger.debug("成功从数据库找到数据"+selectid);
//				return new Json(true,"success",selectid);
				response.setCharacterEncoding("utf-8");
				return JSON.toJSONString(selectid);
			}else{
				logger.debug("失败ID为null"+selectid);
				return JSON.toJSONString("fail");
			}
	}
	/**
	 * 修改角色保存
	 */
	@RequestMapping(value="/updateadd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateId(HttpServletRequest req,AddRole addrole){
		 boolean isResult = roleAddService.updateRole(addrole);
		 if(isResult == true){
			 
			 return  JSON.toJSONString("success");
		 }else{
			 return  JSON.toJSONString("fail");
		 }
	}
	
	@RequestMapping(value="/abcdef")
	public  String ddd(){
		return "test002";
		
	}
	/**
	 * 添加权限
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/addpowerlimit",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json addpowerlimit(HttpServletRequest req ,PermissionEntity permission) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		List<PermissionEntity> rolePermissionId;
		 Map<String,String>roleParam = new HashMap<String,String>();
		//插入角色
		String role_constant = req.getParameter("data");//例如按揭员名
		JSONObject obj = new JSONObject().fromObject(role_constant);
		String roleName = obj.getString("name");//角色名
		String descriPtion = obj.getString("description");//角色描述
		String belongs_City = obj.getString("city");//所属城市
		String note = obj.getString("note");//备注
		String createDate = DateUtils.getInDateTime((new Date()));//日期
		String configuration = obj.getString("setPurview");//权限配置
		System.out.println("权限配置: "+configuration);
		List<String> ll = new ArrayList<String>();
		
		AddRole addrole = new AddRole(roleName,descriPtion,belongs_City,note,configuration,createDate);
		boolean rt = roleAddService.addRoleUser(addrole);//插入角色
		AddRole add = new AddRole();
		add.setRoleName(roleName);//角色
		add.setBelongs_City(belongs_City);//城市
		AddRole rid= roleAddService.selectByRId(add);
//		initrolecs(rid.getRoleName(),rid.getBelongs_City());
//		initrolecs(rid);
		ll.add(configuration);
		AddRole selectid ;
		List<String> l = new ArrayList<String>();
		try {
			String rolename = roleName;
			selectid= roleAddService.selectRoleId(rolename);
			Integer r_id =selectid.getId();
			String sr_id = r_id.toString();
			String url = req.getRequestURI();
			boolean isResult;
			if(selectid.getId() == 0 && selectid.getRoleName() == ""
		||selectid.getRoleName() == null && selectid.getId() <0){
				
			}else if(selectid.getId()>0 && selectid.getRoleName()!=null ||selectid.getRoleName()==""){
				/*String f0 = "power1";String f1 = "power2";
				String f2 = "power3";String f3 ="power4";
				String f4 ="power5";String f5 ="power6";
				String f6 ="power7";String f7 ="power8";
				String f8 ="power9";String f9 ="power10";
				String f10 ="power11";String f11 ="power12";
				String f12 ="power13";String f13 ="power14";
				String f14 ="power15";String f15 ="power16";
				String f16 ="power17";String f17 ="power18";
				String f18 ="power19";String f19 ="power20";
				String f20 ="power21";String f21 ="power22";
				String f22 ="power23";*/
				for (int i = 0; i < ll.size(); i++) {
					System.out.println(configuration);
					 com.alibaba.fastjson.JSONObject j = JSON.parseObject(configuration); 
					 //将JSON转换为KEY = value&key-value&...的形式
					 StringBuilder sb = new StringBuilder();
					 String sbString ="";
					 try {
						JSONObject jsonObject = new JSONObject().fromObject(configuration);
						Iterator iterator = jsonObject.keys();
						while(iterator.hasNext()){
							String key= (String)iterator.next();
//							sb.append(jsonObject.getString(key));
							
							PermissionEntity permissionEntity = new PermissionEntity(jsonObject.getString(key),sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
							 System.out.println(sb.toString());
						}
						System.out.println(sb.toString());
//						sbString = sb.substring(1);  
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}	
				
				
				
				/*String f1 = configuration.substring(11,12);
				String f2 = configuration.substring(24,25);
				String f3 = configuration.substring(37,38);
				String f4 = configuration.substring(50,51);
				String f5 = configuration.substring(63,64);
				String f6 = configuration.substring(76,77);
				String f7 = configuration.substring(89,90);
				String f8 = configuration.substring(102,103);
				String f9 = configuration.substring(115,116);
				String f10 = configuration.substring(128,130);
				String f11 = configuration.substring(142,144);
				String f12 = configuration.substring(156,158);
				String f13 = configuration.substring(170,172);
				String f14 = configuration.substring(184,186); 	   
				String f15 = configuration.substring(198,200);
				String f16 = configuration.substring(212,214); 
				String f17 = configuration.substring(226,228); 
				String f18 = configuration.substring(240,242); 
				String f19 = configuration.substring(254,256); 	   
				String f20 = configuration.substring(268,270);
				String f21 = configuration.substring(282,284); 
				String f22 = configuration.substring(296,298); 
				String f23 = configuration.substring(310,312);
				l.add(f12);l.add(f13);l.add(f14);l.add(f15);l.add(f16);
				l.add(f17);l.add(f18);l.add(f19);l.add(f20);l.add(f21);
				l.add(f22);l.add(f23);l.add(f1);l.add(f2);l.add(f3);
				l.add(f4);l.add(f5);l.add(f6);l.add(f7);
				l.add(f8);l.add(f9);l.add(f10);l.add(f11);*/
				logger.info("插入角色成功");
				
						
						/*if(l.contains(f3)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f3,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f4)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f4,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f5)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f5,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f6)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f6,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f7)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f7,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f8)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f8,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f9)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f9,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}
						if(l.contains(f10)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f10,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f11)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f11,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f12)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f12,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f13)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f13,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}
						if(l.contains(f14)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f14,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f15)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f15,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f16)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f16,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}
						if(l.contains(f17)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f17,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f18)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f18,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f19)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f19,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}
						if(l.contains(f20)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f20,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f21)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f21,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f22)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f22,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}if(l.contains(f23)==true){
							PermissionEntity permissionEntity = new PermissionEntity(f23,sr_id,url);
							 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
						}*/
						
			}else{
				String idrole = req.getParameter("id");
				Integer Convertid = Integer.getInteger(idrole);
				Map<String,Integer> param = new HashMap<String,Integer>();
					param.put("rid", Convertid);
					//判断权限ID是否存在
				selectid =	roleAddService.updateselectId(Convertid);
				if(selectid.getId()== null && selectid.getId() <0){
					String itemss = req.getParameter("权限表中的角色ID");
					List<String> delList = new ArrayList<String>();
					String[] strs = itemss.split(",");
					for(String str1:strs){
						delList.add(str1);
					}
					addpermissionservice.batchDeletes(delList);
				}
			}
			
		} catch (Exception e) {
			logger.debug("插入失败"+e);
			e.printStackTrace();
		}
		
		return new Json(true,"success");
	}
	/**
	 * 查询角色传值到用户
	 * 
	 */
	@RequestMapping(value="/initrole",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	private String initrolecs() {
		List<AddRole> add = roleAddService.list();
		if(add !=null){
			return JSON.toJSONString(add);
		}else{
			return JSON.toJSONString("fail");
		}
		
	
	}
	
	/**
	 * 查询城市传值到用户
	 * 
	 */
	@RequestMapping(value="/initcity",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	private String initrolecss(HttpServletRequest req,AddRole param) {
		String paramdata = req.getParameter("data");
		JSONObject jsonObject = new JSONObject().fromObject(paramdata);
		String roleName = jsonObject.getString("id");
		int rid = Integer.parseInt(roleName);
		AddRole addrole = new AddRole();
			addrole.setId(rid);
		AddRole add = roleAddService.accordingtoroleCity(addrole);
		return JSON.toJSONString(add);
	
	}

	
	private List jsobj(JSONObject jspurview) {
		List<String>list = new ArrayList<String>();
		String paymentConfirm = jspurview.getString("power0");//回款确认
		String loanendtrial = jspurview.getString("power1");//贷款终审
		String loancreate = jspurview.getString("power2");//贷款创建
		String loanfirsttrial = jspurview.getString("power3");//贷款初审
		String loaninformation = jspurview.getString("power4");//贷款信息查看
		String Obtainevidencevoucher = jspurview.getString("power5");//取证凭证上传
		String entervoucher = jspurview.getString("power6");//进押凭证上传
		String Thesolutionchargevoucher = jspurview.getString("power7");//解押凭证上传
		String financialapproval = jspurview.getString("power8");//财务审批
		String Loansp = jspurview.getString("power9");//放款审批
		String settlementvoucher = jspurview.getString("power10");//结算凭证上传
		String transfervoucher = jspurview.getString("power11");//转账凭证上传
		String grxxxg = jspurview.getString("power12");//个人信息修改
		String passwordmodify = jspurview.getString("power13");//密码修改
		String addrole = jspurview.getString("power14");//添加角色
		String modifyRole = jspurview.getString("power15");//修改角色信息
		String delRole = jspurview.getString("power16");//删除角色
		String distributionJurisdiction = jspurview.getString("power17");//分配权限
		String delJurisdiction = jspurview.getString("power18");//删除权限
		String addUser = jspurview.getString("power19");//添加用户
		String distributionRole = jspurview.getString("power20");//分配角色
		String delUser = jspurview.getString("power21");//删除用户
		String modifyUser = jspurview.getString("power22");//修改用户信息
		list.add(Thesolutionchargevoucher);list.add(passwordmodify);list.add(modifyRole);list.add(delRole);
		list.add(entervoucher);list.add(grxxxg);list.add(addrole);list.add(distributionJurisdiction);
		list.add(Obtainevidencevoucher);list.add(transfervoucher);list.add(delJurisdiction);
		list.add(loaninformation);list.add(paymentConfirm);list.add(addUser);
		list.add(loanfirsttrial);list.add(settlementvoucher);list.add(distributionRole);
		list.add(loancreate);list.add(Loansp);list.add(delUser);
		list.add(loanendtrial);list.add(financialapproval);list.add(modifyUser);
		return list;
	}

	/***
	 * 跳转到角色列表
	 * @return
	 */
	@RequestMapping(value = "rolelist")
	public String rolelist(){
		System.out.println("--------------------------");
		return "roleadd/roleList";
	}
	
	/***
	 * 修改角色权限
	 * @return
	 */
	@RequestMapping(value = "updaterole")
	public String updateRole(){
		System.out.println("--------------------------");
		return "roleadd/updateRole";
	}
	/***
	 * 修改角色权限
	 * @return
	 */
	@RequestMapping(value = "roleadd")
	public String roleadd(){
		//		return "redirect:/userManagement/addRole";
		return "roleadd/addRole";
	}
}


//http://53873039oycg.iteye.com/blog/2215984