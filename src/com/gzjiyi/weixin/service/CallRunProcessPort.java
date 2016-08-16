package com.gzjiyi.weixin.service;

import java.rmi.RemoteException;
import java.util.*;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.namespace.QName;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CallRunProcessPort {
	public String runProcess(String docXml, String actionid, String processid, String docUnid, String nextNodeid, 
			String nextUserList, String remark) throws RemoteException{
		String result="";
		String endpoint = "http://120.24.53.197:8000/dfs_oa/WF_RunProcessPort?wsdl";
		try{
			Service service = new Service();
			Call call = (Call)service.createCall();
			call.setTimeout(new Integer(5000)); // 设定调用5秒不返回则超时
			call.setTargetEndpointAddress(endpoint);
			// webService 命名空间  ,webService 需调用的方法
			call.setOperationName(new QName("http://server.ws.linkey.cn/", "runProcess"));
			call.addParameter("docXml", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("actionid", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("processid", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("docUnid", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("nextNodeid", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("nextUserList", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("remark", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("userid", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("sysid", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("syspwd", XMLType.XSD_STRING,ParameterMode.IN);
			
			call.setReturnType(XMLType.XSD_STRING);
			call.setUseSOAPAction(true);
			//	        这个地方没设对就会出现Server was unable to read request的错误 
			call.setSOAPActionURI("http://server.ws.linkey.cn/runProcess");

			Object[] obj={docXml,actionid,processid,docUnid,nextNodeid,nextUserList,remark,"designer","bpm","pass"};
			result = (String)call.invoke(obj);
			
		}catch(ServiceException e){
	    	e.printStackTrace();
		}
	    return result;
	}
}
