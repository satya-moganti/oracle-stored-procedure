package com.jayam.storedprocedure.oracle.repo.types;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.Objects;

import org.springframework.jdbc.core.SqlReturnType;

public class BookType implements SqlReturnType {

	private BigDecimal bookid;
	private String title;
	private String author;
	private String	publishedyear;
	private String genre;
	private BigDecimal price;
	
	


	public BigDecimal getBookid() {
		return bookid;
	}


	public void setBookid(BigDecimal bookid) {
		this.bookid = bookid;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublishedyear() {
		return publishedyear;
	}


	public void setPublishedyear(String publishedyear) {
		this.publishedyear = publishedyear;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	
	@Override
	public int hashCode() {
		return Objects.hash(author, bookid, genre, price, publishedyear, title);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookType other = (BookType) obj;
		return Objects.equals(author, other.author) && Objects.equals(bookid, other.bookid)
				&& Objects.equals(genre, other.genre) && Objects.equals(price, other.price)
				&& Objects.equals(publishedyear, other.publishedyear) && Objects.equals(title, other.title);
	}


	@Override
	public Object getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName) throws SQLException {
		Struct struct = (Struct) cs.getObject(paramIndex);
		Object[] attr = struct.getAttributes();
		BookType bookType = new BookType();
		bookType.setBookid((BigDecimal) attr[0]);
		bookType.setTitle((String) attr[1]);
		bookType.setAuthor((String) attr[2]);
		bookType.setPublishedyear((String) attr[3]);
		bookType.setGenre((String) attr[4]);
		bookType.setPrice((BigDecimal) attr[5]);		
		return bookType;
	}


	@Override
	public String toString() {
		return "TBook [bookid=" + bookid + ", title=" + title + ", author=" + author + ", publishedyear="
				+ publishedyear + ", genre=" + genre + ", price=" + price + "]";
	}


}
