package pl.cocktails.common.data;


import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class IngredientData implements Serializable {
	
	private static final long serialVersionUID = 118851556159141963L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	//private Long id;
	
	@Persistent
	@Unowned
	private ElementData element;
	
	@Persistent
	private int count;
	
	public IngredientData(){}
	public IngredientData(ElementData element, int count){
		this.element = element;
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ElementData getElement(){
		if(element == null)
			element = new ElementData();
		return this.element;
	}
	public void setElement(ElementData element){
		this.element = element;
	}
	/*public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}*/
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
}
