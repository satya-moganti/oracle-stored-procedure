package com.jayam.storedprocedure.oracle.repo.types;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.SqlReturnType;

public class BookListType implements SqlReturnType {

	@Override
	public Object getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName) throws SQLException {

		List<BookType> bookList = new ArrayList<>();
		java.sql.Array array = cs.getArray(paramIndex);
		java.sql.ResultSet rs = array.getResultSet();
		int i = 0;
		while(rs.next()) {			
			Struct struct = (Struct) rs.getObject(2);
			Object[] attr = struct.getAttributes();

			BookType bookType = new BookType();
			bookType.setBookid((BigDecimal) attr[0]);
			bookType.setTitle((String) attr[1]);
			bookType.setAuthor((String) attr[2]);
			bookType.setPublishedyear((String) attr[3]);
			bookType.setGenre((String) attr[4]);
			bookType.setPrice((BigDecimal) attr[5]);
			bookList.add(bookType);
		}
		return bookList;

	}




}
