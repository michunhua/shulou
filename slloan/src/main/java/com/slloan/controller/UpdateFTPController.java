package com.slloan.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
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
import com.slloan.entity.ImageDataUpdate;

import com.slloan.entity.ResultList;
import com.slloan.service.inter.ImagedataService;

import com.slloan.util.DateUtils;
import com.slloan.util.Json;
//import com.slloan.util.ZipUtil1;

import net.sf.json.JSONObject;

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
	public String ftpPort;//端口
	
	@Value("${ftpUser}")
	public String ftpUser;//用户名
	
	@Value("${ftpPwd}")
	public String ftpPwd;//密码
	
	@Value("${ftpRemotePath}")
	public String ftpRemotePath;
	
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
    @RequestMapping(value = "/imagedatafileupload",method=RequestMethod.POST)
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
	  	public Json imagedatafile(@RequestParam(value="file",required=false)MultipartFile[] tmpfile,
	  			final HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
    	final String note = req.getParameter("note");//备注
    	final String upload_type = req.getParameter("upload_type");//上传类型
    	final	String filepath = req.getParameter("filepath");
    	final	String city = req.getParameter("city");
    	ResultList<Object> pageBean = new ResultList<Object>();//返回权限集合
    	List<Object> li = new ArrayList<Object>();
//    	final String shenqingbiao_image = "imagedatafile/shenqingbiao_image";
//   	 	final String shenfenzheng_image = "imagedatafile/shenfenzheng_image";
//   	 final String fangchanzheng_image = "imagedatafile/fangchanzheng_image";
//   	final	String pishi_image = "imagedatafile/pishi_image";
//   	final String qita_image = "imagedatafile/qita_image";
//   	final String pingzheng_image ="imagedatafile/pingzheng_image";
//   	String pic_addr = "d://upload//a.jpg";
//   	String response = pic_addr.substring(pic_addr.lastIndexOf("/")+1);
   	System.out.println(ftpRemotePath);
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
         	 			  
         	 			 if(upload_type.equals("申请表") && requestsize >0 ){
         	 				if (!ftp.changeWorkingDirectory("shenqingbiao_image")) {
             	                ftp.makeDirectory("shenqingbiao_image");//创建目录
             	                ftp.changeWorkingDirectory("shenqingbiao_image");//跳转目录(可根据项目需求选择创建目录的多少)
             	            }
                     		System.out.println("申请表");
                     		imagedata.setNote(note);//备注
                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
                         	imagedata.setFilepath("/ftp/shenqingbiao_image/"+targetFileName.trim());
//                         	imagedata.setFilepath("");
//                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
                         	imagedata.setUploads(note);//上传者
                         	imagedata.setUploadtype(upload_type);//上传类型
                         	imagedata.setParentnode(city);
                         	imagedata.setSubnode("");
                         	String createData = DateUtils.getInDateTime((new Date()));//日期
                         	imagedata.setCreateData(createData);
                         	imagedataservice.imageDataAdd(imagedata);//添加一条记录
                         	
                     	}
         	 			if(upload_type.equals("身份证明")&& requestsize >0 ){
                     		System.out.println("身份证明");
                     		if (!ftp.changeWorkingDirectory("shenfenzheng_image")) {
             	                ftp.makeDirectory("shenfenzheng_image");//创建目录
             	                ftp.changeWorkingDirectory("shenfenzheng_image");//跳转目录(可根据项目需求选择创建目录的多少)
             	            }
//                     		 ZipUtil1.unZip(shenfenzheng_image+"/"+targetFileName);
                     		imagedata.setNote(note);//备注
                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
                         	imagedata.setFilepath("/ftp/shenfenzheng_image/"+targetFileName.trim());
//                         	imagedata.setFilepath("");
//                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
                         	imagedata.setUploads(note);//上传者
                         	imagedata.setUploadtype(upload_type);//上传类型
                         	imagedata.setParentnode(city);
                         	imagedata.setSubnode("");
                         	String createData = DateUtils.getInDateTime((new Date()));//日期
                         	imagedata.setCreateData(createData);
                         	imagedataservice.imageDataAdd(imagedata);
                     	}
                     	if(upload_type.equals("房产证明")&& requestsize >0){
                     		System.out.println("房产证明");
                     		if (!ftp.changeWorkingDirectory("fangchanzheng_image")) {
             	                ftp.makeDirectory("fangchanzheng_image");//创建目录
             	                ftp.changeWorkingDirectory("fangchanzheng_image");//跳转目录(可根据项目需求选择创建目录的多少)
             	            }
                     		imagedata.setNote(note);//备注
                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
                         	imagedata.setFilepath("/ftp/fangchanzheng_image/"+targetFileName.trim());
//                         	imagedata.setFilepath("");
//                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
                         	imagedata.setUploads(note);//上传者
                         	imagedata.setUploadtype(upload_type);//上传类型
                         	imagedata.setParentnode(city);
                         	imagedata.setSubnode("");
                         	String createData = DateUtils.getInDateTime((new Date()));//日期
                         	imagedata.setCreateData(createData);
                         	imagedataservice.imageDataAdd(imagedata);
//                         	 ZipUtil1.unZip(fangchanzheng_image+"/"+targetFileName);
                     	}
                     	if(upload_type.equals("批示")&& requestsize >0){
                     		System.out.println("批示");
                     		if (!ftp.changeWorkingDirectory("pishi_image")) {
             	                ftp.makeDirectory("pishi_image");//创建目录
             	                ftp.changeWorkingDirectory("pishi_image");//跳转目录(可根据项目需求选择创建目录的多少)
             	            }
                     		imagedata.setNote(note);//备注
                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
                         	imagedata.setFilepath("/ftp/pishi_image/"+targetFileName.trim());
//                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+tdf+"/"+"pishi_image/"+str.trim());
//                         	imagedata.setFilepath("");
//                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
                         	imagedata.setUploads(note);//上传者
                         	imagedata.setUploadtype(upload_type);//上传类型
                         	imagedata.setParentnode(city);
                         	imagedata.setSubnode("");
                         	String createData = DateUtils.getInDateTime((new Date()));//日期
                         	imagedata.setCreateData(createData);
                         	imagedataservice.imageDataAdd(imagedata);
//                         	 ZipUtil1.unZip(pishi_image+"/"+targetFileName);
                     	}
                     	if(upload_type.equals("其他类")&& requestsize >0){
                     		System.out.println("其他类");
                     		if (!ftp.changeWorkingDirectory("qita_image")) {
             	                ftp.makeDirectory("qita_image");//创建目录
             	                ftp.changeWorkingDirectory("qita_image");//跳转目录(可根据项目需求选择创建目录的多少)
             	            }
                     		imagedata.setNote(note);//备注
                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
                         	imagedata.setFilepath("/ftp/loan/qita_image/"+city+"/"+targetFileName.trim());
//                         	imagedata.setFilepath(targetDirectory+"/"+targetFileName);//上传路径
                         	imagedata.setUploads(note);//上传者
                         	imagedata.setUploadtype(upload_type);//上传类型
                         	imagedata.setParentnode(city);
                         	imagedata.setSubnode("");
                         	String createData = DateUtils.getInDateTime((new Date()));//日期
                         	imagedata.setCreateData(createData);
                         	imagedataservice.imageDataAdd(imagedata);
//                         	 ZipUtil1.unZip(qita_image+"/"+targetFileName);
                     	}
                     	if(upload_type.equals("凭证类")&& requestsize >0){
                     		System.out.println("凭证类");
                     		if (!ftp.changeWorkingDirectory("pingzheng_image")) {
             	                ftp.makeDirectory("pingzheng_image");//创建目录
             	                ftp.changeWorkingDirectory("pingzheng_image");//跳转目录(可根据项目需求选择创建目录的多少)
             	            }
                     		imagedata.setNote(note);//备注
                         	imagedata.setOriginalfilename(tmpFileName);//原文件名
//                         	imagedata.setFilepath(req.getContextPath()+"/imagedatafile/"+str);
                         	imagedata.setFilepath("/ftp/pingzheng_image/"+targetFileName.trim());
                         	readerZipFile(tmpFileName.trim());
//                         	imagedata.setFilepath("");
                         	imagedata.setUploads(note);//上传者
                         	imagedata.setUploadtype(upload_type);//上传类型
                         	imagedata.setParentnode(city);
                         	imagedata.setSubnode("");
                         	String createData = DateUtils.getInDateTime((new Date()));//日期
                         	imagedata.setCreateData(createData);
                         	imagedataservice.imageDataAdd(imagedata);
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
         	 			 ftp.setFileType(FTP.BINARY_FILE_TYPE);
//          	            String origFileName =  renameFileName(city,upload_type,tmpFileName);
          	            InputStream input = f.getInputStream();
          	            System.out.println(input);
//          	            readStream(input);
          	          boolean isResult =  ftp.storeFile(targetFileName, input);
          	          if(isResult ==  true){
          	        	 System.err.println("上传成功");
          	        	li.add(isResult);
          	        	pageBean.setLists(li);
          	        	  input.close();
          	        	  ftp.logout();
          	        	  return new Json(true,"success",pageBean,"上传成功");
          	        	  
          	          }else{
          	        	  System.err.println("上传失败！"); 
          	        	  return new Json(false,"fail",pageBean,"请选择上传文件类型jpg,png,jpge,bmp,png");
          	          }
         	 		}
//	     	         getPhont(origFileName);
	 	           
    	         }else{
    	        	 System.err.println("FTP连接失败");
    	         }
    	            
//    	            
    	        } catch (IOException e) {
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
     */
    @RequestMapping(value = "/imagedatadel",method=RequestMethod.POST,produces="application/json;charset=utf-8")
    @ResponseBody
	  	public Json imagedatadel(HttpServletRequest req) throws IOException{
    	String sid = req.getParameter("id");
//    	 filenameimage = req.getParameter("image");//图片
    		int imageid = Integer.valueOf(sid);
    		boolean isResult = imagedataservice.imagedatedel(imageid);
    		filename2 ="D:/FTP";
    		File f = new File(filename2);
    		getDirectory(f);
//    		File f = new File(filename2);
//    			boolean b = f.delete();
//    			if(b == true){
//    				System.err.println("成功");
//    			}else{
//    				System.err.println("失败");
//    			}
    		if(isResult ==true){
    			return new Json(true,"success",isResult);
    		}else
    			return new Json(false,"false",isResult);
    		
	  	}
    
    
  //递归遍历  
    private static void getDirectory(File file) {  
    	String filename =filenameimage;
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
           
           if(f.getName().endsWith("1804161622041314_bj_123.jpg")){
    			if(f.delete()){
    				System.out.println("图片文件已经删除: "+f.getName());
    			}else{
    				System.out.println("图片文件删除失败");
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
    	if(str.contains("jpg")|| str.contains("png") || str.contains("jpge")
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
     * 根据 上传类型 原文件名  上传者姓名查所上传的
     * @param req
     * @param res
     * @return 对象
     */
    @RequestMapping(value="/selectfiletype",method=RequestMethod.GET)
    @ResponseBody
    public Json selectUploadsUpdateType(HttpServletRequest req , HttpServletResponse res){
    	String data = req.getParameter("data");
    	JSONObject jsonobj = new JSONObject().fromObject(data);
    	String originalfilename = jsonobj.getString("file");//原文件名
    	String parentnode = jsonobj.getString("city");
    	String uploadtype = jsonobj.getString("upload_type");
    	String id = jsonobj.getString("id");
    	int zid = Integer.parseInt(id);
    	ImageDataUpdate imagedata = new ImageDataUpdate(zid,originalfilename,parentnode,uploadtype);
    List<ImageDataUpdate> listimg= imagedataservice.selectUploadsUpdateType(imagedata);
    	if(listimg.size()> 0){
    		return new Json(true,"success",listimg);
    	}else{
    		return new Json(false,"fail",listimg);
    	}
    		
    	
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
    
}
