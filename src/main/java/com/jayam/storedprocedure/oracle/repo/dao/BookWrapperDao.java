package com.jayam.storedprocedure.oracle.repo.dao;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;

import com.jayam.storedprocedure.oracle.dto.TBook;
import com.jayam.storedprocedure.oracle.repo.BookStoredProcedure;
import com.jayam.storedprocedure.oracle.repo.types.BookIdValueList;
import com.jayam.storedprocedure.oracle.repo.types.BookListType;
import com.jayam.storedprocedure.oracle.repo.types.BookType;
import com.jayam.storedprocedure.oracle.repo.types.BookTypeListValue;
import com.jayam.storedprocedure.oracle.repo.types.BookTypeValue;


@Component
public class BookWrapperDao {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(BookWrapperDao.class);

	
	@Autowired
	DataSource oracleDataSource;
	
    private static final String SP_CREATE_BOOK_BY_INPUT_PARAMS="SP_CREATE_BOOK_BY_INPUT_PARAMS";
	private static final String SP_CREATE_BOOK_BY_BOOK_TYPE="SP_CREATE_BOOK_BY_BOOK_TYPE";
	private static final String SP_SAVE_BULK_BOOK_DATA="SP_SAVE_BULK_BOOK_DATA";
	private static final String SP_GET_BOOK_INFO_BY_ID_TITLE_AUTHOR="SP_GET_BOOK_INFO_BY_ID_TITLE_AUTHOR";
	private static final String SP_GET_BOOKS_INFO_BY_ID="SP_GET_BOOKS_INFO_BY_ID";
	private static final String SP_GET_BOOKS_BULK_DATA_BY_ID_LIST="SP_GET_BOOKS_BULK_DATA_BY_ID_LIST";
	
	
	 public Map<?,?>  saveBookDetails(BigDecimal bookid,String title,String author,String publishedyear,String genre,BigDecimal price) {
		 Map<String,Object> resultMap=null;
		    BigDecimal result =null;
	    	BookStoredProcedure bookStoredProcedure = new BookStoredProcedure(oracleDataSource);
	    	bookStoredProcedure.setSql(SP_CREATE_BOOK_BY_INPUT_PARAMS);
	    	try {

	    		bookStoredProcedure.declareParameter(new SqlParameter("in_bookid", Types.NUMERIC));
	        	bookStoredProcedure.declareParameter(new SqlParameter("in_title", Types.VARCHAR));
	        	bookStoredProcedure.declareParameter(new SqlParameter("in_author", Types.VARCHAR));
	        	bookStoredProcedure.declareParameter(new SqlParameter("in_publishedyear", Types.VARCHAR));
	        	bookStoredProcedure.declareParameter(new SqlParameter("in_genre", Types.VARCHAR));
	        	bookStoredProcedure.declareParameter(new SqlParameter("in_price", Types.NUMERIC));
	        	bookStoredProcedure.declareParameter(new SqlOutParameter("out_result", Types.NUMERIC));
	            bookStoredProcedure.compile();
	           resultMap=bookStoredProcedure.execute(bookid,title,author,
	            		publishedyear,genre,price);
	            
	            result = (BigDecimal) resultMap.get("out_result");
	            if(!result.equals(-1)) {
	            	LOGGER.info("BookWrapperDao saveBookDetails result : "+result);
                 return resultMap;
	            }
	            
	    	}catch(Exception e) {
	    		resultMap =new HashMap<>();
	    		resultMap.put("out_result", "Error :"+e.getMessage());
	    		LOGGER.error(e.getMessage());
	    	}    	
	    	
			return resultMap;
	    }


	 public Map<?,?>  saveBookDetailsByType(TBook book) {
		 Map<String,Object> resultMap=null;
		    BigDecimal result = null;
		    BookTypeValue bookType = new BookTypeValue(book);
	    	BookStoredProcedure bookStoredProcedure = new BookStoredProcedure(oracleDataSource);
	    	bookStoredProcedure.setSql(SP_CREATE_BOOK_BY_BOOK_TYPE);
	    	try {

	    		bookStoredProcedure.declareParameter(new SqlParameter("in_book",Types.STRUCT,"BOOKTYPE"));	        	
	        	bookStoredProcedure.declareParameter(new SqlOutParameter("out_result", Types.NUMERIC));
	        	bookStoredProcedure.compile();
	            resultMap=bookStoredProcedure.execute(bookType);
	            
	            result = (BigDecimal) resultMap.get("out_result");
	            if(!result.equals(-1)) {
	            	
	            	LOGGER.info("BookWrapperDao saveBookDetailsByType result : "+result);
              return resultMap;
	            }
	            
	    	}catch(Exception e) {
	    		resultMap =new HashMap<>();
	    		resultMap.put("out_result", "Error :"+e.getMessage());
	    		LOGGER.error(e.getMessage());
	    	}    	
	    	
			return resultMap;
	    }

 
	 public Map<?,?>  saveBookList(List<TBook> bookList) {
		 Map<String,Object> resultMap= null;
		    BigDecimal result = null;
		    BookTypeListValue bookTypeListValue = new BookTypeListValue(bookList);
	    	BookStoredProcedure bookStoredProcedure = new BookStoredProcedure(oracleDataSource);
	    	bookStoredProcedure.setSql(SP_SAVE_BULK_BOOK_DATA);
	    	try {

	    		bookStoredProcedure.declareParameter(new SqlParameter("in_book_list",Types.ARRAY,"TABBOOKTYPE"));	        	
	        	bookStoredProcedure.declareParameter(new SqlOutParameter("out_result", Types.NUMERIC));
	        	bookStoredProcedure.compile();
	             resultMap=bookStoredProcedure.execute(bookTypeListValue);
	            
	            result = (BigDecimal) resultMap.get("out_result");
	            if(!result.equals(-1)) {
	            	LOGGER.info("BookWrapperDao saveBookList result : "+result);
           return resultMap;
	            }
	            
	    	}catch(Exception e) {
	    		resultMap =new HashMap<>();
	    		resultMap.put("out_result", "Error :"+e.getMessage());
	    		LOGGER.error(e.getMessage());
	    	}    	
	    	
			return resultMap;
	    }


	 public Map<?,?>  getBookByIdTitleAuthor(Integer bookid,String title,String author) {
		 Map<String,Object> resultMap=null;
    	BookType bookType = new BookType();
    	BookStoredProcedure bookStoredProcedure = new BookStoredProcedure(oracleDataSource);
    	bookStoredProcedure.setSql(SP_GET_BOOK_INFO_BY_ID_TITLE_AUTHOR);
    	try {
    		bookStoredProcedure.declareParameter(new SqlParameter("in_bookid", Types.NUMERIC));
        	bookStoredProcedure.declareParameter(new SqlParameter("in_title", Types.VARCHAR));
        	bookStoredProcedure.declareParameter(new SqlParameter("in_author", Types.VARCHAR));
        	bookStoredProcedure.declareParameter(new SqlOutParameter("out_book", Types.STRUCT,"BOOKTYPE",bookType));
        	bookStoredProcedure.declareParameter(new SqlOutParameter("out_result", Types.NUMERIC));
            bookStoredProcedure.compile();
            resultMap=bookStoredProcedure.execute(bookid,title,author);
            
            BigDecimal result = (BigDecimal) resultMap.get("out_result");
            if(!result.equals(-1)) {
            	LOGGER.info("BookWrapperDao getBookByIdTitleAuthor result : "+result);
                bookType = (BookType)resultMap.get("out_book");
            	LOGGER.debug("BookWrapperDao getBookByIdTitleAuthor Book details : "+bookType.toString());

            }
            
    	}catch(Exception e) {
    		LOGGER.error(e.getMessage());
    	}    	
    	
		return resultMap;
    }
	 
	 public BookType  getBookDetailsById(BigDecimal bookid) {
		    Map<String,Object> resultMap=null;
	    	BookType bookType = new BookType();
	    	BookStoredProcedure bookStoredProcedure = new BookStoredProcedure(oracleDataSource);
	    	bookStoredProcedure.setSql(SP_GET_BOOKS_INFO_BY_ID);
	    	try {
	    		bookStoredProcedure.declareParameter(new SqlParameter("in_bookid", Types.NUMERIC));
	        	bookStoredProcedure.declareParameter(new SqlOutParameter("out_book", Types.STRUCT,"BOOKTYPE",bookType));
	        	bookStoredProcedure.declareParameter(new SqlOutParameter("out_result", Types.NUMERIC));
	            bookStoredProcedure.compile();
	            resultMap=bookStoredProcedure.execute(bookid);
	            
	            BigDecimal result = (BigDecimal) resultMap.get("out_result");
	            if(!result.equals(-1)) {
	            	LOGGER.info("BookWrapperDao getBookDetailsById result : "+result);
	                bookType = (BookType)resultMap.get("out_book");
	            	LOGGER.debug("BookWrapperDao getBookDetailsById Book details : "+bookType.toString());

	            }
	            
	    	}catch(Exception e) {
	    		LOGGER.error(e.getMessage());
	    	}    	
	    	
			return bookType;
	    }


	 public Map<String,Object>  getBookDetailsByIdList(List<BigDecimal> bookids) {
		 Map<String,Object> resultMap=null;
		     List<BookType> finalBookList = new ArrayList<BookType>();
	    	BookIdValueList bookIdList = new BookIdValueList(bookids);
	    	BookStoredProcedure bookStoredProcedure = new BookStoredProcedure(oracleDataSource);
	    	bookStoredProcedure.setSql(SP_GET_BOOKS_BULK_DATA_BY_ID_LIST);
	    	try {
	    		bookStoredProcedure.declareParameter(new SqlParameter("in_bookids", Types.ARRAY,"VARRAY_BOOK_IDS"));
	        	bookStoredProcedure.declareParameter(new SqlOutParameter("out_book_list", Types.ARRAY,"VARRAY_BOOK_TYPE",new BookListType()));
	        	bookStoredProcedure.declareParameter(new SqlOutParameter("out_result", Types.NUMERIC));
	            bookStoredProcedure.compile();
	            resultMap=bookStoredProcedure.execute(bookIdList);
	            
	            BigDecimal result = (BigDecimal) resultMap.get("out_result");
	            if(!result.equals(-1)) {
	            	LOGGER.info("BookWrapperDao getBookDetailsByIdList result : "+result);
	            	finalBookList = (List)resultMap.get("out_book_list");
	            	LOGGER.debug("BookWrapperDao getBookDetailsByIdList Book details : "+finalBookList);
                 return resultMap;
	            }
	            
	    	}catch(Exception e) {
	    		LOGGER.error(e.getMessage());
	    	}    	
	    	
			return resultMap;
	    }

	 
	 
    
    
}
