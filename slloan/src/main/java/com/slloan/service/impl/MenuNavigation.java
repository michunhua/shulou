package com.slloan.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.MenunMainAvigationDao;
import com.slloan.dao.SubmenunavigationDao;
import com.slloan.dao.ThreelevelMenuDao;
import com.slloan.entity.MenunMainAvigationEntity;
import com.slloan.entity.ThreelevelMenuEntity;
import com.slloan.entity.SubmenunavigationEntity;
import com.slloan.service.inter.MenuNavigationService;

@Service(value="menumavigation")
public class MenuNavigation implements MenuNavigationService{

	@Autowired
	private MenunMainAvigationDao menuMainuAvigation;//获取一菜单
	@Autowired
	private SubmenunavigationDao submenunavigation;//获取二菜单
	@Autowired
	private ThreelevelMenuDao secondlevelMenuDao;//三级菜单
	
	@Override
	public List<MenunMainAvigationEntity> getMainMenu() {
		return menuMainuAvigation.list();
	}

	@Override
	public List<ThreelevelMenuEntity> getSecondbMenuId(String id) {
		return secondlevelMenuDao.getSecondMenuid(id);
	}

	@Override
	public List<SubmenunavigationEntity> getMainMenuid(String id) {
		return submenunavigation.getMainMenuSubmenuid(id);
	}

}
