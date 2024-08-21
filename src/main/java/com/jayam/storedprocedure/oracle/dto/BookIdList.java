package com.jayam.storedprocedure.oracle.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookIdList {

	
	private List<BigDecimal> bookIdList = new ArrayList<BigDecimal>();

	public List<BigDecimal> getBookIdList() {
		return bookIdList;
	}

	public void setBookIdList(List<BigDecimal> bookIdList) {
		this.bookIdList = bookIdList;
	}
	
	
	
}
