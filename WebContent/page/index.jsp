<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="com.gzjiyi.weixin.utils.WechatUtil" %>
<%@ page import="com.gzjiyi.weixin.utils.Tools" %>
<%@ page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
<title>应用中心</title>
<link rel="stylesheet" href="weui/css/weui.css"/>
<link rel="stylesheet" href="weui/css/example.css"/>
</head>
<body ontouchstart>
<%
String userid="";
if(Tools.isNotBlank(request.getParameter("code"))){
	userid=WechatUtil.GetUserId(request.getParameter("code"));
	Map<String,String> map=WechatUtil.GetUserInfo(userid);
	request.getSession().setAttribute("Userid", map.get("userid"));
	request.getSession().setAttribute("DeptName", map.get("department"));
	request.getSession().setAttribute("CnName", map.get("name"));
	request.getSession().setAttribute("JobTitle", map.get("position"));
}
%>
<div class="container" id="container">
	<div class="hd">
		<h1 class="page_title">应用中心</h1>
	</div>
	<div class="bd">
		<div class="weui_grids">
			<a href="myworkpace.jsp" class="weui_grid">
	            <div class="weui_grid_icon">
	                <img src="image/2016-03-21_112655.png"/>
	            </div>
	            <p class="weui_grid_label">
	            	审批
	            </p>
	        </a>
	        <a href="process.jsp" class="weui_grid">
	            <div class="weui_grid_icon">
	                <img src="image/2016-03-21_112857.png"/>
	            </div>
	            <p class="weui_grid_label">
	               	 流程
	            </p>
	        </a>
	        <a href="in001/indexpage.jsp" class="weui_grid">
	            <div class="weui_grid_icon">
	                <img src="image/2016-03-21_112622.png"/>
	            </div>
	            <p class="weui_grid_label">
	                	公告
	            </p>
	        </a>
	        <a href="#/button" class="weui_grid">
	            <div class="weui_grid_icon">
	                <img src="image/2016-03-21_112921.png"/>
	            </div>
	            <p class="weui_grid_label">
	               	 会议管理
	            </p>
	        </a>
	        
	        
	        <a href="#/dialog" class="weui_grid">
	            <div class="weui_grid_icon">
	                <img src="image/2016-03-21_112802.png"/>
	            </div>
	            <p class="weui_grid_label">
	                	请假管理
	            </p>
	        </a>
	        <a href="#/progress" class="weui_grid">
	            <div class="weui_grid_icon">
	                <img src="image/2016-03-21_112717.png"/>
	            </div>
	            <p class="weui_grid_label">
	                	报销管理
	            </p>
	        </a>
	        
	    </div>
	</div>
</div>
</body>
</html>