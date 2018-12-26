package com.BoHong.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * <li>项目名称: 系统架构
 * <li>功能描述: Action基础类，负责分页和上传下载。
 * 
 * @author lehuo
 * @version v1.00 2014-05-20
 */

public class BaseSupportAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(getClass());

	/** 当前页码 */
	public int pageNo = 1;
	/** 每页行数 */
	public static int PAGE_SIZE = 12;
	/** 日历每页行数 */
	public static int PAGEDB_SIZE = 1;

	/** 总页数 */
	public int pageCount;
	/** 总行数 */
	public int resultRows;

	/** 使用列表接收上传文件,和表单file标签的name必须一致 */
	public List<File> upload;
	/** 使用列表保存多个上传文件的文件名 */
	public List<String> uploadFileName;
	/** 使用列表保存多个上传文件的MIME类型 */
	public List<String> uploadContentType;
	/** 上传、下载文件封装类列表，其文件顺序和页面上传文件顺序一致 */
	public List<UploadFiles> uploadFiles = new ArrayList<UploadFiles>();

	// 代表上传文件的file对象
	public File file;
	// 上传文件名
	public String fileFileName;
	// 上传文件的MIME类型
	public String fileContentType;
	// 把上传文件封装到UploadFiles中
	public UploadFiles upLoadFile = new UploadFiles();

	public BaseSupportAction() {
			PAGE_SIZE = 12;
			PAGEDB_SIZE = 1;

	}

	/**
	 * 
	 * <li>功能描述：多文件上传。
	 * 
	 * @return 0：上传成功；1:没有上传文件；2：上传异常 int
	 */
	public String saveFile() {
		try {
			String targetDirectory = SysConfig.getConfigData("fileUp", "upPath");// 获得保存上传文件的路径
			// 如果有附件文件
			if (upload != null) {
				// 循环处理多个上传文件
				for (int i = 0; i < upload.size(); i++) {

					String fileName = uploadFileName.get(i);// 上传的文件名
					String type = uploadContentType.get(i);// 文件类型
					String realName = UUID.randomUUID().toString()
							+ getExt(fileName);// 保存的文件名称，使用UUID+后缀进行保存
					File target = new File(targetDirectory, realName);
					FileUtils.copyFile(upload.get(i), target);// 上传至服务器

					UploadFiles uf = new UploadFiles();// 把上传文件封装到UploadFiles中
					uf.setUploadContentType(type);
					uf.setUploadFileName(fileName);
					uf.setUploadRealName(realName);
					uploadFiles.add(uf);

				}
				return SUCCESS;// 上传成功
			} else {
				return ERROR;// 没有需上传文件
			}

		} catch (Exception e) {
			log.error(e);// 添加错误日志
			return ERROR;// 上传异常
		}
	}
	public int saveFile1() {
		try {
			String targetDirectory = SysConfig.getConfigData("fileUp", "upPath");// 获得保存上传文件的路径
			// 如果有附件文件
			if (upload != null) {
				// 循环处理多个上传文件
				for (int i = 0; i < upload.size(); i++) {

					String fileName = uploadFileName.get(i);// 上传的文件名
					String type = uploadContentType.get(i);// 文件类型
					String realName = UUID.randomUUID().toString()
							+ getExt(fileName);// 保存的文件名称，使用UUID+后缀进行保存
					File target = new File(targetDirectory, realName);
					FileUtils.copyFile(upload.get(i), target);// 上传至服务器

					UploadFiles uf = new UploadFiles();// 把上传文件封装到UploadFiles中
					uf.setUploadContentType(type);
					uf.setUploadFileName(fileName);
					uf.setUploadRealName(realName);
					uploadFiles.add(uf);

				}
				return 0;// 上传成功
			} else {
				return 1;// 没有需上传文件
			}

		} catch (Exception e) {
			log.error(e);// 添加错误日志
			return 2;// 上传异常
		}
	}

	public int saveFile(String targetDirectory) {
		try {
			// 如果有附件文件
			if (upload != null) {
				// 循环处理多个上传文件
				for (int i = 0; i < upload.size(); i++) {

					String fileName = uploadFileName.get(i);// 上传的文件名
					String type = uploadContentType.get(i);// 文件类型
					String realName = UUID.randomUUID().toString()
							+ getExt(fileName);// 保存的文件名称，使用UUID+后缀进行保存
					File target = new File(targetDirectory, realName);
					// long temp = target.length();
					FileUtils.copyFile(upload.get(i), target);// 上传至服务器

					UploadFiles uf = new UploadFiles();// 把上传文件封装到UploadFiles中
					uf.setUploadContentType(type);
					uf.setUploadFileName(fileName);
					uf.setUploadRealName(realName);
					uploadFiles.add(uf);

				}
				return 0;// 上传成功
			} else {
				return 1;// 没有需上传文件
			}

		} catch (Exception e) {
			log.error(e);// 添加错误日志
			return 2;// 上传异常
		}
	}

	public long saveFilePhone(String targetDirectory, String fileName) {
		try {
			String tempPath = "../Tomcat 6.0/webapps/ztb/tempfile";
			File tempFile = new File(tempPath, fileName);
			File target = new File(targetDirectory, fileName);
			// long temp = target.length();
			FileUtils.copyFile(tempFile, target);// 上传至服务器

			return target.length();

		} catch (Exception e) {
			log.error(e);// 添加错误日志
			return -1;// 上传异常
		}
	}

	/**
	 * 
	 * <li>功能描述：单文件上传。
	 * 
	 * @return 0：上传成功；1:没有上传文件；2：上传异常 int
	 */
	public int saveSingleFile() {
		try {
			String targetDirectory = SysConfig.getConfigData("fileUp", "upPath");// 获得保存上传文件的路径
			// 如果有附件文件
			if (file != null) {
				String realName = UUID.randomUUID().toString()
						+ getExt(fileFileName);// 保存的文件名称，使用UUID+后缀进行保存
				File target = new File(targetDirectory, realName);
				FileUtils.copyFile(file, target);// 上传至服务器

				upLoadFile.setUploadContentType(fileContentType);
				upLoadFile.setUploadFileName(fileFileName);
				upLoadFile.setUploadRealName(realName);
				return 0;// 上传成功
			} else {
				return 1;// 没有需上传文件
			}

		} catch (Exception e) {
			log.error(e);// 添加错误日志
			return 2;// 上传异常
		}
	}

	/**
	 * 替换文件上传中出现的英文错误信息
	 */

	@Override
	public void addActionError(String anErrorMessage) {

		if (anErrorMessage
				.startsWith("the request was rejected because its size")) {
			Matcher m = Pattern.compile("\\d+").matcher(anErrorMessage);
			String s1 = "";
			if (m.find()) {
				s1 = m.group();
			}
			String s2 = "";
			if (m.find()) {
				s2 = m.group();
			}
			// 将信息替换掉
			super.addActionError("您上传的文件（\"" + s1 + "\"）超过允许的大小（\"" + s2
					+ "\"）");
		} else {
			// 不是则不管它
			super.addActionError(anErrorMessage);
		}

	}

	public static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	// -----------------------------------------------------
	// -------------------------下载方法----------------------
	// -----------------------------------------------------

	/**
	 * 文件下载 根据文件名称下载文件
	 * 
	 * @param path,String类型，路径
	 * @param realFileName,String类型，服务器上的文件名字
	 * @param showFileName,String类型，下载到本地的文件名字
	 * 
	 * @return flag true为成功 false为失败
	 */

	public String getfile(String path, String realFileName, String showFileName) {

		String filename = ServletActionContext.getServletContext().getRealPath(
				path)
				+ "/" + realFileName;
		if (filename != null) {
			if (downLoadFile(this.getResponse(), this.getRequest(), filename,
					showFileName)) {
				// System.out.println("文件导出下载成功");
			}
			// this.renderDirect("文件下载成功", false, false, true);
		}
		return null;
	}

	/**
	 * 文件下载
	 * 
	 * @param response,HttpServletResponse类型，当前的响应对象。
	 * @param request,HttpServletRequest类型，当前的请求对象。
	 * 
	 * @param fileName,String类型，服务器上的文件名字
	 * @param fileName,String类型，下载到本地的文件名字
	 * 
	 * @return flag true为成功 false为失败
	 */
	public boolean downLoadFile(HttpServletResponse response,
			HttpServletRequest request, String fileName, String downFilename) {
		DownLoadFile downTool = new DownLoadFile();

		if (downTool.sendDownLoadFile(response, 1, fileName, downFilename)) {
			return true;
		} else {
			super.addActionError("下载文件失败!");
			return false;
		}
	}

	/**
	 * <li>获得HttpServletRequest对象。
	 * 
	 * @return 返回的HttpServletRequest对象
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * <li>获得HttpServletResponse对象。
	 * 
	 * @return 返回的HttpServletResponse对象
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}


	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {

		this.pageNo = pageNo;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageSize() {
		return PAGE_SIZE;
	}

	public Integer getPageDBSize() {
		return PAGEDB_SIZE;
	}

	public int getResultRows() {
		return resultRows;
	}

	public void setResultRows(int resultRows) {
		this.resultRows = resultRows;
	}
}