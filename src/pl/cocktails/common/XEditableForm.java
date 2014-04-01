package pl.cocktails.common;

import java.io.Serializable;

public class XEditableForm implements Serializable{
	

	private static final long serialVersionUID = 2828010601353090148L;

	private String name;
	
	private Object value;
	
	private Long pk;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

}
