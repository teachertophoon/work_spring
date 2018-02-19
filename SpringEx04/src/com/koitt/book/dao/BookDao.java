package com.koitt.book.dao;

import java.util.List;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

public interface BookDao {

	// 글 추가
	public void insert(Book board) throws BookException;

	// 글 번호를 이용하여 글 하나 불러오기
	public Book select(String no) throws BookException;

	// 전체 글 불러오기
	public List<Book> selectAll() throws BookException;

	// 게시글 개수 가져오기
	public int boardCount() throws BookException;

	// 글 수정하기
	public void update(Book board) throws BookException;

	// 글 삭제하기
	public void delete(String no) throws BookException;
}
