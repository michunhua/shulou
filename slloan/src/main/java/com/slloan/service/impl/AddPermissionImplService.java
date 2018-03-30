package com.slloan.service.impl;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slloan.dao.AddPermissionDao;
import com.slloan.entity.PermissionEntity;
import com.slloan.service.inter.AddPermissionService;


/**
 * 权限添加
 * @author Administrator
 *
 */
@Service(value="addperionservice")
@Transactional
public class AddPermissionImplService  implements AddPermissionService{
	private static Logger logger = LoggerFactory.getLogger(AddPermissionImplService.class);
	@Autowired
	private AddPermissionDao addpermissiondao;
	

	@Override
	public boolean addPermission(PermissionEntity permission) {
		String checkboxid = permission.getCheckboxID();
		String r_id = permission.getR_id();
		String url = permission.getUrl();
		PermissionEntity params = new PermissionEntity(checkboxid,r_id,url);
//		boolean isResult = false;
		/*try {
			addpermissiondao.addPermission(params);
			logger.info("添加成功");
			isResult = true;
		} catch (Exception e) {
			logger.debug("添加失败");
			e.printStackTrace();
		}*/
		return addpermissiondao.addPermission(params);
	}


	/**
	 * 角色删除成功后，把权限也删除掉
	 */
	@Override
	public boolean batchDeletes(List delList) {
		return addpermissiondao.batchDeletes(delList);
	}


	@Override
	public List<PermissionEntity> getRolePermissiondata(Map<String, String> param) {
		return addpermissiondao.getRolePermissiondata(param);
	}


}
