<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="com.gzjiyi.weixin.utils.runrule" %>
<%@ page import="com.gzjiyi.weixin.utils.Tools" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
<script src="weui/jscode/jquery.min.js"></script>
<script src="weui/jscode/form.js"></script>
<link rel="stylesheet" href="weui/css/weui.css"/>
<link rel="stylesheet" href="weui/css/style.css"/>
<link rel="stylesheet" href="weui/css/form.css"/>
<link rel="stylesheet" href="weui/css/example.css"/>
<title>启动流程</title>
<script type="text/javascript">
function showPopup(div_id,selid) {//弹出层 
	var div_obj = $("#" + div_id);
	//窗口宽度,高度
    var winWidth = $(window).width();
    var winHeight = $(window).height();
    div_obj.animate({ opacity: "show", left: 0, top: 0,width:winWidth,height:winHeight}, 300);
    $("#sel").attr("src","selectDept.jsp?selid="+selid);
}
function hideDiv(div_id) {
    $("#" + div_id).animate({opacity: "hide" }, 300);
}
function ShowRouterUser(){
	//显示路由用户选择
	var Nodeid="",x;
	var obj=$("[name='WF_NextNodeSelect']");
	if(obj.length==0) return;
	if(obj.length>1) //有多个路由选项
	{
		for(var i=0;i<obj.length;i++)
		{
			Nodeid=obj[i].value;
			var tmpArray=Nodeid.split(",");
			for(x=0;x<tmpArray.length;x++)
			{
				var UserObj=$("#UserTr_"+tmpArray[x]);
				if(UserObj.length>0)
				{
					if(obj[i].checked==true){UserObj.css("display","");}else{UserObj.css("display","none");}
				}
			}
		}
	}else{//只有一个路由选项
		obj[0].checked=true; //只有一个路由选项时默认选中
		Nodeid=obj.val();
		var tmpArray=Nodeid.split(",");
		for(x=0;x<tmpArray.length;x++)
		{
				var UserObj=$("#UserTr_"+tmpArray[x]);
				if(UserObj.length>0)
				{
					if(obj.is(':checked')){UserObj.css("display","");}else{UserObj.css("display","none");}
				}
		}
	}
}
</script>
</head>
<body ontouchstart>
<div class="container" id="container">
<div class="hd">
    <h1 class="page_title"></h1>
</div>
<div class="bd" id="formbody" >
	<%
	String processid=request.getParameter("Processid");
	String currentuser=request.getSession().getAttribute("Userid").toString(); //"nuse";
	String formbody="";
	if(Tools.isNotBlank(currentuser)){
		runrule run = new runrule();
		//获取到字段列表
		formbody=run.openProcessAndForm(processid, "", "", currentuser);
	}else{
		//用户还没登陆返回首页
		
	}
	%>
    <%=formbody %>
 </div>
	 <div id="popDiv"> 
		<div id="popTitle"> <!-- 标题div --> 
		<span class="title_left">用户选择</span>
		<span class="title_right"><a href="#" onClick="hideDiv('popDiv');">关闭</a> </span> 
		</div>
		<iframe id="sel" width="100%" height="100%"></iframe>
	</div>
 </div>
</body>

</html>