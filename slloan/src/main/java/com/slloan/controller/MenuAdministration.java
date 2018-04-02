package com.slloan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slloan.entity.ThreelevelMenuEntity;
import com.slloan.entity.SubmenunavigationEntity;
import com.slloan.service.impl.AddPermissionImplService;
import com.slloan.service.inter.MenuNavigationService;
import com.slloan.util.Json;

/**
 * 菜单导航
 * @author asus-
 *
 */
@Controller
@RequestMapping(value="menunavigation")
public class MenuAdministration {
	private static Logger logger = LoggerFactory.getLogger(MenuAdministration.class);
	@Autowired
	private MenuNavigationService menunavigation;
	
	//主菜单
	@RequestMapping(value="/mainmenu")
	public  String mainmenu(ModelMap map){
		map.addAttribute("menu", menunavigation.getMainMenu());
		return "index";
	}
	
	//获取二级菜单
	@RequestMapping(value="/submenun")
	public Json getSubmenuNavigation(@PathVariable("id") String id){
		List<SubmenunavigationEntity> listSubmenu = menunavigation.getMainMenuid(id);
			if(listSubmenu !=null){
				return new Json(true,"success",listSubmenu);
			}else{
				return new Json(false,"fail",null);
			}
	}
			//获取三级菜单
			@RequestMapping(value="/secondlevelmenu")
			public Json getSecondlevelMenu(@PathVariable("id") String id){
				List<ThreelevelMenuEntity> listSubmenu = menunavigation.getSecondbMenuId(id);
					if(listSubmenu !=null){
						return new Json(true,"success",listSubmenu);
					}else{
						return new Json(false,"fail",null);
					}
		
	}
}
