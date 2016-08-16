<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gzjiyi.weixin.service.UserInfo" %>
<%@ page import="com.gzjiyi.weixin.entity.UserInfomation" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
<title>选择人员</title>
<link rel="stylesheet" type="text/css" href="KickStart/css/kickstart.css" />
<link rel="stylesheet" href="weui/css/style.css"/>
<script src="weui/jscode/jquery.min.js"></script>
<script src="weui/jscode/sharefunction.js"></script>
<style>
ul img{
	display: block;
	float:left;
	padding-top:2px;
	width:50px;
	height:45px;
}
ul li{
	line-height: 2;
	font-weight: 500;
	font-size: 1.05em;
}
</style>
<script type="text/javascript">
function getUserid(strid, strvalue, strimg){
	var selid=getUrlParam("selid");
	var puser=window.parent.document.getElementById(selid+"_user").innerHTML;
	var usersid=window.parent.document.getElementById(selid).value;
	if(puser=="" || usersid==""){
		window.parent.document.getElementById(selid+"_user").innerHTML="<img src=\""+strimg+"\" />"+strvalue;
		window.parent.document.getElementById(selid).value=strid;
	}else{
		window.parent.document.getElementById(selid+"_user").innerHTML=puser+","+"<img src=\""+strimg+"\" />"+strvalue;
		window.parent.document.getElementById(selid).value=usersid+","+strid;
	}
	window.parent.hideDiv('popDiv');
}
</script>
</head>
<body>
	<% 	UserInfo user=new UserInfo();
		List<UserInfomation> userlist=user.getUserInfo(request.getParameter("id"));%>
<div class="wxapi_container">
    <div class="wxapi_index_container">
      	<ul class="label_box lbox_close wxapi_index_list">
			<%for(int i=0; i<userlist.size(); i++){%>
			<li class="label_item wxapi_index_item"><img src="<%=userlist.get(i).getAvatar() %>" />
				<a class="label_inner" href="#" onclick="getUserid('<%=userlist.get(i).getUserid() %>','<%=userlist.get(i).getName() %>','<%=userlist.get(i).getAvatar() %>');">
				<span style="margin-left:10px;"><%=userlist.get(i).getName() %></span></a>
			</li>
			<%}%>
		</ul>
	</div>
</div>
</body>
</html>