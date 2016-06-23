package org.easywechat.msg.req;

/**
 * ���յ���ͼƬ��Ϣ
 */
public final class ImageReqMsg extends BaseReqMsg {

	private String picUrl;// ͼƬ����
	private String mediaId;// ͼƬ��Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ����

	/**
	 * �õ�ͼƬ����
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * �õ�ͼƬ��Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ����
	 */
	public String getMediaId() {
		return mediaId;
	}

	public ImageReqMsg(String picUrl, String mediaId) {
		super();
		this.picUrl = picUrl;
		this.mediaId = mediaId;
		setMsgType(ReqType.IMAGE);
	}

	@Override
	public String toString() {
		return "ImageReqMsg [picUrl=" + picUrl + ", mediaId=" + mediaId
				+ ", toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + ", msgId=" + msgId + "]";
	}

}
