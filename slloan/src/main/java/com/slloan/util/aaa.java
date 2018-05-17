package com.slloan.util;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Value;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
public class aaa {

	@Value("${ftpIp}")
	public static String ftpIp;//IP
	
	@Value("${ftpPort}")
	public static String ftpPort;//�˿�
	
	@Value("${ftpUser}")
	public  static String ftpUser;//�û���
	
	@Value("${ftpPwd}")
	public static String ftpPwd;//����
	
	public static void main(String[] args) throws Exception {
		Session session = null;
		Channel channel = null;

		
		JSch jsch = new JSch();
		
		
		if(22 <=0){
			//连接服务器，采用默认端口
			session = jsch.getSession("root", "120.79.252.173");
//			session = jsch.getSession(ftpUser, ftpIp);
		}else{
			//采用指定的端口连接服务器
			session = jsch.getSession("root", "120.79.252.173",22);
//			session = jsch.getSession(ftpUser, ftpIp,22);
		}

		//如果服务器连接不上，则抛出异常
		if (session == null) {
			throw new Exception("session is null");
		}
		
		//设置登陆主机的密码
		session.setPassword("Dt20180503");//设置密码   
		//设置第一次登陆的时候提示，可选值：(ask | yes | no)
		session.setConfig("StrictHostKeyChecking", "no");
		//设置登陆超时时间   
		session.connect(30000);
			
		try {
			//创建sftp通信通道
			channel = (Channel) session.openChannel("sftp");
			channel.connect(1000);
			ChannelSftp sftp = (ChannelSftp) channel;
			
			
			//进入服务器指定的文件夹
			sftp.cd("/usr/local/apache-tomcat-7.0.86/logs");
			
			//列出服务器指定的文件列表
			Vector v = sftp.ls("*.log");
			for(int i=0;i<v.size();i++){
				System.out.println(v.get(i));
			}
			
			//以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
			OutputStream outstream = sftp.put("error.log");
			InputStream instream = new FileInputStream(new File("D:/logFile.txt"));
			
			byte b[] = new byte[1024];
			int n;
		    while ((n = instream.read(b)) != -1) {
		    	outstream.write(b, 0, n);
		    }
		    
		    outstream.flush();
		    outstream.close();
		    instream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.disconnect();
			channel.disconnect();
		}				
				
				
				
//		int count = 0;
//		for (int i = 1; i>0; i++) {
//			if(i>0)
//			{
//				System.out.println(count++);
//				
//			}
//			break;
//			
//		}
//		
//	}
//	private static  String caseNo(Long id) {
//        String strId = id.toString();
//        int idLenth = 9 - strId.length();
//        StringBuilder builder = new StringBuilder();
////        Calendar cal = Calendar.getInstance();
//        builder.append("(").append(DateUtils.getToDateTime((new Date()))).append(")");
//        builder.append("赎楼第");
//        for (int i = 0; i < idLenth; i++) {
//            builder.append("0");
//        }
//        builder.append(id);
//        builder.append("号");
//        System.out.println(builder.toString());
//        return builder.toString();

		  SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		  String createDate = DateUtils.getInDateTime((new Date()));//日期
		  Date date1 =f.parse(createDate);
		  System.out.println(date1);
		  Calendar calen = Calendar.getInstance();
		  calen.setTime(date1);
		  calen.add(Calendar.DAY_OF_YEAR,5);
		  Date c=calen.getTime();
		  System.out.println(c);
		  System.out.println(f.format(c));
//		 }
    }

}
