package org.easywechat.msg.req;

public class BaseReq {

	String toUserName;// 开发者微信号
	String fromUserName;// 发送方帐号（一个OpenID）
	long createTime;// 消息创建时间
	String msgType;// 消息类型

	/**
	 * 得到开发者微信号
	 */
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * 得到发送方帐号（一个OpenID）
	 */
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * 得到消息创建时间
	 */
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	/**
	 * 得到消息类型
	 */
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
