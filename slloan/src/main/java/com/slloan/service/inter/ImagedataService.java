package com.slloan.service.inter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.ImageDataUpdate;


/**
 * 用户管理登录添加
 * @author Administrator
 *
 */

public interface ImagedataService {
	public boolean imagedatedel(int id);
	public boolean imageDataAdd(ImageDataUpdate imagedata);
	public List<ImageDataUpdate> list();

		
		/**
		 * 根据上传类型查文件
		 * @param param
		 * @return
		 */
	public List<ImageDataUpdate> selectById(ImageDataUpdate param);
		
	/**
	 * 根据 上传类型 原文件名  上传者姓名查所上传的
	 * @param param
	 * @return
	 */
	public List<ImageDataUpdate> selectUploadsUpdateType(ImageDataUpdate param);
}
