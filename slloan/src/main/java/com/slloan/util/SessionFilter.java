package com.slloan.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.slloan.entity.AddRole;
import com.slloan.entity.UserLogin;

public class SessionFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}


	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {  
        //chain.doFilter(req, resp);  
        HttpServletRequest request = (HttpServletRequest)req;  
        HttpServletResponse response = (HttpServletResponse)resp;  
        request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        response.setCharacterEncoding("utf-8");
        response.setHeader( "Pragma", "no-cache" );   
              response.setDateHeader("Expires", 0);   
              response.addHeader( "Cache-Control", "no-cache" );//浏览器和缓存服务器都不应该缓存页面信息
              response.addHeader( "Cache-Control", "no-store" );
//        HttpSession session = request.getSession();  
//        PrintWriter out = response.getWriter();  
//        String path = request.getContextPath();  
//        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;  
//        UserLogin user = (UserLogin)session.getAttribute("user");
        String str = (String) request.getSession().getAttribute("username"); 
        System.out.println("查到会话是否有值---------- "+str+" ----------------------存在 ");
//        AddRole role = (AddRole)session.getAttribute("role"); 
        if(str != null){  
            chain.doFilter(request,response);  
        } else{  
        	PrintWriter out = response.getWriter();
        	  response.setHeader("session-status", "timeout");
//        	String str02 = "用户会话已过期或未登录，安全过滤器禁止访问，并跳转到错误页面";  
//            response.setContentType("text/html;charset=UTF-8");// 解决中文乱码 
//            out.write(str02);
//            response.sendRedirect("../user/signin");
//            response.getWriter().print("用户会话已过期或未登录，安全过滤器禁止访问，并跳转到错误页面");
//            PrintWriter out = response.getWriter();    
        	  HttpSession session = request.getSession();
//        	  session.setMaxInactiveInterval(600); // Session保存两小时
        	  
        	  Cookie cookie = new Cookie("JSESSIONID", session.getId());

        	  cookie.setMaxAge(0); // 客户端的JSESSIONID也保存两小时

//        	 session.setMaxInactiveInterval(600);

        	 cookie.setPath("/");

//        	  response.addCookie(cookie);
        	 Map<String, String> loginMap = (Map<String, String>)request.getSession().getServletContext().getAttribute("loginMap");
        	 	if(str == null){
        	 	}else{
        	 		 loginMap.remove(str);
                    
        	 	}
            
        	 	 request.getSession().getServletContext().setAttribute("loginMap",loginMap);
                 System.out.println(request.getSession().getAttribute("username"));
            	  request.getSession().invalidate();
            StringBuilder builder = new StringBuilder();    
            builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");    
//            builder.append("alert(\"未登录或登录已过时,请重新登陆！\");");    
//            builder.append("parent.window.location.href='http://"+req.getServerName()+":"+req.getServerPort()+"/slloan/user/expirytime';");
            builder.append("parent.window.location.href='http://"+req.getServerName()+":"+req.getServerPort()+"/slloan/user/signin';");  
            builder.append("</script>");    
            out.print(builder.toString());    
//            out.flush();  
//            out.close(); 
            
//            	out.println("您还未登陆，三秒钟后跳转至登录页面");  
            //out.println("<script language='javascript'>alert('你还未登录');");  
//            response.setHeader("refresh","3");  
//            response.sendRedirect("/slloan/jsp/index/index.html");
//			logger.debug("用户会话已过期或未登录，安全过滤器禁止访问，并跳转到错误页面 ");
//			System.out.println("用户会话已过期或未登录，安全过滤器禁止访问，并跳转到错误页面");
           
//            request.getRequestDispatcher("http://localhost:8082/slloan/user/signin").forward(request, response);
//            request.getRequestDispatcher("/slloan/user/signin").forward(request,response);  
        }  
    } 
	
	@Override
	public void destroy() {
	}

}
