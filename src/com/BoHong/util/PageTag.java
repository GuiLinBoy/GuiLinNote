package com.BoHong.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 
 * <li>项目名称: 系统架构
 * <li>功能描述: 分页实体类。
 * 
 * @author lehuo
 * @version v1.00 2014-05-20
 */

public class PageTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1L;

	private String pageNo;
	private String pageCount;
	private String theme;
	private String url;
	private String urlType;

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getPageNo() {
		return pageNo;
	}

	public String getPageCount() {
		return pageCount;
	}

	public String getTheme() {
		return theme;
	}

	public String getUrl() {
		return url;
	}

	public String getUrlType() {
		return urlType;
	}

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		return new Pages(arg0, arg1);
	}

	protected void populateParams() {
		super.populateParams();

		Pages pages = (Pages) component;
		pages.setPageNo(pageNo);
		pages.setPageCount(pageCount);
		pages.setTheme(theme);
		pages.setUrl(url);
		pages.setUrlType(urlType);

	}
}
