package com.jayam.storedprocedure.oracle.repo.types;

import java.math.BigDecimal;
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

public class  BookIdValueList extends AbstractSqlTypeValue{


	private List<BigDecimal> bookIds = new ArrayList<BigDecimal>();

	public BookIdValueList(List<BigDecimal> bookIds) {
		this.bookIds= bookIds;
	}


	@Override
	protected Object createTypeValue(Connection con, int sqlType, String typeName) throws SQLException {

		BigDecimal[] values =new BigDecimal[bookIds.size()];
        for(int i =0;i<bookIds.size();i++) {
        	values[i]= bookIds.get(i);
        }		
		return  con.unwrap(OracleConnection.class).createOracleArray(
				 typeName, values);
	}

}
