package com.koitt.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.book.dao.BookDao;
import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao dao;

	@Override
	public void add(Book board) throws BookException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book detail(String no) throws BookException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> list() throws BookException {
		return dao.selectAll();
	}

	@Override
	public int count() throws BookException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void modify(Book board) throws BookException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String no) throws BookException {
		// TODO Auto-generated method stub
		
	}

}
