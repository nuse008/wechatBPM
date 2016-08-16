package com.gzjiyi.weixin.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Tools {
	public static Logger logger = Logger.getLogger(Tools.class);
	public static String[] split(String str)
	  {
	    return split(str, ",");
	  }

	  public static String[] split(String str, String key)
	  {
	    if (key.length() > 1) {
	      return StringUtils.splitByWholeSeparator(str, key);
	    }
	    return StringUtils.split(str, key);
	  }

	  public static String trim(String str)
	  {
	    return StringUtils.trim(str);
	  }
	  public static String getRandom(int length)
	  {
	    String allChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    StringBuffer sb = new StringBuffer();
	    Random random = new Random();
	    for (int i = 0; i < length; ++i) {
	      sb.append(allChar.charAt(random.nextInt(allChar.length())));
	    }
	    return sb.toString();
	  }

	  public static String[] fullTrim(String[] array)
	  {
	    ArrayList list = (ArrayList)Arrays.asList(array);
	    list.remove("");
	    return ((String[])list.toArray());
	  }

	  public static HashMap<String, String> jsonStr2Map(String jsonStr)
	  {
	    JSONObject jsonobj = JSON.parseObject(jsonStr);
	    HashMap map = new HashMap(jsonobj.size());
	    for (String fdName : jsonobj.keySet()) {
	      map.put(fdName, jsonobj.getString(fdName));
	    }
	    return map;
	  }

	  public static String encodeJson(String str)
	  {
	    str = str.replace("\\", "\\\\");
	    str = str.replace("\"", "\\\"");

	    str = str.replace("\n", "\\n");
	    str = str.replace("\r", "\\r");
	    return str;
	  }

	  public static String decode(String str)
	  {
	    try
	    {
	      return URLDecoder.decode(str, "utf-8");
	    } catch (Exception e) {
	    	logger.error("异常："+e.getMessage()); }
	    return "";
	  }

	  public static String encode(String str)
	  {
	    try
	    {
	      String code = URLEncoder.encode(str, "utf-8");
	      code = code.replace("+", "%20");
	      return code;
	    } catch (Exception e) {
	    	logger.error("异常："+e.getMessage());}
	    return str;
	  }
	  private static boolean isEmpty(String string)
	  {
	    return ((string == null) || (string.equals("null")) || (string.length() == 0));
	  }

	  public static boolean isBlank(String string) {
	    return ((isEmpty(string)) || (string.trim().length() == 0));
	  }

	  public static boolean isNotBlank(String string) {
	    return (!(isBlank(string)));
	  }

	  public static Boolean isString(String str)
	  {
	    if (isBlank(str)) return Boolean.valueOf(true);
	    Boolean bl = Boolean.valueOf(false);

	    Pattern pt = Pattern.compile("^[0-9a-zA-Z_.]+$");
	    Matcher mt = pt.matcher(str);
	    if (mt.matches()) {
	      bl = Boolean.valueOf(true);
	    }
	    return bl;
	  }
	  public static String getErrorMsgFromException(Exception e)
	  {
	    try
	    {
	      StringWriter sw = new StringWriter();
	      PrintWriter pw = new PrintWriter(sw);
	      e.printStackTrace(pw);
	      return sw.toString(); } catch (Exception e2) {
	    }
	    return "错误的异常对像";
	  }
	  public static String substring(String str, String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		String substr="";
		while (matcher.find()) {
		       //System.out.println(matcher.group(1));
		       substr=matcher.group(1);
		}
		return substr;
	  }
}
