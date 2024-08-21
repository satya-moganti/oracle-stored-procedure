package com.jayam.storedprocedure.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayam.storedprocedure.oracle.dto.TBook;
import com.jayam.storedprocedure.oracle.repo.dao.BookWrapperDao;
import com.jayam.storedprocedure.oracle.repo.types.BookType;
@Service
public class BookServiceImpl implements BookService{

	
	@Autowired
	BookWrapperDao bookWrapperDao;
	
	@Override
	public Map<?, ?> saveBookDetails(BigDecimal bookid, String title, String author, String publishedyear, String genre,
			BigDecimal price) {

		return bookWrapperDao.saveBookDetails(bookid, title, author, publishedyear, genre, price);
	}

	@Override
	public Map<?, ?> saveBookDetailsByType(TBook book) {
		return bookWrapperDao.saveBookDetailsByType(book);
	}

	@Override
	public Map<?, ?> saveBookList(List<TBook> bookList) {
		return bookWrapperDao.saveBookList(bookList);
	}

	@Override
	public Map<?, ?> getBookByIdTitleAuthor(Integer bookid, String title, String author) {
		return bookWrapperDao.getBookByIdTitleAuthor(bookid, title, author);
	}

	@Override
	public BookType getBookDetailsById(BigDecimal bookid) {
		return bookWrapperDao.getBookDetailsById(bookid);
	}

	@Override
	public Map<String, Object> getBookDetailsByIdList(List<BigDecimal> bookids) {
		return bookWrapperDao.getBookDetailsByIdList(bookids);
	}

	

	
	

}
