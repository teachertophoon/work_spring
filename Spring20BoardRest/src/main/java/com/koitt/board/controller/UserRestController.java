package com.koitt.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;
import com.koitt.board.service.UsersService;

@RestController
@RequestMapping("/rest") // 아래 클래스에 선언된 RequestMapping value 값에 /rest를 공통으로 붙인다.
public class UserRestController {
	
	@Autowired
	private UsersService usersService;
	
	// produces: 서버가 응답으로 보낼 미디어 타입을 지정 (아래는 응답을 JSON으로 응답할 경우)
	@RequestMapping(value="/user/login", method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> login(@RequestBody Users users) {
		
		System.out.println(users);
		
		// 아이디 존재 유무와 비밀번호 일치 여부 확인
		try {
			boolean isMatched = usersService.isPasswordMatched(
					users.getEmail(), users.getPassword());
			
			if (isMatched) {
				// Base64 인코딩 전 평문
				String plainCredentials = users.getEmail() + ":" + users.getPassword();
				
				// 평문을 Base64로 인코딩
				String base64Credentials = new String(Base64.encodeBase64(
						plainCredentials.getBytes()));
				
				System.out.println("인코딩 한 값: " + base64Credentials);
				
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("credentials", base64Credentials);
				
				return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			
		} catch (UsersException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}







