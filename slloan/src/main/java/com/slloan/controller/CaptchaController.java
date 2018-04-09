package com.slloan.controller;

import java.awt.font.GraphicAttribute;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lowagie.text.pdf.codec.Base64.OutputStream;
import com.slloan.constants.CaptchaConstants;
import com.slloan.util.GraphicHelper;

/**
 * 作者: fenny
 * 邮箱: 893146555@qq.com
 * 时间: 2017/11/21 09:18
 * 描述: 图形验证码
 */
@Controller("captchacontroller")
public class CaptchaController {
	static String code = "";
	@RequestMapping("/yzmtest.do")
	public String ddd(){
		System.out.println("进来了11111111111111111");
		return "yzm";
	}
	
	/**
	 * 验证码
	 * @param request 
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value=CaptchaConstants.VERIFICATIONCODE,method=RequestMethod.GET)
	@ResponseBody
	protected static  String service(HttpServletRequest request, HttpServletResponse response)  
			  throws ServletException, IOException { 
					// 获得 当前请求 对应的 会话对象  
//			String paramcode = request.getParameter("code");
					HttpSession session = request.getSession();
					String uri = request.getRequestURI();
//					System.out.println("hello"+uri);
					final int width = 125;
					final int height=35;
					final String imgType="jpg";
					final ServletOutputStream  output = response.getOutputStream(); // 获得可以向客户端返回图片的输出流 
					
					code = GraphicHelper.create(width, height, imgType, output);
							System.out.println("验证码内容:"+code);
								
//						if(paramcode.toLowerCase().equals(code.toLowerCase())){
//							request.getRequestDispatcher("succsg").forward(request, response);
//							 return JSON.toJSONString("验证码输入证确");
//						}else{
//							request.getRequestDispatcher("/fail.jsp").forward(request, response);
//							return JSON.toJSONString("验证码输入错误");
//						}
					// 创建验证码图片并返回图片上的字符串  
				  
				    // 建立 uri 和 相应的 验证码 的关联 ( 存储到当前会话对象的属性中 )  
				    session.setAttribute(uri, code);  
				  
				    System.out.println(session.getAttribute(uri));  
				    return JSON.toJSONString(code);
	}

	public static String codeyzm() {
		String str = code;
		System.out.println("方法里的验证码： "+str);
		return code;
	}
}

