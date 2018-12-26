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


public class SignonFilter implements Filter {
	protected FilterConfig filterConfig;
	private String on_off = "off";//过滤器开启的开关

	/**
	 * 对客户端请求进行拦截以判断所请求资源是否合法，对服务器端内部的跳转不进行控制。
	 */
	public void doFilter(final ServletRequest req, final ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		if (on_off.equals("on")) {

			HttpServletRequest hreq = (HttpServletRequest) req;
			HttpServletResponse hres = (HttpServletResponse) res;
			HttpSession session = hreq.getSession();
			String isLogin = "";
			try {
				String path = hreq.getContextPath();
				String basePath = hreq.getScheme() + "://"
						+ hreq.getServerName() + ":" + hreq.getServerPort()
						+ path + "/";
				String urlString = hreq.getRequestURL().toString();
				isLogin = (String) session.getAttribute("isLogin");

				if (isLogin == "true") {

					String[] dateArray = urlString.split("/");
					String serString = dateArray[dateArray.length - 1];
					serString = dateArray[dateArray.length - 2] + "/"
							+ serString;

					if (serString.endsWith("index.jsp") || !getAuthoritySource(serString, session)) {
						hres.sendRedirect(basePath + "/errors/auth.jsp");
					} else {
						chain.doFilter(req, res);
					}

				} else {
					hres.sendRedirect(basePath + "/login.jsp");
					//System.out.println("------------" + hreq.getRequestURL());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			chain.doFilter(req, res);
		}

	}

	/**
	 * 根据登录帐号取得用户分组资源,判断是否有该资源权限
	 * @param account
	 */
	public boolean getAuthoritySource(String strReq, HttpSession session) {
		String strSource;
		strSource = session.getAttribute("source").toString();
		//System.out.println("strSource=========="+strSource+"strReq===="+strReq);	
		boolean flag = false;
		///判断页面是否是action且带参数，如果有参数，去掉参数
		int iLen = strReq.length();
		String strAction = strReq.substring(iLen - 6);

		if ("action".equals(strAction)) { //如果是action
			int iAction = strReq.indexOf("-");
			if (-1 != iAction) {
				strReq = strReq.substring(0, iAction) + ".action";

			}
		}
		////判断请求页面是否有权限
		String sourceArray[] = strSource.split("%");
		for (int i = 0; i < sourceArray.length; i++) {
			//System.out.println("strSourcearry["+i+"] =========="+sourceArray[i]);	
			if (sourceArray[i].endsWith(strReq)) {
				//System.out.println("if["+i+"] =========="+sourceArray[i]);	
				flag = true;
				break;
			}
		}

		//System.out.println("flag=========="+flag);	 
		return flag;

	}

	public void setFilterConfig(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
		this.on_off = config.getInitParameter("on_off");
	}

}