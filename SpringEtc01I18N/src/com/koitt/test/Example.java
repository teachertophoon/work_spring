package com.koitt.test;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class Example {
	
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	/*
	 * getMessage 메소드
	 * 첫 번째 파라미터: properties 파일의 code(변수명)
	 * 두 번째 파라미터: {0}와 같이 빈칸에 들어갈 문자열 배열
	 * 		예) new Object[]{"Apple"}을 전달하면 {0} 대신에 Apple로 대치
	 * 			new Object[]{"Car", "Computer"}를 전달하면 {0} 대신에 Car
	 * 			{1} 대신에 Computer로 대치
	 * 세 번째 파라미터: 첫 번째 파라미터에 해당하는 code(변수명)이 없을 경우
	 * 		기본으로 출력하는 문자열
	 * 네 번째 파라미터: 어떤 언어로 문자열을 가져올 것인지 설정
	 * 		예) Locale.KOREAN: greeting_ko.properties의 문자열을 가져옴
	 * 			Locale.ENGLISH: greeting_en.properties의 문자열을 가져옴
	 */
	public void execute() {
		String message01 = 
				this.messages.getMessage("greeting", null, "Hello", Locale.KOREAN);
		
		String message02 =
				this.messages.getMessage("argument.required", new Object[]{"Apple"}, 
						"Banana", Locale.KOREAN);
		
		System.out.println(message01);
		System.out.println(message02);
	}

}
