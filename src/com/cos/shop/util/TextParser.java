package com.cos.shop.util;

import com.nhncorp.lucy.security.xss.XssPreventer;

public class TextParser {

	// content 문자열을 매개값으로 넣으면서 앞에서 몇 글자 정도를 끊어서 반환할지를 두번째 매개값으로 지정함
	public static String getTextPreview(String content, int count) {

		if(content.length() > 0) {
			content = XssPreventer.escape(content);
				
			if(content.length() < count) {
				return content;
			}else {
				return content.substring(0, count) + "...";
			}	
		}

		return "내용 없음...";
	}	
	
	
	// content 문자열만 매개값으로 넣으면 앞에서부터 기본값인 50 글자 정도를 끊어서 반환함
	public static String getTextPreview(String content) {

		return getTextPreview(content, 50);
	}	
	
}
