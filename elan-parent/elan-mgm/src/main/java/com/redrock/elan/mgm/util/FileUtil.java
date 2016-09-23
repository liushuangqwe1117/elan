package com.redrock.elan.mgm.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.google.code.lightssh.common.ApplicationException;
import com.redrock.elan.mgm.common.excel.ExcelImportError;
import com.redrock.elan.mgm.common.excel.RegexEnum;

public class FileUtil {

	public static void main(String[] args) {
		String dirName = "d:/FH/topic/";// 创建目录
		FileUtil.createDir(dirName);
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 *            目标目录名
	 * @return 目录创建成功返回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 读取到字节数组1
	 * 
	 * @param filePath
	 *            //路径
	 * @throws IOException
	 */
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		fi.close();
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		
		return buffer;
	}

	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 读取到字节数组3
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray2(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray3(String filePath) throws IOException {

		FileChannel fc = null;
		try {
			fc = new RandomAccessFile(filePath, "r").getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			// System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				// System.out.println("remain");
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据文件的后缀，获取Workbook Object（由于Office2003和Office2007、2010的处理方式不一样）
	 * 
	 * @param filePath
	 * @return Workbook
	 * @throws Exception
	 */
	public static Workbook getWorkBook(String filePath)
			throws ApplicationException {
		boolean isExcel2003 = true;// 是否是Excel 2003
		Workbook wb = null;
		String houzhui = filePath.substring(filePath.lastIndexOf("."),
				filePath.length());
		if (".xls".equals(houzhui) || ".xlsx".equals(houzhui)) {
			if (".xlsx".equals(houzhui)) {
				isExcel2003 = false;
			}
		} else {
			throw new ApplicationException("只支持xls或者xlsx文件格式的导入");
		}
		try {
			File file = new File(filePath);
			if (file == null || !file.exists()) {
				throw new ApplicationException("该文件不存在");
			}
			FileInputStream fis = new FileInputStream(file);
			wb = (Workbook) (isExcel2003 ? new HSSFWorkbook(fis)
					: new XSSFWorkbook(fis));
		} catch (Exception ex) {
			throw new ApplicationException("获取Workbook对象出现异常");
		}
		return wb;
	}

	/**
	 * 上传文件到临时目录
	 * 
	 * @param file
	 * @return
	 */
	public static String upLoadFile(MultipartFile file,String temp) {
		String filePath = temp + File.separator;
		File files = new File(filePath);
		if (!files.exists()) {// 如果该目录名称不存在，则创建他
			files.mkdirs();
		}
		// 获得上传的文件
		filePath = filePath + new Date().getTime() + file.getOriginalFilename();
		// 上传文件到指定目录
		try {
			file.transferTo(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("文件上传异常");
		}
		return filePath;
	}

	/**
	 * 删除临时文件
	 * 
	 * @param filePath
	 */
	public static void delTempFile(String filePath) {
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	/**
	 * 验证数据
	 * 
	 * @param cellName
	 * @param cellValue
	 * @param cellPosition
	 * @param regexEnum
	 * @return
	 */
	public static List<ExcelImportError> checkData(String cellName,
			String cellValue, int cellPosition, RegexEnum regexEnum) {
		List<ExcelImportError> errorList = new ArrayList<ExcelImportError>();
		// 验证
		if (!Pattern.compile(regexEnum.getRegexValue()).matcher(cellValue)
				.matches()) {
			ExcelImportError error = new ExcelImportError();
			// 错误的列名
			error.setCellName(cellName);
			// 错误列所对应的属性值
			error.setCellValue(cellValue);
			// 错误列所在位置（例如：[3A]第三行第一列）
			error.setCellPosition("第[" + cellPosition + "]行");
			// 错误原因
			error.setCellMessage(regexEnum.getErrorMessage());
			errorList.add(error);
		}
		return errorList;

	}
}