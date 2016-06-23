package org.easywechat.msg.req;

/**
 * 接收到的文本消息
 */
public final class TextReqMsg extends BaseReqMsg {

	private String content;// 文本消息内容

	/**
	 * 得到文本消息内容
	 */
	public String getContent() {
		return content;
	}

	public TextReqMsg(String content) {
		super();
		this.content = content;
		setMsgType(ReqType.TEXT);
	}

	@Override
	public String toString() {
		return "TextReqMsg [content=" + content + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + ", msgId=" + msgId
				+ "]";
	}

}
