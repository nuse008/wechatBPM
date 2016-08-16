<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ page import="com.gzjiyi.weixin.utils.WechatUtil" %>
<%@ page import="com.gzjiyi.weixin.utils.runrule" %>
<%@ page import="com.gzjiyi.weixin.utils.Tools" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.Map"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
<link rel="stylesheet" href="weui/css/weui.css"/>
<link rel="stylesheet" href="weui/css/example.css"/>
<script src="weui/jscode/jquery.min.js"></script>
<title>流程中心</title>
<script>
function gotoURL(Processid){
	window.location = "processform.jsp?Processid="+Processid;
}
function selectTab(id){
	if(id=="rc"){
		$('#rc').show();
		$('#xz').hide();
		$('#yw').hide();
		$('#link_rc').addClass("weui_bar_item_on");
		$('#link_xz').removeClass("weui_bar_item_on");
		$('#link_yw').removeClass("weui_bar_item_on");
	}else if(id=="xz"){
		$('#rc').hide();
		$('#xz').show();
		$('#yw').hide();
		$('#link_rc').removeClass("weui_bar_item_on");
		$('#link_xz').addClass("weui_bar_item_on");
		$('#link_yw').removeClass("weui_bar_item_on");
	}else if(id=="yw"){
		$('#rc').hide();
		$('#xz').hide();
		$('#yw').show();
		$('#link_rc').removeClass("weui_bar_item_on");
		$('#link_xz').removeClass("weui_bar_item_on");
		$('#link_yw').addClass("weui_bar_item_on");
	}
}
</script>
</head>
<body ontouchstart>
<div class="container" id="container">
	<div class="bd" style="height: 100%;">
		<div class="weui_tab">
			<div class="weui_tab_bd">
				<div class="weui_panel weui_panel_access">
				<div id="rc">
				<div class="weui_panel_hd">公文流程</div>
			        <div class="weui_panel_bd">
					<%
					runrule run = new runrule();
					if(Tools.isNotBlank(request.getParameter("code"))){
						String userid=WechatUtil.GetUserId(request.getParameter("code"));
						Map<String,String> map=WechatUtil.GetUserInfo(userid);
						request.getSession().setAttribute("Userid", map.get("userid"));
						request.getSession().setAttribute("DeptName", map.get("department"));
						request.getSession().setAttribute("CnName", map.get("name"));
						request.getSession().setAttribute("JobTitle", map.get("position"));
					}
					if(request.getSession().getAttribute("Userid")!=null){
						String currentuser=request.getSession().getAttribute("Userid").toString();
						if(Tools.isBlank(currentuser)){ response.sendRedirect("error.html"); }
						JSONArray jsonArray = run.getProcessList(currentuser);
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jo = jsonArray.getJSONObject(i);
							if(jo.getString("Folderid").equals("001005")){
					%>
						
						<a href="javascript:gotoURL('<%=jo.getString("Processid") %>');"  class="weui_media_box weui_media_appmsg">
			                <div class="weui_media_hd">
			                    <img class="weui_media_appmsg_thumb" src="image/picons/h0<%=String.valueOf(i+1) %>.png" />
			                </div>
			                <div class="weui_media_bd">
			                    <h4 class="weui_media_title"><%=jo.getString("ProcessName") %></h4>
			                    <p class="weui_media_desc">适用于<%=jo.getString("ProcessName") %>审批。 <br/>提供者：集易软件</p>
			                </div>
			            </a>
						<% }
						}%>
			        </div>
			    </div>
			    <div id="xz" style="display:none;">
			    	<div class="weui_panel_hd">行政流程</div>
			        <div class="weui_panel_bd">
			        <% for (int i = 0; i < jsonArray.length(); i++) {
			        	JSONObject jo = jsonArray.getJSONObject(i);
						if(jo.getString("Folderid").equals("001009")){
					%>
						
						<a href="javascript:gotoURL('<%=jo.getString("Processid") %>');"  class="weui_media_box weui_media_appmsg">
			                <div class="weui_media_hd">
			                    <img class="weui_media_appmsg_thumb" src="image/picons/h0<%=String.valueOf(i+1) %>.png" />
			                </div>
			                <div class="weui_media_bd">
			                    <h4 class="weui_media_title"><%=jo.getString("ProcessName") %></h4>
			                    <p class="weui_media_desc">适用于<%=jo.getString("ProcessName") %>审批。 <br/>提供者：集易软件</p>
			                </div>
			            </a>
						<% }
						}%>
			        </div>
			    </div>
			    <div id="yw" style="display:none;">
			    	<div class="weui_panel_hd">业务流程</div>
			        <div class="weui_panel_bd">
			        <% for (int i = 0; i < jsonArray.length(); i++) {
			        	JSONObject jo = jsonArray.getJSONObject(i);
						if(jo.getString("Folderid").equals("001008")){
					%>
						
						<a href="javascript:gotoURL('<%=jo.getString("Processid") %>');"  class="weui_media_box weui_media_appmsg">
			                <div class="weui_media_hd">
			                    <img class="weui_media_appmsg_thumb" src="image/picons/h0<%=String.valueOf(i+1) %>.png" />
			                </div>
			                <div class="weui_media_bd">
			                    <h4 class="weui_media_title"><%=jo.getString("ProcessName") %></h4>
			                    <p class="weui_media_desc">适用于<%=jo.getString("ProcessName") %>审批。 <br/>提供者：集易软件</p>
			                </div>
			            </a>
						<%}
						}
			        } else{ response.sendRedirect("error.html"); }
			        %>
			        </div>
			    </div>
		    	</div>
		    </div>
		    <div class="weui_tabbar">
		        <a href="javascript:selectTab('rc');" class="weui_tabbar_item weui_bar_item_on" id="link_rc">
		            <div class="weui_tabbar_icon">
		                <img src="weui/images/icon_nav_button.png" alt="" />
		            </div>
		            <p class="weui_tabbar_label">公文流程</p>
		        </a>
		        <a href="javascript:selectTab('xz');" class="weui_tabbar_item" id="link_xz">
		            <div class="weui_tabbar_icon">
		                <img src="weui/images/icon_nav_msg.png" alt="" />
		            </div>
		            <p class="weui_tabbar_label">行政流程</p>
		        </a>
		        <a href="javascript:selectTab('yw');" class="weui_tabbar_item" id="link_yw">
		            <div class="weui_tabbar_icon">
		                <img src="weui/images/icon_nav_article.png" alt="" />
		            </div>
		            <p class="weui_tabbar_label">业务流程</p>
		        </a>
		    </div>
	        
	    </div>
	</div>
</div>
</body>
</html>