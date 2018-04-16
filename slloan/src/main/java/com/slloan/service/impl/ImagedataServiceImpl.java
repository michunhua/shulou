package com.slloan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.ImagedataDao;
import com.slloan.entity.ImageDataUpdate;

import com.slloan.service.inter.ImagedataService;

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
			String Parentnode = imagedata.getParentnode();//父节点
			String Subnode = imagedata.getSubnode();
			String createdate = imagedata.getCreateData();
		ImageDataUpdate imdata = new ImageDataUpdate(filepate,note,uploads,originalfilename,upload_type,Parentnode,Subnode,createdate);
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
			mapparam.put("filepath", param.getOriginalfilename());
			mapparam.put("Parentnode", param.getNote());
			mapparam.put("uploadtype",param.getUploadtype());
		return imagedatadao.selectUploadsUpdateType(mapparam);
	}


}
