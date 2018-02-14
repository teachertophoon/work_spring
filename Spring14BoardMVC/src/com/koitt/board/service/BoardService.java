package com.koitt.board.service;

import java.util.List;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;

public interface BoardService {
	
	// 글 상세정보
	public Board detail(String no);
	
	// 글 전체
	public List<Board> list() throws BoardException;
	
	// 글 개수
	public int count();
	
	// 글 수정하기
	public void modify(Board board);
	
	// 글 삭제하기
	public void remove(String no);
}
