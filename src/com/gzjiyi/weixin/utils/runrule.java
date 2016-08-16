package com.gzjiyi.weixin.utils;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.gzjiyi.weixin.service.CallRunRulePort;

public class runrule {
	public static Logger logger = Logger.getLogger(runrule.class);
	public JSONArray getProcessList(String currentuser){
		CallRunRulePort callrule = new CallRunRulePort();
		String str;
		JSONArray jsonArray = new JSONArray();
		try {
			str = callrule.runRule("R_S017_B013", "{\"Folderid\":\"\"}",currentuser);
			JSONObject demoJson = new JSONObject(str);
			//System.out.println(str);
			jsonArray =demoJson.getJSONArray("rows");
			
		} catch (JSONException | RemoteException e) {
			logger.error("getProcessList异常："+e.getMessage());
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	public String openProcessAndForm(String processid,String docunid,String docXml,String currentuser){
		String formbody="";
		try {
			CallRunRulePort callrule = new CallRunRulePort();
			String str = callrule.runRule("R_S017_B023", "{\"Processid\":\""+processid+"\",\"DocUnid\":\""+docunid+"\",\"DocXml\":\""+docXml+"\"}",currentuser);  
			String script=str.substring(str.lastIndexOf("<script>"), str.lastIndexOf("</script>"));
			formbody=str.substring(str.indexOf("<form action='' method='post' name='linkeyform' id='linkeyform' >"), str.indexOf("</form>"));
		} catch (Exception e) {
			logger.error("openProcessAndForm异常："+e.getMessage());
			e.printStackTrace();
		}
		return formbody;	
	}
	
	//根据流程ID获取流程表单的信息
	public JSONObject getForm(String processid,String currentuser){
		CallRunRulePort callrule = new CallRunRulePort();
		String str;
		JSONArray jsonArray = new JSONArray(); 
		JSONObject objJson = new JSONObject();
		try {
			str = callrule.runRule("R_S017_B037", "{\"Processid\":\""+processid+"\"}",currentuser);  
			objJson = new JSONObject(str);
			jsonArray =objJson.getJSONArray("form");
			
			//获取栏位配置信息
			objJson = new JSONObject(jsonArray.toString().substring(1, jsonArray.toString().length()-1));
			
		} catch (JSONException | RemoteException e) {
			logger.error("getForm异常："+e.getMessage());
			e.printStackTrace();
		}
		
		return objJson;
	}
	
	//根据流程userid获取用户的信息
	public JSONObject getUserInfo(String userid,String currentuser){
		CallRunRulePort callrule = new CallRunRulePort();
		String str;
		JSONObject objJson = new JSONObject();
		try {
			str = callrule.runRule("R_S017_B018", "{\"UserId\":\""+userid+"\"}",currentuser);
			//System.out.println(str);
			objJson = new JSONObject(str);
			
		} catch (JSONException | RemoteException e) {
			logger.error("getUserInfo异常："+e.getMessage());
			e.printStackTrace();
		}
		
		return objJson;
	}
	
	public JSONArray getToDoList(String currentuser){
		CallRunRulePort callrule= new CallRunRulePort();
		JSONArray rows=new JSONArray();
		try{
			String result=callrule.runRule("R_S017_B002", "",currentuser);
			//System.out.println(result);
			JSONObject jsobj = new JSONObject(result);
			int total = Integer.parseInt(jsobj.getString("total"));
			rows = jsobj.getJSONArray("rows");
			
		}catch(Exception e){
			logger.error("getToDoList异常："+e.getMessage());
			e.printStackTrace();
		}
		return rows;
	}
}
