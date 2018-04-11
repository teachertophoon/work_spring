package com.koitt.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.FileException;
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;
import com.koitt.board.service.FileService;
import com.koitt.board.service.UsersService;

@RestController
@RequestMapping("/rest") // 아래 클래스에 선언된 RequestMapping value 값에 /rest를 공통으로 붙인다.
public class UserRestController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FileService fileService;

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

	// 사용자 생성
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> newUser(HttpServletRequest req,
			String email, String password, String name,
			@RequestParam("attachment") MultipartFile attachment) {

		// 클라이언트로부터 전달받은 값으로 객체 생성
		Users users = new Users(null, email, password, name, null);

		try {
			// 프로필 사진 저장
			String filename = fileService.add(req, attachment);

			// 프로필 사진 파일명을 users VO 객체에 담기
			users.setAttachment(filename);

			// 데이터베이스에 사용자 추가
			usersService.add(users);

			// 정상적으로 생성되었다는 응답코드를 클라이언트로 전달
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			// 서버 오류가 발생했다는 응답코드를 클라이언트로 전달
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}







