package com.slloan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("init")
public class initymtz {
	
	//测试是否跳转到index页面
	@RequestMapping(value = "district.do")
	public String ddd(){
		return "all";
	}
	
	//登录页面
	@RequestMapping(value = "dl.do")
	public String dl(){
		return "index";
	}
	
	//登录页面
		@RequestMapping(value = "addUser.do")
		public String ddd2(){
			return "addUser";
		}
		//抽奖
				@RequestMapping(value = "cj.do")
				public String ddd3(){
					return "cj";
				}
				//抽奖
				@RequestMapping(value = "cc.do")
				public String ddd4(){
					return "aa";
				}
				
				
				
				
}
