package com.slloan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.slloan.constants.CaptchaConstants;
import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.ImageDataUpdate;
import com.slloan.entity.ResultList;
import com.slloan.entity.UserLogin;
import com.slloan.entity.circulation;
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.service.inter.ImagedataService;
import com.slloan.service.inter.UserService;
import com.slloan.service.inter.circulationService;
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
	public int ftpPort;//端口
	
	@Value("${ftpUser}")
	public String ftpUser;//用户名
	
	@Value("${ftpPwd}")
	public String ftpPwd;//密码
	
	@Value("${fileSize}")
	public int fileSize;
	
	@Value("${sftp}")
	public String sftpp;
	
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
	 InputStream input;
	Set<String> setlist = new TreeSet<String>();
	ResultList<Object> resultList = new ResultList<Object>();
	List<MultipartFile> fileupdate =new ArrayList<MultipartFile>();
	List<Object> list = new ArrayList<Object>();
	@Autowired
	private ImagedataService imagedataservice;
	
	@Autowired
	private CircuLationRecordSubmitService recordSubmitService;
	
	@Autowired
	private circulationService cService;
	
	@Autowired
	private circulationService circulationservice;
	
	@Autowired
	private UserService userservice;
	
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
	    	String reqid= req.getParameter("uid");
	    	String usernameid = req.getParameter("usernameid");
			String username = req.getParameter("username");
			String roleName = req.getParameter("rolename");
			String t = req.getParameter("id");
		  
//		  final String note = "结算凭证备注";//备注
//	    	final String upload_type = "转账凭证";//上传类型
////	    	final	String filepath = req.getParameter("filepath");
//	    	final	String city = "长沙";
//	    	String reqid= "25";
//	    	String usernameid = "26";
//			String username = "张三";
//			String roleName = "系统管理员";
//			String t = "22";
			
			String createDate = DateUtils.getInDateTime((new Date()));//日期
	    	String targetFileName = "";
	    	Session session = null;
			Channel channel = null;
	    	for(final MultipartFile f:tmpfile){
//	    	        FTPClient ftp = new FTPClient();
	    	        JSch jsch = new JSch();
	    	        try {
	    	        	if(ftpPort <= 0){
    	    	        	session = jsch.getSession(ftpUser, ftpIp);
    		        	}else{
    		        		session = jsch.getSession(ftpUser, ftpIp,ftpPort);
    		        	}
    	        	
    	        	//如果服务器连接不上，则抛出异常
    	     		if (session == null) {
    	     			throw new Exception("session is null");
    	     		}
    	     		//设置登陆主机的密码
    	    		session.setPassword(ftpPwd);//设置密码   
    	    		//设置第一次登陆的时候提示，可选值：(ask | yes | no)
    	    		session.setConfig("StrictHostKeyChecking", "no");
    	    		//设置登陆超时时间   
    	    		session.connect(30000);
    	    		
    	    		//创建sftp通信通道
    				channel = (Channel) session.openChannel(sftpp);
    				channel.connect(1000);
    				ChannelSftp sftp = (ChannelSftp) channel;
	    	         if(session !=null){
	    	        	 System.err.println("FTP站点连接成功！");
//	    	        	 reply = ftp.getReplyCode();
	     	            if (!FTPReply.isPositiveCompletion(reply)) {
	     	                ftp.disconnect();
	     	            }
//	     	            ftp.enterLocalPassiveMode();
	     	            final String tmpFileName = f.getOriginalFilename();
	         	   	 	final int dot = tmpFileName.lastIndexOf(".");
	         	   	 String ext = "" ;//文件名后缀
	         	 		if(dot >-1){
	         	 			ext = tmpFileName.substring(dot+1);
	         	 		}
	         	 		//jpeg,jpg,gif,png,bmp
	         	 		if(filetype(f.getOriginalFilename())){
//	         	 			 targetFileName = renameFileName(city,upload_type,tmpFileName);// 重命名上传的文件名
	         	 			  ImageDataUpdate imagedata = new ImageDataUpdate();
	         	 
	         	 			if(req !=null && ServletFileUpload.isMultipartContent(req)){
	             	 			//判断是否文件上传
	             	 			ServletRequestContext ctx = new ServletRequestContext(req);
	             	 			//获取上传文件尺寸大小
//	             	 			System.out.println(maxSize);
	             	 			long requestsize = ctx.contentLength();
	             	 			System.out.println("aaaa------------:  "+requestsize);
	             	 			int filesize = fileSize;
	             	 			if(requestsize >filesize){
	             	 				return new Json(false,"fail","",CaptchaConstants.UPDATESIZE);
	             	 			}else{
	             	 				 if(upload_type.equals("转账凭证") && requestsize >0 ){
	             	 				MultipartFile mfile = null;
	             	 				for (int i = 0; i < tmpfile.length; i++) {
//	             	 					if(tmpfile.length>0){
	             	 						mfile = tmpfile[i];
	             	 						if(mfile.getSize()>1){
	             	 							
		             	 					fileupdate.add(mfile);
		             	 					 targetFileName = renameFileName(city,upload_type,mfile.getOriginalFilename());// 重命名上传的文件名
		             	 					System.out.println(mfile.getOriginalFilename());
		             	 					System.out.println(mfile.getSize());
		     	                     		System.out.println("转账凭证");
		     	                     		imagedata.setNote(note);//备注
		     	                         	imagedata.setOriginalfilename(mfile.getOriginalFilename());//原文件名
//		     	                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//		     	                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/transfer_accounts_image/"+targetFileName.trim());
//		     	                         	imagedata.setFilepath("");
		     	                         	imagedata.setFilepath("http://120.79.252.173:8080/ftp/transfer_accounts_image/"+targetFileName.trim());
//		     	                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
		     	                         	imagedata.setUploads(note);//上传者
		     	                         	imagedata.setUploadtype(upload_type);//上传类型
		     	                         	imagedata.setCity(city);
		     	                         	imagedata.setSubnode("已传");
		     	                         	imagedata.setParentnode(usernameid);//用户名ID
		     	                         	imagedata.setSpare(username);//用户名
		     	                         	imagedata.setSparetwo(roleName);//角色名
		     	                         	String createData = DateUtils.getInDateTime((new Date()));//日期
		     	                         	imagedata.setCreateData(createData);
		     	                         	imagedata.setUploadFtpRoute(reqid);
		     	                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录
		     	                         	sftp.cd(CaptchaConstants.TRANSFER_ACCOUNTS_IMAGE);
		     	                         	sftp.put(mfile.getInputStream(),new String(targetFileName.getBytes("GBK")));  //上传文件
	             	 					}else{
	             	 						continue;
	             	 					}
	             	 					 
	     	                     		}
	             	 				}
	             	 				circulation userLogin = circulationservice.selectRandomNumbersecond(reqid,city,"按揭员");
	             	 				 boolean listimg = false;
	             	 				if(userLogin !=null){
	             	 					System.out.println(userLogin.getId());
	              	 					System.out.println(userLogin.getCity());
	              	 					System.out.println(userLogin.getRolename());
	              	 					String userName = userLogin.getUsername();
//	             	 					int intid = userLogin.getId();
	             	 					String ParentnodeId = userLogin.getParentnodeId();
	             	 					String roleName2= userLogin.getRolename();
	             	 					String sid = reqid;
	     	                	    	int id = Integer.parseInt(sid);
	     	                	    	Map<Object,Object> map = new HashMap<Object,Object>();
	     	                	    		map.put("username",userName);
	     	                	    		map.put("parentnodeId", ParentnodeId);
	     	                	    		map.put("rolename", roleName2);
	     	                	    		map.put("id", id);
	     	                	     listimg= imagedataservice.tobeforensics(map);//待取证
	             	 				}
	             	 				
     	                	    
     	                	    if(req !=null && ServletFileUpload.isMultipartContent(req)){
     		         	 			//判断是否文件上传
     		         	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
     		         	 			//获取上传文件尺寸大小
//     		         	 			System.out.println(maxSize);
     		         	 			long requestsize2 = ctx.contentLength();
     		         	 			System.out.println(requestsize);
//     		          	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
     		          	             input = f.getInputStream();
     		          	            System.out.println(input);
//     		          	            readStream(input);
//     		          	          boolean isResult =  ftp.storeFile(targetFileName, input);
//     		          	        boolean isResult =  ftp.storeFile(new String(targetFileName.getBytes("GBK"),"iso-8859-1"), input);
     		          	      if(fileupdate.size()>0){
     		          	        	 System.err.println("上传成功");
//     		          	        	sftp.put(f.getInputStream(),new String(targetFileName.getBytes("GBK")));  //上传文件
//     		          	        	 list.add(isResult);
//     		          	        	resultList.setLists(list);
//     		          	        	  input.close();
//     		          	        	  ftp.logout();
     		          	        	String filepath =targetFileName;//原文件名
     		                    	String parentnode =city;
     		                    	String uploadtype = upload_type;
     		                    	String Parentnode = usernameid;
     		                    	String spare = username;
     		                    	String sparetwo = roleName;
     		                    	ImageDataUpdate imagedata2 = new ImageDataUpdate(city,uploadtype,Parentnode,spare,sparetwo,createDate);
     		                        List<ImageDataUpdate> listimg2= imagedataservice.financevoucherSelectToupload(imagedata2);
     		            			String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
     		                        circulation record = new  circulation("5","待取证",createDate,username,t,city,sparetwo,updatedata,reqid);
     		                    	boolean coan = circulationservice.save(record);
     		                        if(listimg2.size()> 0 && listimg ==true && coan== true){
//     		                    		return new Json(true,"success",listimg);
     		                    		  return new Json(true,"success",listimg2,CaptchaConstants.PENDING_LOAN_CONFIRMATION_UPLOAD_SUCCESS);
//     		                    		 return new Json(true, "success", listimg2,CaptchaConstants.PENDING_LOAN_CONFIRMATION_UPLOAD_SUCCESS);
     		                    	}else{
     		                    		return new Json(false,"fail",listimg2,CaptchaConstants.UPLOAD_FAILUREFAILURE_TO_CONFIR_A_LOAN);
     		                    	}
//     		          	        	  return new Json(true,"success",resultList,"上传成功");
     		          	        	  
     		          	        	  
     		          	          }else{
     		          	        	  System.err.println("上传失败！"); 
     		          	        	  return new Json(false,"fail",resultList,CaptchaConstants.IMAGE_TYPE);
     		          	          }
     		         	 		}
	     	         	 			
	             	 			}
	         	 			
//	             	 			}
	         	 			}
	         	 			
	         	 		}else{
	         	 			 return new Json(false,"fail",resultList,CaptchaConstants.IMAGE_TYPE);
	         	 		}
	     	            
	         	 		
//		     	         getPhont(origFileName);
		 	           
	    	         }else{
	    	        	 System.err.println("FTP连接失败");
	    	        	 String str = "FTP连接失败";
	    	        	 list.add(str);
	    	        	 resultList.setLists(list);
	    	        	 return new Json(false,"fail","","FTP连接失败");
	    	         }
	    	            
//	    	            
	    	        } catch (Exception e) {
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
	    	        session.disconnect();
	    			channel.disconnect();
	    			 input.close();
	 	        	  ftp.logout();
	    	    }
			return null;
	  }
	  
	  
	  /**
		 * 结算凭证上传到FTP
		 * @param tmpfile
		 * @param req
		 * @param res
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		  @RequestMapping(value = "/transferaccountsvoucherclearing",method=RequestMethod.POST)
		    @ResponseBody
			public Json transferaccountsvoucherclearing(@RequestParam(value="file",required=false)MultipartFile[] tmpfile,
		  			final HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		    	final String note = req.getParameter("note");//备注
		    	final String upload_type = req.getParameter("upload_type");//上传类型
//		    	final	String filepath = req.getParameter("filepath");
		    	final	String city = req.getParameter("city");
		    	String reqid= req.getParameter("uid");
		    	String usernameid = req.getParameter("usernameid");
				String username = req.getParameter("username");
				String roleName = req.getParameter("rolename");
				String t = req.getParameter("id");
			  
//			  final String note = "结算凭证备注";//备注
//		    	final String upload_type = "结算凭证";//上传类型
////		    	final	String filepath = req.getParameter("filepath");
//		    	final	String city = "长沙";
//		    	String reqid= "25";
//		    	String usernameid = "26";
//				String username = "张三";
//				String roleName = "系统管理员";
//				String t = "22";
				
				String createDate = DateUtils.getInDateTime((new Date()));//日期
		    	String targetFileName = "";
		    	Session session = null;
				Channel channel = null;
		    	for(final MultipartFile f:tmpfile){
//		    	        FTPClient ftp = new FTPClient();
		    	        JSch jsch = new JSch();
		    	        try {
		    	        	if(ftpPort <= 0){
	    	    	        	session = jsch.getSession(ftpUser, ftpIp);
	    		        	}else{
	    		        		session = jsch.getSession(ftpUser, ftpIp,ftpPort);
	    		        	}
	    	        	
	    	        	//如果服务器连接不上，则抛出异常
	    	     		if (session == null) {
	    	     			throw new Exception("session is null");
	    	     		}
	    	     		//设置登陆主机的密码
	    	    		session.setPassword(ftpPwd);//设置密码   
	    	    		//设置第一次登陆的时候提示，可选值：(ask | yes | no)
	    	    		session.setConfig("StrictHostKeyChecking", "no");
	    	    		//设置登陆超时时间   
	    	    		session.connect(30000);
	    	    		
	    	    		//创建sftp通信通道
	    				channel = (Channel) session.openChannel(sftpp);
	    				channel.connect(1000);
	    				ChannelSftp sftp = (ChannelSftp) channel;
		    	         if(session !=null){
		    	        	 System.err.println("FTP站点连接成功！");
//		    	        	 reply = ftp.getReplyCode();
		     	            if (!FTPReply.isPositiveCompletion(reply)) {
		     	                ftp.disconnect();
		     	            }
//		     	            ftp.enterLocalPassiveMode();
		     	            final String tmpFileName = f.getOriginalFilename();
		         	   	 	final int dot = tmpFileName.lastIndexOf(".");
		         	   	 String ext = "" ;//文件名后缀
		         	 		if(dot >-1){
		         	 			ext = tmpFileName.substring(dot+1);
		         	 		}
		         	 		//jpeg,jpg,gif,png,bmp
		         	 		if(filetype(f.getOriginalFilename())){
//		         	 			 targetFileName = renameFileName(city,upload_type,tmpFileName);// 重命名上传的文件名
		         	 			  ImageDataUpdate imagedata = new ImageDataUpdate();
		         	 
		         	 			if(req !=null && ServletFileUpload.isMultipartContent(req)){
		             	 			//判断是否文件上传
		             	 			ServletRequestContext ctx = new ServletRequestContext(req);
		             	 			//获取上传文件尺寸大小
		             	 			System.out.println(maxSize);
		             	 			long requestsize = ctx.contentLength();
		             	 			System.out.println("aaaa------------:  "+requestsize);
		             	 			int filesize = fileSize;
		             	 			if(requestsize >filesize){
		             	 				return new Json(false,"fail","",CaptchaConstants.UPDATESIZE);
		             	 			}else{
		             	 				
	     	                	   MultipartFile mfile2 = null;
	     	                	  if(upload_type.equals("结算凭证")&& requestsize >0 ){
	            	 				for (int i = 0; i < tmpfile.length; i++) {
	            	 					 mfile2 = tmpfile[i];
	            	 					if(mfile2.getSize()>1){
	            	 						fileupdate.add(mfile2);
		             	 					 targetFileName = renameFileName(city,upload_type,mfile2.getOriginalFilename());// 重命名上传的文件名
		             	 					System.out.println(mfile2.getOriginalFilename());
		             	 					System.out.println(mfile2.getSize());
		     	                     		System.out.println("结算凭证");
//		     	                     		 ZipUtil1.unZip(shenfenzheng_image+"/"+targetFileName);
		     	                     		imagedata.setNote(note);//备注
		     	                         	imagedata.setOriginalfilename(mfile2.getOriginalFilename());//原文件名
//		     	                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//		     	                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/settlement_voucher_image/"+targetFileName.trim());
		     	                         	imagedata.setFilepath("http://120.79.252.173:8080/ftp/settlement_voucher_image/"+targetFileName.trim());
//		     	                         	imagedata.setFilepath("");
//		     	                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
		     	                         	imagedata.setUploads(note);//上传者
		     	                         	imagedata.setUploadtype(upload_type);//上传类型
		     	                         	imagedata.setCity(city);
		     	                         	imagedata.setSubnode("已传");
		     	                         	imagedata.setParentnode(usernameid);//用户名ID
		     	                         	imagedata.setSpare(username);//用户名
		     	                         	imagedata.setSparetwo(roleName);//角色名
		     	                         	String createData = DateUtils.getInDateTime((new Date()));//日期
		     	                         	imagedata.setCreateData(createData);
		     	                         	imagedata.setUploadFtpRoute(reqid);
		     	                         	imagedataservice.imageDataAdd(imagedata);
		     	                         	sftp.cd(CaptchaConstants.SETTLEMENT_VOUCHER_IMAGE);
		     	                         	sftp.put(mfile2.getInputStream(),new String(targetFileName.getBytes("GBK")));  //上传文件
	            	 					}else{
	            	 						continue;
	            	 					}
	            	 					
		     	         	 				
		     	                         	
//		     	                        	String sid = reqid;
//		     	                	    	int id = Integer.parseInt(sid);
//		     	                	    boolean listimg= imagedataservice.tobesettled(id);
		     	                	    
		     	                	   
		     	                     	}
	            	 				}
				     	                	 String sid = reqid;
			  	                	    	int id = Integer.parseInt(sid);
			  	                	    boolean listimg= imagedataservice.tobesettled(id);//待取证
		     	         	 		 if(req !=null && ServletFileUpload.isMultipartContent(req)){
		     		         	 			//判断是否文件上传
		     		         	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
		     		         	 			//获取上传文件尺寸大小
//		     		         	 			System.out.println(maxSize);
		     		         	 			long requestsize2 = ctx.contentLength();
		     		         	 			System.out.println(requestsize);
//		     		         	 			 ftp.setFileType(FTP.BINARY_FILE_TYPE);
//		     		          	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
		     		          	            InputStream input = f.getInputStream();
		     		          	            System.out.println(input);
//		     		          	            readStream(input);
//		     		          	          boolean isResult =  ftp.storeFile(targetFileName, input);
		     		          	          
		     		          	          if(req !=null && ServletFileUpload.isMultipartContent(req)){
		     		          	        	 System.err.println("上传成功");
//		     		          	        	 list.add(isResult);
//		     		          	        	resultList.setLists(list);
//		     		          	        	  input.close();
//		     		          	        	  ftp.logout();
		     		          	        	String filepath =targetFileName;//原文件名
		     		                    	String parentnode =city;
		     		                    	String uploadtype = upload_type;
		     		                    	String Parentnode = usernameid;
		     		                    	String spare = username;
		     		                    	String sparetwo = roleName;
		     		                    	ImageDataUpdate imagedata2 = new ImageDataUpdate(city,uploadtype,Parentnode,spare,sparetwo,createDate);
		     		                        List<ImageDataUpdate> listimg2= imagedataservice.financevoucherSelectToupload2(imagedata2);
		     		                        String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
		     		                        circulation record = new  circulation("10","待取证",createDate,username,t,city,sparetwo,updatedata,reqid);
		     		                        boolean coan = circulationservice.save(record);
		     		                        if(listimg2.size()> 0 && listimg ==true && coan == true){
		     		                    		//return new Json(true,"success",listimg);
		     		                    		  return new Json(true,"success",listimg2,CaptchaConstants.UPLOAD_SUCCESS_ALREADY_CLEAR);
		     		                    	}else{
		     		                    		return new Json(false,"fail",listimg2,CaptchaConstants.UPLOAD_FAILURE_WAIT_FOR_INFORMATION_TO_VIEW_FAILURE);
		     		                    	}
//		     		          	        	  return new Json(true,"success",resultList,"上传成功");
		     		          	        	  
		     		          	          }else{
		     		          	        	  System.err.println("上传失败！"); 
		     		          	        	  return new Json(false,"fail",resultList,CaptchaConstants.IMAGE_TYPE);
		     		          	          }
		     		         	 		}
		             	 			}
		         	 			
//		             	 			}
		         	 			}
		         	 			
		         	 		}else{
		         	 			 return new Json(false,"fail",resultList,CaptchaConstants.IMAGE_TYPE);
		         	 		}
		     	            
		         	 		
//			     	         getPhont(origFileName);
			 	           
		    	         }else{
		    	        	 System.err.println("FTP连接失败");
		    	        	 String str = "FTP连接失败";
		    	        	 list.add(str);
		    	        	 resultList.setLists(list);
		    	        	 return new Json(false,"fail","","FTP连接失败");
		    	         }
		    	            
//		    	            
		    	        } catch (Exception e) {
//		    	            e.printStackTrace();
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
		    	        session.disconnect();
		    			channel.disconnect();
		    			 input.close();
		 	        	  ftp.logout();
		    	    }
				return null;
		  }
		  /**
		   *  回款凭证上传
		   * @param req 参数
		   * @param res
		   * @return JSON			
		   */
		  @RequestMapping(value="/voucherupload",method=RequestMethod.POST,produces="application/json;charset=utf-8")
		  @ResponseBody
		  public Json reimbursement_Voucher(
				  @RequestParam(value="file",required=false) MultipartFile[]tmpfile,
				  HttpServletRequest req , HttpServletResponse res){
			  String note = req.getParameter("note");
			  String usernameid = req.getParameter("usernameid");
			  String username = req.getParameter("username");
			  String roleName = req.getParameter("rolename");
			  String upload_type= req.getParameter("upload_type");
			  String createDate = DateUtils.getInDateTime((new Date()));//日期
			  String city = req.getParameter("city");
			  String t = req.getParameter("id");
			  String targetFileName = "";
			  String reqid= req.getParameter("state");
//			  final String note = "备注随便说点什么";
//		    	final	String city = "长沙";
//				String usernameid = "40";//用户ID
//				String reqid= "25";
//				String username = "bb";
//				String roleName = "系统管理员";
//				String uid = "28";
//				final String upload_type= "回款确认";//上传类型
//				 String reqid= "3";
			  Session session = null;
				Channel channel = null;
			  for(MultipartFile f:tmpfile){
				  JSch jsch = new JSch();
				  try {
					  if(ftpPort <= 0){
		    	        	session = jsch.getSession(ftpUser, ftpIp);
			        	}else{
			        		session = jsch.getSession(ftpUser, ftpIp,ftpPort);
			        	}
		        	
		        	//如果服务器连接不上，则抛出异常
		     		if (session == null) {
		     			throw new Exception("session is null");
		     		}
		     		//设置登陆主机的密码
		    		session.setPassword(ftpPwd);//设置密码   
		    		//设置第一次登陆的时候提示，可选值：(ask | yes | no)
		    		session.setConfig("StrictHostKeyChecking", "no");
		    		//设置登陆超时时间   
		    		session.connect(30000);
		    		
		    		//创建sftp通信通道
					channel = (Channel) session.openChannel(sftpp);
					channel.connect(1000);
					ChannelSftp sftp = (ChannelSftp) channel;
					if(session !=null){
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
								if(requestsize > fileSize){
									
								}else{
									MultipartFile mfile3 = null;
									
									if(upload_type.equals("回款确认") && requestsize>0){
										for (int i = 0; i < tmpfile.length; i++) {
											 mfile3 = tmpfile[i];
											if(mfile3.getSize()>1){
												 fileupdate.add(mfile3);
			             	 					 targetFileName = renameFileName(city,upload_type,mfile3.getOriginalFilename());// 重命名上传的文件名
			             	 					System.out.println(mfile3.getOriginalFilename());
			             	 					System.out.println(mfile3.getSize());
			             	 					System.out.println("回款确认");
												imagedata.setNote(note);//备注
					                         	imagedata.setOriginalfilename(mfile3.getOriginalFilename());//原文件名
//					                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//					                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/return_confirmation/"+targetFileName.trim());
					                        	imagedata.setFilepath("http://120.79.252.173:8080/ftp/return_confirmation/"+targetFileName.trim());
//					                         	imagedata.setFilepath("");
//					                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
					                         	imagedata.setUploads(note);//上传者
					                         	imagedata.setUploadtype(upload_type);//上传类型
					                         	imagedata.setCity(city);
					                         	imagedata.setSubnode("己传");
					                         	imagedata.setParentnode(usernameid);//用户名ID
					                         	imagedata.setSpare(username);//用户名
					                         	imagedata.setSparetwo(roleName);//角色名
					                         	String createData = DateUtils.getInDateTime((new Date()));//日期
					                         	imagedata.setCreateData(createData);
					                         	imagedata.setUploadFtpRoute(reqid);
					                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录
					                         	sftp.cd(CaptchaConstants.RETURN_CONFIRMATION);
					                         	sftp.put(mfile3.getInputStream(),new String(targetFileName.getBytes("GBK")));  //上传文件  
											}else{
												continue;
											}
										}
									}
			                         	if(req !=null && ServletFileUpload.isMultipartContent(req)){
			                 	 			//判断是否文件上传
			                 	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
			                 	 			//获取上传文件尺寸大小
//			                 	 			System.out.println(maxSize);
			                 	 			long requestsize2 = ctx2.contentLength();
			                 	 			System.out.println(requestsize2);
//			                  	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
			                  	            InputStream input = f.getInputStream();
			                  	            System.out.println(input);
//			                  	            readStream(input);
//			                  	          boolean isResult =  ftp.storeFile(targetFileName, input);
			                  	       
				                	    
//			                  	        boolean isResult =  ftp.storeFile(new String(targetFileName.getBytes("GBK"),"iso-8859-1"), input);
//			                  	          sftp.put(f.getInputStream(),new String(targetFileName.getBytes("UTF-8")));  //上传文件  
			                  	          if(req !=null && ServletFileUpload.isMultipartContent(req)){
			                  	        	 System.err.println("上传成功");
//			                  	        	  input.close();
//			                  	        	  ftp.logout();
			                  	        	String filepath =targetFileName;//原文件名
			                            	String parentnode =city;
			                            	String uploadtype = upload_type;
			                            	String Parentnode = usernameid;
			                            	String spare = username;
			                            	String sparetwo = roleName;
			                            	ImageDataUpdate imagedata2 = new ImageDataUpdate(city,uploadtype,Parentnode,spare,sparetwo,createDate);
			                            List<ImageDataUpdate> listimg2= imagedataservice.financevoucherSelectToupload(imagedata2);
			                            String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
				                        circulation record = new  circulation("9","待取证",createDate,username,t,city,sparetwo,updatedata,reqid);
				                        boolean coan = circulationservice.save(record);
				                        
				                        circulation userLogin = circulationservice.selectRandomNumbersecond(reqid,city,"财务审批");
				                        boolean listimg = false;
		             	 				if(userLogin !=null){
		             	 					System.out.println(userLogin.getId());
		              	 					System.out.println(userLogin.getCity());
		              	 					System.out.println(userLogin.getRolename());
		              	 					String userName = userLogin.getUsername();
//		             	 					int intid = userLogin.getId();
		             	 					String ParentnodeId = userLogin.getParentnodeId();
		             	 					String roleName2= userLogin.getRolename();
		             	 					String sid = reqid;
				                	    	int id = Integer.parseInt(sid);
				                	    	Map<Object,Object> map = new HashMap<Object,Object>();
				                	    	map.put("username",userName);
	     	                	    		map.put("parentnodeId", ParentnodeId);
	     	                	    		map.put("rolename", roleName2);
	     	                	    		map.put("id", id);
	     	                	    		 listimg= imagedataservice.loanClearing(map);//待解压
		             	 				}
			                            if(listimg2.size()> 0 && listimg == true && coan == true){
			                            		//return new Json(true,"success",listimg);
			                            		  return new Json(true,"success",listimg2,CaptchaConstants.UPLOAD_SUCCESS_PENDING_AUDIT);
			                            	}else{
			                            		return new Json(false,"fail",listimg2,CaptchaConstants.FILE_TYPE);
			                            	}
			                  	        	  
			                  	          }else{
			                  	        	  System.err.println("上传失败！"); 
			                  	        	  return new Json(false,"fail","",CaptchaConstants.IMAGE_TYPE);
			                  	          }
			                 	 		}else{
//			            					System.out.println("FTP连接失败");
			            					 return new Json(false,"fail","",CaptchaConstants.CONNECTION_FAILED);
			            				}
			                         	
									}
							}
						}else{
	        	 			 return new Json(false,"fail",resultList,CaptchaConstants.IMAGE_TYPE);
	        	 		}
						
					}
				} catch (Exception e) {
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
		   *  进押凭证上传
		   * @param req 参数
		   * @param res
		   * @return JSON			
		   */
		  @RequestMapping(value="/forensicsincarceration",method=RequestMethod.POST,produces="application/json;charset=utf-8")
		  @ResponseBody
		  public Json forensicsincarceration(
				  @RequestParam(value="file",required=false) MultipartFile[]tmpfile,
				  HttpServletRequest req , HttpServletResponse res){
			  String note = req.getParameter("note");
			  String usernameid = req.getParameter("usernameid");
				String username = req.getParameter("username");
				String roleName = req.getParameter("rolename");
			  String upload_type= req.getParameter("upload_type");
			  String createDate = DateUtils.getInDateTime((new Date()));//日期
//			  String filepath = req.getParameter("filepath");
			  String city = req.getParameter("city");
			  String t = req.getParameter("id");
			  String targetFileName = "";
			  String reqid= req.getParameter("state");
//			  String note = req.getParameter("note");
//			  String usernameid = req.getParameter("usernameid");
//				String username = req.getParameter("username");
//				String roleName = req.getParameter("rolename");
//			  String upload_type= req.getParameter("upload_type");
			  
//			  String createDate = DateUtils.getInDateTime((new Date()));//日期
//			  String city = req.getParameter("city");
//			  String t = "25";
//			  String targetFileName = "";
////			  String reqid= req.getParameter("state");
//			  final String note = "备注随便说点什么";
//		    	final	String city = "长沙";
//				String usernameid = "40";//用户ID
//				String reqid= "25";
//				String username = "bb";
//				String roleName = "系统管理员";
//				String uid = "28";
//				final String upload_type= "进押凭证";//上传类型
			  Session session = null;
				Channel channel = null;
			  for(MultipartFile f:tmpfile){
				  JSch jsch = new JSch();
				  try {
					  if(ftpPort <= 0){
		    	        	session = jsch.getSession(ftpUser, ftpIp);
			        	}else{
			        		session = jsch.getSession(ftpUser, ftpIp,ftpPort);
			        	}
		        	
		        	//如果服务器连接不上，则抛出异常
		     		if (session == null) {
		     			throw new Exception("session is null");
		     		}
		     		//设置登陆主机的密码
		    		session.setPassword(ftpPwd);//设置密码   
		    		//设置第一次登陆的时候提示，可选值：(ask | yes | no)
		    		session.setConfig("StrictHostKeyChecking", "no");
		    		//设置登陆超时时间   
		    		session.connect(30000);
		    		
		    		//创建sftp通信通道
					channel = (Channel) session.openChannel(sftpp);
					channel.connect(1000);
					ChannelSftp sftp = (ChannelSftp) channel;
					if(session !=null){
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
								if(requestsize > fileSize){
									return new Json(false,"fail","",CaptchaConstants.UPDATESIZE);
								}else{
									MultipartFile mfile7 = null;
									if(upload_type.equals("进押凭证")&& requestsize>0){
										for (int i = 0; i < tmpfile.length; i++) {
											mfile7 = tmpfile[i];
											if(mfile7.getSize()>1){
//			             	 					resultList.setLists(list);
			             	 					fileupdate.add(mfile7);
			             	 					 targetFileName = renameFileName(city,upload_type,mfile7.getOriginalFilename());// 重命名上传的文件名
			             	 					System.out.println(mfile7.getOriginalFilename());
			             	 					System.out.println(mfile7.getSize());
			             	 					System.out.println("进押凭证");
												imagedata.setNote(note);//备注
					                         	imagedata.setOriginalfilename(mfile7.getOriginalFilename());//原文件名
//					                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//					                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/entervoucher/"+targetFileName.trim());
					                         	imagedata.setFilepath("http://120.79.252.173:8080/ftp/entervoucher/"+targetFileName.trim());
//					                         	imagedata.setFilepath("");
//					                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
					                         	imagedata.setUploads(note);//上传者
					                         	imagedata.setUploadtype(upload_type);//上传类型
					                         	imagedata.setCity(city);
					                         	imagedata.setSubnode("己传");
					                         	imagedata.setParentnode(usernameid);//用户名ID
					                         	imagedata.setSpare(username);//用户名
					                         	imagedata.setSparetwo(roleName);//角色名
					                         	String createData = DateUtils.getInDateTime((new Date()));//日期
					                         	imagedata.setCreateData(createData);
					                         	imagedata.setUploadFtpRoute(reqid);
					                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录
					                         	sftp.cd(CaptchaConstants.ENTERVOUCHER);
					                         	 sftp.put(mfile7.getInputStream(),new String(targetFileName.getBytes("GBK")));  //上传文件 
											}else{
												continue;
											  }
											}
										}
				                	    if(req !=null && ServletFileUpload.isMultipartContent(req)){
			                 	 			//判断是否文件上传
			                 	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
			                 	 			//获取上传文件尺寸大小
//			                 	 			System.out.println(maxSize);
			                 	 			long requestsize2 = ctx2.contentLength();
			                 	 			System.out.println(requestsize2);
//			                  	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
			                  	            InputStream input = f.getInputStream();
			                  	            System.out.println(input);
//			                  	            readStream(input);
//			                  	          boolean isResult =  ftp.storeFile(targetFileName, input);
			                  	          if(req !=null && ServletFileUpload.isMultipartContent(req)){
			                  	        	 System.err.println("上传成功");
			                  	        	String filepath =targetFileName;//原文件名
			                            	String parentnode =city;
			                            	String uploadtype = upload_type;
			                            	String Parentnode = usernameid;
			                            	String spare = username;
			                            	String sparetwo = roleName;
			                            	ImageDataUpdate imagedata2 = new ImageDataUpdate(city,uploadtype,Parentnode,spare,sparetwo,createDate);
			                            List<ImageDataUpdate> listimg2= imagedataservice.financevoucherSelectToupload(imagedata2);
			                            String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
			                            circulation record = new  circulation("8","待进押",createDate,username,t,city,sparetwo,updatedata,reqid);
			                			boolean coan = circulationservice.save(record);		
			                			
			                			circulation userLogin = circulationservice.selectRandomNumbersecond(reqid,city,"初审");
			                			boolean listimg = false;
			                			if(userLogin !=null){
			                				System.out.println(userLogin.getId());
		              	 					System.out.println(userLogin.getCity());
		              	 					System.out.println(userLogin.getRolename());
		              	 					String userName = userLogin.getUsername();
//		             	 					int intid = userLogin.getId();
		             	 					String ParentnodeId = userLogin.getParentnodeId();
		             	 					String roleName2= userLogin.getRolename();
			                				String sid = reqid;
				                	    	int id = Integer.parseInt(sid);
				                	
				                	    Map<Object,Object> map = new HashMap<Object,Object>();
     	                	    		map.put("username",userName);
     	                	    		map.put("parentnodeId", ParentnodeId);
     	                	    		map.put("rolename", roleName2);
     	                	    		map.put("id", id);
     	                	    		listimg = imagedataservice.pendingconfirmation(map);//待解压
			                			}	
			                            if(listimg2.size()> 0 && listimg == true && coan == true){
			                            		//return new Json(true,"success",listimg);
			                            		  return new Json(true,"success",listimg2,"上传成功-- 待确认回款审核");
			                            	}else{
			                            		return new Json(false,"fail",listimg2,CaptchaConstants.FILE_TYPE);
			                            	}
			                  	        	  
			                  	          }else{
			                  	        	  System.err.println("上传失败！"); 
			                  	        	  return new Json(false,"fail","",CaptchaConstants.IMAGE_TYPE);
			                  	          }
			                 	 		}else{
			            					System.out.println("FTP连接失败");
			            				}
		                         	
									}
							}
						}else{
	        	 			 return new Json(false,"fail",resultList,CaptchaConstants.IMAGE_TYPE);
	        	 		}
						
					}
				} catch (Exception e) {
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
		   *  解押凭证上传
		   * @param req 参数
		   * @param res
		   * @return JSON			
		   */
		  @RequestMapping(value="/forensicsrelief",method=RequestMethod.POST,produces="application/json;charset=utf-8")
		  @ResponseBody
		  public Json forensicsrelief(
				  @RequestParam(value="file",required=false) MultipartFile[]tmpfile,
				  HttpServletRequest req , HttpServletResponse res){
			  String note = req.getParameter("note");
			  String usernameid = req.getParameter("usernameid");
				String username = req.getParameter("username");
				String roleName = req.getParameter("rolename");
			  String upload_type= req.getParameter("upload_type");
			  String createDate = DateUtils.getInDateTime((new Date()));//日期
//			  String filepath = req.getParameter("filepath");
			  String city = req.getParameter("city");
			  String t = req.getParameter("id");
			  String targetFileName = "";
			  String reqid= req.getParameter("state");
//			  String createDate = DateUtils.getInDateTime((new Date()));//日期
//			  String city = req.getParameter("city");
//			  String t = "25";
//			  String targetFileName = "";
////			  String reqid= req.getParameter("state");
//			  final String note = "备注随便说点什么";
//		    	final	String city = "长沙";
//				String usernameid = "40";//用户ID
//				String reqid= "25";
//				String username = "bb";
//				String roleName = "系统管理员";
//				String uid = "28";
//				final String upload_type= "解押凭证";//上传类型
			  Session session = null;
				Channel channel = null;
			  for(MultipartFile f:tmpfile){
				  JSch jsch = new JSch();
				  try {
					  if(ftpPort <= 0){
		    	        	session = jsch.getSession(ftpUser, ftpIp);
			        	}else{
			        		session = jsch.getSession(ftpUser, ftpIp,ftpPort);
			        	}
		        	
		        	//如果服务器连接不上，则抛出异常
		     		if (session == null) {
		     			throw new Exception("session is null");
		     		}
		     		//设置登陆主机的密码
		    		session.setPassword(ftpPwd);//设置密码   
		    		//设置第一次登陆的时候提示，可选值：(ask | yes | no)
		    		session.setConfig("StrictHostKeyChecking", "no");
		    		//设置登陆超时时间   
		    		session.connect(30000);
		    		
		    		//创建sftp通信通道
					channel = (Channel) session.openChannel(sftpp);
					channel.connect(1000);
					ChannelSftp sftp = (ChannelSftp) channel;
					if(session !=null){
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
								if(requestsize > fileSize){
									return new Json(false,"fail","",CaptchaConstants.UPDATESIZE);
								}else{
									if(upload_type.equals("解押凭证")&& requestsize>0){
										MultipartFile mfile6 = null;	
										for (int i = 0; i < tmpfile.length; i++) {
											mfile6 = tmpfile[i];
											if(mfile6.getSize()>1){
												fileupdate.add(mfile6);
			             	 					 targetFileName = renameFileName(city,upload_type,mfile6.getOriginalFilename());// 重命名上传的文件名
			             	 					System.out.println(mfile6.getOriginalFilename());
			             	 					System.out.println(mfile6.getSize());
											System.out.println("解押凭证");
											imagedata.setNote(note);//备注
				                         	imagedata.setOriginalfilename(mfile6.getOriginalFilename());//原文件名
//				                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//				                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/solutionvoucher/"+targetFileName.trim());
				                         	imagedata.setFilepath("http://120.79.252.173:8080/ftp/solutionvoucher/"+targetFileName.trim());
//				                         	imagedata.setFilepath("");
//				                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
				                         	imagedata.setUploads(note);//上传者
				                         	imagedata.setUploadtype(upload_type);//上传类型
				                         	imagedata.setCity(city);
				                         	imagedata.setSubnode("己传");
				                         	imagedata.setParentnode(usernameid);//用户名ID
				                         	imagedata.setSpare(username);//用户名
				                         	imagedata.setSparetwo(roleName);//角色名
				                         	String createData = DateUtils.getInDateTime((new Date()));//日期
				                         	imagedata.setCreateData(createData);
				                         	imagedata.setUploadFtpRoute(reqid);
				                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录
				                         	sftp.cd(CaptchaConstants.SOLUTIONVOUCHER);
				                         	sftp.put(mfile6.getInputStream(),new String(targetFileName.getBytes("GBK")));  //上传文件  
											}else{
												continue;
											}
										}
									}
									
									if(req !=null && ServletFileUpload.isMultipartContent(req)){
		                 	 			//判断是否文件上传
		                 	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
		                 	 			//获取上传文件尺寸大小
//		                 	 			System.out.println(maxSize);
		                 	 			long requestsize2 = ctx2.contentLength();
		                 	 			System.out.println(requestsize2);
//		                  	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
		                  	            InputStream input = f.getInputStream();
		                  	            System.out.println(input);
		                  	          if(req !=null && ServletFileUpload.isMultipartContent(req)){
		                  	        	 System.err.println("上传成功");
//		                  	        	  input.close();
//		                  	        	  ftp.logout();
		                  	        	String filepath =targetFileName;//原文件名
		                            	String parentnode =city;
		                            	String uploadtype = upload_type;
		                            	String Parentnode = usernameid;
		                            	String spare = username;
		                            	String sparetwo = roleName;
		                            	ImageDataUpdate imagedata2 = new ImageDataUpdate(city,uploadtype,Parentnode,spare,sparetwo,createDate);
		                            List<ImageDataUpdate> listimg2= imagedataservice.financevoucherSelectToupload(imagedata2);
		                            String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
		                            circulation record = new  circulation("7","待解押",createDate,username,t,city,sparetwo,updatedata,reqid);
		                			boolean coan = circulationservice.save(record);	
		                			
		                			circulation userLogin = circulationservice.selectRandomNumbersecond(reqid,city,"按揭员");
									 boolean listimg = false;
									 Map<Object,Object> map = new HashMap<Object,Object>();
									 if(userLogin !=null){
										 System.out.println(userLogin.getId());
										 System.out.println(userLogin.getId());
		              	 					System.out.println(userLogin.getCity());
		              	 					System.out.println(userLogin.getRolename());
		              	 					String userName = userLogin.getUsername();
//		             	 					int intid = userLogin.getId();
		             	 					String ParentnodeId = userLogin.getParentnodeId();
		             	 					String roleName2= userLogin.getRolename();
		             	 					String sid = reqid;
				                	    	int id = Integer.parseInt(sid);
				                	    	map.put("username",userName);
	     	                	    		map.put("parentnodeId", ParentnodeId);
	     	                	    		map.put("rolename", roleName2);
	     	                	    		map.put("id", id);
				                	     listimg= imagedataservice.tobedetained(map);//待解压
				                	     
									 }
		                			
		                			
		                			
		                            if(listimg2.size()> 0 && listimg == true && coan == true){
		                            		//return new Json(true,"success",listimg);
		                            		  return new Json(true,"success",listimg2,"上传成功-- 待进押凭证审核");
		                            	}else{
		                            		return new Json(false,"fail",listimg2,CaptchaConstants.FILE_TYPE);
		                            	}
		                  	        	  
		                  	          }else{
		                  	        	  System.err.println("上传失败！"); 
		                  	        	  return new Json(false,"fail","",CaptchaConstants.IMAGE_TYPE);
		                  	          }
		                 	 		}else{
		            					System.out.println("FTP连接失败");
		            				}
		                	    
									}
								
				                	    
			                         	
							}
						}else{
	        	 			 return new Json(false,"fail",resultList,CaptchaConstants.IMAGE_TYPE);
	        	 		}
						
					}
				} catch (Exception e) {
//					e.printStackTrace();
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
	   *  取证凭证上传
	   * @param req 参数
	   * @param res
	   * @return JSON			
	   */
	  @RequestMapping(value="/forensics",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	  @ResponseBody
	  public Json Forensics(
			  @RequestParam(value="file",required=false) MultipartFile[]tmpfile,
			  HttpServletRequest req , HttpServletResponse res){
		  String note = req.getParameter("note");
		  String usernameid = req.getParameter("usernameid");
		  String username = req.getParameter("username");
		  String roleName = req.getParameter("rolename");
		  String upload_type= req.getParameter("upload_type");
		  String createDate = DateUtils.getInDateTime((new Date()));//日期
//		  String filepath = req.getParameter("filepath");
		  String city = req.getParameter("city");
		  String t = req.getParameter("id");
		  String targetFileName = "";
		  String reqid= req.getParameter("state");
//		  String createDate = DateUtils.getInDateTime((new Date()));//日期
//		  String city = req.getParameter("city");
//		  String t = "25";
//		  String targetFileName = "";
//		  String reqid= req.getParameter("state");
//		  final String note = "备注随便说点什么";
//	    	final	String city = "长沙";
//			String usernameid = "40";//用户ID
//			String reqid= "25";
//			String username = "bb";
//			String roleName = "系统管理员";
//			String uid = "28";
//			final String upload_type= "取证凭证";//上传类型
		  Session session = null;
			Channel channel = null;
		  for(MultipartFile f:tmpfile){
			  JSch jsch = new JSch();
			  try {
				  if(ftpPort <= 0){
	    	        	session = jsch.getSession(ftpUser, ftpIp);
		        	}else{
		        		session = jsch.getSession(ftpUser, ftpIp,ftpPort);
		        	}
	        	
	        	//如果服务器连接不上，则抛出异常
	     		if (session == null) {
	     			throw new Exception("session is null");
	     		}
	     		//设置登陆主机的密码
	    		session.setPassword(ftpPwd);//设置密码   
	    		//设置第一次登陆的时候提示，可选值：(ask | yes | no)
	    		session.setConfig("StrictHostKeyChecking", "no");
	    		//设置登陆超时时间   
	    		session.connect(30000);
	    		
	    		//创建sftp通信通道
				channel = (Channel) session.openChannel(sftpp);
				channel.connect(1000);
				ChannelSftp sftp = (ChannelSftp) channel;
				if(session !=null){
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
							if(requestsize > fileSize){
								return new Json(false,"fail","",CaptchaConstants.UPDATESIZE);
							}else{
								if(upload_type.equals("取证凭证")&& requestsize > 0){
									MultipartFile mfile4 = null;
									for (int i = 0; i < tmpfile.length; i++) {
										mfile4 = tmpfile[i];
										if(mfile4.getSize()>1){
											resultList.setLists(list);
		             	 					fileupdate.add(mfile4);
		             	 					 targetFileName = renameFileName(city,upload_type,mfile4.getOriginalFilename());// 重命名上传的文件名
		             	 					System.out.println(mfile4.getOriginalFilename());
		             	 					System.out.println(mfile4.getSize());
		             	 					System.out.println("取证凭证");
											imagedata.setNote(note);//备注
				                         	imagedata.setOriginalfilename(mfile4.getOriginalFilename());//原文件名
//				                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//				                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/obtain_evidence/"+targetFileName.trim());
				                         	imagedata.setFilepath("http://120.79.252.173:8080/ftp/obtain_evidence/"+targetFileName.trim());
//				                         	imagedata.setFilepath("");
//				                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
				                         	imagedata.setUploads(note);//上传者
				                         	imagedata.setUploadtype(upload_type);//上传类型
				                         	imagedata.setCity(city);
				                         	imagedata.setSubnode("己传");
				                         	imagedata.setParentnode(usernameid);//用户名ID
				                         	imagedata.setSpare(username);//用户名
				                         	imagedata.setSparetwo(roleName);//角色名
				                         	imagedata.setUploadFtpRoute(reqid);
				                         	String createData = DateUtils.getInDateTime((new Date()));//日期
				                         	imagedata.setCreateData(createData);
				                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录
				                         	sftp.cd(CaptchaConstants.OBTAIN_EVIDENCE);
				                         	sftp.put(mfile4.getInputStream(),new String(targetFileName.getBytes("GBK")));  //上传文件  
										}else{
											continue;
										}
									}
								}
								
	                	    
                         	if(req !=null && ServletFileUpload.isMultipartContent(req)){
                 	 			//判断是否文件上传
                 	 			ServletRequestContext ctx2 = new ServletRequestContext(req);
                 	 			//获取上传文件尺寸大小
//                 	 			System.out.println(maxSize);
                 	 			long requestsize2 = ctx2.contentLength();
                 	 			System.out.println(requestsize2);
//                  	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
                  	            InputStream input = f.getInputStream();
                  	            System.out.println(input);
//                  	            readStream(input);
//                  	          boolean isResult =  ftp.storeFile(targetFileName, input);
                  	       
	                	    
                  	          
                  	          if(req !=null && ServletFileUpload.isMultipartContent(req)){
                  	        	 System.err.println("上传成功");
//                  	        	  input.close();
//                  	        	  ftp.logout();
                  	        	String filepath =targetFileName;//原文件名
                            	String parentnode =city;
                            	String uploadtype = upload_type;
                            	String Parentnode = usernameid;
                            	String spare = username;
                            	String sparetwo = roleName;
                            	ImageDataUpdate imagedata2 = new ImageDataUpdate(city,uploadtype,Parentnode,spare,sparetwo,createDate);
                            List<ImageDataUpdate> listimg2= imagedataservice.financevoucherSelectToupload(imagedata2);
                			String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
                            circulation record = new  circulation("6","待取证",createDate,username,t,city,sparetwo,updatedata,reqid);
                			boolean coan = circulationservice.save(record);	
                			
                			circulation userLogin = circulationservice.selectRandomNumbersecond(reqid,city,"按揭员");
                			 boolean listimg = false;
                			 if(userLogin !=null){
          	 					System.out.println(userLogin.getId());
          	 					System.out.println(userLogin.getCity());
          	 					System.out.println(userLogin.getRolename());
          	 					String userName = userLogin.getUsername();
//          	 					int intid = userLogin.getId();
          	 					String ParentnodeId = userLogin.getParentnodeId();
          	 					String roleName2= userLogin.getRolename();
          	 					String sid = reqid;
                    	    	int id = Integer.parseInt(sid);
                    	    	Map<Object,Object> map = new HashMap<Object,Object>();
	                	    		map.put("username",userName);
	                	    		map.put("parentnodeId", ParentnodeId);
	                	    		map.put("rolename", roleName2);
	                	    		map.put("id", id);
                    	     listimg= imagedataservice.WaitForensics(map);//待解压
          	 					
                			 }
                			
                			
                            if(listimg2.size()> 0 && listimg == true && coan == true){
                            		//return new Json(true,"success",listimg);
                            		  return new Json(true,"success",listimg2,"上传成功-- 待进解压凭证审核");
                            	}else{
                            		return new Json(false,"fail",listimg2,CaptchaConstants.FILE_TYPE);
                            	}
                  	        	  
                  	          }else{
                  	        	  System.err.println("上传失败！"); 
                  	        	  return new Json(false,"fail","",CaptchaConstants.IMAGE_TYPE);
                  	          }
                 	 		}else{
            					System.out.println("FTP连接失败");
            				}
		                         	
								}
						}
					}else{
        	 			 return new Json(false,"fail",resultList,CaptchaConstants.IMAGE_TYPE);
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
    	res.setContentType("text/html; charset=utf-8");
    	String createDate = DateUtils.getInDateTime((new Date()));//日期
    	String data = req.getParameter("data");
    	JSONObject jsonobj = new JSONObject().fromObject(data);
    	String uploadtype = jsonobj.getString("uploadtype");//原文件名
    	String city = jsonobj.getString("city");
    	String sparetwo = jsonobj.getString("rolename");//角色名
    	String spare = jsonobj.getString("username");//用户名
    	String Parentnode = jsonobj.getString("usernameid");//用户ID
    	String[] splist = uploadtype.split(",");
    	Map<Object,Object> map = new HashMap<Object,Object>();
    		ResultList<ImageDataUpdate> result = new ResultList<ImageDataUpdate>();
    	Map<String,List<ImageDataUpdate>> listmap = new HashMap<String,List<ImageDataUpdate>>();
    	for(String s :splist){
    		String strplist = s.replace("[", " ").replace("]"," ").replace("\""," ").trim();
    		if(strplist.equals("转账凭证")){
    			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate);
    		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
    		    result.setLists(listimg);
    		    listmap.put("转账凭证", listimg);
    		}else if(strplist.equals("结算凭证")){
    			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate);
    		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
    		    result.setLists(listimg);
    		    listmap.put("结算凭证", listimg);
    		}else if(strplist.equals("回款确认")){
    			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate);
    		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
    		    result.setLists(listimg);
    		    listmap.put("回款确认", listimg);
    		}else if(strplist.equals("取证凭证")){
    			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate);
    		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
    		    result.setLists(listimg);
    		    listmap.put("取证凭证", listimg);
    		}else if(strplist.equals("解押凭证")){
    			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate);
    		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
    		    result.setLists(listimg);
    		    listmap.put("解押凭证", listimg);
    		}else if(strplist.equals("进押凭证")){
    			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate);
    		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
    		    result.setLists(listimg);
    		    listmap.put("进押凭证", listimg);
    		}
    	}
    	 return new Json(true,"success",listmap,"财务凭证");
    	
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
	    	String usernameindexof = null;
	    	String rolenameindexof = null ;
	    	String cityindexof= null;
	    	String parentnodeIdindexof= null;
//	    	JSONObject jsonobj = new JSONObject().fromObject(items);
	    	List<String> loanFinalRefuse = new ArrayList<String>();
//	    	String id = jsonobj.getString("id");
	    	String[] split= items.split(",");
	    	String sid = null;
	    	for(String s:split){
	    		String streplacee = s.replace("[", " ").replace("]", " ").replace("{", " ").replace("}", " ").replace("\"", " ").trim();
	    		sid = streplacee;
	    		if(streplacee.contains("username")){
	    			String username = sid;
	    			 usernameindexof = username.substring(username.lastIndexOf(":")+1);
	    			System.out.println(usernameindexof);
	    		}else if(streplacee.contains("parentnodeId")){
	    			String parentnodeId = sid;
	    			 parentnodeIdindexof = parentnodeId.substring(parentnodeId.lastIndexOf(":")+1);
	    			System.out.println(parentnodeIdindexof);
	    		}else if(streplacee.contains("city")){
	    			String city = sid;
	    			 cityindexof = city.substring(city.lastIndexOf(":")+1);
	    			System.out.println(cityindexof);
	    		}else if(streplacee.contains("rolename")){
	    			String rolename = sid;
	    			 rolenameindexof = rolename.substring(rolename.lastIndexOf(":")+1);
	    			System.out.println(rolenameindexof);
	    		}else{
	    			loanFinalRefuse.add(streplacee);
	    		}
	    		
	    	}
	    	
	    	String createDate =  DateUtils.getInDateTime2((new Date()));//日期
			String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
	    	
	    	String t = null;
	    	boolean coan = false;
			for(String str: loanFinalRefuse){
				t= str;
				circulation record = new  circulation("1","待终回退到初审审批中",createDate,usernameindexof,parentnodeIdindexof,cityindexof,rolenameindexof,updatedata,t);
				System.out.println(str);
				coan = circulationservice.save2(record);
				
//				 UserLogin userLogin = userservice.selectRandomNumber("初审",cityindexof.trim());
				 circulation userLogin = circulationservice.selectRandomNumbersecond(t,cityindexof.trim(),"初审");
					if(userLogin !=null){
//						for(UserLogin userLogin: user){
						System.out.println(userLogin.getId());
  	 					System.out.println(userLogin.getCity());
  	 					System.out.println(userLogin.getRolename());
  	 					String userName = userLogin.getUsername();
// 	 					int intid = userLogin.getId();
 	 					String ParentnodeId = userLogin.getParentnodeId();
 	 					String roleName2= userLogin.getRolename();
							Map<Object,Object> map = new HashMap<Object,Object>();
		     	    		map.put("username",userName);
		     	    		map.put("parentnodeId", ParentnodeId);
		     	    		map.put("rolename", roleName2);
		     	    		map.put("id", t);
		     	    		imagedataservice.initialbatch(map);
//						}
					}else{
						return new Json(false,"fail",userLogin,"无下一步操作人");
					}
			}
			boolean isResult = imagedataservice.loanFinalReviewRefuse(loanFinalRefuse);
	    	if(isResult == true && coan){
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
	    	String item = request.getParameter("data");
//	    	JSONObject jsonobj = new JSONObject().fromObject(item);
	    	String usernameindexof = null;
	    	String rolenameindexof = null ;
	    	String cityindexof= null;
	    	String parentnodeIdindexof= null;
	    	List<String> batchPast = new ArrayList<String>();
	    	String [] split = item.split(",");
	    	String sid = null;
	    	for(String s : split){
	    		String streplacee = s.replace("[", " ").replace("]", " ").replace("{", " ").replace("}", " ").replace("\"", " ").trim();
	    		sid = streplacee;
	    		if(streplacee.contains("username")){
	    			String username = sid;
	    			 usernameindexof = username.substring(username.lastIndexOf(":")+1);
	    			System.out.println(usernameindexof);
	    		}else if(streplacee.contains("parentnodeId")){
	    			String parentnodeId = sid;
	    			 parentnodeIdindexof = parentnodeId.substring(parentnodeId.lastIndexOf(":")+1);
	    			System.out.println(parentnodeIdindexof);
	    		}else if(streplacee.contains("city")){
	    			String city = sid;
	    			 cityindexof = city.substring(city.lastIndexOf(":")+1);
	    			System.out.println(cityindexof);
	    		}else if(streplacee.contains("rolename")){
	    			String rolename = sid;
	    			 rolenameindexof = rolename.substring(rolename.lastIndexOf(":")+1);
	    			System.out.println(rolenameindexof);
	    		}else{
	    			batchPast.add(streplacee);
	    		}
	    		
	    		
	    		
	    	}
	    
			String createDate =  DateUtils.getInDateTime2((new Date()));//日期
			String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
//	    	circulation record = new  circulation("2","待终审审批中",createDate,"","","","");
			String t = null;
			boolean coan =false;
			for(String str: batchPast){
				System.out.println(str);
				t= str;
				circulation record = new  circulation("2","待财务审批中",createDate,usernameindexof,parentnodeIdindexof,cityindexof,rolenameindexof,updatedata,t);
				coan = circulationservice.save(record);
				 CircuLationRecord c  = new CircuLationRecord();
					c.setSubmit(t);
					c.setCity(cityindexof.trim());
					c.setState(2);
					CircuLationRecord cir = recordSubmitService.selectByidHangup(c);
					if(cir.getSubmit().equals(t) || cir.getMarked().equals("己挂起")){
						boolean bo = recordSubmitService.updateDateStateCancel(t);
					}
					
					UserLogin userLogin = userservice.selectRandomNumber("财务审批",cityindexof.trim());
					if(userLogin !=null){
//						for(UserLogin userLogin: user){
							System.out.println(userLogin.getId());
							System.out.println(userLogin.getBelongs_City());
							System.out.println(userLogin.getDistribution_Role());
							String userName = userLogin.getUserName();
							int intid = userLogin.getId();
							String ParentnodeId = String.valueOf(intid);
							String roleName2= userLogin.getDistribution_Role();
							Map<Object,Object> map = new HashMap<Object,Object>();
		     	    		map.put("username",userName);
		     	    		map.put("parentnodeId", ParentnodeId);
		     	    		map.put("rolename", roleName2);
		     	    		map.put("id", t);
		     	    		imagedataservice.loanFinalReviewPastMap(map);
//						}
					}else{
						return new Json(false,"fail",userLogin,"无下一步操作人");
					}
					

			}
			boolean isResult = imagedataservice.loanFinalReviewPast(batchPast);
			    	if(isResult == true && coan == true){
			    		return new Json(true,"success",isResult,"终审审批通过待财务审批");
			    	}else{
			    		return new Json(false,"fail",isResult,"终审审批通过待财务审批失败");
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
	    	String usernameindexof = null;
	    	String rolenameindexof = null ;
	    	String cityindexof= null;
	    	String parentnodeIdindexof= null;
//	    	String items = "[2,3,4]";
//	    	JSONObject jsonobj = new JSONObject().fromObject(items);
	    	List<String> batchRefuse = new ArrayList<String>();
//	    	String id = jsonobj.getString("id");
	    	String[] split= items.split(",");
	    	String sid = null;
	    	String NumberingId = null;
	    	for(String s:split){
	    		String streplacee = s.replace("[", " ").replace("]", " ").replace("{", " ").replace("}", " ").replace("\"", " ").trim();
	    		sid = streplacee;
//	    		NumberingId = streplacee;
	    		if(streplacee.contains("username")){
	    			String username = sid;
	    			 usernameindexof = username.substring(username.lastIndexOf(":")+1);
	    			System.out.println(usernameindexof);
	    		}else if(streplacee.contains("parentnodeId")){
	    			String parentnodeId = sid;
	    			 parentnodeIdindexof = parentnodeId.substring(parentnodeId.lastIndexOf(":")+1);
	    			System.out.println(parentnodeIdindexof);
	    		}else if(streplacee.contains("city")){
	    			String city = sid;
	    			 cityindexof = city.substring(city.lastIndexOf(":")+1);
	    			System.out.println(cityindexof);
	    		}else if(streplacee.contains("rolename")){
	    			String rolename = sid;
	    			 rolenameindexof = rolename.substring(rolename.lastIndexOf(":")+1);
	    			System.out.println(rolenameindexof);
	    		}else{
	    			batchRefuse.add(streplacee);
	    		}
	    		
	    	}
	    	String createDate =  DateUtils.getInDateTime2((new Date()));//日期
	    	String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
//			circulation record = new  circulation("0","贷款初审退回到贷款创建",createDate,updatedata,spare);
	    	String t = null;
	    	boolean coan = false;
	    	
			for(String str: batchRefuse){
				NumberingId = str;
				System.out.println(str);
				t= str;
		    	circulation record = new  circulation("0","贷款初审退回到贷款创建",createDate,usernameindexof,parentnodeIdindexof,cityindexof,rolenameindexof,updatedata,t);
				coan = cService.save2(record);
				 CircuLationRecord c  = new CircuLationRecord();
					c.setSubmit(t);
					c.setCity(cityindexof.trim());
					c.setState(1);
					CircuLationRecord cir = recordSubmitService.selectByidHangup(c);
					if(cir.getSubmit().equals(t) || cir.getMarked().equals("己挂起")){
						boolean bo = recordSubmitService.updateDateStateCancel(t);
					}
					circulation userLogin = circulationservice.selectRandomNumbersecond(NumberingId,cityindexof.trim(),"按揭员");
					if(userLogin !=null){
//						for(UserLogin userLogin: user){
						System.out.println(userLogin.getId());
  	 					System.out.println(userLogin.getCity());
  	 					System.out.println(userLogin.getRolename());
  	 					String userName = userLogin.getUsername();
// 	 					int intid = userLogin.getId();
 	 					String ParentnodeId = userLogin.getParentnodeId();
 	 					String roleName2= userLogin.getRolename();
							Map<Object,Object> map = new HashMap<Object,Object>();
		     	    		map.put("username",userName);
		     	    		map.put("parentnodeId", ParentnodeId);
		     	    		map.put("rolename", roleName2);
		     	    		map.put("id", NumberingId);
		     	    		imagedataservice.initialbatch(map);
//						}
					}else{
						return new Json(false,"fail",userLogin,"无下一步操作人");
					}
			}
			
			
	    	boolean isResult = imagedataservice.FirsttrialbatchRefuse(batchRefuse);
	    	
	    	if(isResult == true && coan == true){
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
	    @ResponseBody
	    public Json batchPast(HttpServletRequest request,HttpServletResponse response){
	    	String item = request.getParameter("data");
//	    	JSONObject jsonobj = new JSONObject().fromObject(item);
	    	String usernameindexof = null;
	    	String rolenameindexof = null ;
	    	String cityindexof= null;
	    	String parentnodeIdindexof= null;
	    	List<String> batchPast = new ArrayList<String>();
	    	String [] split = item.split(",");
	    	String sid = null;
	    	for(String s : split){
	    		String streplacee = s.replace("[", " ").replace("]", " ").replace("{", " ").replace("}", " ").replace("\"", " ").trim();
	    		sid = streplacee;
	    		if(streplacee.contains("username")){
	    			String username = sid;
	    			 usernameindexof = username.substring(username.lastIndexOf(":")+1);
	    			System.out.println(usernameindexof);
	    		}else if(streplacee.contains("parentnodeId")){
	    			String parentnodeId = sid;
	    			 parentnodeIdindexof = parentnodeId.substring(parentnodeId.lastIndexOf(":")+1);
	    			System.out.println(parentnodeIdindexof);
	    		}else if(streplacee.contains("city")){
	    			String city = sid;
	    			 cityindexof = city.substring(city.lastIndexOf(":")+1);
	    			System.out.println(cityindexof);
	    		}else if(streplacee.contains("rolename")){
	    			String rolename = sid;
	    			 rolenameindexof = rolename.substring(rolename.lastIndexOf(":")+1);
	    			System.out.println(rolenameindexof);
	    		}else{
	    			batchPast.add(streplacee);
	    		}
	    		
	    		
	    		
	    	}
	    
			String createDate =  DateUtils.getInDateTime2((new Date()));//日期
			String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
//	    	circulation record = new  circulation("2","待终审审批中",createDate,"","","","");
			String t = null;
			boolean coan =false;
			for(String str: batchPast){
				System.out.println(str);
				t= str;
				circulation record = new  circulation("2","待终审审批中",createDate,usernameindexof,parentnodeIdindexof,cityindexof,rolenameindexof,updatedata,t);
				coan = circulationservice.save(record);
				 CircuLationRecord c  = new CircuLationRecord();
					c.setSubmit(t);
					c.setCity(cityindexof.trim());
					c.setState(1);
					CircuLationRecord cir = recordSubmitService.selectByidHangup(c);
					if(cir.getSubmit().equals(t) || cir.getMarked().equals("己挂起")){
						boolean bo = recordSubmitService.updateDateStateCancel(t);
					}
					
					UserLogin userLogin = userservice.selectRandomNumber("终审",cityindexof.trim());
					if(userLogin !=null){
//						for(UserLogin userLogin: user){
							System.out.println(userLogin.getId());
							System.out.println(userLogin.getBelongs_City());
							System.out.println(userLogin.getDistribution_Role());
							String userName = userLogin.getUserName();
							int intid = userLogin.getId();
							String ParentnodeId = String.valueOf(intid);
							String roleName2= userLogin.getDistribution_Role();
							Map<Object,Object> map = new HashMap<Object,Object>();
		     	    		map.put("username",userName);
		     	    		map.put("parentnodeId", ParentnodeId);
		     	    		map.put("rolename", roleName2);
		     	    		map.put("id", t);
		     	    		imagedataservice.initialbatch(map);
//						}
					}else{
						return new Json(false,"fail",userLogin,"无下一步操作人");
					}
					

			}
			boolean isResult = imagedataservice.FirsttrialbatchPast(batchPast);
			    	if(isResult == true && coan == true){
			    		return new Json(true,"success",isResult,"初审批通过待终审审批");
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
	    	String usernameindexof = null;
	    	String rolenameindexof = null ;
	    	String cityindexof= null;
	    	String parentnodeIdindexof= null;
	    	String [] split = items.split(",");
	    	String sid = null;
	    	List<String> updatelist = new ArrayList<String>();
	    	for(String s:split){
	    		String streplacee = s.replace("[", " ").replace("]", " ").replace("{", " ").replace("}", " ").replace("\"", " ").trim();
	    		sid = streplacee;
	    		if(streplacee.contains("username")){
	    			String username = sid;
	    			 usernameindexof = username.substring(username.lastIndexOf(":")+1);
	    			System.out.println(usernameindexof);
	    		}else if(streplacee.contains("parentnodeId")){
	    			String parentnodeId = sid;
	    			 parentnodeIdindexof = parentnodeId.substring(parentnodeId.lastIndexOf(":")+1);
	    			System.out.println(parentnodeIdindexof);
	    		}else if(streplacee.contains("city")){
	    			String city = sid;
	    			 cityindexof = city.substring(city.lastIndexOf(":")+1);
	    			System.out.println(cityindexof);
	    		}else if(streplacee.contains("rolename")){
	    			String rolename = sid;
	    			 rolenameindexof = rolename.substring(rolename.lastIndexOf(":")+1);
	    			System.out.println(rolenameindexof);
	    		}else{
	    			updatelist.add(streplacee);
	    		}
	    		
	    	}
	    	String createDate =  DateUtils.getInDateTime2((new Date()));//日期
			String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
			String t = null;
			boolean coan = false;
			for(String str: updatelist){
				System.out.println(str);
				t= str;
				circulation record = new  circulation("4","待出账确认成功",createDate,usernameindexof,parentnodeIdindexof,cityindexof,rolenameindexof,updatedata,t);
				 coan = circulationservice.save(record);
				 CircuLationRecord c  = new CircuLationRecord();
					c.setSubmit(t);
					c.setCity(cityindexof.trim());
					System.out.println(cityindexof);
					c.setState(3);
					CircuLationRecord cir = recordSubmitService.selectByidHangup(c);
					if(cir.getSubmit().equals(t) || cir.getMarked().equals("己挂起")){
						boolean bo = recordSubmitService.updateDateStateCancel(t);
					}
					UserLogin userLogin = userservice.selectRandomNumber("放款",cityindexof.trim());
					if(userLogin !=null){
//						for(UserLogin userLogin: user){
							System.out.println(userLogin.getId());
							System.out.println(userLogin.getBelongs_City());
							System.out.println(userLogin.getDistribution_Role());
							String userName = userLogin.getUserName();
							int intid = userLogin.getId();
							String ParentnodeId = String.valueOf(intid);
							String roleName2= userLogin.getDistribution_Role();
							Map<Object,Object> map = new HashMap<Object,Object>();
		     	    		map.put("username",userName);
		     	    		map.put("parentnodeId", ParentnodeId);
		     	    		map.put("rolename", roleName2);
		     	    		map.put("id", t);
		     	    		imagedataservice.initialbatch(map);
//						}
					}else{
						return new Json(false,"fail",userLogin,"无下一步操作人");
					}
					
			}
	    	boolean isResult = imagedataservice.batchUpdateStudent(updatelist);
			
			if(isResult == true && coan == true){
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
	    	String usernameindexof = null;
	    	String rolenameindexof = null ;
	    	String cityindexof= null;
	    	String parentnodeIdindexof= null;
//	    	JSONObject obj = new JSONObject().fromObject(items);
//	    	String id = obj.getString("id");
	    	List<String> updatelist = new ArrayList<String>();
	    	String[] splist = items.split(",");
	    	String sid = null;
	    	for(String s :splist){
	    		String streplacee = s.replace("["," ").replace("]"," ").replace("{", " ").replace("}", " ").replace("\"", " ").trim();
	    		sid = streplacee;
	    		if(streplacee.contains("username")){
	    			String username = sid;
	    			 usernameindexof = username.substring(username.lastIndexOf(":")+1);
	    			System.out.println(usernameindexof);
	    		}else if(streplacee.contains("parentnodeId")){
	    			String parentnodeId = sid;
	    			 parentnodeIdindexof = parentnodeId.substring(parentnodeId.lastIndexOf(":")+1);
	    			System.out.println(parentnodeIdindexof);
	    		}else if(streplacee.contains("city")){
	    			String city = sid;
	    			 cityindexof = city.substring(city.lastIndexOf(":")+1);
	    			System.out.println(cityindexof);
	    		}else if(streplacee.contains("rolename")){
	    			String rolename = sid;
	    			 rolenameindexof = rolename.substring(rolename.lastIndexOf(":")+1);
	    			System.out.println(rolenameindexof);
	    		}else{
	    			updatelist.add(streplacee);
	    		}
	    		
	    	}
	    	String createDate =  DateUtils.getInDateTime2((new Date()));//日期
			String updatedata =  DateUtils.getInDateTime2((new Date()));//日期
	    	
	    	String t = null;
	    	boolean coan = false;
			for(String str: updatelist){
				System.out.println(str);
				t= str;
				circulation record = new  circulation("2","回退到终审成功",createDate,usernameindexof,parentnodeIdindexof,cityindexof,rolenameindexof,updatedata,t);
				 coan = circulationservice.save2(record);
				
					
					circulation userLogin = circulationservice.selectRandomNumbersecond(t,cityindexof.trim(),"终审");
					if(userLogin !=null){
//						for(UserLogin userLogin: user){
						System.out.println(userLogin.getId());
  	 					System.out.println(userLogin.getCity());
  	 					System.out.println(userLogin.getRolename());
  	 					String userName = userLogin.getUsername();
// 	 					int intid = userLogin.getId();
 	 					String ParentnodeId = userLogin.getParentnodeId();
 	 					String roleName2= userLogin.getRolename();
							Map<Object,Object> map = new HashMap<Object,Object>();
		     	    		map.put("username",userName);
		     	    		map.put("parentnodeId", ParentnodeId);
		     	    		map.put("rolename", roleName2);
		     	    		map.put("id", t);
		     	    		imagedataservice.initialbatch(map);
//						}
					}
			}
			boolean isResult = imagedataservice.batchUpdateadopt(updatelist);
			 CircuLationRecord c  = new CircuLationRecord();
				c.setSubmit(t);
				c.setCity(cityindexof.trim());
				System.out.println(cityindexof);
				c.setState(2);
				CircuLationRecord cir = recordSubmitService.selectByidHangup(c);
				if(cir.getSubmit().equals(t) || cir.getMarked().equals("己挂起")){
					boolean bo = recordSubmitService.updateDateStateCancel(t);
				}
	    	if(isResult == true&& coan == true){
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
	    	if(str.contains("jpg")|| str.contains("png") || str.contains("jpeg")
	    			 || str.contains("bmp")
	    			 || str.contains("png")|| str.contains("zip")){
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
