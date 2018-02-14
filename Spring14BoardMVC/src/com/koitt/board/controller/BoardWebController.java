package com.koitt.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;
import com.koitt.board.service.BoardService;

@Controller
public class BoardWebController {
	
	@Autowired
	private BoardService service;
	
	/*
	 *  HTTP Method GET 방식으로 /board-list.do를 클라이언트가 요청하면
	 *  아래 메소드 호출
	 */
	@RequestMapping("/board-list.do")
	public String list(Model model) {
		List<Board> list = null;
		
		try {
			// service를 이용하여 글 목록 가져오기
			list = service.list();
			
		} catch (BoardException e) {
			// 예외가 발생하면 error키의 값을 이용하여 JSP에 표시
			model.addAttribute("error", "server");
		}
		
		// list키에 list 객체를 추가
		model.addAttribute("list", list);
		
		/*
		 *  ViewResolver에 의해서 /WEB-INF/views/board-list.jsp 페이지로
		 *  포워딩 하게 된다.
		 */
		return "board-list";
	}
}
