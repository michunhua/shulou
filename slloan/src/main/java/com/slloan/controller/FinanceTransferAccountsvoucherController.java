package com.slloan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.ImageDataUpdate;
import com.slloan.entity.ResultList;
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.service.inter.ImagedataService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

import net.sf.json.JSONObject;

/**
 * 财务凭证&结算上传到FTP
 * @author Administrator
 *
 */
@Controller(value="financeTransferaccountsvouchercontroller")
@RequestMapping(value="financevoucher")
public class FinanceTransferAccountsvoucherController {
	
	@Value("${ftpIp}")
	public String ftpIp;//IP
	
	@Value("${ftpPort}")
	public String ftpPort;//端口
	
	@Value("${ftpUser}")
	public String ftpUser;//用户名
	
	@Value("${ftpPwd}")
	public String ftpPwd;//密码
	
	FileInputStream fis = null;
	static ZipFile zf ;
	static List<Long> filesize;
	static Long sum = 0l;//文件总大小
	static StringBuffer sb ;
	Iterator it ;//定义一个迭代器	
	static String filenameimage;
	 FTPClient ftp = new FTPClient();
	 File target;
	 String filename2;
	 static int reply;
	 private long maxSize;
	Set<String> setlist = new TreeSet<String>();
	ResultList<Object> resultList = new ResultList<Object>();
	List<Object> list = new ArrayList<Object>();
	@Autowired
	private ImagedataService imagedataservice;
	
	@Autowired
	private CircuLationRecordSubmitService recordSubmitService;
	
	/**
	 * 转账凭证上传到FTP
	 * @param tmpfile
	 * @param req
	 * @param res
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	  @RequestMapping(value = "/transferaccountsvoucher",method=RequestMethod.POST)
	    @ResponseBody
		public Json transferaccountsvoucher(@RequestParam(value="file",required=false)MultipartFile[] tmpfile,
	  			final HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
	    	final String note = req.getParameter("note");//备注
	    	final String upload_type = req.getParameter("upload_type");//上传类型
//	    	final	String filepath = req.getParameter("filepath");
	    	final	String city = req.getParameter("city");
	    	String reqid= req.getParameter("id");
	    	String targetFileName = "";
	    	for(final MultipartFile f:tmpfile){
	    	        FTPClient ftp = new FTPClient();
	    	        try {
	    	       
	    	            ftp.connect(ftpIp);// 连接FTP服务器
	    	            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
	    	         boolean ftpfalse=   ftp.login(ftpUser, ftpPwd);// 登录
	    	         if(ftpfalse == true){
	    	        	 System.err.println("FTP站点连接成功！");
	    	        	 reply = ftp.getReplyCode();
	     	            if (!FTPReply.isPositiveCompletion(reply)) {
	     	                ftp.disconnect();
	     	            }
	     	            ftp.enterLocalPassiveMode();
	     	            final String tmpFileName = f.getOriginalFilename();
	         	   	 	final int dot = tmpFileName.lastIndexOf(".");
	         	   	 String ext = "" ;//文件名后缀
	         	 		if(dot >-1){
	         	 			ext = tmpFileName.substring(dot+1);
	         	 		}
	         	 		//jpeg,jpg,gif,png,bmp
	         	 		if(filetype(f.getOriginalFilename())){
	         	 			 targetFileName = renameFileName(city,upload_type,tmpFileName);// 重命名上传的文件名
	         	 			  ImageDataUpdate imagedata = new ImageDataUpdate();
	         	 
	         	 			if(req !=null && ServletFileUpload.isMultipartContent(req)){
	             	 			//判断是否文件上传
	             	 			ServletRequestContext ctx = new ServletRequestContext(req);
	             	 			//获取上传文件尺寸大小
	             	 			System.out.println(maxSize);
	             	 			long requestsize = ctx.contentLength();
	             	 			System.out.println("aaaa------------:  "+requestsize);
	         	 			  
	         	 			 if(upload_type.equals("转账凭证") && requestsize >0 ){
	         	 				if (!ftp.changeWorkingDirectory("transfer_accounts_image")) {
	             	                ftp.makeDirectory("transfer_accounts_image");//创建目录
	             	                ftp.changeWorkingDirectory("transfer_accounts_image");//跳转目录(可根据项目需求选择创建目录的多少)
	             	            }
	                     		System.out.println("转账凭证");
	                     		imagedata.setNote(note);//备注
	                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//	                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
	                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/transfer_accounts_image/"+targetFileName.trim());
//	                         	imagedata.setFilepath("");
//	                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
	                         	imagedata.setUploads(note);//上传者
	                         	imagedata.setUploadtype(upload_type);//上传类型
	                         	imagedata.setCity(city);
	                         	imagedata.setSubnode("已传");
	                         	String createData = DateUtils.getInDateTime((new Date()));//日期
	                         	imagedata.setCreateData(createData);
	                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录
	                         	
	                         	String sid = reqid;
	                	    	int id = Integer.parseInt(sid);
	                	    boolean listimg= imagedataservice.tobeforensics(id);//待取证
	                	    
	                	    if(req !=null && ServletFileUpload.isMultipartContent(req)){
		         	 			//判断是否文件上传
		         	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
		         	 			//获取上传文件尺寸大小
//		         	 			System.out.println(maxSize);
		         	 			long requestsize2 = ctx.contentLength();
		         	 			System.out.println(requestsize);
		         	 			 ftp.setFileType(FTP.BINARY_FILE_TYPE);
//		          	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
		          	            InputStream input = f.getInputStream();
		          	            System.out.println(input);
//		          	            readStream(input);
//		          	          boolean isResult =  ftp.storeFile(targetFileName, input);
		          	        boolean isResult =  ftp.storeFile(new String(targetFileName.getBytes("GBK"),"iso-8859-1"), input);
		          	          if(isResult ==  true){
		          	        	 System.err.println("上传成功");
		          	        	 list.add(isResult);
		          	        	resultList.setLists(list);
		          	        	  input.close();
		          	        	  ftp.logout();
		          	        	String filepath =targetFileName;//原文件名
		                    	String parentnode =city;
		                    	String uploadtype = upload_type;
		                    	ImageDataUpdate imagedata2 = new ImageDataUpdate(filepath,parentnode,uploadtype);
		                        List<ImageDataUpdate> listimg2= imagedataservice.selectUploadsUpdateType(imagedata2);
		                        if(listimg2.size()> 0 && listimg ==true){
		                    		//return new Json(true,"success",listimg);
		                    		  return new Json(true,"success",listimg2,"上传成功 -待放款确认");
		                    	}else{
		                    		return new Json(false,"fail",listimg2,"请选择城市或上海类型-待放款确认失败");
		                    	}
//		          	        	  return new Json(true,"success",resultList,"上传成功");
		          	        	  
		          	        	  
		          	          }else{
		          	        	  System.err.println("上传失败！"); 
		          	        	  return new Json(false,"fail",resultList,"请选择上传文件类型jpg,png,jpge,bmp,png");
		          	          }
		         	 		}
	                         	
	                         	/*//改成待待取证
	                         	CircuLationRecord circuLationRecord = new CircuLationRecord();//状态改成待取证
	                         	circuLationRecord.setState(5);//待取证
	                         	circuLationRecord.setFallbackname("待取证");//待取证
	                         	circuLationRecord.setUsername("财务");
	                         	circuLationRecord.setCity("");
	                         	circuLationRecord.setParentnodeId("1");
	                         	circuLationRecord.setRolename("按揭");
	                         	boolean isResultInsert = recordSubmitService.fallbackinsert(circuLationRecord);
	                    		if(isResultInsert == true){
	                    			return new Json(true,"success",isResultInsert,"");
	                    		}else{
	                    			return new Json(true,"fail",isResultInsert,"");
	                    		}*/
	                         	
	                     	}
	         	 			if(upload_type.equals("结算凭证")&& requestsize >0 ){
	                     		System.out.println("结算凭证");
	                     		if (!ftp.changeWorkingDirectory("settlement_voucher_image")) {
	             	                ftp.makeDirectory("settlement_voucher_image");//创建目录
	             	                ftp.changeWorkingDirectory("settlement_voucher_image");//跳转目录(可根据项目需求选择创建目录的多少)
	             	            }
//	                     		 ZipUtil1.unZip(shenfenzheng_image+"/"+targetFileName);
	                     		imagedata.setNote(note);//备注
	                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//	                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
	                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/settlement_voucher_image/"+targetFileName.trim());
//	                         	imagedata.setFilepath("");
//	                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
	                         	imagedata.setUploads(note);//上传者
	                         	imagedata.setUploadtype(upload_type);//上传类型
	                         	imagedata.setCity(city);
	                         	imagedata.setSubnode("已传");
	                         	String createData = DateUtils.getInDateTime((new Date()));//日期
	                         	imagedata.setCreateData(createData);
	                         	imagedataservice.imageDataAdd(imagedata);
	                         	
	                        	String sid = reqid;
	                	    	int id = Integer.parseInt(sid);
	                	    boolean listimg= imagedataservice.tobesettled(id);
	                	    
	                	    if(req !=null && ServletFileUpload.isMultipartContent(req)){
		         	 			//判断是否文件上传
		         	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
		         	 			//获取上传文件尺寸大小
//		         	 			System.out.println(maxSize);
		         	 			long requestsize2 = ctx.contentLength();
		         	 			System.out.println(requestsize);
		         	 			 ftp.setFileType(FTP.BINARY_FILE_TYPE);
//		          	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
		          	            InputStream input = f.getInputStream();
		          	            System.out.println(input);
//		          	            readStream(input);
//		          	          boolean isResult =  ftp.storeFile(targetFileName, input);
		          	        boolean isResult =  ftp.storeFile(new String(targetFileName.getBytes("GBK"),"iso-8859-1"), input);
		          	          if(isResult ==  true){
		          	        	 System.err.println("上传成功");
		          	        	 list.add(isResult);
		          	        	resultList.setLists(list);
		          	        	  input.close();
		          	        	  ftp.logout();
		          	        	String filepath =targetFileName;//原文件名
		                    	String parentnode =city;
		                    	String uploadtype = upload_type;
		                    	ImageDataUpdate imagedata2 = new ImageDataUpdate(filepath,parentnode,uploadtype);
		                        List<ImageDataUpdate> listimg2= imagedataservice.selectUploadsUpdateType(imagedata2);
		                        if(listimg2.size()> 0 && listimg ==true){
		                    		//return new Json(true,"success",listimg);
		                    		  return new Json(true,"success",listimg2,"上传成功 -已结清");
		                    	}else{
		                    		return new Json(false,"fail",listimg2,"请选择城市或上海类型-待放款确认失败");
		                    	}
//		          	        	  return new Json(true,"success",resultList,"上传成功");
		          	        	  
		          	          }else{
		          	        	  System.err.println("上传失败！"); 
		          	        	  return new Json(false,"fail",resultList,"请选择上传文件类型jpg,png,jpge,bmp,png");
		          	          }
		         	 		}
	                     	}
	                     	
	         	 			}
	         	 			
	         	 		}else{
	         	 			 return new Json(false,"fail",resultList,"请选择上传文件类型jpg,png,jpge,bmp,png");
	         	 		}
	     	            
	         	 		
//		     	         getPhont(origFileName);
		 	           
	    	         }else{
	    	        	 System.err.println("FTP连接失败");
	    	        	 String str = "FTP连接失败";
	    	        	 list.add(str);
	    	        	 resultList.setLists(list);
//	    	        	 return new Json(false,"fail"resultList,"");
	    	         }
	    	            
//	    	            
	    	        } catch (IOException e) {
//	    	            e.printStackTrace();
	    	            return new Json(false,"fail",e.getMessage(),"连接异常");
	    	        } finally {
	    	            if (ftp.isConnected()) {
	    	                try {
	    	                    ftp.disconnect();
	    	                } catch (IOException ioe) {
	    	                    ioe.printStackTrace();
	    	                }
	    	            }
	    	        }
	    	    }
			return null;
	  }
	  
	  
	  /**
	   *  回款&取证&解押&进押凭证上传
	   * @param req 参数
	   * @param res
	   * @return JSON			
	   */
	  @RequestMapping(value="/voucherupload",method=RequestMethod.POST)
	  @ResponseBody
	  public Json reimbursement_Voucher(
			  @RequestParam(value="file",required=false) MultipartFile[]tmpfile,
			  HttpServletRequest req , HttpServletResponse res){
		  String note = req.getParameter("note");
		  String upload_type= req.getParameter("upload_type");
//		  String filepath = req.getParameter("filepath");
		  String city = req.getParameter("city");
		  String targetFileName = "";
		  String reqid= req.getParameter("id");
		  for(MultipartFile f:tmpfile){
			  FTPClient ftp = new FTPClient();
			  try {
				ftp.connect(ftpIp);
				boolean ftpfalse = ftp.login(ftpUser, ftpPwd);//登录
				if(ftpfalse == true){
					System.err.println("FTP站点连接成功");
					reply = ftp.getReplyCode();
					if(!FTPReply.isPositiveCompletion(reply)){
						ftp.disconnect();
					}
					ftp.enterLocalPassiveMode();
					String tmpFileName = f.getOriginalFilename();
					int dot = tmpFileName.lastIndexOf(".");
					String ext = "";
					if(dot >-1){
						ext = tmpFileName.substring(dot+1);
					}
					if(filetype(f.getOriginalFilename())){
						targetFileName = renameFileName(city, upload_type, tmpFileName);//重命名上传文件名
						ImageDataUpdate imagedata = new ImageDataUpdate();
						if(req !=null && ServletFileUpload.isMultipartContent(req)){
							//判断是否有文件上传
							ServletRequestContext ctx = new ServletRequestContext(req);
							//获取上传文件尺寸大小
							long requestsize = ctx.contentLength();
							System.out.println("上传文件大小: "+requestsize);
							if(upload_type.equals("回款确认") && requestsize>0){
								if(!ftp.changeWorkingDirectory("return_confirmation")){
									ftp.makeDirectory("return_confirmation");
									ftp.changeWorkingDirectory("return_confirmation");
								}
								System.out.println("回款确认");
								imagedata.setNote(note);//备注
	                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//	                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
	                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/return_confirmation/"+targetFileName.trim());
//	                         	imagedata.setFilepath("");
//	                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
	                         	imagedata.setUploads(note);//上传者
	                         	imagedata.setUploadtype(upload_type);//上传类型
	                         	imagedata.setCity(city);
	                         	imagedata.setSubnode("己传");
	                         	String createData = DateUtils.getInDateTime((new Date()));//日期
	                         	imagedata.setCreateData(createData);
	                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录
	                         	

	                  	          String sid = reqid;
		                	    	int id = Integer.parseInt(sid);
		                	    boolean listimg= imagedataservice.loanClearing(id);//待解压
		                	    
	                         	if(req !=null && ServletFileUpload.isMultipartContent(req)){
	                 	 			//判断是否文件上传
	                 	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
	                 	 			//获取上传文件尺寸大小
//	                 	 			System.out.println(maxSize);
	                 	 			long requestsize2 = ctx2.contentLength();
	                 	 			System.out.println(requestsize2);
	                 	 			 ftp.setFileType(FTP.BINARY_FILE_TYPE);
//	                  	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
	                  	            InputStream input = f.getInputStream();
	                  	            System.out.println(input);
//	                  	            readStream(input);
//	                  	          boolean isResult =  ftp.storeFile(targetFileName, input);
	                  	       
		                	    
	                  	        boolean isResult =  ftp.storeFile(new String(targetFileName.getBytes("GBK"),"iso-8859-1"), input);
	                  	          if(isResult ==  true){
	                  	        	 System.err.println("上传成功");
	                  	        	  input.close();
	                  	        	  ftp.logout();
	                  	        	String filepath =targetFileName;//原文件名
	                            	String parentnode =city;
	                            	String uploadtype = upload_type;
	                            	ImageDataUpdate imagedata2 = new ImageDataUpdate(filepath,parentnode,uploadtype);
	                            List<ImageDataUpdate> listimg2= imagedataservice.selectUploadsUpdateType(imagedata2);
	                  	        	if(listimg2.size()> 0 && listimg == true){
	                            		//return new Json(true,"success",listimg);
	                            		  return new Json(true,"success",listimg2,"上传成功-- 待结算审核");
	                            	}else{
	                            		return new Json(false,"fail",listimg2,"请选择城市或上海类型");
	                            	}
	                  	        	  
	                  	          }else{
	                  	        	  System.err.println("上传失败！"); 
	                  	        	  return new Json(false,"fail",isResult,"请选择上传文件类型jpg,png,jpge,bmp,png");
	                  	          }
	                 	 		}else{
	            					System.out.println("FTP连接失败");
	            				}
	                         	
							}if(upload_type.equals("取证凭证")&& requestsize > 0){
								System.out.println("取证凭证");
								if(!ftp.changeWorkingDirectory("obtain_evidence")){
									ftp.makeDirectory("obtain_evidence");
									ftp.changeWorkingDirectory("obtain_evidence");
								}
								System.out.println("取证凭证");
								imagedata.setNote(note);//备注
	                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//	                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
	                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/obtain_evidence/"+targetFileName.trim());
//	                         	imagedata.setFilepath("");
//	                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
	                         	imagedata.setUploads(note);//上传者
	                         	imagedata.setUploadtype(upload_type);//上传类型
	                         	imagedata.setCity(city);
	                         	imagedata.setSubnode("己传");
	                         	String createData = DateUtils.getInDateTime((new Date()));//日期
	                         	imagedata.setCreateData(createData);
	                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录

	                  	          String sid = reqid;
		                	    	int id = Integer.parseInt(sid);
		                	    boolean listimg= imagedataservice.WaitForensics(id);//待解压
		                	    
	                         	if(req !=null && ServletFileUpload.isMultipartContent(req)){
	                 	 			//判断是否文件上传
	                 	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
	                 	 			//获取上传文件尺寸大小
//	                 	 			System.out.println(maxSize);
	                 	 			long requestsize2 = ctx2.contentLength();
	                 	 			System.out.println(requestsize2);
	                 	 			 ftp.setFileType(FTP.BINARY_FILE_TYPE);
//	                  	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
	                  	            InputStream input = f.getInputStream();
	                  	            System.out.println(input);
//	                  	            readStream(input);
//	                  	          boolean isResult =  ftp.storeFile(targetFileName, input);
	                  	       
		                	    
	                  	        boolean isResult =  ftp.storeFile(new String(targetFileName.getBytes("GBK"),"iso-8859-1"), input);
	                  	          if(isResult ==  true){
	                  	        	 System.err.println("上传成功");
	                  	        	  input.close();
	                  	        	  ftp.logout();
	                  	        	String filepath =targetFileName;//原文件名
	                            	String parentnode =city;
	                            	String uploadtype = upload_type;
	                            	ImageDataUpdate imagedata2 = new ImageDataUpdate(filepath,parentnode,uploadtype);
	                            List<ImageDataUpdate> listimg2= imagedataservice.selectUploadsUpdateType(imagedata2);
	                  	        	if(listimg2.size()> 0 && listimg == true){
	                            		//return new Json(true,"success",listimg);
	                            		  return new Json(true,"success",listimg2,"上传成功-- 待进解压凭证审核");
	                            	}else{
	                            		return new Json(false,"fail",listimg2,"请选择城市或上海类型");
	                            	}
	                  	        	  
	                  	          }else{
	                  	        	  System.err.println("上传失败！"); 
	                  	        	  return new Json(false,"fail",isResult,"请选择上传文件类型jpg,png,jpge,bmp,png");
	                  	          }
	                 	 		}else{
	            					System.out.println("FTP连接失败");
	            				}
	                         	
							}if(upload_type.equals("解押凭证")&& requestsize>0){
								System.out.println("解押凭证");
								if(!ftp.changeWorkingDirectory("solutionvoucher")){
									ftp.makeDirectory("solutionvoucher");
									ftp.changeWorkingDirectory("solutionvoucher");
								}
								System.out.println("解押凭证");
								imagedata.setNote(note);//备注
	                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//	                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
	                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/solutionvoucher/"+targetFileName.trim());
//	                         	imagedata.setFilepath("");
//	                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
	                         	imagedata.setUploads(note);//上传者
	                         	imagedata.setUploadtype(upload_type);//上传类型
	                         	imagedata.setCity(city);
	                         	imagedata.setSubnode("己传");
	                         	String createData = DateUtils.getInDateTime((new Date()));//日期
	                         	imagedata.setCreateData(createData);
	                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录
	                         	
	                            
	                  	          String sid = reqid;
		                	    	int id = Integer.parseInt(sid);
		                	    boolean listimg= imagedataservice.tobedetained(id);//待进压
		                	    
	                         	if(req !=null && ServletFileUpload.isMultipartContent(req)){
	                 	 			//判断是否文件上传
	                 	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
	                 	 			//获取上传文件尺寸大小
//	                 	 			System.out.println(maxSize);
	                 	 			long requestsize2 = ctx2.contentLength();
	                 	 			System.out.println(requestsize2);
	                 	 			 ftp.setFileType(FTP.BINARY_FILE_TYPE);
//	                  	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
	                  	            InputStream input = f.getInputStream();
	                  	            System.out.println(input);
//	                  	            readStream(input);
//	                  	          boolean isResult =  ftp.storeFile(targetFileName, input);
	                  	       
		                	    
	                  	        boolean isResult =  ftp.storeFile(new String(targetFileName.getBytes("GBK"),"iso-8859-1"), input);
	                  	          if(isResult ==  true){
	                  	        	 System.err.println("上传成功");
	                  	        	  input.close();
	                  	        	  ftp.logout();
	                  	        	String filepath =targetFileName;//原文件名
	                            	String parentnode =city;
	                            	String uploadtype = upload_type;
	                            	ImageDataUpdate imagedata2 = new ImageDataUpdate(filepath,parentnode,uploadtype);
	                            List<ImageDataUpdate> listimg2= imagedataservice.selectUploadsUpdateType(imagedata2);
	                  	        	if(listimg2.size()> 0 && listimg == true){
	                            		//return new Json(true,"success",listimg);
	                            		  return new Json(true,"success",listimg2,"上传成功-- 待进押凭证审核");
	                            	}else{
	                            		return new Json(false,"fail",listimg2,"请选择城市或上海类型");
	                            	}
	                  	        	  
	                  	          }else{
	                  	        	  System.err.println("上传失败！"); 
	                  	        	  return new Json(false,"fail",isResult,"请选择上传文件类型jpg,png,jpge,bmp,png");
	                  	          }
	                 	 		}else{
	            					System.out.println("FTP连接失败");
	            				}
	                         	
	                         	
							}if(upload_type.equals("进押凭证")&& requestsize>0){
								System.out.println("进押凭证");
								if(!ftp.changeWorkingDirectory("entervoucher")){
									ftp.makeDirectory("entervoucher");
									ftp.changeWorkingDirectory("entervoucher");
								}
								System.out.println("进押凭证");
								imagedata.setNote(note);//备注
	                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//	                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
	                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/entervoucher/"+targetFileName.trim());
//	                         	imagedata.setFilepath("");
//	                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
	                         	imagedata.setUploads(note);//上传者
	                         	imagedata.setUploadtype(upload_type);//上传类型
	                         	imagedata.setCity(city);
	                         	imagedata.setSubnode("己传");
	                         	String createData = DateUtils.getInDateTime((new Date()));//日期
	                         	imagedata.setCreateData(createData);
	                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录
	                         	
	                         	String sid = reqid;
	                	    	int id = Integer.parseInt(sid);
	                	    boolean listimg= imagedataservice.pendingconfirmation(id);//待确认回款
	                	    
                         	if(req !=null && ServletFileUpload.isMultipartContent(req)){
                 	 			//判断是否文件上传
                 	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
                 	 			//获取上传文件尺寸大小
//                 	 			System.out.println(maxSize);
                 	 			long requestsize2 = ctx2.contentLength();
                 	 			System.out.println(requestsize2);
                 	 			 ftp.setFileType(FTP.BINARY_FILE_TYPE);
//                  	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
                  	            InputStream input = f.getInputStream();
                  	            System.out.println(input);
//                  	            readStream(input);
//                  	          boolean isResult =  ftp.storeFile(targetFileName, input);
                  	       
	                	    
                  	        boolean isResult =  ftp.storeFile(new String(targetFileName.getBytes("GBK"),"iso-8859-1"), input);
                  	          if(isResult ==  true){
                  	        	 System.err.println("上传成功");
                  	        	  input.close();
                  	        	  ftp.logout();
                  	        	String filepath =targetFileName;//原文件名
                            	String parentnode =city;
                            	String uploadtype = upload_type;
                            	ImageDataUpdate imagedata2 = new ImageDataUpdate(filepath,parentnode,uploadtype);
                            List<ImageDataUpdate> listimg2= imagedataservice.selectUploadsUpdateType(imagedata2);
                  	        	if(listimg2.size()> 0 && listimg == true){
                            		//return new Json(true,"success",listimg);
                            		  return new Json(true,"success",listimg2,"上传成功-- 待确认回款审核");
                            	}else{
                            		return new Json(false,"fail",listimg2,"请选择城市或上海类型");
                            	}
                  	        	  
                  	          }else{
                  	        	  System.err.println("上传失败！"); 
                  	        	  return new Json(false,"fail",isResult,"请选择上传文件类型jpg,png,jpge,bmp,png");
                  	          }
                 	 		}else{
            					System.out.println("FTP连接失败");
            				}
							}
							
						}
					}else{
        	 			 return new Json(false,"fail",resultList,"请选择上传文件类型jpg,png,jpge,bmp,png");
        	 		}
					
				}
			} catch (Exception e) {
//				e.printStackTrace();
				return new Json(false,"fail",e.getMessage(),"连接异常");
			}finally {
	            if (ftp.isConnected()) {
	                try {
	                    ftp.disconnect();
	                } catch (IOException ioe) {
	                    ioe.printStackTrace();
	                }
	            }
	        }
		  }
		return null;
		  
	  }
	  
	  /**
	     * 根据 上传类型 原文件名  上传者姓名查所上传的
	     * @param req
	     * @param res
	     * @return 对象
	     */
	    @RequestMapping(value="/selectuploadsupdatetype",method=RequestMethod.GET)
	    @ResponseBody
	    public Json selectUploadsUpdateType(HttpServletRequest req , HttpServletResponse res){
	    	String data = req.getParameter("data");
	    	JSONObject jsonobj = new JSONObject().fromObject(data);
	    	String originalfilename = jsonobj.getString("file");//原文件名
	    	String parentnode = jsonobj.getString("city");
	    	String uploadtype = jsonobj.getString("upload_type");
//	    	String id = jsonobj.getString("id");
//	    	int zid = Integer.parseInt(id);
	    	ImageDataUpdate imagedata = new ImageDataUpdate(originalfilename,parentnode,uploadtype);
	    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
	    	if(listimg.size()> 0){
	    		return new Json(true,"success",listimg);
	    	}else{
	    		return new Json(false,"fail",listimg);
	    	}
	    }
	    
	    /**
	     * 修改待取证
	     * @param req
	     * @param res
	     * @return 对象
	     */
	    @RequestMapping(value="/waitforensics",method=RequestMethod.POST)
	    @ResponseBody
	    public Json WaitForensics(HttpServletRequest req , HttpServletResponse res){
//	    	String data = req.getParameter("data");
//	    	JSONObject jsonobj = new JSONObject().fromObject(data);
//	    	String originalfilename = jsonobj.getString("file");//原文件名
//	    	String parentnode = jsonobj.getString("city");
//	    	String uploadtype = jsonobj.getString("upload_type");
	    	String sid = req.getParameter("id");
	    	int id = Integer.parseInt(sid);
//	    	ImageDataUpdate imagedata = new ImageDataUpdate(originalfilename,parentnode,uploadtype);
	    boolean listimg= imagedataservice.WaitForensics(id);
	    	if(listimg == true){
	    		return new Json(true,"success",listimg,"待放款到待取证修改成功");
	    	}else{
	    		return new Json(false,"fail",listimg,"待放款到待取证修改失改");
	    	}
	    }
	    
	    
	    /**
	     * 终审批量拒绝回退到初审
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value="/loaninalreviewbatch",method=RequestMethod.POST)
	    @ResponseBody
	    public Json loaninalreviewbatch(HttpServletRequest request,HttpServletResponse response){
	    	String items = request.getParameter("data");
	    	JSONObject jsonobj = new JSONObject().fromObject(items);
	    	List<String> loanFinalRefuse = new ArrayList<String>();
	    	String id = jsonobj.getString("id");
	    	String[] split= id.split(",");
	    	for(String s:split){
	    		String str = s.replace("[", " ").replace("]", " ").trim();
	    		loanFinalRefuse.add(str);
	    	}
	    	boolean isResult = imagedataservice.loanFinalReviewRefuse(loanFinalRefuse);
	    	if(isResult == true){
	    		return new Json(true,"success",isResult,"待终回退到初审审批中成功");
	    	}else{
	    		return new Json(false,"fail",isResult,"待终回退到初审审批中失败");
	    	}
	    }
	    
	    /**
	     * 终审批量审批通到财务
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value="/loaninalreviewbatchpast",method=RequestMethod.POST)
	    @ResponseBody
	    public Json loaninalreviewbatchpast(HttpServletRequest request,HttpServletResponse response){
	    	String items = request.getParameter("data");
	    	JSONObject jsonobj = new JSONObject().fromObject(items);
	    	List<String> loanFinalRefuse = new ArrayList<String>();
	    	String id = jsonobj.getString("id");
	    	String[] split= id.split(",");
	    	for(String s:split){
	    		String str = s.replace("[", " ").replace("]", " ").trim();
	    		loanFinalRefuse.add(str);
	    	}
	    	boolean isResult = imagedataservice.loanFinalReviewPast(loanFinalRefuse);
	    	if(isResult == true){
	    		return new Json(true,"success",isResult,"终审到待出账确认成功");
	    	}else{
	    		return new Json(false,"fail",isResult,"终审到待出账确认失败");
	    	}
	    }
	    
	    /**
	     * 初审批量拒绝回退到按揭员
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value="/refusegobackmortgage",method=RequestMethod.POST)
	    @ResponseBody
	    public Json batchRefuse(HttpServletRequest request,HttpServletResponse response){
	    	String items = request.getParameter("data");
	    	JSONObject jsonobj = new JSONObject().fromObject(items);
	    	List<String> batchRefuse = new ArrayList<String>();
	    	String id = jsonobj.getString("id");
	    	String[] split= id.split(",");
	    	for(String s:split){
	    		String str = s.replace("[", " ").replace("]", " ").trim();
	    		batchRefuse.add(str);
	    	}
	    	boolean isResult = imagedataservice.FirsttrialbatchRefuse(batchRefuse);
	    	if(isResult == true){
	    		return new Json(true,"success",isResult,"初审审批回退成功");
	    	}else{
	    		return new Json(false,"fail",isResult,"初审审批回退失败");
	    	}
	    }
	    
	    /**
	     * 初审批通过待终审审批成功
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value="/pastgobackfinalreview",method=RequestMethod.POST)
	    public Json batchPast(HttpServletRequest request,HttpServletResponse response){
	    	String item = request.getParameter("data");
	    	JSONObject jsonobject = new JSONObject().fromObject(item);
	    	String id = jsonobject.getString("id");
	    	List<String> batchPast = new ArrayList<String>();
	    	String [] split = id.split(",");
	    	for(String s : split){
	    		String streplacee = s.replace("[", " ").replace("]", " ").trim();
	    		batchPast.add(streplacee);
	    	}
	    	boolean isResult = imagedataservice.FirsttrialbatchPast(batchPast);
			    	if(isResult == true){
			    		return new Json(true,"success",isResult,"初审批通过待终审审批成功");
			    	}else{
			    		return new Json(false,"fail",isResult,"初审批通过待终审审批失败");
			    	}
	    }
	    
	    
	    /**
	     *  财务批量审核通过 待放款
	     * @param request 请求参数
	     * @param response JSON格式
	     * @return
	     */
	    @RequestMapping(value="/batchupdatestudent",method=RequestMethod.POST)
		@ResponseBody
		public Json batchUpdateStudent(HttpServletRequest request,HttpServletResponse response){
	    	String items = request.getParameter("data");
	    	JSONObject obj = new JSONObject().fromObject(items);
//	    	System.out.println(obj);
	    	String id = obj.getString("id");
	    	
//	    	String id = request.getParameter("id");
//	    	String dt = id.replace("[[\"", " ").replace("\"]]", " ").replace("\"", "");
//	    	System.out.println(dt);
	    	String [] split = id.split(",");
//	    	String  split = id;
	    	List<String> updatelist = new ArrayList<String>();
	    	for(String s:split){
	    		String dt = s.replace("[", " ").replace("]", " ").trim();
//	    		System.out.println(dt);
	    		updatelist.add(dt);
	    	}
	    	
	    	boolean isResult = imagedataservice.batchUpdateStudent(updatelist);
	    	if(isResult == true){
	    		return new Json(true,"success",isResult,"待出账确认成功");
	    	}else{
	    		return new Json(false,"fail",isResult,"待出账确认失败");
	    	}
	    }
	    
	    
	    /**
	     * 财务批量审核拒绝退回到终审 
	     * @param request 请求参数
	     * @param response JSON格式
	     * @return
	     */
	    @RequestMapping(value="batchupdateadopt")
	    @ResponseBody
	    public Json batchUpdateadopt(HttpServletRequest request,HttpServletResponse response){
//	    	String items = request.getParameter("data");
//	    	JSONObject obj = new JSONObject().fromObject(items);
//	    	String id = obj.getString("id");
//	    	String dt = id.replace("[\"","").replace("\"]","").replace("\"", "");
//	    	List<String> adoptlist = new ArrayList<String>();
//	    	String[] splist = dt.split(",");
//	    	for(String s :splist){
//	    		adoptlist.add(s);
//	    	}
	    	String items = request.getParameter("data");
	    	JSONObject obj = new JSONObject().fromObject(items);
	    	String id = obj.getString("id");
	    	List<String> updatelist = new ArrayList<String>();
	    	String[] splist = id.split(",");
	    	for(String s :splist){
	    		String dt = s.replace("["," ").replace("]"," ").trim();
	    		updatelist.add(dt);
	    	}
	    	boolean isResult = imagedataservice.batchUpdateadopt(updatelist);
	    	if(isResult == true){
	    		return new Json(true,"success",isResult,"回退到终审成功");
	    	}else{
	    		return new Json(false,"fail",isResult,"回退到终审失败");
	    	}
	    }
	    
	    
	    
	    
	  /**
	   * 文件类型
	   * @param str 文件类型
	   * @return jpg,png,jpge,png,bmp
	   */
	  private boolean filetype(String str) {
	    	if(str.contains("jpg")|| str.contains("png") || str.contains("jpge")
	    			 || str.contains("bmp")
	    			 || str.contains("png")){
	    		return true;
	    	}else
			return false;
		}
	  
	  /**
	     * 文件重命名
	     * @param tmpFileName 
	     */
	    public static String renameFileName(String city,String renameFileName,String fileName) {
	        String formatDate = new SimpleDateFormat("yyMMddHHmmss").format(new Date()); // 当前时间字符串
	        int random = new Random().nextInt(10000);
	        String extension = fileName.substring(fileName.lastIndexOf(".")+1); // 文件后缀
	 
	        return formatDate + random+"_"+city+"_"+fileName;
	    }
}
