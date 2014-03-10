package pl.cocktails.common;

import java.io.Serializable;


public class PageableResult implements Serializable {
	
	private static final long serialVersionUID = -4210290241038502367L;
	
	private Long currentPageNr;
	
	private Long pagesCount;

	public PageableResult(){}
	
	public PageableResult(long realSize, long currentPageNr){
		pagesCount = realSize/SearchCriteria.PAGE_SIZE;
		if(realSize%SearchCriteria.PAGE_SIZE !=0){
			pagesCount++;
		}
		this.currentPageNr = currentPageNr;
	}
	
	public Long getCurrentPageNr() {
		return currentPageNr;
	}

	public void setCurrentPageNr(Long currentPageNr) {
		this.currentPageNr = currentPageNr;
	}

	public Long getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(Long pagesCount) {
		this.pagesCount = pagesCount;
	}
	
}
