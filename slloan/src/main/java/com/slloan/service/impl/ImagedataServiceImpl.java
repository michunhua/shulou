package com.slloan.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.ImagedataDao;
import com.slloan.entity.ImageDataUpdate;
import com.slloan.entity.ObjectSeq;
import com.slloan.service.inter.ImagedataService;
import com.slloan.util.DateUtils;

@Service(value="imagedataService")
public class ImagedataServiceImpl implements ImagedataService {

	@Autowired
	private ImagedataDao imagedatadao;
	@Override
	public boolean imagedatedel(int id) {
		return imagedatadao.imagedatedel(id);
	}

	@Override
	public boolean imageDataAdd(ImageDataUpdate imagedata) {
			String filepate = imagedata.getFilepath();
			String note = imagedata.getNote();
			String uploads = imagedata.getUploads();
			String originalfilename = imagedata.getOriginalfilename();
			String upload_type = imagedata.getUploadtype();
//			String Parentnode = imagedata.getParentnode();//父节点
			String Subnode = imagedata.getSubnode();
			String createdate = imagedata.getCreateData();
			String city = imagedata.getCity();
			String Parentnode = imagedata.getParentnode();//用户名ID
			String spare = imagedata.getSpare();
			String sparetwo = imagedata.getSparetwo();
		ImageDataUpdate imdata = new ImageDataUpdate(filepate,note,uploads,originalfilename,upload_type,city,Subnode,createdate,Parentnode,spare,sparetwo);
		return imagedatadao.imageDataAdd(imdata);
	}

	@Override
	public List<ImageDataUpdate> list() {
		return imagedatadao.list();
	}



	@Override
	public List<ImageDataUpdate> selectById(ImageDataUpdate param) {
		Map<String,Object> map = new HashMap<String,Object>();
			map.put("uploads", "");////上传者
			map.put("updatetype", "");//上传类型
		return imagedatadao.selectById(map);
	}

	@Override
	public List<ImageDataUpdate> selectUploadsUpdateType(ImageDataUpdate param) {
		Map<String,Object> mapparam = new HashMap<String,Object>();
//		mapparam.put("id",param.getId());
		mapparam.put("filepath", param.getFilepath());
		mapparam.put("Parentnode", param.getParentnode());
		mapparam.put("uploadtype",param.getUploadtype());
		return imagedatadao.selectUploadsUpdateType(mapparam);
	}

	@Override
	public List<ImageDataUpdate> financevoucherSelectToupload(ImageDataUpdate param){
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		  String createDate = DateUtils.getInDateTime((new Date()));//日期
		  Map<String,Object> mapparam = new HashMap<String,Object>();
		try {
			Date date1 = f.parse(createDate);
			  Calendar calen = Calendar.getInstance();
			  calen.setTime(date1);
			  calen.add(Calendar.DAY_OF_YEAR,5);
			  Date createdata2=calen.getTime();
			 System.out.println(f.format(createdata2));
				mapparam.put("city", param.getCity());
				mapparam.put("parentnode", param.getParentnode());
				mapparam.put("spare", param.getSpare());
				mapparam.put("sparetwo",param.getSparetwo());
				mapparam.put("uploadtype",param.getUploadtype());
				mapparam.put("createData",param.getCreateData());
				mapparam.put("createData2",f.format(createdata2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return imagedatadao.financevoucherSelectToupload(mapparam);
	}

	@Override
	public List<ImageDataUpdate> financevoucherSelectToupload2(ImageDataUpdate param){
		Map<String,Object> mapparam = new HashMap<String,Object>();
		mapparam.put("city", param.getCity());
		mapparam.put("parentnode", param.getParentnode());
		mapparam.put("spare", param.getSpare());
		mapparam.put("sparetwo",param.getSparetwo());
		mapparam.put("uploadtype",param.getUploadtype());
		mapparam.put("createdata",param.getCreateData());
		mapparam.put("uid",param.getId());
		mapparam.put("filepath",param.getFilepath());
		return imagedatadao.financevoucherSelectToupload2(mapparam);
	}

	@Override
	public boolean batchUpdateStudent(List updatelList) {
		return imagedatadao.batchUpdateStudent(updatelList);
	}

	@Override
	public boolean batchUpdateadopt(List updatelist) {
		return imagedatadao.batchUpdateadopt(updatelist);
	}

	@Override
	public ObjectSeq listSeq() {
		return imagedatadao.listSeq();
	}

	@Override
	public boolean FirsttrialbatchRefuse(List batchRefuse) {
		return imagedatadao.FirsttrialbatchRefuse(batchRefuse);
	}
	
	@Override
	public boolean FirsttrialbatchPast(List batchPast) {
		return imagedatadao.FirsttrialbatchPast(batchPast);
	}

	@Override
	public boolean loanFinalReviewPast(List loanfianl) {
		return imagedatadao.loanFinalReviewPast(loanfianl);
	}

	@Override
	public boolean loanFinalReviewRefuse(List loanFinalRefuse) {
		return imagedatadao.loanFinalReviewRefuse(loanFinalRefuse);
	}
	@Override
	public boolean WaitForensics(int id) {
		return imagedatadao.WaitForensics(id);
	}
	@Override
	public boolean tobesettled(int id) {
		return imagedatadao.tobesettled(id);
	}
	
	@Override
	public boolean tobeforensics(int id) {
		return imagedatadao.tobeforensics(id);
	}
	@Override
	public boolean tobedetained(int id) {
		return imagedatadao.tobedetained(id);
	}
	@Override
	public boolean pendingconfirmation(int id) {
		return imagedatadao.pendingconfirmation(id);
	}
	@Override
	public boolean loanClearing(int id) {
		return imagedatadao.loanClearing(id);
	}

	@Override
	public ObjectSeq addSeq() {
		return imagedatadao.addSeq();
	}
}
