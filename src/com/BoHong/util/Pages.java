package com.BoHong.util;

import java.io.IOException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.components.Component;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;

import com.opensymphony.xwork2.util.ValueStack;
import com.tairui.util.SysConfig;

/**
 * 
 * <li>项目名称: 系统架构
 * <li>功能描述: 分页类。
 * 
 * @author lehuo
 * @version v1.00 2014-05-20
 */

public class Pages extends Component {
	private Log log = LogFactory.getLog(getClass());

	private String pageNo; // 当前页码
	private String pageCount; // 总页数
	private String theme; // 分页的主题
	private String url; // action的路径
	private String urlType; // 路径的类型，主要用于URL重写的扩展

	@SuppressWarnings("unchecked")
	public boolean end(Writer writer, String body) {
		boolean result = super.start(writer);
		try {
			// 从ValueStack中取出数值
			Object objP = this.getStack().findValue(pageNo);
			Object obj = this.getStack().findValue(pageCount);

			pageNo = String.valueOf((Integer) objP);
			pageCount = String.valueOf((Integer) obj);

			StringBuilder str = new StringBuilder();
			Map cont = this.getStack().getContext();
			StrutsRequestWrapper req = (StrutsRequestWrapper) cont
					.get(StrutsStatics.HTTP_REQUEST);

			if (url == null || "".equals(url)) {
				url = (String) req
						.getAttribute("javax.servlet.forward.request_uri");
			}
			String pageNoStr = "?pageNo=";// 拼接url,传pageNo参数
			if ("dir".equals(urlType)) {// 当url的类型为目录类型时，比如http://localhost:8080/iWork/page/1
				pageNoStr = "";
				url = url.substring(0, url.lastIndexOf("/") + 1);
			}
			// 下面这段处理主要是用来处理动态查询的参数，并拼接成url
			StringBuffer perUrl = new StringBuffer("");
			if (0 != this.getParameters().size()) {
				Iterator iter = this.getParameters().keySet().iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					Object o = this.getParameters().get(key);
					if (o.getClass().equals(String.class)) // 如果是字符串类型参数
					
					{
                   
						perUrl.append("&").append(key).append("=").append(
								URLEncoder.encode(o.toString(),"utf-8"));
					} 
					
					else {
						perUrl.append("&").append(key).append("=").append(o);
					}
					
				} // end while
			} // end if

			// 文本样式

			str
					.append("<script language='javascript'>  var strPara=\""
							+ url
							+ "\";"
							+ "function setPage(){var total="
							+ pageCount
							+ ";var pagNo=document.getElementById(\"pgn\").value;if (pagNo==\"\") {return;} if (pagNo<=0)pagNo=1;if( pagNo>total)pagNo=total;window.location.href=strPara+\"?pageNo=\"+pagNo+\""
							+ perUrl + "\" ; }</script>");
			if (theme == null || "image".equals(theme)) {// 图片类型的分页按钮
				Integer cpageInt = Integer.valueOf(pageNo);

				// 当前页与总页数相等
				if (pageNo.equals(pageCount)) {
					// 如果total = 1，则无需分页，显示“[第1页] [共1页]”
					if (!"1".equals(pageCount)) {
						// 到达最后一页,显示“[首页] [上一页] [末页]”
						str
								.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
										+ url
										+ pageNoStr
										+ "1"
										+ perUrl
										+ "'><img height=\"15\"  width=\"37\"  src=\""
										+ SysConfig.getConfigData("PageImg",
												"firstImg", "首页图片路径")
										+ "\" border=\"0\" align=\"bottom\"></a> ");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url
								+ pageNoStr
								+ (cpageInt - 1)
								+ perUrl
								+ "'><img height=\"15\"  width=\"37\"  src=\""
								+ SysConfig.getConfigData("PageImg", "backImg",
										"上一页图片路径")
								+ "\" border=\"0\" align=\"bottom\"></a>");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<a href='"
								+ url
								+ pageNoStr
								+ pageCount
								+ perUrl
								+ "'><img height=\"15\"  width=\"37\"  src=\""
								+ SysConfig.getConfigData("PageImg", "lastImg",
										"末图片路径")
								+ "\" border=\"0\" align=\"bottom\"></a> ");

						str
								.append("&nbsp;&nbsp;<font> 转到第 </font><input type='text' id='pgn'  onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onblur=\"value=value.replace(/[^\\d]/g,'')\" onbeforepaste=\"clipboardData.setData('text',clipboardData.getData('text').replace(/[^\uFF00-\uFFFF]/g,''))\" class=\"pageInput\" /> <font>页</font>"
										+ "<a   onclick=\"setPage()\"><img height=\"15\"  width=\"37\"  src=\""
										+ SysConfig.getConfigData("PageImg",
												"goImg", "跳转图片路径")
										+ "\" border=\"0\" align=\"bottom\"></a> ");
					}
				} else {
					// 当前页与总页数不相同
					if ("1".equals(pageNo)) {
						// 第一页，显示“[首页] [下一页] [末页]”
						str
								.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
										+ url
										+ pageNoStr
										+ "1"
										+ perUrl
										+ "'><img height=\"15\"  width=\"37\"  src=\""
										+ SysConfig.getConfigData("PageImg",
												"firstImg", "首页图片路径")
										+ "\" border=\"0\" align=\"bottom\"></a> ");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url
								+ pageNoStr
								+ (cpageInt + 1)
								+ perUrl
								+ "'><img height=\"15\"  width=\"37\"  src=\""
								+ SysConfig.getConfigData("PageImg", "nextImg",
										"下一页图片路径")
								+ "\" border=\"0\"  align=\"bottom\"></a>");
						str
								.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
										+ url
										+ pageNoStr
										+ pageCount
										+ perUrl
										+ "'  ><img height=\"15\"  width=\"37\"  src=\""
										+ SysConfig.getConfigData("PageImg",
												"lastImg", "末图片路径")
										+ "\" border=\"0\"  align=\"bottom\" align=\"bottom\"></a>");

						str
								.append("&nbsp;&nbsp;<font> 转到第  </font><input type='text' id='pgn' onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onblur=\"value=value.replace(/[^\\d]/g,'')\" onbeforepaste=\"clipboardData.setData('text',clipboardData.getData('text').replace(/[^\uFF00-\uFFFF]/g,''))\"  class=\"pageInput\" /> <font>页</font>"
										+ " <a  onclick=\"setPage()\"><img height=\"15\"  width=\"37\"  src=\""
										+ SysConfig.getConfigData("PageImg",
												"goImg", "跳转图片路径")
										+ "\" border=\"0\"  align=\"bottom\"></a> ");

					} else {
						// 不是第一页，显示“[首页] [上一页] [下一页] [末页]”
						str
								.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
										+ url
										+ pageNoStr
										+ "1"
										+ perUrl
										+ "'><img height=\"15\"  width=\"37\"  src=\""
										+ SysConfig.getConfigData("PageImg",
												"firstImg", "首页图片路径")
										+ "\" border=\"0\" align=\"bottom\"></a> ");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url
								+ pageNoStr
								+ (cpageInt - 1)
								+ perUrl
								+ "'><img height=\"15\"  width=\"37\"  src=\""
								+ SysConfig.getConfigData("PageImg", "backImg",
										"上一页图片路径")
								+ "\"  border=\"0\"  align=\"bottom\"></a>");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url
								+ pageNoStr
								+ (cpageInt + 1)
								+ perUrl
								+ "'><img height=\"15\"  width=\"37\"  src=\""
								+ SysConfig.getConfigData("PageImg", "nextImg",
										"下一页图片路径")
								+ "\"  border=\"0\"  align=\"bottom\"></a>");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url
								+ pageNoStr
								+ pageCount
								+ perUrl
								+ "'><img height=\"15\"  width=\"37\"  src=\""
								+ SysConfig.getConfigData("PageImg", "lastImg",
										"末图片路径")
								+ "\"  border=\"0\"  align=\"bottom\"></a>");

						str
								.append("&nbsp;&nbsp;<font> 转到第  </font><input type='text' id='pgn'  onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onblur=\"value=value.replace(/[^\\d]/g,'')\" onbeforepaste=\"clipboardData.setData('text',clipboardData.getData('text').replace(/[^\uFF00-\uFFFF]/g,''))\"  class=\"pageInput\" /> <font>页</font>"
										+ "<a onclick=\"setPage()\"><img height=\"15\"  width=\"37\"  src=\""
										+ SysConfig.getConfigData("PageImg",
												"goImg", "跳转图片路径")
										+ "\" border=\"0\"  align=\"bottom\"></a> ");

					}
				}
			} else if (theme == null || "text".equals(theme)) {// 文字类型的分页链接
				Integer cpageInt = Integer.valueOf(pageNo);

				// 当前页与总页数相等
				if (pageNo.equals(pageCount)) {
					// 如果total = 1，则无需分页，显示“[第1页] [共1页]”
					if (!"1".equals(pageCount)) {
						// 到达最后一页,显示“[首页] [上一页] [末页]”
						str
								.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
										+ url
										+ pageNoStr
										+ "1"
										+ perUrl
										+ "'>首页</a> ");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url + pageNoStr + (cpageInt - 1) + perUrl
								+ "'>上一页</a>");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<a href='"
								+ url + pageNoStr + pageCount + perUrl
								+ "'>尾页</a> ");

						str
								.append("&nbsp;&nbsp;<font> 转到第 </font><input type='text' id='pgn'  onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onblur=\"value=value.replace(/[^\\d]/g,'')\" onbeforepaste=\"clipboardData.setData('text',clipboardData.getData('text').replace(/[^\uFF00-\uFFFF]/g,''))\"  class=\"pageInput\" /> <font>页&nbsp;&nbsp;</font>"
										+ "<a  href='#' onclick=\"setPage()\">跳转</a> ");
					}
				} else {
					// 当前页与总页数不相同
					if ("1".equals(pageNo)) {
						// 第一页，显示“[首页] [下一页] [末页]”
						str
								.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
										+ url
										+ pageNoStr
										+ "1"
										+ perUrl
										+ "'>首页</a> ");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url + pageNoStr + (cpageInt + 1) + perUrl
								+ "'>下一页</a>");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url + pageNoStr + pageCount + perUrl
								+ "'  >尾页</a>");

						str
								.append("&nbsp;&nbsp;<font> 转到第 </font><input type='text' id='pgn' onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onblur=\"value=value.replace(/[^\\d]/g,'')\" onbeforepaste=\"clipboardData.setData('text',clipboardData.getData('text').replace(/[^\uFF00-\uFFFF]/g,''))\"  class=\"pageInput\" /> <font>页&nbsp;&nbsp;</font>"
										+ " <a href='#' onclick=\"setPage()\">跳转</a> ");

					} else {
						// 不是第一页，显示“[首页] [上一页] [下一页] [末页]”
						str
								.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
										+ url
										+ pageNoStr
										+ "1"
										+ perUrl
										+ "'>首页</a> ");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url + pageNoStr + (cpageInt - 1) + perUrl
								+ "'>上一页</a>");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url + pageNoStr + (cpageInt + 1) + perUrl
								+ "'>下一页</a>");
						str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"
								+ url + pageNoStr + pageCount + perUrl
								+ "'>尾页</a>");

						str
								.append("&nbsp;&nbsp;<font> 转到第 </font> <input type='text' id='pgn'  onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onblur=\"value=value.replace(/[^\\d]/g,'')\" onbeforepaste=\"clipboardData.setData('text',clipboardData.getData('text').replace(/[^\uFF00-\uFFFF]/g,''))\"  class=\"pageInput\" /> <font>页&nbsp;&nbsp;</font>"
										+ "<a href='#' onclick=\"setPage()\">跳转</a> ");

					}
				}
			} else if (theme == null || "front".equals(theme)) {// 前台--文字类型的分页链接
				Integer cpageInt = Integer.valueOf(pageNo);

				// 当前页与总页数相等
				if (pageNo.equals(pageCount)) {
					// 如果total = 1，则无需分页，显示“[第1页] [共1页]”
					if (!"1".equals(pageCount)) {
						// 到达最后一页,显示“[首页] [上一页] [末页]”
						str.append("<a href='" + url + pageNoStr + "1" + perUrl
								+ "' onclick='reLoaded();'>首页</a> ");
						str.append("&nbsp;<a href='" + url + pageNoStr
								+ (cpageInt - 1) + perUrl
								+ "' onclick='reLoaded();'>上一页</a>");
						str.append("&nbsp;<a href='" + url + pageNoStr
								+ pageCount + perUrl
								+ "' onclick='reLoaded();'>尾页</a> ");
					}
				} else {
					// 当前页与总页数不相同
					if ("1".equals(pageNo)) {
						// 第一页，显示“[首页] [下一页] [末页]”
						str.append("<a href='" + url + pageNoStr + "1" + perUrl
								+ "'  onclick='reLoaded();'>首页</a> ");
						str.append("&nbsp;<a href='" + url + pageNoStr
								+ (cpageInt + 1) + perUrl
								+ "' onclick='reLoaded();'>下一页</a>");
						str.append("&nbsp;<a href='" + url + pageNoStr
								+ pageCount + perUrl
								+ "' onclick='reLoaded();' >尾页</a>");
					} else {
						// 不是第一页，显示“[首页] [上一页] [下一页] [末页]”
						str.append("<a href='" + url + pageNoStr + "1" + perUrl
								+ "' onclick='reLoaded();'>首页</a> ");
						str.append("&nbsp;<a href='" + url + pageNoStr
								+ (cpageInt - 1) + perUrl
								+ "' onclick='reLoaded();'>上一页</a>");
						str.append("&nbsp;<a href='" + url + pageNoStr
								+ (cpageInt + 1) + perUrl
								+ "' onclick='reLoaded();'>下一页</a>");
						str.append("&nbsp;<a href='" + url + pageNoStr
								+ pageCount + perUrl
								+ "' onclick='reLoaded();'>尾页</a>");
					}
				}
			}
			writer.write(str.toString());

		} catch (IOException ex) {
			log.error(ex);// 添加错误日志
			ex.printStackTrace();
		}
		return result;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public Pages(ValueStack arg0, HttpServletRequest request) {
		super(arg0);

	}

	public String getPageNo() {
		return pageNo;
	}

	public String getPageCount() {
		return pageCount;
	}

}
