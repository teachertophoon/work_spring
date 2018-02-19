package com.koitt.book.service;

import java.util.List;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

public interface BookService {
	
	// 글 추가
	public void add(Book board) throws BookException;
	
	// 글 상세정보
	public Book detail(String no) throws BookException;
	
	// 글 전체
	public List<Book> list() throws BookException;
	
	// 글 개수
	public int count() throws BookException;
	
	// 글 수정하기
	public void modify(Book board) throws BookException;
	
	// 글 삭제하기
	public void remove(String no) throws BookException;
}
