package com.koitt.kakao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/kakao-pay")	// 페이 관련 URL 앞에는 항상 kakao-pay를 사용
public class KakaoPayWebController {
	
	// 결제준비 화면
	@RequestMapping(value="/ready.do", method=RequestMethod.GET)
	public String ready() {
		return "kakao-pay/ready";
	}
}
