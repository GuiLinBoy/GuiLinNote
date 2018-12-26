package com.tairui.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.ActionSupport;

/**
 * <li>项目名称: qhdIT <li>功能描述: 该文件的功能描述 <li>公司: 石家庄融尚科技有限公司 *
 * 
 * @author Administrator 正则验证工具类，验证了double、手机号
 */

public class ValidateUtil {

	/**
	 * 表单提交的校验 thisAction
	 * 
	 * @param thisAction,ActionSupport类型，校验的Action对象
	 * @param checkType,String类型，校验类型
	 * @param checkType描述，整数:校验不为空的整数
	 * @param checkType描述，小数:校验不为空的浮点数
	 * @param checkType描述，固话:校验不为空的固定电话
	 * @param checkType描述，手机:校验不为空的手机号
	 * @param checkType描述，邮箱:校验不为空的邮箱
	 * @param checkType描述，邮编:校验不为空的邮编
	 * @param checkType描述，身份证:校验不为空的身份证
	 * @param checkType描述，IP:校验不为空的IP
	 * @param checkType描述，字符:校验不为空的字符
	 * @param checkType描述，校空:校验不为空
	 * @param checkProperty,Object类型，被校验对象
	 * @param fieldError,String类型，显示错误信息的标签名
	 * @param checkName,String类型，校验项目的中文描述
	 * @param strLen,int类型，允许的字符串最大长度。字符串填写真实校验长度，其他类型填写0。
	 * @return void
	 */

	public Boolean submitCheck(ActionSupport thisAction, String checkType, Object checkProperty, String fieldError, String checkName, int strLen) {
		Boolean isOk = true;

		if (checkType.equals("整数") || checkType.equals("小数") || checkType.equals("固话") || checkType.equals("手机") || checkType.equals("邮箱") || checkType.equals("邮编")
				|| checkType.equals("身份证") || checkType.equals("IP")) {

			String reg = "";
			if (checkType.equals("整数")) {
				reg = "/^[1-9]\\d*$/";
			} else if (checkType.equals("小数")) {
				reg = "/^(-?\\d+)(\\.\\d+)?$/";
			} else if (checkType.equals("固话")) {
				reg = "/^([0-9]{3,4}-)?[0-9]{7,8}$/";
			} else if (checkType.equals("手机")) {
				// reg = "/(^(((13[0-9]{1})|15[0-3]{1}|15[5-9]{1}|18[0-3]{1}|18[5-9]{1}|147)+\\d{8})$)/";
				reg = "/^1[0-9]{10}$/";
			} else if (checkType.equals("邮箱")) {
				reg = "/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/";
			} else if (checkType.equals("邮编")) {
				reg = "/^\\d{6}$/";
			} else if (checkType.equals("身份证")) {
				reg = "/^[0-9]{17}[0-9A-Za-z]{1}$|^[0-9]{14}[0-9A-Za-z]{1}$/";
			} else if (checkType.equals("IP")) {
				reg = "/^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$/";
			}

			Pattern pattern = Pattern.compile(reg);

			if (checkProperty == null || checkProperty.toString().trim().equals("")) {
				thisAction.addFieldError(fieldError, checkName + "不能为空！");
				isOk = false;
			} else {
				String checkStr = checkProperty.toString().trim();

				if (pattern.matcher(checkStr).find()) {
					thisAction.addFieldError(fieldError, checkName + "必须是格式正确的" + checkType + "！");
					isOk = false;
				}
			}
		} else {
			if (checkType == "字符") {
				if (checkProperty == null || checkProperty.toString().trim().equals("")) {
					thisAction.addFieldError(fieldError, checkName + "不能为空！");
					isOk = false;
				} else {
					String checkStr = checkProperty.toString().trim();
					if (checkStr.length() > strLen) {
						thisAction.addFieldError(fieldError, checkName + "不能超过" + strLen + "字符！");
						isOk = false;
					}
				}
			}

			if (checkType == "校空") {
				if (checkProperty == null || checkProperty.toString().trim().equals("")) {
					thisAction.addFieldError(fieldError, checkName + "不能为空！");
					isOk = false;
				}
			}
		}

		return isOk;
	}

	/**
	 * <li>功能描述：正则验证是否为非0正整数类型。
	 * 
	 * @param aString
	 * @return boolean
	 */
	public static boolean isIntegerNumber(String aString) {
		if (aString == null || aString.length() < 1) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\d*[1-9]\\d*$");
		return pattern.matcher(aString).find();
	}

	/**
	 * <li>功能描述：正则验证是否为正整数类型,包括0。
	 * 
	 * @param aString
	 * @return boolean
	 */
	public static boolean isIntegerNumberIn0(String aString) {
		if (aString == null || aString.length() < 1) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\d*[0-9]\\d*$");
		return pattern.matcher(aString).find();
	}

	/**
	 * <li>功能描述：正则验证是否为数值型，可以是整数或浮点数,不包括0。
	 * 
	 * @param aString
	 * @return boolean
	 */
	public static boolean isNumber(String aString) {
		if (aString == null || aString.length() < 1) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\d*.[0-9]\\d*$");
		return pattern.matcher(aString).find();
	}

	/**
	 * <li>功能描述：正则验证是否为double类型。
	 * 
	 * @param aString
	 * @return boolean
	 */
	public static boolean isDoubleNumber(String aString) {
		if (aString == null || aString.length() < 1) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?\\d*(\\.\\d*)?$");
		return pattern.matcher(aString).find();
	}

	/**
	 * <li>功能描述：正则验证手机号。
	 * 
	 * @param num
	 * @return boolean 如果是手机号则返回true，否则返回false 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
	 */
	public static boolean isMobileNumber(String num) {
		Pattern p = Pattern.compile("^1[0-9]{10}$");
		Matcher m = p.matcher(num);
		return m.matches();
	}

	public static boolean isPhone(String num) {
		Pattern p = Pattern.compile("^([0-9]{3,4}-)?[0-9]{7,8}$");
		Matcher m = p.matcher(num);
		return m.matches();
	}

	/**
	 * <li>功能描述：正则验证日期格式。
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static boolean isDateFormat(String dateFormat) {
		Pattern p = Pattern.compile("^(\\d{2}|\\d{4})[-/](((0?[13578]|10|12)[-/](0?[1-9]|[12]\\d|3[01]))|((0?[469]|11)[-/](0?[1-9]|[12]\\d|30))|(0?2[-/](0?[1-9]|[12]\\d)))$");
		Matcher m = p.matcher(dateFormat);
		return m.matches();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ValidateUtil allidate = new ValidateUtil();
		// System.out.println(allidate.isMobileNumber("13232434244"));
		// System.out.println(allidate.isDoubleNumber("20"+""));
		// System.out.println(allidate.isDoubleNumber("20.0i"+""));
	}

}
