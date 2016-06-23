package org.easywechat.demo;

import org.easywechat.msg.BaseMsg;
import org.easywechat.msg.ImageMsg;
import org.easywechat.msg.NewsMsg;
import org.easywechat.msg.TextMsg;
import org.easywechat.msg.req.ImageReqMsg;
import org.easywechat.msg.req.TextReqMsg;
import org.easywechat.servlet.WeixinServletSupport;

/**
 * demo
 */
public class MyWeixinServlet extends WeixinServletSupport {

	private static final long serialVersionUID = 2934570713761546554L;

	@Override
	protected String getToken() {
		return "myToken";
	}

	@Override
	protected BaseMsg handleTextMsg(TextReqMsg req) {
		String content = req.getContent();
		if (content.equals("xxx")) {
			// �����ı���Ϣ
			return new TextMsg("nnn");
		} else {
			// ����ͼ����Ϣ
			NewsMsg msg = new NewsMsg();
			msg.add("����1", "����", "picurl", "url");
			msg.add("title2");
			msg.add("title3");

			return msg;
		}
	}
	
@Override
protected BaseMsg handleImageMsg(ImageReqMsg msg) {
	ImageMsg imageMsg=new ImageMsg("");
	return imageMsg;
}

}
