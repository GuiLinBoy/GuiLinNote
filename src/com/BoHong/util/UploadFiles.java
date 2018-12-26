package com.BoHong.util;

/**
 * 
 * <li>项目名称: 系统架构
 * <li>功能描述: 上传类。
 * 
 * @author lehuo
 * @version v1.00 2014-05-20
 */

public class UploadFiles {
	private String uploadFileName;// 上传的文件名称
	private String uploadContentType;// 文件的MIME类型
	private String uploadRealName;// 服务器保存的文件名称

	/**
	 * 
	 * <li>取得上传的文件名称。
	 * 
	 * @return 返回上传的文件名称 String
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * 
	 * <li>设置上传的文件名称。
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * 
	 * <li>取得文件的MIME类型。
	 * 
	 * @return 返回文件的MIME类型 String
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}

	/**
	 * 
	 * <li>设置文件的MIME类型。
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 
	 * <li>取得服务器保存的文件名称。
	 * 
	 * @return 返回服务器保存的文件真实名称 String
	 */
	public String getUploadRealName() {
		return uploadRealName;
	}

	/**
	 * 
	 * <li>设置服务器保存的文件名称。
	 */
	public void setUploadRealName(String uploadRealName) {
		this.uploadRealName = uploadRealName;
	}

}
