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
    	System.out.println("Token： xuxiaopang");
        return "xuxiaopang";  
    }  
  
    @Override  
    protected BaseMsg handleTextMsg(TextReqMsg msg) {  
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("您好，我是小q，请回复数字选择服务：").append("\n\n");
    	buffer.append("1 天气预报").append("\n");
    	buffer.append("2 公交查询").append("\n");
    	buffer.append("3 周边搜索").append("\n");
    	buffer.append("4 歌曲点播").append("\n");
    	buffer.append("5 经典游戏").append("\n");
    	buffer.append("6 美女电台").append("\n");
    	buffer.append("7 人脸识别").append("\n");
    	buffer.append("8 聊天唠嗑").append("\n\n");
    	buffer.append("回复“?”显示此帮助菜单");
    	
//        return new TextMsg("你说了: <a href=\"http://blog.csdn.net/lyq8479\">柳峰的博客</a>" + msg.getContent());  
    	return new TextMsg(buffer.toString());
    }  
  
}  
