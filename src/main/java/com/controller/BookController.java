package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.BookBean;
import com.dao.BookDao;

@RestController
@CrossOrigin
public class BookController {
	
	@Autowired
	BookDao bookDao;
	
	@PostMapping("/book")
	public BookBean addBook(@RequestBody BookBean book) {
		bookDao.addBook(book);
		return book;
	}
	
	@GetMapping("/books")
	public List<BookBean> getAllBooks(){
		return bookDao.getAllBooks();
	}
	
	@DeleteMapping("/book/{bookId}")
	public List<BookBean> deleteBook(@PathVariable("bookId") int bookId){
		bookDao.deleteBookById(bookId);
		return bookDao.getAllBooks();
	}
	
	@PutMapping("/book")
	public BookBean updateBook(BookBean book) {
		System.out.println(book.getBookId());
		bookDao.updateBook(book);
		return book;
	}

}
