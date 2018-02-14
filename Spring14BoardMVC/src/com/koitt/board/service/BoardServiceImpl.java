package com.koitt.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koitt.board.dao.BoardDao;
import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao dao;
	
	public BoardServiceImpl() {}
	
	@Override
	public void add(Board board) throws BoardException {
		dao.insert(board);
	}

	@Override
	public Board detail(String no) throws BoardException {
		return dao.select(no);
	}

	@Override
	public List<Board> list() throws BoardException {
		return dao.selectAll();
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void modify(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String no) {
		// TODO Auto-generated method stub
		
	}

}
