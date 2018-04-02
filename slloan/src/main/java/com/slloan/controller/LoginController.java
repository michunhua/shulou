package com.slloan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.bootstrap.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.slloan.constants.CaptchaConstants;
import com.slloan.entity.AddRole;
import com.slloan.entity.UserLogin;
import com.slloan.service.impl.AddPermissionImplService;
import com.slloan.service.inter.LoginService;

/**
 * 
 * @author asus- 登录
 *
 */
//@WebService(name="/UserWXPServlet")
@Controller("logincfAction")
@RequestMapping(value="user")
public class LoginController{
	
	private static Logger logger = LoggerFactory.getLogger(AddPermissionImplService.class);

	@Autowired
	private LoginService logindelu;
	
	@RequestMapping(value=CaptchaConstants.LOGIN,method=RequestMethod.POST)
	protected String  checkLogin(HttpSession session, @RequestParam("username")String username, @RequestParam("password")String password) throws ServletException, IOException {
		UserLogin login = new UserLogin(username,password);
		System.out.println(login);
//		HttpSession session =req.getSession();
		ServletContext application = session.getServletContext();
		if(login!=null){//登录成功
		 Map<String, String> loginMap = (Map<String, String>)application.getAttribute("loginMap");
		 
		 if(loginMap==null){
             loginMap = new HashMap<>();
         }
		 for(String key:loginMap.keySet()) {
             if (login.getUserName().equals(key)) {
                 if(session.getId().equals(loginMap.get(key))) {
                     System.out.println(username+"在同一地点多次登录！");
                     System.out.println("1: "+session.getId());
                     System.out.println("2: "+key);
//                     session.setMaxInactiveInterval(1);//1分钟 过期时间 
//                     req.getRequestDispatcher("procutent/success.jsp").forward(req, resp);
                 } else{
                     System.out.println(username+"异地登录被拒绝！");
//                     session.setAttribute("tip", "该用户已经异地登录！");
                     System.out.println("3: "+session.getId());
                     System.out.println("4: "+key);
                     session.setAttribute("tip", "该用户已经登录！");
//                     session.setMaxInactiveInterval(1);//1分钟 过期时间 
                     return "forward:/index.jsp";
                 }
                 
             }
         }
		 loginMap.put(login.getUserName(),session.getId());
         application.setAttribute("loginMap", loginMap);
         session.setAttribute("username",login.getUserName());
         System.out.println("5: "+loginMap);
         System.out.println("6: "+session.getId());
         System.out.println("登录成功！");
         return "redirect:/logincf.jsp";
		}else{
            //登录失败
            System.out.println("登录失败！");
            session.setAttribute("tip","登录失败！");
            return "forward:/index.jsp";
        }  
	}
	
			//用户管理添加
			@RequestMapping(value = "usergladd")
			public String useradd(){
				System.out.println("用户管理添加进来了");
				return "tjuser";
			}
			
			//权限添加
			@RequestMapping(value = "radd")
			public String roleadd(){
				System.out.println("权限添加进来了");
				return "roletj";
			}
			
			//保存角色
			@RequestMapping(value = "addrole.do")
			public String addRole(HttpServletRequest req,AddRole addrole) throws UnsupportedEncodingException{
				req.setCharacterEncoding("utf-8");
				String dd[] = req.getParameterValues("test");
				String str3 ="";
				List<String> list = new ArrayList<>();
				for (int i = 0; i < dd.length; i++) {
					list.add(dd[i].toString());
					str3 += dd[i] +",";	
				}
				str3 = str3.substring(0,str3.length() - 1);
				System.out.println("------ "+str3+"--------------");
				System.out.println("添加角色进来了");
//				logindelu.addRole(addrole);
				return "index";
			}
			//测试跳转到保存角色页面
			@RequestMapping(value = "aa.do")
			public String dtf(){
				return "roletj";	
			}
}
