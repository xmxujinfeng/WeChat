package org.easywechat.msg.req;

/**
 * ���յ�����Ƶ��Ϣ
 */
public final class VideoReqMsg extends BaseReqMsg {

	private String mediaId;// ��Ƶ��Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ����
	private String thumbMediaId;// ��Ƶ��Ϣ����ͼ��ý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ����

	/**
	 * ��Ƶ��Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ����
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * ��Ƶ��Ϣ����ͼ��ý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ����
	 */
	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public VideoReqMsg(String mediaId, String thumbMediaId) {
		super();
		this.mediaId = mediaId;
		this.thumbMediaId = thumbMediaId;
		setMsgType(ReqType.VIDEO);
	}

	@Override
	public String toString() {
		return "VideoReqMsg [mediaId=" + mediaId + ", thumbMediaId="
				+ thumbMediaId + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + ", msgId=" + msgId
				+ "]";
	}

}
