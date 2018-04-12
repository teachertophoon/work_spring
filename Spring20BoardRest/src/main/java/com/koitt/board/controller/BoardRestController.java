package com.koitt.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;
import com.koitt.board.model.FileException;
import com.koitt.board.service.BoardService;
import com.koitt.board.service.FileService;

@RestController
@RequestMapping("/rest")
public class BoardRestController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private FileService fileService;

	@RequestMapping(value="/board", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> list() {
		List<Board> list = null;

		try {
			// service를 이용하여 글 목록 가져오기
			list = boardService.list();

			if (list != null && list.size() > 0) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("boardList", list);

				return new ResponseEntity<>(resultMap, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (BoardException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 글 상세 화면
	@RequestMapping(value="/board/{no}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> detail(HttpServletRequest request, 
			@PathVariable("no") String no) {

		Board board = null;
		String filename = null;
		String imgPath = null;

		try {

			if (no != null) {
				board = boardService.detail(no);

				if (board != null) {
					filename = board.getAttachment();
					imgPath = fileService.getImgPath(request, filename);

					Map<String, Object> resultMap = new HashMap<>();
					resultMap.put("item", board);
					resultMap.put("src", imgPath);

					return new ResponseEntity<>(resultMap, HttpStatus.OK);
				}
				else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 글 추가
	@RequestMapping(value="/board", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add(HttpServletRequest request,
			Integer userNo,
			String title,
			String content,
			@RequestParam("attachment") MultipartFile attachment) {

		Board board = new Board();
		board.setUserNo(userNo);
		board.setTitle(title);
		board.setContent(content);

		try {
			// 파일 서비스로부터 전달받은 파일명을 VO 객체에 담는다.
			String filename = fileService.add(request, attachment);
			board.setAttachment(filename);

			// 모든 내용을 저장한 VO 객체를 데이터베이스로 전달
			boardService.add(board);
			
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
















