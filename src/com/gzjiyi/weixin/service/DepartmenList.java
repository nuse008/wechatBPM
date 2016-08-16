package com.gzjiyi.weixin.service;

import java.io.*;
import java.net.*;
import java.util.*;

import com.gzjiyi.weixin.entity.DeptsList;
import com.gzjiyi.weixin.utils.WechatUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DepartmenList {
	public DepartmenList(){}
	public List<DeptsList> getDeptString(){
		String access = WechatUtil.getAccessToken();
		String getDeptUrl="https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+access+"&id=1";
		StringBuffer strBuf=new StringBuffer();
		List<DeptsList> DeptArr = new ArrayList<DeptsList>();
		try{
			URL url = new URL(getDeptUrl);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//转码。
			String line = null;
			//取得部门jason数据
			while ((line = reader.readLine()) != null)
				strBuf.append(line + " ");
			reader.close();
			
			//把jason数据序列化
			JSONObject jsobj = JSONObject.fromObject(strBuf.toString());
			String errcode = jsobj.getString("errcode");
			DeptsList dList;
			
			if(errcode.equals("0")){
				JSONArray depts=jsobj.getJSONArray("department");
				for (Object obj : depts) {
					dList = new DeptsList();
					JSONObject dept = JSONObject.fromObject(obj);
					dList.setId(dept.getString("id"));
					dList.setName(dept.getString("name"));
					dList.setOrder(dept.getString("order"));
					dList.setParentid(dept.getString("parentid"));
					DeptArr.add(dList);
				}
				//System.out.println(DeptArr.get(0).getName());
			}
		}catch(MalformedURLException e) {
			e.printStackTrace(); 
		}catch(IOException e){
			e.printStackTrace(); 
		}
		return DeptArr;
	}
}
