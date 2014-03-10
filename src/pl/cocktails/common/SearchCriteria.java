package pl.cocktails.common;

import java.io.Serializable;

public class SearchCriteria implements Serializable {
	
	private static final long serialVersionUID = -8124635157679053939L;

	public static final int PAGE_SIZE = 5;
	
	private Long pageNr;

	public SearchCriteria(){}
	
	public SearchCriteria(long pageNr){
		this.pageNr = pageNr;
	}
	
	public Long getPageNr() {
		return pageNr;
	}

	public void setPageNr(Long pageNr) {
		this.pageNr = pageNr;
	}

}
