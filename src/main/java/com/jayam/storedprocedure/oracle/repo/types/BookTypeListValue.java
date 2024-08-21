package com.jayam.storedprocedure.oracle.repo.types;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.support.AbstractSqlTypeValue;

import com.jayam.storedprocedure.oracle.dto.TBook;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class  BookTypeListValue extends AbstractSqlTypeValue{


	private List<TBook> bookList = new ArrayList<TBook>();

	public BookTypeListValue(List<TBook> bookList) {
		this.bookList= bookList;
	}


	@Override
	protected Object createTypeValue(Connection con, int sqlType, String typeName) throws SQLException {

		Connection connection = con.unwrap(oracle.jdbc.OracleConnection.class);
		STRUCT[] structs =new STRUCT[bookList.size()];
		StructDescriptor itemDescripter = new StructDescriptor("BOOKTYPE",connection);
		for(int i=0;i<bookList.size();i++) {

			TBook tbook = bookList.get(i);
			Object[] dbValues = new Object[] {tbook.getBookid(),tbook.getTitle(),tbook.getAuthor(),
					tbook.getPublishedyear(),tbook.getGenre(),tbook.getPrice()};
			structs[i] = new STRUCT(itemDescripter,connection,dbValues);

		}
		return connection.unwrap(OracleConnection.class).createOracleArray(typeName, structs);
	}

}
