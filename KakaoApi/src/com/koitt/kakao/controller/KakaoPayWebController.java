package com.koitt.kakao.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/kakao-pay")	// 페이 관련 URL 앞에는 항상 kakao-pay를 사용
public class KakaoPayWebController {
	
	// 결제준비 화면
	@RequestMapping(value="/ready.do", method=RequestMethod.GET)
	public String ready() {
		return "kakao-pay/ready";
	}
	
	// 결제준비 수행
	@RequestMapping(value="/ready.do", method=RequestMethod.POST)
	public String ready(@RequestParam Map<String, String> order, 
			HttpSession session) throws JsonParseException, JsonMappingException, IOException {
		
		/*
		 * 서버에 요청할 주소 및 Body 정보
		 * 항목에 대한 정보는 아래 링크 참조
		 * https://developers.kakao.com/docs/restapi/kakaopay-api
		 */
		RestTemplate rt = new RestTemplate();
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", order.get("partner_order_id"));
		params.add("partner_user_id", order.get("partner_user_id"));
		params.add("item_name", order.get("item_name"));
		params.add("quantity", order.get("quantity"));
		params.add("total_amount", order.get("total_amount"));
		params.add("tax_free_amount", order.get("tax_free_amount"));
		params.add("approval_url", "http://localhost:8080/KakaoApi/kakao-pay/approve.do");
		params.add("cancel_url", "http://localhost:8080/KakaoApi/kakao-pay/cancel.do");
		params.add("fail_url", "http://localhost:8080/KakaoApi/kakao-pay/fail.do");
		
		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "96edf173089addfb3e18d69803c01b00");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		
		HttpEntity<MultiValueMap<String, String>> request = 
				new HttpEntity<MultiValueMap<String, String>>(params, headers);
		String response = rt.postForObject("https://kapi.kakao.com/v1/payment/ready", 
				request, String.class);
		
		/*
		 *  JSON String을 Map 형태로 변환
		 *  JavaScript 객체를 Java에서 사용하기 위해
		 *  Java 객체로 변환하는 과정을 거쳐야 한다.
		 */
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resultMap = mapper.readValue(response, 
				new TypeReference<Map<String, Object>>(){});
		
		return "redirect:" + resultMap.get("next_redirect_pc_url");
	}
}
