package com.xjf.wechat.servlet;

import org.apache.log4j.Logger;
import org.easywechat.msg.BaseMsg;
import org.easywechat.msg.TextMsg;
import org.easywechat.msg.req.TextReqMsg;
import org.easywechat.servlet.WeixinServletSupport;
  

public class CoreServlet extends WeixinServletSupport {  

	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(CoreServlet.class);	
  
    @Override  
    protected String getToken() {  
    	System.out.println("Token�� xuxiaopang");
        return "xuxiaopang";  
    }  
  
    @Override  
    protected BaseMsg handleTextMsg(TextReqMsg msg) {  
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("���ã�����Сq����ظ�����ѡ�����").append("\n\n");
    	buffer.append("1 ����Ԥ��").append("\n");
    	buffer.append("2 ������ѯ").append("\n");
    	buffer.append("3 �ܱ�����").append("\n");
    	buffer.append("4 �����㲥").append("\n");
    	buffer.append("5 ������Ϸ").append("\n");
    	buffer.append("6 ��Ů��̨").append("\n");
    	buffer.append("7 ����ʶ��").append("\n");
    	buffer.append("8 �������").append("\n\n");
    	buffer.append("�ظ���?����ʾ�˰����˵�");
    	
//        return new TextMsg("��˵��: <a href=\"http://blog.csdn.net/lyq8479\">����Ĳ���</a>" + msg.getContent());  
    	return new TextMsg(buffer.toString());
    }  
  
}  
