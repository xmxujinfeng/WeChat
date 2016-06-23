package org.easywechat.msg.req;

/**
 * 接收到的视频消息
 */
public final class VideoReqMsg extends BaseReqMsg {

	private String mediaId;// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
	private String thumbMediaId;// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据

	/**
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
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
