package com.jayam.storedprocedure.oracle.repo;
import javax.sql.DataSource;

import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;


@Component
public class BookStoredProcedure extends StoredProcedure {
	
    public BookStoredProcedure(DataSource dataSource) {
        super(dataSource, "default");
        
       
    }   
    
}
