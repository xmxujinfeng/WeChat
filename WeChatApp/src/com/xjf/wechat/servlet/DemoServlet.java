package com.xjf.wechat.servlet;

import org.easywechat.msg.BaseMsg;
import org.easywechat.msg.TextMsg;
import org.easywechat.msg.req.TextReqMsg;
import org.easywechat.servlet.WeixinServletSupport;
  

public class DemoServlet extends WeixinServletSupport {  
  
    private static final long serialVersionUID = 1L;  
  
    @Override  
    protected String getToken() {  
    	System.out.println("Token： xuxiaopang");
        return "xuxiaopang";  
    }  
  
    @Override  
    protected BaseMsg handleTextMsg(TextReqMsg msg) {  
        return new TextMsg("你说了: <a href=\"http://blog.csdn.net/lyq8479\">柳峰的博客</a>" + msg.getContent());  
    }  
  
}  
