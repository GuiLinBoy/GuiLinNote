package com.BoHong.util;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpSession;

public class IsLoginFilter implements Filter {
	protected FilterConfig filterConfig;
	private String on_off = "off";//过滤器开启的开关	

	public void doFilter(final ServletRequest req, final ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		if (on_off.equals("on")) {
			
			HttpServletRequest hreq = (HttpServletRequest) req;
			HttpServletResponse hres = (HttpServletResponse) res;
			HttpSession session = hreq.getSession();
			String isLogin = "";
			String path = hreq.getContextPath();
			String basePath = hreq.getScheme() + "://"
					+ hreq.getServerName() + ":" + hreq.getServerPort()
					+ path + "/";

			
			hres.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
			hres
					.setHeader("Cache-Control",
							"no-store,no-cache,must-revalidate");
			hres.addHeader("Cache-Control", "post-check=0, pre-check=0");
			hres.setHeader("Pragma", "no-cache");
			try {
				isLogin = (String) session.getAttribute("isLogin");
				if (isLogin == "true") {
					chain.doFilter(req, res);
				} else {
					hres.sendRedirect(basePath + "login.jsp");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			chain.doFilter(req, res);
		}

	}

	public void setFilterConfig(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * ��ٹ�����
	 */
	public void destroy() {
		this.filterConfig = null;
	}

	/**
	 *��ʼ��������
	 */
	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
		this.on_off = config.getInitParameter("on_off");
	}

}
