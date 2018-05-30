package com.slloan.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
	public boolean imagedatedel(ImageDataUpdate uploadFtpRoute) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uploadFtpRoute", uploadFtpRoute.getUploadFtpRoute());
		map.put("filepath", uploadFtpRoute.getFilepath());
		return imagedatadao.imagedatedel(map);
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
			String uploadFtpRoute = imagedata.getUploadFtpRoute();
		ImageDataUpdate imdata = new ImageDataUpdate(filepate,note,uploads,originalfilename,upload_type,city,Subnode,createdate,Parentnode,spare,sparetwo,uploadFtpRoute);
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
			 
			  calen.setTime(date1);
			  
			  calen.add(Calendar.DAY_OF_YEAR,-5);
			  Date createdata3=calen.getTime();
			 System.out.println(f.format(createdata3));
			 
				mapparam.put("city", param.getCity());
				mapparam.put("parentnode", param.getParentnode());
				mapparam.put("spare", param.getSpare());
				mapparam.put("sparetwo",param.getSparetwo());
				mapparam.put("uploadtype",param.getUploadtype());
				mapparam.put("createData",f.format(createdata3));
				mapparam.put("createData2",f.format(createdata2));
				mapparam.put("uploadFtpRoute",param.getUploadFtpRoute());
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return imagedatadao.financevoucherSelectToupload(mapparam);
	}

	@Override
	public List<ImageDataUpdate> financevoucherSelectTouploadAdmin(ImageDataUpdate param){
		  Map<String,Object> mapparam = new HashMap<String,Object>();
		try {
				mapparam.put("uploadtype",param.getUploadtype());
				mapparam.put("uploadFtpRoute",param.getUploadFtpRoute());
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imagedatadao.financevoucherSelectTouploadAdmin(mapparam);
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
	public boolean WaitForensics(Map<Object,Object>param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		Iterator it = param.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			map.put(key, value);
		}
		return imagedatadao.WaitForensics(map);
	}
	@Override
	public boolean tobesettled(int id) {
		return imagedatadao.tobesettled(id);
	}
	
	@Override
	public boolean tobeforensics(Map<Object,Object>param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		Iterator it = param.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry) it.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				map.put(key, value);
			}
		return imagedatadao.tobeforensics(map);
	}
	@Override
	public boolean tobedetained(Map<Object,Object>param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		Iterator it = param.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			map.put(key, value);
		}
		return imagedatadao.tobedetained(map);
	}
	@Override
	public boolean pendingconfirmation(Map<Object,Object> param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		Iterator it = param.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry)it.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				map.put(key, value);
			}
		return imagedatadao.pendingconfirmation(map);
	}
	@Override
	public boolean loanClearing(Map<Object,Object> param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		Iterator it = param.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			map.put(key, value);
		}
		return imagedatadao.loanClearing(map);
	}

	@Override
	public ObjectSeq addSeq() {
		return imagedatadao.addSeq();
	}

	
	@Override
	public ImageDataUpdate imagedataUpdateNote(ImageDataUpdate image) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("city", image.getCity());
			map.put("uploadFtpRoute", image.getId());
			map.put("uploadtype", image.getUploadtype());
			map.put("filepath", image.getFilepath());
		return imagedatadao.imagedataUpdateNote(map);
	}
	@Override
	public ImageDataUpdate selectByDelId(String spare,String upload,String filepath) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("spare", spare);
		map.put("uploadFtpRoute", upload);
		map.put("filepath", filepath);
		return imagedatadao.selectByDelId(map);
	}
	@Override
	public boolean initialbatch(Map<Object, Object> image) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		Iterator it = image.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			map.put(key, value);
		}
		return imagedatadao.initialbatch(map);
	}
}
