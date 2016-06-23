package org.easywechat.msg.req;

/**
 * ���յ����ı���Ϣ
 */
public final class TextReqMsg extends BaseReqMsg {

	private String content;// �ı���Ϣ����

	/**
	 * �õ��ı���Ϣ����
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
