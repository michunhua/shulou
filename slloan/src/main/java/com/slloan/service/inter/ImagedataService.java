package com.slloan.service.inter;

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

public interface ImagedataService {
	public boolean imagedatedel(ImageDataUpdate uploadFtpRoute);
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
	public List<ImageDataUpdate> selectUploadsUpdateType(ImageDataUpdate param)throws Exception ;
	
	/**
	 * 转账凭证&结算凭证上传文件上传改为已传 
	 * @param param
	 * @return
	 */
	public List<ImageDataUpdate> financevoucherSelectToupload(ImageDataUpdate param);
	/**
	 * 查询回调
	 * @param param
	 * @return
	 */
	public List<ImageDataUpdate> financevoucherSelectToupload2(ImageDataUpdate param);
	
	/**
	 * 查询回调admin
	 * @param param
	 * @return
	 */
	public List<ImageDataUpdate> financevoucherSelectTouploadAdmin(ImageDataUpdate param);
	
	/**
	 * 财务批量修改审核通过
	 * @param delList
	 * @return
	 */
	 boolean batchUpdateStudent(List updatelist);
	 
	 /**
		 * 财务批量批量审核拒绝
		 * @param delList
		 * @return
		 */
		 boolean batchUpdateadopt(List adoptlist);
		 
		 public ObjectSeq listSeq();
		 
		 boolean FirsttrialbatchRefuse(List batchRefuse);
		 
		 boolean FirsttrialbatchPast(List batchPast);
		 boolean loanFinalReviewPast(List loanFinalReviewpast);
		 boolean loanFinalReviewRefuse(List loanFinalRefuse);
		 boolean WaitForensics(Map<Object,Object>param);
		 boolean tobesettled(int id);
		 boolean tobeforensics(Map<Object,Object>param);//待取证
		 boolean tobedetained(Map<Object,Object>param);//待进押
		 boolean pendingconfirmation(Map<Object,Object>param);//待确认回款
		 boolean loanClearing(Map<Object,Object>param);//待结算
		 
		  ObjectSeq addSeq();
		  
		 ImageDataUpdate imagedataUpdateNote(ImageDataUpdate image);
		 public ImageDataUpdate selectByDelId(String spare,String upload,String filepath);
		 public boolean initialbatch(Map<Object,Object> image);
}
