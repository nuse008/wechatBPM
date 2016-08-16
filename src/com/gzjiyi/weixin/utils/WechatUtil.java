package com.gzjiyi.weixin.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.gzjiyi.weixin.dao.ExcutSQL;

public class WechatUtil {
	public static Logger logger = Logger.getLogger(WechatUtil.class);
	public static String corpid="wx4aeb0a3995afdbe6";
	public static String secret="fjnR_G6XIkQfQs04Tr4nEyoFWtRLKYHBHwEgaXvI9sDAzrYMUFvRLOf1rUCUkptH";
	private static String getAccess_token(){  // ���ACCESS_TOKEN
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpid + "&corpsecret="+secret;
        String accessToken = null;
       try {
              URL urlGet = new URL(url);
              HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();    
              http.setRequestMethod("GET");      //������get��ʽ��ʽ����    
              http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
              http.setDoOutput(true);        
              http.setDoInput(true);
              http.connect();
              InputStream is =http.getInputStream();
              int size =is.available();
              byte[] jsonBytes =new byte[size];
              is.read(jsonBytes);
              String message=new String(jsonBytes,"UTF-8");
              JSONObject demoJson = new JSONObject(message);
              accessToken = demoJson.getString("access_token");
              //System.out.println(message);
       } catch (Exception e) {
    	   logger.error("�쳣��"+e.getMessage());
    	   e.printStackTrace();
       }
         return accessToken;
	}
	
	//΢�ŵ���ҵToken��ȡÿ������2000�Σ����԰ѻ�ȡ����token���浽���ݿ��У�ÿСʱ����һ��
	public static String getAccessToken(){
		String sql="";
		String accesstoken=""; //token�ַ���
		String savedatetime=""; //token����ʱ��
		try {
			sql="select * from token where type='qy'";
			ExcutSQL exc = new ExcutSQL();
			Map<String,String> map = exc.SelectToken(sql);
			
			Iterator it=map.entrySet().iterator();
			while(it.hasNext()){    
		        Map.Entry entry = (Map.Entry)it.next();
		        if(entry.getKey().toString().equals("accesstoken")){
		        	accesstoken=entry.getValue().toString();
		        }
		        if(entry.getKey().toString().equals("savedatetime")){
		        	savedatetime=entry.getValue().toString();
		        }		        
			}
			
			Date now = new Date();//��ǰʱ��
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowtime = dateFormat.format(now);
			if(accesstoken.length()==0) { //�����ݿ�Ϊ�յ�ʱ��
				accesstoken =  WechatUtil.getAccess_token();
				sql="INSERT INTO [dbo].[token]([accesstoken],[savedatetime],[type])VALUES('"+accesstoken+"','"+nowtime+"','qy')";
				exc.execSQL(sql);
				logger.info("��һ�λ�ȡ΢����ҵ��accesstoken");
			}else if(savedatetime.length()>0 && accesstoken.length()>0){ //�������ݵ�ʱ��
		        Date d = dateFormat.parse(savedatetime); //����tokenʱ��
		        long diff = now.getTime() - d.getTime();
		        long hours = diff / (1000 * 60 * 60); //�뵱ǰʱ���ʱ���
		        if(hours>1){
		        	accesstoken =  WechatUtil.getAccess_token();
		        	sql="Update token Set accesstoken='"+accesstoken+"', savedatetime='"+nowtime+"' where type='qy'";   	
					exc.execSQL(sql);
					logger.info("��ҵ�ŵ�accesstoken��һСʱ�����»�ȡ");
		        }else{
		        	logger.info("��ҵ�ŵ�accesstoken��һСʱ�ڻ���Ч���������»�ȡ");
		        }
			}else{

			}
			
		} catch (Exception e) {
			logger.error("�쳣��"+e.getMessage());
			//e.printStackTrace();
		}
		return accesstoken;
	}
	public static String GetTicket() throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, MalformedURLException, ProtocolException, UnsupportedEncodingException, IOException, JSONException
	{
	    String access_token = getAccessToken();
	    String url1 = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
	    String[] results = httpPost(url1, "","ticket");
	    String ticket = results[0];
	
	    return ticket;
	}
	
	/**
	 * ����΢�Žӿڣ����ؾ������λ
	 * */
	public static String[] httpPost(String action, String postStr,String getVaues)
			throws NoSuchAlgorithmException, NoSuchProviderException,
			KeyManagementException, MalformedURLException, IOException,
			ProtocolException, UnsupportedEncodingException, JSONException {
  	  
		// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// ������SSLContext�����еõ�SSLSocketFactory����
		SSLSocketFactory ssf = sslContext.getSocketFactory();

		URL url = new URL(action); // ���ַ���ת��ΪURL�����ַ
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();// ������
		connection.setSSLSocketFactory(ssf);
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		// ��������ʽ��GET/POST��
		connection.setRequestMethod("POST");
		connection.connect();
		// ���ӻỰ
		OutputStream os = connection.getOutputStream();
		os.write(postStr.getBytes("UTF-8"));// �������
		os.flush();
		os.close();
		// ��ȡ������
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));//����Ҫ����utf-8�������ȡ������������
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {// ѭ����ȡ��
			sb.append(line);
		}
		br.close();// �ر���
		connection.disconnect();// �Ͽ�����
		System.out.println(sb.toString());
		JSONObject demoJson = new JSONObject(sb.toString());
		String[] getVauez = getVaues.split(",");
		String[] result = new String[getVauez.length];
		for (int i=0;i<getVauez.length;i++) {
			String val = demoJson.getString(getVauez[i]);
			result[i]= val;
		}
        return result;
	}
	
	public static String getHtmlStr(String url,String charSet) throws ClientProtocolException, IOException {
		// ����HttpClientʵ��     
        HttpClient httpclient = new DefaultHttpClient();  
        // ����Get����ʵ��     
        // ģ�������
        HttpGet httpgets = new HttpGet(url);   
        httpgets.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
        httpgets.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpgets.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        httpgets.addHeader("Content-Type", "text/html; charset=UTF-8");
        httpgets.addHeader("Cookie", CookiePolicy.BROWSER_COMPATIBILITY);
        
        HttpResponse response = httpclient.execute(httpgets);    
        HttpEntity entity = response.getEntity();    
        String str ="";
        if (entity != null) {    
            InputStream instreams = entity.getContent();    
            str = convertStreamToString(instreams,charSet);    
            httpgets.abort();    
        } 
        return str;
	}
	
	private static String convertStreamToString(InputStream is,String charSet) throws UnsupportedEncodingException {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,charSet));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {  
        	logger.error("����ת��Ϊ�ַ�������"+e.getMessage());
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {
            	logger.error("����ת��Ϊ�ַ�������"+e.getMessage());
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }
	
	public static String URLEncoder(String url) throws Exception{
		return java.net.URLEncoder.encode(url, "utf-8");
	}
	
	/**
	 * ����code��ȡ��ԱUserId
	 * @param code
	 * @return
	 */
	public static String GetUserId(String code){
		String get_userid_url="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
		get_userid_url=get_userid_url.replace("ACCESS_TOKEN", WechatUtil.getAccessToken()).replace("CODE", code);
		String UserId="";
		try {
			String[] results = WechatUtil.httpPost(get_userid_url, "","UserId");
			UserId = results[0];
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException | JSONException e) {
			// TODO Auto-generated catch block
			logger.error("������ҵcode��ȡuserid����"+e.getMessage());
			e.printStackTrace();
		}
		
		return UserId;
	}
	
	public static Map<String,String> GetUserInfo(String userid){
		String get_user_url="https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
		get_user_url=get_user_url.replace("ACCESS_TOKEN", WechatUtil.getAccessToken()).replace("USERID", userid);
		Map<String,String> map = new HashMap<String,String>();
		try {
			String[] results = WechatUtil.httpPost(get_user_url, "","userid,name,department,position,mobile,weixinid,avatar");
			map.put("userid", results[0]);
			map.put("name", results[1]);
			String deptid=results[2].substring(1, results[2].length()-1);
			map.put("department", GetDeptInfo(deptid));
			map.put("position", results[3]);
			map.put("mobile", results[4]);
			map.put("weixinid", results[5]);
			map.put("avatar", results[6]);
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException | JSONException e) {
			logger.error("����userid��ȡ�û���Ϣ����"+e.getMessage());
		}
		
		return map;
	}
	
	/**
	 * 
	 * ��ȡ������Ϣ
	 */
	public static String GetDeptInfo(String deptid){
		String get_dept_url="https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID";
		get_dept_url=get_dept_url.replace("ACCESS_TOKEN", WechatUtil.getAccessToken()).replace("ID", deptid);
		String department="";
		try {
			String[] results = WechatUtil.httpPost(get_dept_url, "","department");
			department=results[0];
			JSONObject demoJson = new JSONObject(department.substring(1, department.length()-1));
			department=demoJson.getString("name");
			System.out.println(department);
			
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException | JSONException e) {
			logger.error("����id��ȡ������Ϣ����"+e.getMessage());
		}
		
		return department;
	}
}
