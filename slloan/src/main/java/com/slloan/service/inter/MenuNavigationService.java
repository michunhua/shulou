package com.slloan.service.inter;

import java.util.List;


import com.slloan.entity.MenunMainAvigationEntity;
import com.slloan.entity.ThreelevelMenuEntity;
import com.slloan.entity.SubmenunavigationEntity;

public interface MenuNavigationService{
	public List<ThreelevelMenuEntity> getSecondbMenuId(String id);//获取三级菜单ID
	public List<SubmenunavigationEntity> getMainMenuid(String id);//获取二级菜单ID
	public List<MenunMainAvigationEntity> getMainMenu ();//获取主菜单

}
