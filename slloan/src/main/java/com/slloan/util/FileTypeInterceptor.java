package com.slloan.util;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.SynchronizedStatement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 文件上载拦截器
 * @author Administrator
 *
 */
public class FileTypeInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiii");
		super.postHandle(request, response, handler, modelAndView);
		boolean flag = true;
		if(request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest mult =(MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = mult.getFileMap();
			Iterator<String> it = files.keySet().iterator();
			//对多部件请求资源进行遍历
			while(it.hasNext()){
				 String formKey = (String) it.next();
				 MultipartFile multipartFile = mult.getFile(formKey);
				 String filename=multipartFile.getOriginalFilename();
				//判断是否为限制文件类型
					if(! checkFile(filename)){
						//限制文件类型，请求转发到原始请求页面，并带提示信息
						request.setAttribute("errormessage", "不支持的文件类型");
						request.getRequestDispatcher("WEB-INF/html/error/filetype/500.html").forward(request, response);
						flag = false;
						
					}
			}
			
		}
	}

	private boolean checkFile(String filename) {
		//设置允许上上传文件类型
		String sum = "txt,xls,jpeg,jpg,gif";
		System.out.println("uuuuuuuuuuuuuuuuuu");
		//获取文件后缀
		String suffix=filename.substring(filename.lastIndexOf(".")+1,filename.length());
		
		if(sum.contains(suffix.trim().toLowerCase())){
			return true;
		}
		return false;
	}
	
	
}
