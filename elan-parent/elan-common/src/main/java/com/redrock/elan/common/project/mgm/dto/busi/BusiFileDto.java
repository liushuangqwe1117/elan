package com.redrock.elan.common.project.mgm.dto.busi;

import com.redrock.elan.common.core.dto.IdentityDto;

public class BusiFileDto extends IdentityDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1817197665588810900L;

	/**
	 * 主键
	 *
	 * @mbggenerated Sun Aug 28 00:06:40 CST 2016
	 */
	private String id;

	/**
	 * 文件名称
	 *
	 * @mbggenerated Sun Aug 28 00:06:40 CST 2016
	 */
	private String fileName;

	/**
	 * 文件大小
	 *
	 * @mbggenerated Sun Aug 28 00:06:40 CST 2016
	 */
	private Long fileSize;

	/**
	 * 文件路径
	 *
	 * @mbggenerated Sun Aug 28 00:06:40 CST 2016
	 */
	private String fileLocation;

	/**
	 * 文件类型
	 *
	 * @mbggenerated Sun Aug 28 00:06:40 CST 2016
	 */
	private String fileType;

	/**
	 * 文件扩展名
	 *
	 * @mbggenerated Sun Aug 28 00:06:40 CST 2016
	 */
	private String fileExt;
	/**
	 * 文件二进制
	 */
	private byte[] fileContent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation == null ? null : fileLocation.trim();
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt == null ? null : fileExt.trim();
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

}