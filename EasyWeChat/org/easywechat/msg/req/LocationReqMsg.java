package org.easywechat.msg.req;

/**
 * 接收到的地理位置消息
 */
public final class LocationReqMsg extends BaseReqMsg {

	private double locationX;// 地理位置纬度
	private double locationY;// 地理位置经度
	private int scale;// 地图缩放大小
	private String label;// 地理位置信息

	/**
	 * 得到地理位置纬度
	 */
	public double getLocationX() {
		return locationX;
	}

	/**
	 * 得到地理位置经度
	 */
	public double getLocationY() {
		return locationY;
	}

	/**
	 * 得到地图缩放大小
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * 得到地理位置信息
	 */
	public String getLabel() {
		return label;
	}

	public LocationReqMsg(double locationX, double locationY, int scale,
			String label) {
		super();
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label = label;
		setMsgType(ReqType.LOCATION);
	}

	@Override
	public String toString() {
		return "LocationReqMsg [locationX=" + locationX + ", locationY="
				+ locationY + ", scale=" + scale + ", label=" + label
				+ ", toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + ", msgId=" + msgId + "]";
	}

}
