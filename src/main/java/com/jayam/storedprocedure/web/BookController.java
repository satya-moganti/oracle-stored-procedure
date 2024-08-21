package com.jayam.storedprocedure.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayam.storedprocedure.oracle.dto.BookIdList;
import com.jayam.storedprocedure.oracle.dto.TBook;
import com.jayam.storedprocedure.oracle.repo.types.BookType;
import com.jayam.storedprocedure.service.BookService;


@CrossOrigin
@RestController
@RequestMapping("books")
public class BookController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	
	@Autowired
	BookService bookService;
	
	

	@GetMapping(value="{bookid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBookDetails(@PathVariable("bookid") BigDecimal bookid) {		
		BookType bookType= bookService.getBookDetailsById(bookid);
		TBook tbook =new TBook();
		tbook.setBookid(bookType.getBookid());
		tbook.setTitle(bookType.getTitle());
		tbook.setAuthor(bookType.getAuthor());
		tbook.setPublishedyear(bookType.getPublishedyear());
		tbook.setGenre(bookType.getGenre());
		tbook.setPrice(bookType.getPrice());
		return new ResponseEntity<TBook>(tbook,HttpStatus.OK);

	}
	

	@GetMapping(value = "booksList",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<?,?>> getBookDetailBYIds(@RequestBody BookIdList bookIdList) {	
		
		Map<String,Object> resultMap = bookService.getBookDetailsByIdList(bookIdList.getBookIdList());
	
		return new ResponseEntity<Map<?,?>>(resultMap,HttpStatus.OK);

	}
	
	
	

	@PostMapping(value="createBook",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<?,?>> getBook(@RequestBody TBook tbook) {
		Map result = bookService.saveBookDetails(tbook.getBookid(), tbook.getTitle(), tbook.getAuthor(), 
				tbook.getPublishedyear(), tbook.getGenre(), tbook.getPrice());		
		
		return new ResponseEntity<Map<?,?>>(result,HttpStatus.OK);

	}
	
	
	@PostMapping(value="createBookByType",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<?,?>> getBookByType(@RequestBody TBook tbook) {				
		Map result = bookService.saveBookDetailsByType(tbook);				
		return new ResponseEntity<Map<?,?>>(result,HttpStatus.OK);

	}
	
	@PostMapping(value="createBooks",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<?,?>> getBook(@RequestBody List<TBook> tbooks) {
		Map result =  bookService.saveBookList(tbooks);
	return new ResponseEntity<Map<?,?>>(result,HttpStatus.OK);
	}
	

}
