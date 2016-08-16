<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gzjiyi.weixin.service.DepartmenList" %>
<%@ page import="com.gzjiyi.weixin.entity.DeptsList" %>
<%@ page import="com.gzjiyi.weixin.service.UserInfo" %>
<%@ page import="com.gzjiyi.weixin.entity.UserInfomation" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
<title>选择部门</title>
<link rel="stylesheet" type="text/css" href="KickStart/css/kickstart.css" />
<link rel="stylesheet" href="weui/css/style.css"/>
<script src="weui/jscode/jquery.min.js"></script>
<style type="text/css">
.go{
display: block;
font-size:18px;
float:right;
}
</style>

</head>
<body>
<% 	DepartmenList dept=new DepartmenList();
		List<DeptsList> deptlists=dept.getDeptString();%>
	
<div class="wxapi_container">
    <div class="wxapi_index_container">
      <ul class="label_box lbox_close wxapi_index_list">
		<%for(int i=0; i<deptlists.size(); i++){%>
		<li class="label_item wxapi_index_item">
			<a class="label_inner" href="selectUser.jsp?selid=<%=request.getParameter("selid") %>&id=<%=deptlists.get(i).getId() %>">
			<span class="go"><%=deptlists.get(i).getId() %><i class="fa fa-chevron-right"></i></span>
			<%=deptlists.get(i).getName() %></a>
		</li>
		<%}%>
	</ul>
    </div>
</div>
</body>
</html>