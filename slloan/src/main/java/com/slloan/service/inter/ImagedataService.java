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
	
	/**
	 * 转账凭证&结算凭证上传文件上传改为已传 
	 * @param param
	 * @return
	 */
	public List<ImageDataUpdate> financevoucherSelectToupload(ImageDataUpdate param);
	
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
		 boolean WaitForensics(int id);
		 boolean tobesettled(int id);
		 boolean tobeforensics(int id);//待取证
		 boolean tobedetained(int id);//待进押
		 boolean pendingconfirmation(int id);//待确认回款
		 boolean loanClearing(int id);//待结算
}
