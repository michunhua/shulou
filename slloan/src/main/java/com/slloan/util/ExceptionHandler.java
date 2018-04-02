package com.slloan.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler  implements  HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
			if(ex instanceof MaxUploadSizeExceededException){
				 //指定错误信息
				mv.addObject("errormessage","文件上传过大");
				 //设置跳转视图
				mv.setViewName("error");
				 return mv;
			}
			if(ex instanceof DuplicateKeyException){
				mv.addObject("errormessage", "插入的值不能重复");
				mv.setViewName("error");
				return mv;
			}
		return null;
	}

}
