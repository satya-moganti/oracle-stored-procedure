package com.jayam.storedprocedure.oracle.repo.types;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

import org.springframework.jdbc.core.support.AbstractSqlTypeValue;

import com.jayam.storedprocedure.oracle.dto.TBook;

import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class  BookTypeValue extends AbstractSqlTypeValue{

	private TBook tbook;

	public BookTypeValue(TBook tbook) {
		this.tbook=tbook;
	}


	@Override
	protected Object createTypeValue(Connection con, int sqlType, String typeName) throws SQLException {

		Connection connection = con.unwrap(oracle.jdbc.OracleConnection.class);
		Object[] dbValues = new Object[] {tbook.getBookid(),tbook.getTitle(),tbook.getAuthor(),
				tbook.getPublishedyear(),tbook.getGenre(),tbook.getPrice()};
		Struct item = connection.createStruct(typeName, dbValues);
		return item;
	}




}
