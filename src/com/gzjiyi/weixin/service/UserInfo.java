package com.gzjiyi.weixin.service;

import java.io.*;
import java.net.*;
import java.text.CollationKey;
import java.text.Collator;
import java.util.*;

import com.gzjiyi.weixin.entity.UserInfomation;
import com.gzjiyi.weixin.utils.WechatUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserInfo{
	private static final String FETCH_CHILD = "1"; //1/0���Ƿ�ݹ��ȡ�Ӳ�������ĳ�Ա
    private static final String STATUS = "0"; //0��ȡȫ����Ա��1��ȡ�ѹ�ע��Ա�б�2��ȡ���ó�Ա�б�4��ȡδ��ע��Ա�б�status�ɵ���,δ��д��Ĭ��Ϊ4
	
    public List<UserInfomation> getUserInfo(String department_id){
		
		String access = WechatUtil.getAccessToken();
		List<UserInfomation> userslist=new ArrayList<UserInfomation>();
		List<UserInfomation> newuserslist=new ArrayList<UserInfomation>();
		String getuserurl="https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token="+access+"&department_id="+department_id+"&fetch_child="+FETCH_CHILD+"&status="+STATUS;
		StringBuffer strBuf=new StringBuffer();
		
		try{
			URL url = new URL(getuserurl);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//ת�롣
			String line = null;
			//ȡ�ò���jason����
			while ((line = reader.readLine()) != null)
				strBuf.append(line + " ");
			reader.close();
			
			//��jason�������л�
			//System.out.println(strBuf.toString());
			JSONObject jsobj = JSONObject.fromObject(strBuf.toString());
			String errcode = jsobj.getString("errcode");
			UserInfomation userinfo;
			
			if(errcode.equals("0")){
				JSONArray users=jsobj.getJSONArray("userlist");
				String avatar;
				for (Object obj : users) {
					userinfo = new UserInfomation();
					JSONObject user = JSONObject.fromObject(obj);
					userinfo.setUserid(user.getString("userid"));
					userinfo.setName(user.getString("name"));
					userinfo.setDepartment(user.getString("department"));
					userinfo.setGender(user.getString("gender"));
					userinfo.setStatus(user.getString("status"));
					if(user.containsKey("email")){
						userinfo.setEmail(user.getString("email"));
					}
					if(user.containsKey("mobile")){
						userinfo.setMobile(user.getString("mobile"));
					}
					if(user.containsKey("position")){
						userinfo.setPosition(user.getString("position"));
					}
					if(user.containsKey("weixinid")){
						userinfo.setWeixinid(user.getString("weixinid"));
					}
					if(user.containsKey("avatar")){
						avatar=user.getString("avatar")+"64";
						userinfo.setAvatar(avatar);
					}else{
						avatar="image/default.jpg";
						userinfo.setAvatar(avatar);
					}
					if(user.containsKey("extattr")){
						userinfo.setExtattr(user.getString("extattr"));
					}
					userslist.add(userinfo);
				}
				//System.out.println(userslist.get(0).getName());
				newuserslist = getNewsList(userslist);
			}
		}catch(MalformedURLException e) {
			e.printStackTrace(); 
		}catch(IOException e){
			e.printStackTrace(); 
		}
		return newuserslist;
	}
    
    /**
	* compare
	* ʵ������
	* @param o1 Object
	* @param o2 Object
	* @return int
	*/
  	private Collator collator = Collator.getInstance();
  	
	public List<UserInfomation> getNewsList(List<UserInfomation> sortList) {
		// ����������ĸ����  
        Collections.sort(sortList, new Comparator<UserInfomation>() {  
            public int compare(UserInfomation user0, UserInfomation user1) {  
                String name0 = user0.getName();  
                String name1 = user1.getName();  
              //���ַ���ת��Ϊһϵ�б��أ����ǿ����Ա�����ʽ�� CollationKeys ��Ƚ�
        		CollationKey key1=collator.getCollationKey(name0.toString());//Ҫ�벻���ִ�Сд���бȽ���o1.toString().toLowerCase()
        		CollationKey key2=collator.getCollationKey(name1.toString());
        		return key1.compareTo(key2);//���صķֱ�Ϊ1,0,-1 �ֱ������ڣ����ڣ�С�ڡ�Ҫ�밴����ĸ��������Ļ� �Ӹ���-����
            }  
        }); 
        return sortList; 
	}
    
}
