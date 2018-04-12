package com.slloan.util;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.slloan.dao.UserDao;
import com.slloan.entity.ResultList;
import com.slloan.entity.UserLogin;
import com.slloan.service.inter.UserService;

import net.sf.json.JSONArray;

//import net.sf.json.JSONObject;

/**
 * 登录拦截器
 * @author asus-
 *
 */
public class interceptordelu implements HandlerInterceptor{

	@Autowired
	public UserService logindeluservice;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("拦截器初始化执行中");
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		String code = request.getParameter("code");
		UserLogin login = new UserLogin(username,pwd);
		UserLogin userlogin =	logindeluservice.logindelu(login);
		String uri = request.getRequestURI();
//		PrintWriter out = null;
//		ResultList<Json> pageBean = new ResultList<Json>();//返回权限集合
		System.out.println(uri);
		if(uri.indexOf("login")>0){
				if(userlogin == null || code == null){
//					request.getSession().getRequestDispatcher("signin").forward(request, response);
//					pageBean.setLists(json);
					Map<Character,Object> param = new TreeMap<Character,Object>();
//					param.put(json.getStatus(), "false");
//					param.put(json.getObj(), "用户名或密码错误");
//					param.put(json.getMsg(), "fail");
					param.put('a', "false");
					param.put('c', "用户名或密码错误");
					param.put('d', "fail");
					param.put('b', "usernameorpassworderror");
					response.getWriter().write("{");
					for(Character key :param.keySet()){
//						System.out.println(key+""+param.get(key));
						response.getWriter().write(key+":"+param.get(key)+" "+"\r");
//						response.getWriter().append(key.toString());
					}
					response.getWriter().write("}");
//					 JSONObject jsont = new JSONObject(true);
//					JSONObject res = new JSONObject(true).fo(param);
//					res.put("status", "false");
//					res.put("msg", "fail");
//					res.put("obj", "用户名或密码错误");
//					res.put("value","usernameorpassworderror");
//					response.sendRedirect("/slloan/user/signin");
//					out = response.getWriter();
//					out.append(param.toString());

//					 response.getWriter().print(json.toString());
					
//					out.append("msg: "+res.getString("msg").toString());
//					System.out.println(replaceParam);
//					response.getWriter().append(res.toString())
//					 request.getSession()).getRequestDispatcher("signin").forward(request, response);
//					response.sendRedirect("/slloan/user/signin");
					return false;
				}else{
//					response.sendRedirect("/index/firstPage");
					return true;
				}
		}
		/*String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		UserLogin login = new UserLogin(username,pwd);
		UserLogin userlogin =	logindeluservice.logindelu(login);
		String uri = request.getRequestURI();
		System.out.println(uri);
		String str = (String) request.getAttribute("loginMap");
		if(uri.indexOf("login")>0){
			if(userlogin == null|| str == null){
				request.getRequestDispatcher("signin").forward(request, response);
				return false;
			}else{
				response.encodeRedirectURL("firstPage");
				return true;
			}
		}*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("拦截器执行过程中");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
			
		System.out.println("拦截器执行完");
	}

}
