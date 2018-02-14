package com.koitt.board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private JdbcTemplate template;
	
	public BoardDaoImpl() {}

	@Override
	public void insert(Board board) throws BoardException {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO board (title, content, user_no, regdate) ");
			sql.append("VALUES (?, ?, ?, CURDATE())");
			
			template.update(sql.toString(),
					board.getTitle(),
					board.getContent(),
					board.getUserNo());
			
		} catch (Exception e) {
			throw new BoardException(e.getMessage());
		}
	}

	@Override
	public Board select(String no) throws BoardException {
		Board board = null;
		
		try {
			String sql = "SELECT * FROM board WHERE no = ?";
			board = template.queryForObject(sql, new BeanPropertyRowMapper<Board>(Board.class),
					no);
		} catch (Exception e) {
			throw new BoardException(e.getMessage());
		}
		
		return board;
	}

	@Override
	public List<Board> selectAll() throws BoardException {
		List<Board> list = null;
		
		try {
			String sql = "SELECT * FROM board ORDER BY no DESC";
			list = template.query(sql, new BeanPropertyRowMapper<Board>(Board.class));
			
		} catch (Exception e) {
			throw new BoardException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public int boardCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String no) {
		// TODO Auto-generated method stub
		
	}

}
