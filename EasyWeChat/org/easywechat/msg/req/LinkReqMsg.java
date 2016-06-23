package org.easywechat.msg.req;

/**
 * ���յ���������Ϣ
 */
public final class LinkReqMsg extends BaseReqMsg {

	private String title;// ��Ϣ����
	private String description;// ��Ϣ����
	private String url;// ��Ϣ����

	/**
	 * �õ���Ϣ����
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * �õ���Ϣ����
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * �õ���Ϣ����
	 */
	public String getUrl() {
		return url;
	}

	public LinkReqMsg(String title, String description, String url) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		setMsgType(ReqType.EVENT);
	}

	@Override
	public String toString() {
		return "LinkReqMsg [title=" + title + ", description=" + description
				+ ", url=" + url + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + ", msgId=" + msgId
				+ "]";
	}

}
