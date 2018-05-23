package com.slloan.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.jcraft.jsch.JSch;
import com.slloan.constants.CaptchaConstants;
import com.slloan.entity.ImageDataUpdate;
import com.slloan.entity.ObjectSeq;
import com.slloan.entity.ResultList;
import com.slloan.service.inter.ImagedataService;

import com.slloan.util.DateUtils;
import com.slloan.util.Json;
//import com.slloan.util.ZipUtil1;

import net.sf.json.JSONObject;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
/**
 * 文件上传到FTP
 * @author Administrator
 *
 */
@Controller(value="updateftpcontroller")
@RequestMapping(value="updateftpimage")
public class UpdateFTPController{

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
	
	@Value("${admin}")
	public String admin;
	static ChannelSftp sftp;
	
	FileInputStream fis = null;
	static ZipFile zf ;
	static List<Long> filesize;
	static Long sum = 0l;//文件总大小
	static StringBuffer sb ;
	Iterator it ;//定义一个迭代器
//	List<String>seqselect;
	static String filenameimage;
	 FTPClient ftp = new FTPClient();
	 File target;
	 String filename2;
	 static int reply;
	 private long maxSize;
	Set<String> setlist = new TreeSet<String>();
	
	@Autowired
	private ImagedataService imagedataservice;
	
	@RequestMapping(value="/file",method=RequestMethod.GET)
	public String aa(){
		System.out.println("-------------------------进来了------------------");
		return "fileupdate/fileupdate";
	}
	 /**
     * 影像文件上传
     * @param req
     * @return
     * @throws IOException 
     * @throws ServletException 
     */
    @RequestMapping(value = "/imagedatafileupload",method=RequestMethod.POST,produces="application/json;charset=utf-8")
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
	  	public Json imagedatafile(@RequestParam(value="file",required=false)MultipartFile[] tmpfile,
	  			final HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
    	res.setContentType("text/html; charset=utf-8");
    	res.setCharacterEncoding("text/html; charset=utf-8");
    	final String note = req.getParameter("note");//备注
    	final String upload_type = req.getParameter("upload_type");//上传类型
    	final	String filepath2 = req.getParameter("filepath");
    	final	String city = req.getParameter("city");
    			String usernameid = req.getParameter("id");//用户ID
    			String reqid= req.getParameter("state");
    			String username = req.getParameter("username");
    			String roleName = req.getParameter("rolename");
    			String uid = req.getParameter("uid");
    			int intid = Integer.parseInt(uid);
    			Session session = null;
    			Channel channel = null;
//    	final	String file = req.getParameter("FileUpload");
//    	System.out.println(file);
    	ResultList<Object> pageBean = new ResultList<Object>();//返回权限集合
    	List<Object> li = new ArrayList<Object>();
    	System.out.println("获取web项目的根路径"+req.getLocalPort());
    	System.out.println("获取请求的IP地址"+req.getServerPort());
    	System.out.println("网页所在目录的上一层目录"+req.getServerName());
    	
    	
//    	final String shenqingbiao_image = "imagedatafile/shenqingbiao_image";
//   	 	final String shenfenzheng_image = "imagedatafile/shenfenzheng_image";
//   	 final String fangchanzheng_image = "imagedatafile/fangchanzheng_image";
//   	final	String pishi_image = "imagedatafile/pishi_image";
//   	final String qita_image = "imagedatafile/qita_image";
//   	final String pingzheng_image ="imagedatafile/pingzheng_image";
//   	String pic_addr = "d://upload//a.jpg";
//   	String response = pic_addr.substring(pic_addr.lastIndexOf("/")+1);
//   	System.out.println(ftpRemotePath);
   	String targetFileName = "";
    	for(final MultipartFile f:tmpfile){
//    	        FTPClient ftp = new FTPClient();
    	        JSch jsch = new JSch();
    	       
    	        try {
    	        	
    	        	 if(ftpPort<= 0){
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
//    	            ftp.connect(ftpIp);// 连接FTP服务器
    	            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
//    	         boolean ftpfalse=   ftp.login(ftpUser, ftpPwd);// 登录
    	         if(session !=null){
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
         	 			  
             	 			if(requestsize > fileSize){
             	 				return new Json(false,"fail","",CaptchaConstants.UPDATESIZE);
             	 			}else{
             	 				if(upload_type.equals("申请表") && requestsize >0 ){
//                 	 				if (!ftp.changeWorkingDirectory("shenqingbiao_image")) {
//                     	                ftp.makeDirectory("shenqingbiao_image");//创建目录
//                     	                ftp.changeWorkingDirectory("shenqingbiao_image");//跳转目录(可根据项目需求选择创建目录的多少)
//                     	            }
                             		System.out.println("申请表");
                             		imagedata.setNote(note);//备注
                                 	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                                 	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//                                 	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/shenqingbiao_image/"+targetFileName.trim());
                                 	imagedata.setFilepath("http://120.79.252.173:8080/ftp/shenqingbiao_image/"+targetFileName.trim());
//                                 	imagedata.setFilepath("");
//                                 	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
                                 	imagedata.setUploads(note);//上传者
                                 	imagedata.setUploadtype(upload_type);//上传类型
                                 	imagedata.setCity(city);
                                 	imagedata.setSubnode("");
                                 	imagedata.setParentnode(usernameid);//用户名ID
                                 	imagedata.setSpare(username);//用户名
                                 	imagedata.setSparetwo(roleName);//角色名
                                 	String createData = DateUtils.getInDateTime((new Date()));//日期
                                 	imagedata.setCreateData(createData);
                                 	imagedata.setUploadFtpRoute(uid);
                                 	imagedataservice.imageDataAdd(imagedata);//添加一条记录
                                 	sftp.cd(CaptchaConstants.SHENQINGBIAO_IMAGE);
                                 	
                             	}
                 	 			if(upload_type.equals("身份证明")){
                             		System.out.println("身份证明");
//                             		if (!ftp.changeWorkingDirectory("shenfenzheng_image")) {
//                     	                ftp.makeDirectory("shenfenzheng_image");//创建目录
//                     	                ftp.changeWorkingDirectory("shenfenzheng_image");//跳转目录(可根据项目需求选择创建目录的多少)
//                     	            }
//                             		 ZipUtil1.unZip(shenfenzheng_image+"/"+targetFileName);
                             		imagedata.setNote(note);//备注
                                 	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                                 	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//                                 	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/shenfenzheng_image/"+targetFileName.trim());
                                 	imagedata.setFilepath("http://120.79.252.173:8080/ftp/shenfenzheng_image/"+targetFileName.trim());
//                                 	imagedata.setFilepath("");
//                                 	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
                                 	imagedata.setUploads(note);//上传者
                                 	imagedata.setUploadtype(upload_type);//上传类型
                                 	imagedata.setCity(city);
                                 	imagedata.setSubnode("");
                                 	imagedata.setParentnode(usernameid);//用户名ID
                                 	imagedata.setSpare(username);//用户名
                                 	imagedata.setSparetwo(roleName);//角色名
                                 	String createData = DateUtils.getInDateTime((new Date()));//日期
                                 	imagedata.setCreateData(createData);
                                 	imagedata.setUploadFtpRoute(uid);
                                 	imagedataservice.imageDataAdd(imagedata);
                                 	sftp.cd(CaptchaConstants.SHENFENZHENG_IMAGE);
                             	}
                             	if(upload_type.equals("房产证明")&& requestsize >0){
                             		System.out.println("房产证明");
//                             		if (!ftp.changeWorkingDirectory("fangchanzheng_image")) {
//                     	                ftp.makeDirectory("fangchanzheng_image");//创建目录
//                     	                ftp.changeWorkingDirectory("fangchanzheng_image");//跳转目录(可根据项目需求选择创建目录的多少)
//                     	            }
                             		imagedata.setNote(note);//备注
                                 	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                                 	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//                                 	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/fangchanzheng_image/"+targetFileName.trim());
                                 	imagedata.setFilepath("http://120.79.252.173:8080/ftp/fangchanzheng_image/"+targetFileName.trim());
//                                 	imagedata.setFilepath("");
//                                 	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
                                 	imagedata.setUploads(note);//上传者
                                 	imagedata.setUploadtype(upload_type);//上传类型
                                 	imagedata.setCity(city);
                                 	imagedata.setSubnode("");
                                 	imagedata.setParentnode(usernameid);//用户名ID
                                 	imagedata.setSpare(username);//用户名
                                 	imagedata.setSparetwo(roleName);//角色名
                                 	String createData = DateUtils.getInDateTime((new Date()));//日期
                                 	imagedata.setCreateData(createData);
                                 	imagedata.setUploadFtpRoute(uid);
                                 	imagedataservice.imageDataAdd(imagedata);
                                 	sftp.cd(CaptchaConstants.FANGCHANZHENG_IMAGE);
//                                 	 ZipUtil1.unZip(fangchanzheng_image+"/"+targetFileName);
                             	}
                             	if(upload_type.equals("批示")&& requestsize >0){
                             		System.out.println("批示");
//                             		if (!ftp.changeWorkingDirectory("pishi_image")) {
//                     	                ftp.makeDirectory("pishi_image");//创建目录
//                     	                ftp.changeWorkingDirectory("pishi_image");//跳转目录(可根据项目需求选择创建目录的多少)
//                     	            }
                             		imagedata.setNote(note);//备注
                                 	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                                 	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//                                 	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/pishi_image/"+targetFileName.trim());
                                 	imagedata.setFilepath("http://120.79.252.173:8080/ftp/pishi_image/"+targetFileName.trim());
//                                 	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+tdf+"/"+"pishi_image/"+str.trim());
//                                 	imagedata.setFilepath("");
//                                 	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
                                 	imagedata.setUploads(note);//上传者
                                 	imagedata.setUploadtype(upload_type);//上传类型
                                 	imagedata.setCity(city);
                                 	imagedata.setSubnode("");
                                 	imagedata.setParentnode(usernameid);//用户名ID
                                 	imagedata.setSpare(username);//用户名
                                 	imagedata.setSparetwo(roleName);//角色名
                                 	String createData = DateUtils.getInDateTime((new Date()));//日期
                                 	imagedata.setCreateData(createData);
                                 	imagedata.setUploadFtpRoute(uid);
                                 	imagedataservice.imageDataAdd(imagedata);
                                 	sftp.cd(CaptchaConstants.PISHI_IMAGE);
//                                 	 ZipUtil1.unZip(pishi_image+"/"+targetFileName);
                             	}
                             	if(upload_type.equals("其他类")&& requestsize >0){
                             		System.out.println("其他类");
//                             		Vector content2 = sftp.ls("qita_image");
//                             			if(content2 == null){
//                             				sftp.mkdir("/usr/local/FTP/qita_image");
//                             			}else{
                             				imagedata.setNote(note);//备注
                                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//                                         	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/qita_image/"+targetFileName.trim());
                                         	imagedata.setFilepath("http://120.79.252.173:8080/ftp/qita_image/"+targetFileName.trim());
//                                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
                                         	imagedata.setUploads(note);//上传者
                                         	imagedata.setUploadtype(upload_type);//上传类型
                                         	imagedata.setCity(city);
                                         	imagedata.setSubnode("");
                                         	imagedata.setParentnode(usernameid);//用户名ID
                                         	imagedata.setSpare(username);//用户名
                                         	imagedata.setSparetwo(roleName);//角色名
                                         	imagedata.setUploadFtpRoute(uid);
                                         	String createData = DateUtils.getInDateTime((new Date()));//日期
                                         	imagedata.setCreateData(createData);
                                         	imagedataservice.imageDataAdd(imagedata);
                                         	sftp.cd(CaptchaConstants.QITA_IMAGE);
//                             			}
                             		
//                                 	 ZipUtil1.unZip(qita_image+"/"+targetFileName);
                             	}
                             	if(upload_type.equals("凭证类")&& requestsize >0){
                             		System.out.println("凭证类");//mkdir() 
                             	// 判断子目录文件夹是否存在，不存在即创建  
//                             		SftpATTRS attrs = null;
                             		 
//                             		Vector content = sftp.ls("pingzheng_image"); 
//                             		for(int i=0;i<content.size();i++){
//                        				System.out.println(content.get(i));
//                        			}
                             		try {
//                             			SftpATTRS attrs = sftp.lstat("/usr/local/FTP/pingzheng_image"); 
//                             			if(attrs == null) {   
//                                  	       sftp.mkdir("/usr/local/FTP/pingzheng_image");   
//                                  	      System.err.println("创建子目录：" + sftp);  
//                                  	    }else{
                                  	    	imagedata.setNote(note);//备注
                                          	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                                          	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
//                                          	imagedata.setFilepath("http://"+req.getServerName()+":"+req.getServerPort()+"/ftp/pingzheng_image/"+targetFileName.trim());
//                                          	imagedata.setFilepath("http://120.79.252.173:"+req.getServerPort()+"/usr/local/FTP/pingzheng_image/"+targetFileName.trim());
                                          	imagedata.setFilepath("http://120.79.252.173:8080/ftp/pingzheng_image/"+targetFileName.trim());
//                                          	http://120.79.252.173:8080/ftp/pingzheng_image/1805051655477836_aa_123.jpg
                                          	readerZipFile(tmpFileName.trim());
//                                          	imagedata.setFilepath("");
                                          	imagedata.setUploads(note);//上传者
                                          	imagedata.setUploadtype(upload_type);//上传类型
                                          	imagedata.setCity(city);
                                          	imagedata.setSubnode("");
                                          	imagedata.setParentnode(usernameid);//用户名ID
                                          	imagedata.setSpare(username);//用户名
                                          	imagedata.setSparetwo(roleName);//角色名
                                          	String createData = DateUtils.getInDateTime((new Date()));//日期
                                          	imagedata.setCreateData(createData);
                                          	imagedata.setUploadFtpRoute(uid);
                                          	imagedataservice.imageDataAdd(imagedata);
                                          	sftp.cd(CaptchaConstants.PINGZHENG_IMAGE);
//                                  	    }
        							} catch (Exception e) {
        							}
                             	    
//                     	            }
                             			
                             	}
             	 			}
         	 			 
                     	
         	 			}
         	 			
         	 		}
     	            
         	 		if(req !=null && ServletFileUpload.isMultipartContent(req)){
         	 			//判断是否文件上传
         	 			ServletRequestContext ctx = new ServletRequestContext(req);
         	 			//获取上传文件尺寸大小
//         	 			System.out.println(maxSize);
         	 			long requestsize = ctx.contentLength();
         	 			System.out.println(requestsize);
//         	 			 ftp.setFileType(FTP.BINARY_FILE_TYPE);
//          	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
          	            InputStream input = f.getInputStream();
          	            System.out.println("safasdfasdf: "+input);
//          	            readStream(input);
//          	          ObjectSeq seq = imagedataservice.listSeq();
//                    	int idseq= seq.getStart_value();
//                    	String seqid = String.valueOf(idseq-1);
//                    	System.out.println(seqid);
                    	
                    	
          	          if(tmpFileName !="" || tmpFileName !=null && dot>=0){
          	        	 System.err.println("上传成功");
//          	        	li.add(isResult);
//          	        	pageBean.setLists(li);
          	        	
                    	sftp.put(f.getInputStream(),new String(targetFileName.getBytes("GBK")));  //上传文件  
          	        	 
//          	        	String filepath =targetFileName;//原文件名
                    	String parentnode =city;
                    	String uploadtype = upload_type;
                    	String Parentnode = usernameid;
                    	String spare = username;
                    	String sparetwo = roleName;
                    	String createDate = DateUtils.getInDateTime((new Date()));
						ImageDataUpdate imagedata2 = new ImageDataUpdate(city,uploadtype,Parentnode,spare,sparetwo,createDate,0,targetFileName.trim());
                    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload2(imagedata2);
                    	if(listimg.size()> 0){
                    		//return new Json(true,"success",listimg);
                    		  return new Json(true,"success",listimg,"上传成功");
                    	}else{
                    		return new Json(false,"fail",listimg,CaptchaConstants.FILE_TYPE);
                    	}
          	          }else{
          	        	  System.err.println("上传失败！"); 
          	        	  return new Json(false,"fail",pageBean,CaptchaConstants.IMAGE_TYPE);
          	          }
         	 		}
//	     	         getPhont(origFileName);
	 	           
    	         }else{
//    	        	 System.err.println("FTP连接失败");
    	        	 return new Json(false,"fail","","FTP连接失败");
    	         }
    	            
//    	            
    	        } catch (Exception e) {
//    	        	 res.encodeRedirectURL("file");
//    	        	 li.add("连接异常");
//    	        	 pageBean.setLists(li);
//    	            e.getStackTrace();
//    	            return "redirect:/welcome.jsp";
//    	            throw new BusinessException(10, "该异常代表用户信息不完整");
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
//    			 input.close();
 	        	  ftp.logout();
    	    }
//		return "fileupdate/sccg";
//    	return new Json(); 
//		return null;
		return null;
}
	//获取上传文件大小
    public void setMaxSize(long maxSize){
		this.maxSize =maxSize;
	}
    
    /**
	 * 获取贷款数据
	 * @return
	 */
	@RequestMapping(value="/filetype500")
	public String filetype500(){
		
		return "error/filetype/500";
	}
    private byte[] getPhont(String tmpFileName) throws IOException {
    	reply = ftp.getReplyCode();
    	if (!FTPReply.isPositiveCompletion(reply)) {  
            ftp.disconnect();  
            System.err.println("FTP站点连接失败！");  
            return null;  
        } else {  
            InputStream is = ftp.retrieveFileStream(tmpFileName);  
            byte[] imgByte = this.readStream(is);  
            return imgByte;  
  
        }  
	}
	public byte[] readStream(InputStream inStream) {  
        ByteArrayOutputStream bops = new ByteArrayOutputStream();  
        int data = -1;  
        try {  
            while ((data = inStream.read()) != -1) {  
                bops.write(data);  
            }  
            return bops.toByteArray();  
        } catch (Exception e) {  
            return null;  
        }  
    }
	
	 /**
     * 影像删除图片
     * @param req
     * @return
	 * @throws IOException 
	 * @throws SftpException 
     */
    @RequestMapping(value = "/imagedatadel",method=RequestMethod.POST,produces="application/json;charset=utf-8")
    @ResponseBody
	  	public Json imagedatadel(HttpServletRequest req,HttpServletResponse res) throws IOException, SftpException{
    	String data = req.getParameter("data");
    	Session session = null;
		Channel channel = null;
		JSch jsch = new JSch();
		try {
			if(ftpPort<= 0){
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
			channel = (Channel) session.openChannel(sftpp);
			channel.connect(1000);
			ChannelSftp sftp = (ChannelSftp) channel;
			JSONObject obj = new JSONObject().fromObject(data);
	    	String uploadFtpRoute = obj.getString("id");
	    	String image = obj.getString("file");
	    	String type = obj.getString("type");
	    	String spare = obj.getString("username");
	    	 filenameimage = image;//图片
	    	String ss = URLDecoder.decode(filenameimage, "UTF-8");
	    	 int imageid = Integer.valueOf(uploadFtpRoute);
	    	System.out.println(ss);
	    	
	    	 String imagefile = ss.substring(ss.lastIndexOf("/")+1);
	    	 ImageDataUpdate imagedata = new ImageDataUpdate();
	    	 imagedata.setUploadFtpRoute(uploadFtpRoute);
	    	 imagedata.setFilepath(imagefile);
	    	 String upload = uploadFtpRoute;
	    	 ImageDataUpdate  isResultselectDel= imagedataservice.selectByDelId(spare,upload,ss);
	    	 if(isResultselectDel !=null){
	    		 if(type.equals("申请表")){
	     	    	boolean isResult = imagedataservice.imagedatedel(imagedata);
	     	    	if(isResult == true){
	     	    		sftp.cd(CaptchaConstants.SHENQINGBIAO_IMAGE);
	          	 	 	sftp.rm(imagefile);
	         			return new Json(true,"success",isResult);
	         		}else{
	         			return new Json(false,"false",isResult);
	         		}
	      	 	}else if(type.equals("身份证明")){
	      	 		boolean isResult = imagedataservice.imagedatedel(imagedata);
	      	 		if(isResult == true){
	 	     	 		sftp.cd(CaptchaConstants.SHENFENZHENG_IMAGE);
	 	         	 	sftp.rm(imagefile);
	          	 		return new Json(true,"success",isResult);
	         		}else{
	         			return new Json(false,"false",isResult);
	         		}
	      	 	}else if(type.equals("房产证明")){
	      	 		boolean isResult = imagedataservice.imagedatedel(imagedata);
	      	 		if(isResult == true){
	      	 			sftp.cd(CaptchaConstants.FANGCHANZHENG_IMAGE);
	              	 	sftp.rm(imagefile);
	      	 			return new Json(true,"success",isResult);
	      	 		}else{
	      	 			return new Json(false,"false",isResult);
	      	 		}
	      	 	}else if(type.equals("批示")){
	      	 		boolean isResult = imagedataservice.imagedatedel(imagedata);
	      	 		if(isResult == true){
	      	 			sftp.cd(CaptchaConstants.PISHI_IMAGE);
	              	 	sftp.rm(imagefile);
	      	 			return new Json(true,"success",isResult);
	      	 		}else{
	      	 			return new Json(false,"false",isResult);
	      	 		}
	      	 		
	      	 	}else if(type.equals("其他类")){
	      	 		boolean isResult = imagedataservice.imagedatedel(imagedata);
	      	 		if(isResult == true){
	      	 			sftp.cd(CaptchaConstants.QITA_IMAGE);
	              	 	sftp.rm(imagefile);
	      	 			return new Json(true,"success",isResult);
	      	 		}else{
	      	 			return new Json(false,"false",isResult);
	      	 		}
	      	 	}
	    	 }else{
	    		 return new Json(false,"fail","无权限删除",""); 
	    	 }
	    	 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
    	
    	 	
//    		int imageid = Integer.valueOf(uploadFtpRoute);
//    		boolean isResult = imagedataservice.imagedatedel(imageid);
//    		filename2 = "/usr/local/FTP";//FTP路径
//    		
//    		try {
//    			
////    			sftp.cd("/usr/local/FTP/shenqingbiao_image");
////    			  List<String> list=sftp.ls("/usr/local/FTP/shenqingbiao_image");
////    			  for (int i = 0; i < list.size(); i++) {
////    			        sftp.rm(list.get(i));
////    			    }
//    			
//    			sftp.rm(filenameimage);
//				File f = new File(filename2);
//	    		getDirectory(f);
//			} catch (SftpException e) {
//				e.printStackTrace();
//			}
//    		File f = new File(filename2);
//    			boolean b = f.delete();
//    			if(b == true){
//    				System.err.println("成功");
//    			}else{
//    				System.err.println("失败");
//    			}
//    		if(isResult ==true){
//    			return new Json(true,"success",isResult);
//    		}else
//    			return new Json(false,"false",isResult);
    		
	  	}
    
    
  //递归遍历  
    private static void getDirectory(File file) throws SftpException {  
    	String filename =filenameimage;
    	System.out.println(filename);
    File flist[] = file.listFiles();  
    if (flist == null || flist.length == 0) {  
    }  
    for (File f : flist) {  
       if (f.isDirectory()) {  
           //这里将列出所有的文件夹  
           System.out.println("Dir==>" + f.getAbsolutePath());   
           getDirectory(f);  
       } else {  
          //这里将列出所有的文件  
           System.out.println("file==>" + f.getAbsolutePath());  
           
           if(f.getName().endsWith(filename)){
        	   sftp.rm(filename);
    			if(f.delete()){
//        	   if(sftp.rm("filename") == true){
    				System.err.println("图片文件已经删除: "+f.getName());
    			}else{
    				System.err.println("图片文件删除失败");
    			}
    		}
           
       }  
    }  
    }
    //删除ZIP
    public void delZipFile(String filename2)
    {
    	File f = new File(filename2);
    	if(f.isDirectory()){
    		File[] files = f.listRoots();
    		for(File ff :files){
    			if(ff.getName().endsWith("1804152112254098_sh_456.jpg")){
    				if(ff.delete()){
    					System.out.println("zip文件已经删除");
    				}else{
    					System.out.println("zip文件删除失败");
    				}
    			}
    		}
    	}
    }
    
    private boolean filetype(String str) {
    	if(str.contains("jpg")|| str.contains("png") || str.contains("jpeg")
    			 || str.contains("bmp")
    			 || str.contains("png")){
    		return true;
    	}else
		return false;
	}

	private Long getDirSize() {
    	Long t=null;
    	for(Long d : filesize){
    			t=+d;
    	}
		return t++;
	}

	private static List readerZipFile(String fileName) {
    	List<String> listfile = new ArrayList<String>();
    	filesize = new ArrayList<Long>();
    	Date d = new Date();
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String strformat = f.format(d);
    	InputStream in = null;
    	ZipInputStream zin = null ;
    	try {
    	 zf = new ZipFile(fileName);
    	 in = new BufferedInputStream(new FileInputStream(fileName));
    	 zin = new ZipInputStream(in);
    	ZipEntry ze;
    	while ((ze = zin.getNextEntry()) != null) {
    	String a = ze.getName();
    	String b = a.replace("/", " ");
    	String c = b.substring(a.lastIndexOf("/"), b.length());
//    	String s = c.replace("[ , ", "").replace("]", "");
    	listfile.add(c);
    	
    	if(listfile == null || listfile.size()==1){
    	}else{
    		Date dd = new Date();
    		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String strformat2 = ff.format(dd);
    		long zhdate2 = ff.parse(strformat2).getTime()- f.parse(strformat).getTime();
    		System.out.println("file" + c + ":" + ze.getSize() + " byts");
    		filesize.add(ze.getSize());
//    		for(Long l: filesize){
    			filesizeupdate();
//    		}
    		System.out.println("服务器扫描时间相差毫秒数:");
    		System.out.println(zhdate2+"m");
    	}
    	
    	}
//    	in.close();
//    	zin.close();
    	} catch (Exception e) {
    	e.getMessage();
    	}finally {
    		try {
    			if (in != null) {  
    				in.close();
    			}
    			 if(zin !=null){
    				 zin.close();
    			 }
			} catch (Exception e) {
				System.out.println("删除失败");
			}
    	}
		return listfile;
	}
    
    private static List<Long> filesizeupdate() {
    	List<Long>ll = new ArrayList<Long>();
    	for(Long filesize :filesize){
    		ll.add(filesize);
    		System.out.println("大小: "+filesize+" byts");
    	}
		return ll;
	}

    /**
     * 查所有的记录数
     * @return
     */
	@RequestMapping(value="/selectimage",method=RequestMethod.GET)
    @ResponseBody
    public String abc(){
    	List<ImageDataUpdate> imagelist = imagedataservice.list();
    		if(imagelist !=null){
//    			return new Json(true,"success",imagelist);
    			return JSON.toJSONString(imagelist);
    		}else
    			return JSON.toJSONString(imagelist);
//    			return new Json(true,"fail",imagelist);
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
    
  /**
   * 点击查看返回结果
   * @param req 
   * @param res
   * @return
 * @throws UnsupportedEncodingException 
   */
    @RequestMapping(value="/selectupdateinformation",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    @ResponseBody
    public Json selectNoteUpdateinformation(HttpServletRequest req , HttpServletResponse res) throws UnsupportedEncodingException{
    	String data = req.getParameter("data");
    	JSONObject jsonobj = new JSONObject().fromObject(data);
    	String uploadtype = jsonobj.getString("uploadtype");//上传类型
    	String city = jsonobj.getString("city");
//    	String sparetwo = jsonobj.getString("rolename");
    	String id = jsonobj.getString("id");
    	String filepathsub = jsonobj.getString("filepath");
    	String filepathsubstring = filepathsub.substring(filepathsub.lastIndexOf("/")+1);
    String filepath = URLDecoder.decode(filepathsubstring,"UTF-8");
//    	String id = req.getParameter("id");
//    	String uploadtype = req.getParameter("uploadtype");
//    	String city = req.getParameter("city");
//    	String filepath = req.getParameter("filepath");
    	int uid = Integer.valueOf(id);
    	ImageDataUpdate image = new ImageDataUpdate();
    	image.setUploadtype(uploadtype);//上传类型
    	image.setCity(city);
    	image.setId(uid);
    	image.setFilepath(filepath);
    	ImageDataUpdate imageupdate = imagedataservice.imagedataUpdateNote(image);
    	if(imageupdate !=null){
    		return new Json(true,"success",imageupdate,"");
    	}else
    		return new Json(false,"fail",imageupdate,"");
    	
    	
    }
    /**
     * 根据 上传类型 原文件名  上传者姓名查所上传的
     * @param req
     * @param res
     * @return 对象
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/selectfiletype",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    @ResponseBody
    public Json selectUploadsUpdateType(HttpServletRequest req , HttpServletResponse res) throws UnsupportedEncodingException{
    	res.setContentType("text/html; charset=utf-8");
    	String data = req.getParameter("data");
    	JSONObject jsonobj = new JSONObject().fromObject(data);
    	String uploadtype = jsonobj.getString("uploadtype");//原文件名
    	String city = jsonobj.getString("city");
    	String sparetwo = jsonobj.getString("rolename");//角色名
    	String spare = jsonobj.getString("username");//用户名
    	String Parentnode = jsonobj.getString("usernameid");//用户ID
    	String uploadFtpRoute = jsonobj.getString("listid");//用户ID
    	String[] splist = uploadtype.split(",");
    	String createDate = DateUtils.getInDateTime((new Date()));
    	Map<Object,Object> map = new HashMap<Object,Object>();
    		ResultList<ImageDataUpdate> result = new ResultList<ImageDataUpdate>();
    	Map<String,List<ImageDataUpdate>> listmap = new HashMap<String,List<ImageDataUpdate>>();
    	
    	for(String s :splist){
    		String strplist = s.replace("[", " ").replace("]", " ").replace("\"", " ").trim();
//    		System.out.println(strplist);
    		if(strplist.equals("申请表")){
    			
    			if(spare.contains(admin)){
    				map.put("申请表", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("申请表", listimg);
    			}else{
    				map.put("申请表", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("申请表", listimg);
    			}
    			
    		}else if(strplist.equals("身份证明")){
    			
    			if(spare.contains(admin)){
    				map.put("身份证明", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("身份证明", listimg);
    			}else{
    				map.put("身份证明", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("身份证明", listimg);
    			}
    			
    		}else if(strplist.equals("批示")){
    			
    			if(spare.contains(admin)){
    				map.put("批示", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        			listmap.put("批示", listimg);
    			}else{
    				map.put("批示", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        			listmap.put("批示", listimg);
    			}
    			
    			
    			
    		}else if(strplist.equals("转账凭证")){
    			if(spare.contains(admin)){
    				map.put("转账凭证", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("转账凭证", listimg);
    			}else{
    				map.put("转账凭证", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("转账凭证", listimg);
    			}
    			
    			
    			
    		}else if(strplist.equals("结算凭证")){
    			
    			if(spare.contains(admin)){
    				map.put("结算凭证", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("结算凭证", listimg);
    			}else{
    				map.put("结算凭证", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("结算凭证", listimg);
    			}
    			
    			
    		}else if(strplist.equals("取证凭证")){
    			
    			
    			if(spare.contains(admin)){
    				map.put("取证凭证", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("取证凭证", listimg);
    			}else{
    				map.put("取证凭证", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("取证凭证", listimg);
    			}
    			
    			
    		}else if(strplist.equals("解押凭证")){
    			
    			if(spare.contains(admin)){
    				map.put("解押凭证", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("解押凭证", listimg);
    			}else{
    				map.put("解押凭证", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("解押凭证", listimg);
    			}
    			
    			
    		}else if(strplist.equals("进押凭证")){
    			
    			
    			if(spare.contains(admin)){
    				map.put("进押凭证", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("进押凭证", listimg);
    			}else{
    				map.put("进押凭证", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("进押凭证", listimg);
    			}
    			
    			
    		}else if(strplist.equals("回款确认")){
    			
    			if(spare.contains(admin)){
    				map.put("回款确认", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("回款确认", listimg);
    			}else{
    				map.put("回款确认", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("回款确认", listimg);
    			}
    			
    			
    		}else if(strplist.equals("其他类")){
    			if(spare.contains(admin)){
    				map.put("其他类", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("其他类", listimg);
    			}else{
    				map.put("其他类", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("其他类", listimg);
    			}
    			
    			
    			
    		}else if(strplist.equals("房产证明")){
    			
    			if(spare.contains(admin)){
    				map.put("房产证明", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectTouploadAdmin(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("房产证明", listimg);
    			}else{
    				map.put("房产证明", strplist);
        			System.out.println(strplist);
        			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("房产证明", listimg);
    			}
    			
    			
    		}else if(strplist.equals("贷款信息查看")){
    			
    			map.put("贷款信息查看", strplist);
    			System.out.println(strplist);
    			if(spare.contains("admin")){
    				ImageDataUpdate imagedata = new ImageDataUpdate("",strplist,"","","","");
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("贷款信息查看", listimg);
    			}else{
    				ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,"","","");
        		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
        		    result.setLists(listimg);
        		    listmap.put("贷款信息查看", listimg);
    			}
    		}else if(strplist.equals("凭证类")){
    			map.put("凭证类", strplist);
    			System.out.println(strplist);
    			ImageDataUpdate imagedata = new ImageDataUpdate(city,strplist,Parentnode,spare,sparetwo,createDate,uploadFtpRoute);
    		    List<ImageDataUpdate> listimg= imagedataservice.financevoucherSelectToupload(imagedata);
    		    result.setLists(listimg);
    		    listmap.put("凭证类", listimg);
    		}

    		
    		
    	}
		 return new Json(true,"success",listmap,"贷款凭证");
    }
    
    @RequestMapping(value = "/bb",method=RequestMethod.GET)
    public String bb(){
    	return "sccg";
    }
    
    //for循环方式迭代器,使用不多
    /*for(Iterator<String> it2 = coll.iterator();it2.hasNext();){
               String s = it2.next();//取出元素,移动指针到下一位
               System.out.println(s);
            }*/
    
    public boolean upload(String basePath,String directory, String sftpFileName, InputStream input) throws SftpException {    
        try {     
            sftp.cd(basePath);  
            sftp.cd(directory);    
        } catch (SftpException e) {   
            //目录不存在，则创建文件夹  
            String [] dirs=directory.split("/");  
            String tempPath=basePath;  
            for(String dir:dirs){  
                if(null== dir || "".equals(dir)) continue;  
                tempPath+="/"+dir;  
                try{   
                    sftp.cd(tempPath);  
                }catch(SftpException ex){  
                    sftp.mkdir(tempPath);  
                    sftp.cd(tempPath);  
                }  
            }  
        }    
        sftp.put(input, sftpFileName);  //上传文件  
		return true;
    } 
}