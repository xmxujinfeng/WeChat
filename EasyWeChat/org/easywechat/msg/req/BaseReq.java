package org.easywechat.msg.req;

public class BaseReq {

	String toUserName;// ������΢�ź�
	String fromUserName;// ���ͷ��ʺţ�һ��OpenID��
	long createTime;// ��Ϣ����ʱ��
	String msgType;// ��Ϣ����

	/**
	 * �õ�������΢�ź�
	 */
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * �õ����ͷ��ʺţ�һ��OpenID��
	 */
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * �õ���Ϣ����ʱ��
	 */
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	/**
	 * �õ���Ϣ����
	 */
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
