package org.easywechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class MessageUtil {

	/**
	 * ����΢�ŷ���������XML��
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) {
		// ����������洢��HashMap��
		Map<String, String> map = new HashMap<String, String>();

		// ��request��ȡ��������
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader reader = factory.createXMLEventReader(inputStream);
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				if (event.isStartElement()) {
					String tagName = event.asStartElement().getName()
							.toString();
					if (!tagName.equals("xml")) {
						String text = reader.getElementText();
						map.put(tagName, text);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}
