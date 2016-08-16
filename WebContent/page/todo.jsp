<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gzjiyi.weixin.utils.WechatUtil" %>
<%@ page import="com.gzjiyi.weixin.utils.runrule" %>
<%@ page import="com.gzjiyi.weixin.utils.Tools" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
	<title>待我处理的</title>
	<link rel="stylesheet" href="weui/css/weui.css"/>
	<link rel="stylesheet" href="weui/css/example.css"/>
	<script src="weui/jscode/jquery.min.js"></script>
	<style>
		ul.alt span{
			display:block;
			font-size:40px;
			margin:20px;
			color:#ccc;
			padding-left:50px;
		}
	</style>
</head>
<body>
<div>
	<ul class="alt">
		
		<% runrule run = new runrule();
		if(request.getSession().getAttribute("Userid")!=null){
			String currentuser=request.getSession().getAttribute("Userid").toString();
			if(Tools.isBlank(currentuser)){ response.sendRedirect("error.html"); }
			JSONArray rows = run.getToDoList(currentuser);
			for (int i = 0; i < rows.length(); i++) {
				JSONObject jo = rows.getJSONObject(i);
		%>
		<li><i class="fa fa-folder-open" style="color:#FFA953;margin-top:20px;"></i>
		<%=jo.getString("Subject") %>
		<br/>
		<span>流程名称:<%=jo.getString("Subject") %> 申请人:<%=jo.getString("Add_Name_CN") %></span>
		<span>到达时间:<%=jo.getString("Subject") %> 已到达:<font style="color: red;"><%=jo.getString("Subject") %></font></span>
		</li>
		<%}
		} else{ response.sendRedirect("error.html"); }%>
	</ul>
</div>
</body>
</html>