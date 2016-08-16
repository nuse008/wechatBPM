//页面加载后执行
$(document).ready(function(){
  //$("#WF_TotalTime").next().children("input").click(function(){$("#WF_TotalTime").next().children("span").children("span").click();});
  //$("#WF_EndTime").next().children("input").click(function(){$("#WF_EndTime").next().children("span").children("span").click();});
	var newhtml="";
	//设置标题
	var titl=$("#TopFormTable b").text();
	$(".page_title").text(titl);
	//表单样式
	var trList = $(".linkeytable tbody").children("tr");
	for (var i=0;i<trList.length;i++) {
	    var tdArr = trList.eq(i).find("td");
	    var str="";
	    for (var x=0;x<tdArr.length;x++) {
	    	str=tdArr.eq(x).html();
	    	if(x%2==0){
	    		$("span:[type=\"remark\"]", tdArr.eq(x)).each(function(){
	    			var o=$(this).parent().parent();
	    			o.remove();
	    			return true;
	    		});
	    		if(str.indexOf("以下为流转记录", 0)>0){
	    			newhtml=newhtml+"";
	    		}else{
	    			newhtml=newhtml+"<div class=\"weui_cell\"><div class=\"weui_cell_hd\"><label class=\"weui_label\">"+str+"</label></div>";
	    		}
	    	}else{
	    		$("input:text", tdArr.eq(x)).each(function(){
	    			if($(this).attr("class")=="easyui-datebox"){
	    				str="<input name=\""+this.name+"\" class=\"weui_input\" id=\""+this.id+"\" type=\"date\"/>";
	    			}else if($(this).data('options')){
	    				if($(this).data('options').validType=="integer"){
	    					str="<input name=\""+this.name+"\" class=\"weui_input\" id=\""+this.id+"\" type=\"number\" pattern=\"[0-9]*\"/>";
	    				}else{
	    					str="<input name=\""+this.name+"\" class=\"weui_input\" id=\""+this.id+"\" type=\"text\"/>";
	    				}		
	    			}else{
	    				str="<input name=\""+this.name+"\" class=\"weui_input\" id=\""+this.id+"\" type=\"text\"/>";
	    			}
	    			if($(this).data('options')){
	    				if($(this).data('options').required) str=str+"<font color=\"red\">*</font>";
	    			}
	    		});
	    		$("input:radio", tdArr.eq(x)).each(function(){
	    			var items=document.getElementsByName(this.name); 
	    			str="<div class=\"button-holder\">";
	    			for(var i=0;i<items.length;i++){
	    				str=str+"<input name=\""+this.name+"\" id=\""+items[i].id+"\" class=\"regular-radio\" type=\"radio\" value=\""+items[i].value+"\" ><label for=\""+items[i].id+"\"></label>"+items[i].nextSibling.nodeValue;
	    			}
	    			str=str+"</div>";
	    			return true;
	    		});
	    		$("input:checkbox", tdArr.eq(x)).each(function(){
	    			var items=document.getElementsByName(this.name); 
	    			str="";
	    			for(var i=0;i<items.length;i++){
	    				str=str+"<input name=\""+items[i].name+"\" id=\""+items[i].id+"\" class=\"regular-checkbox\" type=\"checkbox\" value=\""+items[i].value+"\" ><label for=\""+items[i].id+"\"></label>"+items[i].nextSibling.nodeValue;
	    			}	
	    		});
	    		$("select", tdArr.eq(x)).each(function(){
	    			//$(this).attr("class","weui_select"); 
	    			str="<select name=\""+this.name+"\" class=\"weui_select\" id=\""+this.id+"\" type=\"text\">";
	    			$("option", this).each(function(){ //遍历全部option
	    		        var txt = $(this).text(); //获取option的内容
	    		        var val = $(this).val(); //获取option的内容
	    		        str=str+"<option value=\""+val+"\">"+txt+"</option>";
	    		    });
	    			str=str+"</select>";
	    		});
	    		$("textarea", tdArr.eq(x)).each(function(){
	    			str="<textarea name=\""+this.name+"\" class=\"weui_textarea\" id=\""+this.id+"\" rows=\"3\"></textarea><div class=\"weui_textarea_counter\">0/200</div>";
	    		});
	    		$("iframe", tdArr.eq(x)).each(function(){
	    			str="";
	    		});
	    		
	    		newhtml=newhtml+"<div class=\"weui_cell_bd weui_cell_primary\">"+str+"</div></div>";
	    	}
	    }
	}
	
	$("#formbody").html(newhtml);
	
	var appform = $("#ApprovalForm").children("div");
	var approvalhtml="<div class=\"weui_cells_tips\">"+appform.attr("title")+"</div>";
	trList = $("#ApprovalTable tbody").children("tr");
	for (var i=0;i<trList.length;i++) {
		var tdArr = trList.eq(i).find("td");
		var str="";
		var btn="";
		if(trList.eq(i).css("display")=="none"){
			for (var x=0;x<tdArr.length;x++) {
		    	if(x%2==0){
		    		approvalhtml=approvalhtml+"<div class=\"weui_cell\" style=\"display:none\" id=\""+trList.eq(i).attr("id")+"\" ><div class=\"weui_cell_hd\"><label class=\"weui_label\">请选择参与者</label></div>";
		    	}else{
		    		$("select:[multiple=\"multiple\"]", tdArr.eq(x)).each(function(){
		    			approvalhtml=approvalhtml+"<div id=\""+this.id+"_user\"></div><input type=\"hidden\" name=\""+this.id+"\" id=\""+this.id+"\"><a href=\"#\" onclick=\"showPopup('popDiv','"+this.id+"')\" ><img src=\"image/selectuser.png\" /></a></div>";
		    		});
		    	}
			}
		}else{
		    for (var x=0;x<tdArr.length;x++) {
		    	str=tdArr.eq(x).html();
		    	if(x%2==0){
		    		if(str!=""){
		    			approvalhtml=approvalhtml+"<div class=\"weui_cell\"><div class=\"weui_cell_hd\"><label class=\"weui_label\">"+str+"</label></div>";
		    		}
		    	}else{
		    		$("input:radio", tdArr.eq(x)).each(function(){
		    			var items=document.getElementsByName(this.name); 
		    			str="<div class=\"button-holder\">";
		    			for(var i=0;i<items.length;i++){
		    				str=str+"<input name=\""+this.name+"\" id=\""+items[i].id+"\" class=\"regular-radio\" type=\"radio\" onclick=\"ShowRouterUser(this)\" value=\""+items[i].value+"\" ><label for=\""+items[i].id+"\"></label>"+$(items[i]).next("label").text()+"<br/>";
		    			}
		    			str=str+"</div>";
		    			return true;
		    		});
		    		$("input:checkbox", tdArr.eq(x)).each(function(){
		    			var items=document.getElementsByName(this.name); 
		    			str="";
		    			for(var i=0;i<items.length;i++){
		    				str=str+"<input name=\""+items[i].name+"\" id=\""+items[i].id+"\" class=\"regular-checkbox\" type=\"checkbox\" value=\""+items[i].value+"\" ><label for=\""+items[i].id+"\"></label>"+$(this).eq(i).next("label").text()+"<br/>";
		    			}
		    			return true;
		    		});
		    		$("textarea", tdArr.eq(x)).each(function(){
		    			str="<textarea name=\""+this.name+"\" class=\"weui_textarea\" id=\""+this.id+"\" rows=\"3\"></textarea><div class=\"weui_textarea_counter\">0/200</div>";
		    		});
		    		
		    		$("a", tdArr.eq(x)).each(function(){
		    			str="";
		    			btn=btn+"<div class=\"weui_btn_area\"><a class=\"weui_btn weui_btn_primary\" id="+this.id+">"+this.text+"</a></div>";
		    		});
		    		approvalhtml=approvalhtml+"<div class=\"weui_cell_bd weui_cell_primary\">"+str+"</div></div>"+btn;
		    	}
		    }
		}
	}
	$("#ApprovalForm").html(approvalhtml);
	$("#BottomToolbar").html("");
});