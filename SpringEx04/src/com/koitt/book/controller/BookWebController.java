package com.koitt.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;
import com.koitt.book.service.BookService;

@Controller
public class BookWebController {
	
	@Autowired
	private BookService service;
	
	@RequestMapping(value="/book-list.do", method=RequestMethod.GET)
	public String list(Model model) {
		List<Book> list = null;
		
		try {
			list = service.list();
			
		} catch (BookException e) {
			model.addAttribute("error", "server");
		}
		
		model.addAttribute("list", list);
		
		return "book-list";
	}
}
