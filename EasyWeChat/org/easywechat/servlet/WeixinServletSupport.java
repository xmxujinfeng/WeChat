package org.easywechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easywechat.msg.BaseMsg;
import org.easywechat.msg.TextMsg;
import org.easywechat.msg.req.BaseEvent;
import org.easywechat.msg.req.BaseReq;
import org.easywechat.msg.req.BaseReqMsg;
import org.easywechat.msg.req.EventType;
import org.easywechat.msg.req.ImageReqMsg;
import org.easywechat.msg.req.LinkReqMsg;
import org.easywechat.msg.req.LocationEvent;
import org.easywechat.msg.req.LocationReqMsg;
import org.easywechat.msg.req.MenuEvent;
import org.easywechat.msg.req.QrCodeEvent;
import org.easywechat.msg.req.ReqType;
import org.easywechat.msg.req.TextReqMsg;
import org.easywechat.msg.req.VideoReqMsg;
import org.easywechat.msg.req.VoiceReqMsg;
import org.easywechat.util.MessageUtil;
import org.easywechat.util.SignUtil;

/**
 * ����΢�������ServletӦ�̳д��࣬������Ҫ��д��ط���
 */
@SuppressWarnings("serial")
public abstract class WeixinServletSupport extends HttpServlet {

	/**
	 * �÷�������token
	 */
	protected abstract String getToken();

	/**
	 * �÷�����Ψһ��������֤����΢�ŷ���������Ϣ<br>
	 * ���û��������Ҫ����������д�÷���
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ȷ����������΢�ŷ�����
		if (isLegal(request)) {
			PrintWriter out = response.getWriter();
			out.print(request.getParameter("echostr"));
			out.close();
		} else {
			// �Ƿ�����Ĭ�ϲ�����Ӧ
		}
	}

	/**
	 * �÷�����֤��Ϣ�Ƿ�����΢�ŷ�����������������΢�ŷ���������Ϣ<br>
	 * ���û��������Ҫ����������д�÷���
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		if (!isLegal(request)) {
			// �Ƿ�����Ĭ�ϲ�����Ӧ
			return;
		}

		// ������Ϣ
		String resp = processRequest(request);

		// ��Ӧ��Ϣ
		PrintWriter out = response.getWriter();
		out.print(resp);
		out.close();
	}

	/**
	 * ������Ϣ
	 */
	private String processRequest(HttpServletRequest request) {

		Map<String, String> reqMap = MessageUtil.parseXml(request);
		String fromUserName = reqMap.get("FromUserName");
		String toUserName = reqMap.get("ToUserName");
		String msgType = reqMap.get("MsgType");

		BaseMsg msg = null;// Ҫ���͵���Ϣ

		// �¼�����
		if (msgType.equals(ReqType.EVENT)) {
			// �¼�����
			String eventType = reqMap.get("Event");

			// ��ά���¼�
			String ticket = reqMap.get("Ticket");
			if (ticket != null) {
				String eventKey = reqMap.get("EventKey");
				QrCodeEvent event = new QrCodeEvent(eventKey, ticket);
				buildBasicEvent(reqMap, event);
				msg = handleQrCodeEvent(event);
			}
			// ����
			if (eventType.equals(EventType.SUBSCRIBE)) {
				BaseEvent event = new BaseEvent();
				buildBasicEvent(reqMap, event);
				msg = handleSubscribe(event);
			}
			// ȡ������
			else if (eventType.equals(EventType.UNSUBSCRIBE)) {
				BaseEvent event = new BaseEvent();
				buildBasicEvent(reqMap, event);
				msg = handleUnsubscribe(event);
			}
			// ����˵���ȡ��Ϣʱ���¼�����
			else if (eventType.equals(EventType.CLICK)) {
				String eventKey = reqMap.get("EventKey");
				MenuEvent event = new MenuEvent(eventKey);
				buildBasicEvent(reqMap, event);
				msg = handleMenuClickEvent(event);
			}
			// ����˵���ת����ʱ���¼�����
			else if (eventType.equals(EventType.VIEW)) {
				String eventKey = reqMap.get("EventKey");
				MenuEvent event = new MenuEvent(eventKey);
				buildBasicEvent(reqMap, event);
				msg = handleMenuViewEvent(event);
			}
			// �ϱ�����λ���¼�
			else if (eventType.equals(EventType.LOCATION)) {
				double latitude = Double.parseDouble(reqMap.get("Latitude"));
				double longitude = Double.parseDouble(reqMap.get("Longitude"));
				double precision = Double.parseDouble(reqMap.get("Precision"));
				LocationEvent event = new LocationEvent(latitude, longitude,
						precision);
				buildBasicEvent(reqMap, event);
				msg = handleLocationEvent(event);
			}

		} else {// ������ͨ��Ϣ

			// �ı���Ϣ
			if (msgType.equals(ReqType.TEXT)) {
				String content = reqMap.get("Content");
				TextReqMsg textReqMsg = new TextReqMsg(content);
				buildBasicReqMsg(reqMap, textReqMsg);
				msg = handleTextMsg(textReqMsg);
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(ReqType.IMAGE)) {
				String picUrl = reqMap.get("PicUrl");
				String mediaId = reqMap.get("MediaId");
				ImageReqMsg imageReqMsg = new ImageReqMsg(picUrl, mediaId);
				buildBasicReqMsg(reqMap, imageReqMsg);
				msg = handleImageMsg(imageReqMsg);
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(ReqType.VOICE)) {
				String format = reqMap.get("Format");
				String mediaId = reqMap.get("MediaId");
				String recognition = reqMap.get("Recognition");
				VoiceReqMsg voiceReqMsg = new VoiceReqMsg(mediaId, format,
						recognition);
				buildBasicReqMsg(reqMap, voiceReqMsg);
				msg = handleVoiceMsg(voiceReqMsg);
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(ReqType.VIDEO)) {
				String thumbMediaId = reqMap.get("ThumbMediaId");
				String mediaId = reqMap.get("MediaId");
				VideoReqMsg videoReqMsg = new VideoReqMsg(mediaId, thumbMediaId);
				buildBasicReqMsg(reqMap, videoReqMsg);
				msg = handleVideoMsg(videoReqMsg);
			}
			// ����λ����Ϣ
			else if (msgType.equals(ReqType.LOCATION)) {
				double locationX = Double.parseDouble(reqMap.get("Location_X"));
				double locationY = Double.parseDouble(reqMap.get("Location_Y"));
				int scale = Integer.parseInt(reqMap.get("Scale"));
				String label = reqMap.get("Label");
				LocationReqMsg locationReqMsg = new LocationReqMsg(locationX,
						locationY, scale, label);
				buildBasicReqMsg(reqMap, locationReqMsg);
				msg = handleLocationMsg(locationReqMsg);
			}
			// ������Ϣ
			else if (msgType.equals(ReqType.LINK)) {
				String title = reqMap.get("Title");
				String description = reqMap.get("Description");
				String url = reqMap.get("Url");
				LinkReqMsg linkReqMsg = new LinkReqMsg(title, description, url);
				buildBasicReqMsg(reqMap, linkReqMsg);
				msg = handleLinkMsg(linkReqMsg);
			}

		}

		if (msg == null) {
			// �ظ��մ���΢�ŵĹ涨�������ظ�
			return "";
		}

		msg.setFromUserName(toUserName);
		msg.setToUserName(fromUserName);
		return msg.toXml();
	}

	/**
	 * �����ı���Ϣ
	 */
	protected BaseMsg handleTextMsg(TextReqMsg msg) {
		return handleDefaultMsg(msg);
	}

	/**
	 * ����ͼƬ��Ϣ
	 */
	protected BaseMsg handleImageMsg(ImageReqMsg msg) {
		return handleDefaultMsg(msg);
	}

	/**
	 * ����������Ϣ
	 */
	protected BaseMsg handleVoiceMsg(VoiceReqMsg msg) {
		return handleDefaultMsg(msg);
	}

	/**
	 * ������Ƶ��Ϣ
	 */
	protected BaseMsg handleVideoMsg(VideoReqMsg msg) {
		return handleDefaultMsg(msg);
	}

	/**
	 * �������λ����Ϣ
	 */
	protected BaseMsg handleLocationMsg(LocationReqMsg msg) {
		return handleDefaultMsg(msg);
	}

	/**
	 * ����������Ϣ
	 */
	protected BaseMsg handleLinkMsg(LinkReqMsg msg) {
		return handleDefaultMsg(msg);
	}

	/**
	 * ����ɨ���������ά���¼�
	 */
	protected BaseMsg handleQrCodeEvent(QrCodeEvent event) {
		return handleDefaultEvent(event);
	}

	/**
	 * �����ϱ�����λ���¼�
	 */
	protected BaseMsg handleLocationEvent(LocationEvent event) {
		return handleDefaultEvent(event);
	}

	/**
	 * �������˵���ȡ��Ϣʱ���¼�����
	 */
	protected BaseMsg handleMenuClickEvent(MenuEvent event) {
		return handleDefaultEvent(event);
	}

	/**
	 * �������˵���ת����ʱ���¼�����
	 */
	protected BaseMsg handleMenuViewEvent(MenuEvent event) {
		return handleDefaultEvent(event);
	}

	/**
	 * �������¼�<br>
	 * Ĭ�ϻظ��ı���Ϣ����л���Ĺ�ע����
	 */
	protected BaseMsg handleSubscribe(BaseEvent event) {
		return new TextMsg("��л���Ĺ�ע��");
	}

	/**
	 * ����ȡ�������¼�<br>
	 * Ĭ�ϲ��ظ�
	 */
	protected BaseMsg handleUnsubscribe(BaseEvent event) {
		return null;
	}

	/**
	 * ������Ϣ��Ĭ�Ϸ�ʽ<br>
	 * �������д�÷�������Ĭ�ϲ������κ���Ϣ
	 */
	protected BaseMsg handleDefaultMsg(BaseReqMsg msg) {
		return null;
	}

	/**
	 * ���ô����¼���Ĭ�Ϸ�ʽ<br>
	 * �������д�÷�������Ĭ�ϲ������κ���Ϣ
	 */
	protected BaseMsg handleDefaultEvent(BaseEvent event) {
		return null;
	}

	/**
	 * Ϊ�¼���ͨ��Ϣ������ӻ�������<br>
	 * ����������MsgId��MsgType��FromUserName��ToUserName��CreateTime
	 */
	private void buildBasicReqMsg(Map<String, String> reqMap, BaseReqMsg reqMsg) {
		addBasicReqParams(reqMap, reqMsg);
		reqMsg.setMsgId(reqMap.get("MsgId"));
	}

	/**
	 * Ϊ�¼����Ͷ�����ӻ�������<br>
	 * ����������Event��MsgType��FromUserName��ToUserName��CreateTime
	 */
	private void buildBasicEvent(Map<String, String> reqMap, BaseEvent event) {
		addBasicReqParams(reqMap, event);
		event.setEvent(reqMap.get("Event"));
	}

	/**
	 * Ϊ���������ӻ�������������MsgType��FromUserName��ToUserName��CreateTime<br>
	 * ������������ͨ��Ϣ���¼�����
	 */
	private void addBasicReqParams(Map<String, String> reqMap, BaseReq req) {
		req.setMsgType(reqMap.get("MsgType"));
		req.setFromUserName(reqMap.get("FromUserName"));
		req.setToUserName(reqMap.get("ToUserName"));
		req.setCreateTime(Long.parseLong(reqMap.get("CreateTime")));
	}

	/**
	 * �ж������Ƿ�����΢�ŷ�����
	 */
	private boolean isLegal(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		return SignUtil.checkSignature(getToken(), signature, timestamp, nonce);
	}
}
