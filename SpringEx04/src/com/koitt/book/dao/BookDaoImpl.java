package com.koitt.book.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public void insert(Book board) throws BookException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book select(String no) throws BookException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> selectAll() throws BookException {
		List<Book> list = null;
		
		try {
			String sql = "SELECT * FROM book ORDER BY isbn DESC";
			list = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public int boardCount() throws BookException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Book board) throws BookException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String no) throws BookException {
		// TODO Auto-generated method stub
		
	}

}
