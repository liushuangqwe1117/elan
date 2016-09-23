package com.redrock.elan.common.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类说明：
 *  sftp文件读取工具类
 * 
 * <p>
 * 详细描述：
 *  sftp连接建立、文件读取、目录遍历
 * 
 * @author  
 *   
 * CreateDate: 2014-11-3
 */
public class FtpUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	/**ftp文件上传时候的文件名称*/
	public static final String UPLOADING=".uploading";
 
	
	/**
	 * Description: 从FTP服务器下载文件
	 * @param url FTP服务器hostname
	 * @param port FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param remotePath FTP服务器上的相对路径
	 * @param fileName 要下载的文件名
	 * @param localPath 下载后保存到本地的路径
	 * @return
	 */
	public static boolean downFile(String url, int port,String username, String password, String remotePath,String fileName,String localPath) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);//登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for(FTPFile ff:fs){
				if(ff.getName().equals(fileName)){
					File localFileDir=new File(localPath);					
					if(!localFileDir.exists()) localFileDir.mkdirs();
					File localFile = new File(localPath+ff.getName());
					OutputStream is = new FileOutputStream(localFile); 
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}
			
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	} 
	
	
	/**
	 * 删除远程FTP文件
	 * 
	 * @param remote
	 *            远程文件路径
	 * @return
	 * @throws IOException
	 */
	public static void delete(FTPClient ftpClient,String remote) throws IOException{
		FTPFile[] files = ftpClient.listFiles(remote);
		if(files.length == 1){
			ftpClient.deleteFile(remote);
		}
	}
	
	/**
	 * Description: 向FTP服务器上传文件
	 * @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建
	 * @param url FTP服务器hostname
	 * @param port FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param path FTP服务器保存目录
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input 输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String url,int port,String username, String password, String filePath, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		String remoteFileName=filePath;
		try {
			int reply;
			ftp.connect(url, port);//连接FTP服务器
			//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);//登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
//			ftp.changeWorkingDirectory(path);
			if (filePath.contains("/")) {
				remoteFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
				String directory = filePath.substring(0,
						filePath.lastIndexOf("/") + 1);
				if (!directory.equalsIgnoreCase("/")
						&& !ftp.changeWorkingDirectory(directory)) {
					// 如果远程目录不存在，则递归创建远程服务器目录
					int start = 0;
					int end = 0;
					if (directory.startsWith("/")) {
						start = 1;
					} else {
						start = 0;
					}
					end = directory.indexOf("/", start);
					while (true) {
						String subDirectory = filePath.substring(start, end);
						if (!ftp.changeWorkingDirectory(subDirectory)) {
							if (ftp.makeDirectory(subDirectory)) {
								ftp.changeWorkingDirectory(subDirectory);
							} else {
								logger.info("创建目录失败");
								return false;
							}
						}
						start = end + 1;
						end = directory.indexOf("/", start);
						// 检查所有目录是否创建完毕
						if (end <= start) {
							break;
						}
					}
				}
			}
			ftp.storeFile(remoteFileName, input);			
			input.close();
			ftp.rename(remoteFileName, remoteFileName.replace(UPLOADING, ""));
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
	
	/**
	 * @方法描述:  持续上传文件
	 * @作者： 1603254
	 * @日期： 2016-6-16-下午5:23:19
	 * @param filePath  远程路径和文件名
	 * @param input	            输出流
	 * @param isLast    是否是最后的一次上传
	 * @param ftp   	ftp对象
	 * @return 
	 * @返回类型： boolean
	*/
	public static boolean uploadFileContuine(String filePath,
			InputStream input,
			boolean isLast,
			FTPClient ftp) {
		boolean success = false;
		String remoteFileName=filePath;
		try {
			if (filePath.contains("/")) {
				remoteFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
				String directory = filePath.substring(0,
						filePath.lastIndexOf("/") + 1);
				if (!directory.equalsIgnoreCase("/")
						&& !ftp.changeWorkingDirectory(directory)) {
					// 如果远程目录不存在，则递归创建远程服务器目录
					int start = 0;
					int end = 0;
					if (directory.startsWith("/")) {
						start = 1;
					} else {
						start = 0;
					}
					end = directory.indexOf("/", start);
					while (true) {
						String subDirectory = filePath.substring(start, end);
						if (!ftp.changeWorkingDirectory(subDirectory)) {
							if (ftp.makeDirectory(subDirectory)) {
								ftp.changeWorkingDirectory(subDirectory);
							} else {
								logger.info("创建目录失败");
								return false;
							}
						}
						start = end + 1;
						end = directory.indexOf("/", start);
						// 检查所有目录是否创建完毕
						if (end <= start) {
							break;
						}
					}
				}
			}
			// 检查远程是否存在文件，如果存在，那么直接从文件的尾部添加
			FTPFile[] files = ftp.listFiles(remoteFileName);
			if(files.length == 1){
				long remoteSize = files[0].getSize();
				ftp.setRestartOffset(remoteSize);
			}
			if(isLast){
				ftp.storeFile(remoteFileName, input);			
				input.close();
				ftp.rename(remoteFileName, remoteFileName.replace(UPLOADING, ""));
				ftp.logout();
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
					}
				}
			}else{
				ftp.storeFile(remoteFileName, input);			
				input.close();
			}
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return success;
	}
}
