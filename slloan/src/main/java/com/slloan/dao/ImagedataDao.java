package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.ImageDataUpdate;


/**
 * 用户管理登录添加
 * @author Administrator
 *
 */
@Repository(value="imageDao")
public interface ImagedataDao {
	public boolean imagedatedel(int id);
	public boolean imageDataAdd(ImageDataUpdate imagedata);
	public List<ImageDataUpdate> list();
	
	/**
	 * 根据上传类型查文件
	 * @param param
	 * @return
	 */
	public List<ImageDataUpdate> selectById(Map<String,Object> param);

	
//	//自增ID
//	public boolean addSeq(ObjectSeq addseq);
	
	/**
	 * 根据 上传类型 原文件名  上传者姓名查所上传的
	 * @param param
	 * @return
	 */
	public List<ImageDataUpdate> selectUploadsUpdateType(Map<String,Object> param);
	
	/**
	 * 转账凭证&结算凭证上传文件上传改为已传 
	 * @param param
	 * @return
	 */
	public List<ImageDataUpdate> financevoucherSelectToupload(Map<String,Object> param);
	
	/**
	 * 财务批量修改审核通过
	 * @param delList
	 * @return
	 */
	 boolean batchUpdateStudent(List updatelList);
	 
	 /**
		 *财务批量审核拒绝退回到终审
		 * @param delList
		 * @return
		 */
		 boolean batchUpdateadopt(List updatelList);
}
