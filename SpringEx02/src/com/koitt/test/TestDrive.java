package com.koitt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.koitt.dao.BoardDao;
import com.koitt.dao.BoardDao2;
import com.koitt.model.Board;

public class TestDrive {
	
	public static void main(String[] args) {
		// 1. 스프링 설정파일 불러오기
		ApplicationContext context = 
				new GenericXmlApplicationContext("/com/koitt/config/config.xml");
		
		BoardDao dao01 = context.getBean(BoardDao.class);
		Board board01 = dao01.getBoard(1);
		System.out.println(board01);
		
		BoardDao2 dao02 = context.getBean(BoardDao2.class);
		Board board02 = dao02.getBoard(1);
		System.out.println(board02);
	}
}
