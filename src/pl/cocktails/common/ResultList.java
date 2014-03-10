package pl.cocktails.common;

import java.util.ArrayList;
import java.util.Collection;

public class ResultList<T> extends ArrayList<T> {

	private static final long serialVersionUID = 7193889016008845931L;
	
	private boolean partial;
	private long realSize;
	
	/**
	 * @return Returns the realSize.
	 */
	public long getRealSize() {
		return partial ? realSize : size();
	}
	
	/**
	 * @param realSize The realSize to set.
	 */
	public void setRealSize(long realSize) {
		this.realSize = realSize;
	}
	
	/**
	 * 
	 */
	public ResultList() {
		super();
	}

	/**
	 * @param initialCapacity
	 */
	public ResultList(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * @param c
	 */
	public ResultList(Collection<T> c) {
		super(c);
	}
	
	/**
	 * @return Returns the partial.
	 */
	public boolean isPartial() {
		return partial;
	}
	
	/**
	 * @param partial The partial to set.
	 */
	public void setPartial(boolean partial) {
		this.partial = partial;
	}
}

