package com.jayam.storedprocedure.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.jayam.storedprocedure.oracle.dto.TBook;
import com.jayam.storedprocedure.oracle.repo.types.BookType;

public interface BookService {
	 public Map<?,?>  saveBookDetails(BigDecimal bookid,String title,String author,String publishedyear,String genre,BigDecimal price);
	 public Map<?,?>  saveBookDetailsByType(TBook book);
	 public Map<?,?>  saveBookList(List<TBook> bookList);
	 public Map<?,?>  getBookByIdTitleAuthor(Integer bookid,String title,String author);
	 public BookType  getBookDetailsById(BigDecimal bookid);
	 public Map<String,Object>  getBookDetailsByIdList(List<BigDecimal> bookids);	 
	 
}
