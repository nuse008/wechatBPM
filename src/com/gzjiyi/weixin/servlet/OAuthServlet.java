package com.gzjiyi.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gzjiyi.weixin.utils.WechatUtil;

public class OAuthServlet extends HttpServlet {

	private static final long serialVersionUID = -8710610302758144013L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String redirect_uri;
		String pagekey=request.getQueryString();
		pagekey=pagekey.substring(pagekey.indexOf("=")+1, pagekey.length());
		//System.out.println(pagekey);
		try {
			if(pagekey.equals("one")){
				redirect_uri = WechatUtil.URLEncoder("http://" + request.getServerName()+request.getContextPath()+"/page/index.jsp");
			}else if(pagekey.equals("two")){
				redirect_uri = WechatUtil.URLEncoder("http://" + request.getServerName()+request.getContextPath()+"/page/process.jsp");
			}else if(pagekey.equals("three")){
				redirect_uri = WechatUtil.URLEncoder("http://" + request.getServerName()+request.getContextPath()+"/page/myworkpace.jsp");
			}else{
				redirect_uri = WechatUtil.URLEncoder("http://" + request.getServerName()+request.getContextPath()+"/index.jsp");
			}
			
			String GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=a123#wechat_redirect";
			String get_code_url = GET_CODE.replace("CORPID", WechatUtil.corpid).replace("REDIRECT_URI", redirect_uri);
			
			// Ìø×ªµ½
			response.sendRedirect(get_code_url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
