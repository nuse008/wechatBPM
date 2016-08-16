package com.gzjiyi.weixin.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.gzjiyi.weixin.utils.WechatUtil;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * ������������
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;
	private String token = "gzjiyi";
	private String encodingAESKey = "s8vFF4f6AWay3uAdJh79WD6imaam4BV6Kl4eL4UzgfM";
	private String corpId = "wx4aeb0a3995afdbe6"; //�����ҵ��ID

	/**
	 * ȷ����������΢�ŷ�����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("comein");
		// ΢�ż���ǩ��
		String sVerifyMsgSig = request.getParameter("msg_signature");
		System.out.println("sVerifyMsgSig:"+sVerifyMsgSig);
		// ʱ���
		String sVerifyTimeStamp = request.getParameter("timestamp");
		System.out.println("sVerifyTimeStamp:"+sVerifyTimeStamp);
		// �����
		String sVerifyNonce = request.getParameter("nonce");
		System.out.println("sVerifyNonce:"+sVerifyNonce);
		// ����ַ���
		String sVerifyEchoStr = request.getParameter("echostr");
		System.out.println("sVerifyEchoStr:"+sVerifyEchoStr);
		
		try {
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey,corpId);
			String sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,sVerifyNonce, sVerifyEchoStr);
			PrintWriter out = response.getWriter();
			out.print(sEchoStr);
			out.close();
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	/**
	 * ����΢�ŷ�������������Ϣ
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		request.setCharacterEncoding("UTF-8");
		try {
			// ��ȡpost����
			StringBuffer info=new java.lang.StringBuffer();
		    InputStream in=request.getInputStream();
		    BufferedInputStream buf=new BufferedInputStream(in);
		    byte[] buffer=new byte[1024]; 
		    int iRead;
		    while((iRead=buf.read(buffer))!=-1)   
		    {
		     info.append(new String(buffer,0,iRead,"UTF-8"));
		    }
			String sReqMsgSig = request.getParameter("msg_signature");
			String sReqTimeStamp = request.getParameter("timestamp");
			String sReqNonce = request.getParameter("nonce");
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey,corpId);
			// ���ܵõ�zhegn's
			String sMsg = wxcpt.DecryptMsg(sReqMsgSig, sReqTimeStamp, sReqNonce, info.toString());
			//System.out.println(sMsg);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(sMsg);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("FromUserName");
			String FromUserName = nodelist1.item(0).getTextContent();
			NodeList nodelist2 = root.getElementsByTagName("AgentID");
			String AgentID = nodelist2.item(0).getTextContent();
			NodeList nodelist3 = root.getElementsByTagName("EventKey");
			String EventKey = nodelist3.item(0).getTextContent();
			
			// ��Ӧ��Ϣ
			PrintWriter out = response.getWriter();
			//ǩ��
			if(EventKey.equals("signin")){
				//sMsg=WechatUtil.getHtmlStr("http://182.254.227.178/syWechat/wechat/js_sdk.jsp", "UTF-8");
				String sRespData = "<xml><ToUserName><![CDATA[mycreate]]></ToUserName><FromUserName><![CDATA["+FromUserName+"]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[ǩ���ɹ�]]></Content><MsgId>1234567890123456</MsgId><AgentID>"+AgentID+"</AgentID></xml>";
				String sEncryptMsg = wxcpt.EncryptMsg(sRespData, sReqTimeStamp, sReqNonce);
				out.print(sEncryptMsg);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}