package com.slloan.util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.eclipse.jdt.internal.compiler.ast.SynchronizedStatement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
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
	
	private long maxSize;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("------------文件上传文件格式拦截---------------");
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
				 String uri = request.getRequestURI();
				//判断是否为限制文件类型
				 if((uri.indexOf("updateftpimage")>0)|| uri.indexOf("financevoucher")>0){
					 if(! checkFile(filename)){
							//限制文件类型，请求转发到原始请求页面，并带提示信息
							request.setAttribute("errormessage", "不支持的文件类型");
							System.out.println("不支持该上传类型");
//							request.getRequestDispatcher("WEB-INF/jsp/error/filetype/500.html").forward(request, response);
//							 request.getRequestDispatcher("/slloan/user/signin").forward(request, response);
							Map<Character,Object> param = new TreeMap<Character,Object>();
							param.put('a', "false");
							param.put('c', "不支持的文件类型");
							param.put('d', "fail");
							param.put('b', "filetypefail");
							response.getWriter().write("{");
							for(Character key : param.keySet()){
								response.getWriter().write(key+":"+param.get(key)+" "+"\r");
							}
//							 response.encodeRedirectURL("/slloan/updateftpimage/filetype500");
							response.getWriter().write("}");
							
							/* if(request !=null && ServletFileUpload.isMultipartContent(request)){
									//判断是否文件上传
									ServletRequestContext ctx = new ServletRequestContext(request);
									//获取上传文件尺寸大小
									long requestsize = ctx.contentLength();
									if(requestsize > maxSize);
									//当上传文件大小超过指定大小限制后，模拟抛出MaxUploadSizeExceededException异常
//									throw  new MaxUploadSizeExceededException(maxSize);
									response.sendRedirect("/slloan/updateftpimage/filetype500");
								}*/
							 
							flag = false;
						}else{
							flag = true;
						}
				 }
					
			}
			/*if(request !=null && ServletFileUpload.isMultipartContent(request)){
				//判断是否文件上传
				ServletRequestContext ctx = new ServletRequestContext(request);
				//获取上传文件尺寸大小
				long requestsize = ctx.contentLength();
				if(requestsize > maxSize);
				//当上传文件大小超过指定大小限制后，模拟抛出MaxUploadSizeExceededException异常
//				throw  new MaxUploadSizeExceededException(maxSize);
				response.sendRedirect("/slloan/updateftpimage/filetype500");
			}
			return flag;*/
//			 response.sendRedirect("/updateftpimage/filetype500");
			
		}
		return flag;
	}
	public void setMaxSize(long maxSize){
		this.maxSize =maxSize;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		 /*if(request !=null && ServletFileUpload.isMultipartContent(request)){
				//判断是否文件上传
				ServletRequestContext ctx = new ServletRequestContext(request);
				//获取上传文件尺寸大小
				long requestsize = ctx.contentLength();
				if(requestsize > maxSize);
				//当上传文件大小超过指定大小限制后，模拟抛出MaxUploadSizeExceededException异常
//				throw  new MaxUploadSizeExceededException(maxSize);
				response.sendRedirect("/slloan/updateftpimage/filetype500");
			}*/
//			return flag;
		System.out.println("---------------------------文件上传拦截器 执行过程中---------------------------");
		/*super.postHandle(request, response, handler, modelAndView);
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
				 String uri = request.getRequestURI();
				//判断是否为限制文件类型
				 if((uri.indexOf("updateftpimage")>0)){
					 if(! checkFile(filename)){
							//限制文件类型，请求转发到原始请求页面，并带提示信息
							request.setAttribute("errormessage", "不支持的文件类型");
							System.out.println("不支持该上传类型");
//							request.getRequestDispatcher("WEB-INF/jsp/error/filetype/500.html").forward(request, response);
//							 request.getRequestDispatcher("/slloan/user/signin").forward(request, response);
							Map<Character,Object> param = new TreeMap<Character,Object>();
							param.put('a', "false");
							param.put('c', "不支持的文件类型");
							param.put('d', "fail");
							param.put('b', "filetypefail");
							response.getWriter().write("{");
							for(Character key : param.keySet()){
								response.getWriter().write(key+":"+param.get(key)+" "+"\r");
							}
//							 response.encodeRedirectURL("/slloan/updateftpimage/filetype500");
							response.getWriter().write("}");
							flag = false;
						}else{
							flag = true;
						}
				 }
					
			}
//			 response.sendRedirect("/updateftpimage/filetype500");
		}*/
		
	}

	private boolean checkFile(String filename) {
		//设置允许上上传文件类型
		String sum = "jpeg,jpg,gif,png,bmp";
		System.out.println("uuuuuuuuuuuuuuuuuu");
		//获取文件后缀
		String suffix=filename.substring(filename.lastIndexOf(".")+1,filename.length());
		
		if(sum.contains(suffix.trim().toLowerCase())){
			return true;
		}
		return false;
	}
	
	
}
