<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="com.gzjiyi.weixin.utils.WechatUtil" %>
<%@ page import="com.gzjiyi.weixin.utils.Tools" %>
<%@ page import="java.util.Map"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
	<title>工作台</title>
	<link rel="stylesheet" href="weui/css/weui.css"/>
	<link rel="stylesheet" href="weui/css/example.css"/>
	<script src="weui/jscode/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="KickStart/css/kickstart.css" />
</head>
<body ontouchstart>
<div class="container" id="container">
<%
if(Tools.isNotBlank(request.getParameter("code"))){
	String userid=WechatUtil.GetUserId(request.getParameter("code"));
	Map<String,String> map=WechatUtil.GetUserInfo(userid);
	request.getSession().setAttribute("Userid", map.get("userid"));
	request.getSession().setAttribute("DeptName", map.get("department"));
	request.getSession().setAttribute("CnName", map.get("name"));
	request.getSession().setAttribute("JobTitle", map.get("position"));
}
String currentuser=request.getSession().getAttribute("Userid").toString();
if(Tools.isBlank(currentuser)){ return ;}
%>
	<div class="hd">
		<h1 class="page_title">我的工作台</h1>
	</div>
	<div class="weui_cells">
        <div class="weui_cell">
            <div class="weui_cell_hd"><i class="fa fa-envelope fa-1x" style="color:#FF8040;"></i></div>
            <a href="todo.jsp"><div class="weui_cell_bd weui_cell_primary">
                <p>待我处理的</p>
            </div>
            <div class="weui_cell_ft"></div></a>
        </div>
        <div class="weui_cell">
            <div class="weui_cell_hd"><i class="fa fa-clipboard fa-1x" style="color:#42BDB4;"></i></div>
			<a href="done.jsp"><div class="weui_cell_bd weui_cell_primary">
                <p>我已处理的</p>
            </div>
            <div class="weui_cell_ft"></div></a>
        </div>
        <div class="weui_cell">
            <div class="weui_cell_hd"><i class="fa fa-paint-brush fa-1x" style="color:#2894ff;"></i></div>
			<a href="doing.jsp"><div class="weui_cell_bd weui_cell_primary">
                <p>所有在处理中的</p>
            </div>
            <div class="weui_cell_ft"></div></a>
        </div>
        <div class="weui_cell">
            <div class="weui_cell_hd"><i class="fa fa-file-text fa-1x" style="color:#FF0080;"></i></div>
			<a href="read.jsp"><div class="weui_cell_bd weui_cell_primary">
                <p>待我阅读的</p>
            </div>
            <div class="weui_cell_ft"></div></a>
        </div>
        <div class="weui_cell">
            <div class="weui_cell_hd"><i class="fa fa-cube fa-1x" style="color:#42BDB4;"></i></div>
			<a href="end.jsp"><div class="weui_cell_bd weui_cell_primary">
                <p>办理结束的</p>
            </div>
            <div class="weui_cell_ft"></div></a>
        </div>
        <div class="weui_cell">
            <div class="weui_cell_hd"><i class="fa fa-folder-open fa-1x" style="color:#FFA953;"></i></div>
			<a href="favorites.jsp"><div class="weui_cell_bd weui_cell_primary">
                <p>我的收藏夹</p>
            </div>
            <div class="weui_cell_ft"></div></a>
        </div>
        <div class="weui_cell">
            <div class="weui_cell_hd"><i class="fa fa-paper-plane fa-1x" style="color:#2894ff;"></i></div>
			<a href="drafts.jsp"><div class="weui_cell_bd weui_cell_primary">
                <p>我的草稿箱</p>
            </div>
            <div class="weui_cell_ft"></div></a>
        </div>
    </div>
</div>
</body>
</html>