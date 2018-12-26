package com.BoHong.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
	protected FilterConfig filterConfig;
	private String targetEncoding = "utf-8";

	/**
	 * 初始化过滤器。
	 */
	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
		this.targetEncoding = config.getInitParameter("encoding");
	}

	/**
	 * 进行编码过滤处理。
	 */
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) srequest;
		@SuppressWarnings("unused")
		HttpServletResponse response = (HttpServletResponse) sresponse;
		request.setCharacterEncoding(targetEncoding);

		// 把处理权发送到下一个
		chain.doFilter(srequest, sresponse);
	}

	public void setFilterConfig(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	// 销毁过滤器
	public void destroy() {
		this.filterConfig = null;
	}
}
