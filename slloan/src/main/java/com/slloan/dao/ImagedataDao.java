package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.ImageDataUpdate;
import com.slloan.entity.ObjectSeq;


/**
 * 用户管理登录添加
 * @author Administrator
 *
 */
@Repository(value="imageDao")
public interface ImagedataDao {
	public boolean imagedatedel(Map<String,Object> uploadFtpRoute);
	public boolean imageDataAdd(ImageDataUpdate imagedata);
	public List<ImageDataUpdate> list();
	
	/**
	 * 根据上传类型查文件
	 * @param param
	 * @return
	 */
	public List<ImageDataUpdate> selectById(Map<String,Object> param);

		public ObjectSeq listSeq();
//	//自增ID
		ObjectSeq   addSeq( );
	
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
	public List<ImageDataUpdate> financevoucherSelectToupload2(Map<String,Object> param);
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
		 boolean batchUpdateadopt(List updatelist);
		 
		 boolean FirsttrialbatchRefuse(List batchRefuse);
		 
		 boolean FirsttrialbatchPast(List batchPast);
		 
		 boolean loanFinalReviewPast(List loanfianl);
		 boolean loanFinalReviewRefuse(List loanFinalRefuse);
		 
		 
		 boolean WaitForensics(Map<Object,Object> param);//待取证
		 boolean tobesettled(int id);//已结清
		 boolean tobedetained(Map<Object,Object> param);//待进押
		 boolean tobeforensics(Map<Object,Object> param);//待取证
		 boolean pendingconfirmation(Map<Object,Object> param);//待取证
		 boolean loanClearing(Map<Object,Object> param);//待结算
		 boolean loanFinalReviewPastMap(Map<Object,Object> param);//
		 
		 ImageDataUpdate imagedataUpdateNote(Map<String,Object> image);
		 /**
			 * 查询回调admin
			 * @param param
			 * @return
			 */
			public List<ImageDataUpdate> financevoucherSelectTouploadAdmin(Map<String,Object> param);
			public ImageDataUpdate selectByDelId(Map<String,Object> image);
			public boolean initialbatch(Map<Object,Object> image);
}
