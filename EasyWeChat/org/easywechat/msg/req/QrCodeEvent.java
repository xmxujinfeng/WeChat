package org.easywechat.msg.req;

/**
 * ɨ���������ά���¼�
 */
public final class QrCodeEvent extends BaseEvent {

	private String eventKey;// �¼�KEYֵ
	private String ticket;// ��ά���ticket����������ȡ��ά��ͼƬ

	/**
	 * �õ��¼�KEYֵ
	 */
	public String getEventKey() {
		return eventKey;
	}

	/**
	 * �õ���ά���ticket����������ȡ��ά��ͼƬ
	 */
	public String getTicket() {
		return ticket;
	}

	public QrCodeEvent(String eventKey, String ticket) {
		super();
		this.eventKey = eventKey;
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "QrCodeEvent [eventKey=" + eventKey + ", ticket=" + ticket
				+ ", toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + "]";
	}

}
