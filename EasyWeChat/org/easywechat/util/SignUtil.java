package org.easywechat.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * ����У�鹤����
 */
public class SignUtil {

	private static final char[] digit = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * ��֤ǩ��
	 */
	public static boolean checkSignature(String token, String signature,
			String timestamp, String nonce) {
		if (Validator.hasNull(token, signature, timestamp, nonce)) {
			return false;
		}
		String[] arr = new String[] { token, timestamp, nonce };
		// ��token��timestamp��nonce�������������ֵ�������
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
			byte[] digest = md.digest(content.toString().getBytes("UTF-8"));
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// ��sha1���ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��
		return tmpStr != null && tmpStr.equalsIgnoreCase(signature);
	}

	/**
	 * ���ֽ�����ת��Ϊʮ�������ַ���
	 */
	private static String byteToStr(byte[] byteArray) {
		int len = byteArray.length;
		StringBuilder strDigest = new StringBuilder(len * 2);
		for (int i = 0; i < len; i++) {
			strDigest.append(byteToHexStr(byteArray[i]));
		}
		return strDigest.toString();
	}

	/**
	 * ���ֽ�ת��Ϊʮ�������ַ���
	 */
	private static String byteToHexStr(byte mByte) {
		char[] tempArr = new char[2];
		tempArr[0] = digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = digit[mByte & 0X0F];

		return new String(tempArr);
	}
}
