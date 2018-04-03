package com.slloan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.entity.AddRole;
import com.slloan.entity.Page;
import com.slloan.entity.PermissionEntity;
import com.slloan.entity.UserLogin;
import com.slloan.service.inter.AddPermissionService;
import com.slloan.service.inter.LoginService;
import com.slloan.service.inter.RoleAddService;
import com.slloan.service.inter.UserService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;
import javax.servlet.http.HttpSession;
/**
 * 用户列表
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userservice;
	@Autowired
	private RoleAddService roleAddService;
	@Autowired
	private AddPermissionService addPermissionService;
	@Autowired
	HttpServletRequest req;
	/**
	 * 用户列表展示
	 * @return
	 */
	@RequestMapping(value="/userlist",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String userLlist(HttpServletRequest req){
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int startPos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		return JSON.toJSONString(userservice.getUserByPage(startPos));
	}
	/**
	 * 初始化角色与城市
	 */
	/*@RequestMapping(value="/initrole",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String initrolecs(AddRole rid){
		AddRole addrole = new AddRole();
			addrole.setRoleName(rid.getRoleName());
			addrole.setBelongs_City(rid.getBelongs_City());
		AddRole add = userservice.accordingtoroleCity(addrole);
		return JSON.toJSONString(add);
	}*/
	/**
	 * 用户添加
	 * @return
	 */
	@RequestMapping(value="/adduser",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String addUser(HttpServletRequest req,UserLogin userlogin){
		boolean isResult =false;
		try {
			String json = req.getParameter("data");
			JSONObject jsonobj = new JSONObject().fromObject(json);
			String username = jsonobj.getString("userName");
			String password = jsonobj.getString("password");
			String employeeis_Name = jsonobj.getString("employee");//员工姓名
			String distribution_Role = jsonobj.getString("role");//分配角色
			String belongs_City = jsonobj.getString("city");//所属城市
			String note = jsonobj.getString("note");
			String createDate = DateUtils.getInDateTime((new Date()));//日期
			AddRole add = new AddRole();
			add.setRoleName(distribution_Role);//角色
			add.setBelongs_City(belongs_City);//城市
			AddRole rid= roleAddService.selectByRId(add);
//			initrolecs(rid);
			UserLogin u = new UserLogin(username,password,employeeis_Name,distribution_Role,belongs_City,note,rid.getId(),createDate);
			 isResult=	userservice.addUser(u);
			
		} catch (Exception e) {
			logger.error("连接异常"+e);
		}
		if(isResult == true){
			logger.info("添加用户成功");
			return JSON.toJSONString("success");
		}else
			logger.debug("添加用户失败");
			return JSON.toJSONString("fail");
		
	}
	/***
	 * 得到所有角色
	 * @return
	 */
	@RequestMapping(value="/getrole")
	@ResponseBody
	public Json getrole(){
		List<UserLogin> rolelist = userservice.list();
		if(rolelist !=null){
			return new Json(true,"success",rolelist);
		}else
		    return new Json(false,"fail",null);
	}
	/***
	 * 得到所属城市与角色
	
	@RequestMapping(value="/getprovince.do")
	public Json getprovince(){
			Map<String,String> p = new HashMap<String,String>();
			p.put("rolename", "");
		userservice.accordingtoroleCity(param);
	}
	 */
	
	/**
	 * 删除用户
	 * @return
	 */
	@RequestMapping(value="/deluser")
	@ResponseBody
	public String delUser(@RequestParam("id")Integer id){
		boolean isResult =userservice.deleteById(id);
		if(isResult == true){
			return JSON.toJSONString(isResult); 
		}else
			return JSON.toJSONString(isResult); 
	}
	/**
	 * 修改用户
	 * @return
	 */
	@RequestMapping(value="/modifyuser",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateUser(UserLogin userlogin){
		boolean isResult =userservice.updateaddUser(userlogin);
		if(isResult == true){
			return JSON.toJSONString(isResult);
		}else
			return JSON.toJSONString("fail");
	}
	
	/**
	 * 修改查询
	 * @param id 参数
	 * @return
	 */
	@RequestMapping(value="/modifselect",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateselectId(@RequestParam("id") Integer id){
		UserLogin isResult = userservice.updateselectId(id);
			if(isResult != null){
				return JSON.toJSONString(isResult);
			}else
				logger.info("修改失败");
				return JSON.toJSONString(isResult);
	}
	
	/**
	 * 登录
	 * @param id 参数
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=utf-8")
	@ResponseBody
	public Object updateselectId(HttpSession session,HttpServletRequest req,HttpServletResponse response) throws IOException{
		String username = req.getParameter("username");
		String password  = req.getParameter("password");
		List<Object> li = new ArrayList<Object>();
		UserLogin user = new UserLogin(username,password);
		UserLogin userlogin = userservice.logindelu(user);
		AddRole addrole = new AddRole();
		PermissionEntity permission = new PermissionEntity();
		UserLogin pageBean = new UserLogin();
		addrole.setId(userlogin.getR_id());
		addrole.setRoleName(userlogin.getDistribution_Role());//分配角色
		addrole.setBelongs_City(userlogin.getBelongs_City());//所属城市
		List<AddRole> listrole= roleAddService.find(addrole);
		for(AddRole role : listrole){
			li.add(role);
			int zh =role.getId();
			String zhrole = String.valueOf(zh);
			permission.setR_id(zhrole);
		}
		
		List<PermissionEntity> rolepermission=	addPermissionService.find(permission);
			for(PermissionEntity entity : rolepermission){
				li.add(entity);
			}
		//		li.add(addrole);
		li.add(userlogin);
	
//		List<AddRole> r = neW ARRAYLIST<ADDROLE>();
//		LIST<USERLOGIN> DD = new ArrayList<UserLogin>();
//		userlogin.setUserRole(r);
		if(userlogin !=null){
			 ServletContext application=session.getServletContext();
	            Map<String, String> loginMap = (Map<String, String>)application.getAttribute("loginMap");
			if(loginMap == null){
				loginMap =new HashMap<>();
			}
			for(String key:loginMap.keySet()){
				if(userlogin.getUserName().equals(key)){
					if(session.getId().equals(loginMap.get(key))){
						 System.out.println("在同一地点多次登录");
						return JSON.toJSONString(username+"在同一地点多次登录！");
					}else{
//						response.sendRedirect("/signin");
						 System.out.println("异地登录被拒绝！");
						return JSON.toJSONString(username+"异地登录被拒绝！该用户已经登录！");
						
					}
				}
			}
			 loginMap.put(userlogin.getUserName(),session.getId());
			 application.setAttribute("loginMap", loginMap);
			 session.setAttribute("username",user.getUserName());
			 pageBean.setUserRole(li);
			return  JSON.toJSONString("登录成功！"+pageBean);
		}else{
			//登录失败
            System.out.println("登录失败！");
            session.setAttribute("tip","登录失败！");
            return JSON.toJSON(username+"登录失败");
		}
		
	}

	/***
	 * 密码修改
	 * @param newpassword 新密码
	 * @param username 用户名
	 * @param oldpassword 旧密码
	 * @return
	 */
	@RequestMapping(value="/updatepwd",method=(RequestMethod.POST),produces="application/json;charset=utf-8")
	@ResponseBody
	public String updatepwd(HttpServletRequest req){
		
		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String username = obj.getString("username");
		String passWord = obj.getString("oldpassword");
		String newpassword = obj.getString("newpassword");
//		UserLoginUpdate updateupdate = new UserLoginUpdate();
		boolean isResult = userservice.updatePassWord(username, newpassword, passWord);
			if(isResult == true){
				return JSON.toJSONString(isResult);
			}else
				return JSON.toJSONString("旧密码或用户名不正确");
	}
	
	/**
	 * 用户列表分页
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/pagefy",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String pagefy(HttpServletRequest req){
		 String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		List<UserLogin> listuser= new ArrayList<UserLogin>();  
		 int totalCount = (int) userservice.getCount();  
		 int startPos= Integer.parseInt(page);
			int pageSize = Integer.parseInt(limit);
		/* if(pageNow !=null){
			 page = new PageBean(totalCount,Integer.parseInt(pageNow));
			 userservice.getUserByPage(page.getStartPos(), page.getPageSize());
			 return new Json(true,"success",page);
		 }else if(pageNow ==null){
			 page = new Page(totalCount, 1);
			 userservice.getUserByPage(page.getStartPos(), page.getPageSize());
			 return new Json(true,"success",page);
		 }else{
			 return new Json(true,"success",listuser);
		 }*/
		 	
			return JSON.toJSONString(userservice.getRolePage(startPos));
		/*
		String id = req.getParameter("1");
		//每页显示的条数
		int pagesize =page.getPageSize();
		int pageTimes;
		int startRow =(Integer.parseInt(id)-1)*pagesize;
		List<UserLogin>users=userservice.list();
		users = userservice.getUserByPage(startRow, pagesize);
		if(users.size()% pagesize  ==0){
			pageTimes = users.size()/pagesize;
		}else{
			pageTimes = users.size()/pagesize +1;
		}
		if(users.size()>0){
			return new Json(true,"success",users.size());
		}else if(pageTimes >=0){
			return new Json(true,"success",pageTimes);
		}else if(users !=null){
			return new Json(true,"success",users);//每页开始的第几条记录   
		}else
			return new Json(false,"fail",false);
	*/
		
		}
	
	
//	/**
//	 * 登出 方法一
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value="/exit")
//	public String exit(HttpSession session){
//		 session.invalidate();  
//		return "index/index";
//	}
	
	/**
	 * 登出 方法二
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/exit")
	public String exit(HttpServletRequest req,HttpServletResponse res) throws IOException{
		UserLogin userlogin = (UserLogin)req.getSession().getAttribute("loginMap");
		//清除页面缓存 在html页里
		res.setHeader("Pragma", "No-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.flushBuffer();
		//删除登录cookie
//		Cookie userNameCookie = new Cookie("loginUserName", userlogin.getUserName());
//		Cookie passWordCokie = new Cookie("loginPassowrd",userlogin.getPassWord());
//		userNameCookie.setMaxAge(0);//设置0为立即删除该cooke
//		userNameCookie.setPath("/");//删除指定路径的COOKIE，不设置该路径
//		passWordCokie.setMaxAge(0);
//		passWordCokie.setPath("/");
//		res.addCookie(userNameCookie);
//		res.addCookie(passWordCokie);
		
		Cookie[] cookie = req.getCookies();
		for(Cookie c: cookie){
			//方法一
			/*if("autoLogin".equals(c.getName())){
				c.setMaxAge(0);
				res.addCookie(c);
			}*/
			//方法二
			if(null == c){
				System.out.println("没有cookie");
			}else{
				for(Cookie cc:cookie){
					if(cc.getName().equals("name_test")){
						cc.setValue(null);
						cc.setMaxAge(0);
						cc.setPath("/");
						res.addCookie(cc);;
						break;
					}
				}
			}
		}
//		 session.invalidate();  
		req.getSession().removeAttribute("loginMap");
		req.getSession().invalidate();//清除 session 中的所有信息  
		return "index/index";
	}
	
	 //创建cookie，并将新cookie添加到“响应对象”response中。
    public void addCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("name_test","value_test");//创建新cookie
        cookie.setMaxAge(5 * 60);// 设置存在时间为5分钟
        cookie.setPath("/");//设置作用域
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
    }
	/***
	 * 主页面
	 * @return
	 */
	@RequestMapping(value = "/signin")
	public String index(){
		return "index/index";
	}
	/***
	 * 用户列表
	 * @return
	 */
	@RequestMapping(value = "/ulist")
	public String ulist(){
		return "useradd/userList";
	}
	
	/***
	 * 添加用户
	 * @return
	 */
	@RequestMapping(value = "/addu")
	public String addu(){
//		return "redirect:useradd/addUser";
		return "useradd/addUser";
	}
	/***
	 * 删除用户
	 * @return
	 */
	@RequestMapping(value = "/delu")
	public String delu(){
		return "useradd/deleteUser";
	}
	/***
	 * 删除用户
	 * @return
	 */
	@RequestMapping(value = "updateuser")
	public String updateUser(){
		return "useradd/updateUser";
	}
	
	/***
	 * 分配角色
	 * @return
	 */
	@RequestMapping(value = "allotuser")
	public String allotUser(){
		return "useradd/allotUser";
	}
	/***
	 * 个人信息
	 * @return
	 */
	@RequestMapping(value = "userinfo/updateuserInfo")
	public String personalmanagement(){
		return "userInfo/updateUserInfo";
	}
	
	/***
	 * 个人信息密码修改
	 * @return
	 */
	@RequestMapping(value = "userinfo/updatepassword")
	public String updatePassword(){
		return "userInfo/updatePassword";
	}
	/***
	 * 跳转到firstPage.html
	 * @return
	 */
	@RequestMapping(value = "firstPage")
	public String firstPage(){
		return "index/firstPage";
	}
}
