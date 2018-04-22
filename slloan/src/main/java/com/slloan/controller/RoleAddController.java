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
	public Json selectById(HttpServletRequest req,HttpServletResponse response){
		String data = req.getParameter("data");
		JSONObject jsonobj = new JSONObject().fromObject(data);
		String rid = jsonobj.getString("id");
		int id = Integer.parseInt(rid);
		AddRole selectid = roleAddService.updateselectId(id);
			if(selectid !=null){
				logger.debug("成功从数据库找到数据"+selectid);
//				return new Json(true,"success",selectid);
				response.setCharacterEncoding("utf-8");
				return new Json(true,"success",selectid,"获取数据成功");
			}else{
				logger.debug("失败ID为null"+selectid);
				return new Json(false,"fail",selectid,"获取数据失败");
			}
	}
	/**
	 * 修改角色保存
	 */
	@RequestMapping(value="/updateroleadd",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Json updateId(HttpServletRequest req){
		String dataid = req.getParameter("data");
		JSONObject json = new JSONObject().fromObject(dataid);
		String username = json.getString("roleName");//用户名
		String belongs_City = json.getString("belongs_City");//员工姓名
		String descriPtion = json.getString("descriPtion");//分配角色
		String note = json.getString("note");//所属城市
		String id = json.getString("id");//id
		int idint = Integer.parseInt(id);
		String configuration = json.getString("configuration");//权限配置
		String updatedate = DateUtils.getInDateTime((new Date()));//日期
		AddRole addrole = new AddRole(username,descriPtion,belongs_City,note,configuration,updatedate,idint);
		
//		Map<Object,Object> map = new HashMap<Object,Object>();
//		map.put("rolename", username);
//		AddRole role= roleAddService.selectroleRoleName(map);
//			if(role !=null){
//				return JSON.toJSONString("修改保存角色名已存在插入失败");//new Json(false,"fail",role,"角色名已存在插入失败");
//			}else{
				 boolean isResult = roleAddService.updateRole(addrole);
				 if(isResult == true){
					 
					 return  new Json(true,"success",isResult,"修改角色保存成功");//JSON.toJSONString("success");
				 }else{
					 return new Json(true,"success",isResult,"修改角色保存失败");
				 }
//			}
		
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
		
		//先查询roleName权限表是否有同名否则插入失败
		
//		String rolenamee = req.getParameter("name");//查询角色名
//		Map<Object,Object> map = new HashMap<Object,Object>();
//		map.put("rolename", roleName);
//		AddRole role= roleAddService.selectroleRoleName(map);
//			if(role !=null){
//				return new Json(false,"fail",role,"角色名已存在插入失败");
//			}else{
				AddRole addrole = new AddRole(roleName,descriPtion,belongs_City,note,configuration,createDate);
				boolean rt = roleAddService.addRoleUser(addrole);//插入角色
				AddRole add = new AddRole();
				add.setRoleName(roleName);//角色
				add.setBelongs_City(belongs_City);//城市
				AddRole rid= roleAddService.selectByRId(add);
//				initrolecs(rid.getRoleName(),rid.getBelongs_City());
//				initrolecs(rid);
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
//									sb.append(jsonObject.getString(key));
									
									PermissionEntity permissionEntity = new PermissionEntity(jsonObject.getString(key),sr_id,url);
									 isResult = addpermissionservice.addPermission(permissionEntity);//插入权限表数据
									 System.out.println(sb.toString());
								}
								System.out.println(sb.toString());
//								sbString = sb.substring(1);  
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}	
						logger.info("插入角色成功");
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
//			}
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
	
	@RequestMapping(value = "/rolenameselect",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String rolename(HttpServletRequest req){
		String rolename = req.getParameter("rolename");
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("rolename", rolename);
		return  JSON.toJSONString(roleAddService.selectroleRoleName(map));
	}
}


//http://53873039oycg.iteye.com/blog/2215984