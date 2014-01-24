package pl.cocktails.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



@PersistenceCapable
public class CocktailData implements Serializable {
	
	private static final long serialVersionUID = -3388400727300450298L;

	public interface Status{
		public static final String ACTIVE = "Aktywny";
		public static final String INACTIVE = "Nieaktywny";
	}
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String name;
	
	@Persistent
	private String description;
	
	@Persistent
	private List<IngredientData> ingredients;
	
	@Persistent
	private int taste;
	
	@Persistent
	private int type;
	
	@Persistent
	private int makingTime;
	
	@Persistent
	private String author;
	
	@Persistent
	private String blobKey;
	
	@Persistent
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public CocktailData(){}
	public CocktailData(String name, String description, List<IngredientData> ingreients, String author){
		this.name = name;
		this.description = description;
		this.ingredients = ingreients;
		this.author = author;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTaste() {
		return taste;
	}
	public void setTaste(int taste) {
		this.taste = taste;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getMaking_time() {
		return makingTime;
	}
	public void setMaking_time(int making_time) {
		this.makingTime = making_time;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBlobKey() {
		return blobKey;
	}
	public void setBlobKey(String blobkey) {
		this.blobKey = blobkey;
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<IngredientData> getIngredients() {
		if(ingredients == null)
			ingredients = new ArrayList<IngredientData>();
		return ingredients;
	}
	public void setIngredients(List<IngredientData> ingredients) {
		this.ingredients = ingredients;
	}
	public int getMakingTime() {
		return makingTime;
	}
	public void setMakingTime(int makingTime) {
		this.makingTime = makingTime;
	}
}
