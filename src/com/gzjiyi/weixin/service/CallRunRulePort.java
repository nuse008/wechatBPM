package com.gzjiyi.weixin.service;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;

import com.gzjiyi.weixin.utils.runrule;

public class CallRunRulePort {
	public static Logger logger = Logger.getLogger(CallRunRulePort.class);
	public String runRule(String rulenum, String params,String userid) throws RemoteException{
		String result="";
		String endpoint = "http://bpm.gzjiyi.com.cn/bpm2015/WF_RunRulePort?wsdl";
		try{
			Service service = new Service();
			Call call = (Call)service.createCall();
			call.setTimeout(new Integer(5000)); // 设定调用5秒不返回则超时
			call.setTargetEndpointAddress(endpoint);
			// webService 命名空间  ,webService 需调用的方法
			call.setOperationName(new QName("http://server.ws.linkey.cn/", "runRule"));
			call.addParameter("rulenum", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("params", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("userid", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("sysid", XMLType.XSD_STRING,ParameterMode.IN);
			call.addParameter("syspwd", XMLType.XSD_STRING,ParameterMode.IN);
			
			call.setReturnType(XMLType.XSD_STRING);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI("http://server.ws.linkey.cn/runRule");

			Object[] obj={rulenum,params,userid,"bpm","pass"};
			result = (String)call.invoke(obj);
			
		}catch(ServiceException e){
			logger.error("runRule异常："+e.getMessage());
	    	//e.printStackTrace();
		}
	    return result;
	}
}
