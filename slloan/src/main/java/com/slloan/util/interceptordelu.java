package com.slloan.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.slloan.dao.UserDao;
import com.slloan.entity.UserLogin;
import com.slloan.service.inter.UserService;

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
		System.out.println("拦截器初始化执行中");
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		UserLogin login = new UserLogin(username,pwd);
		UserLogin userlogin =	logindeluservice.logindelu(login);
		String uri = request.getRequestURI();
		System.out.println(uri);
		if(uri.indexOf("login")>0){
				if(userlogin == null){
					request.getRequestDispatcher("signin").forward(request, response);
					return false;
				}else{
					response.encodeRedirectURL("firstPage");
					return true;
				}
		}
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
