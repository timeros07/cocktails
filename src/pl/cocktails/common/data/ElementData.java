package pl.cocktails.common.data;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class ElementData implements Serializable{
	
	private static final long serialVersionUID = -187399049842911717L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String name;
	
	@Persistent
	private String description;
	
	private int type;
	
	@Persistent	
	private String blobKey;
	
	@Persistent
	private String imageURL;
	
	public ElementData(){}
	
	public ElementData(String name, String description){
		this.name = name;
		this.description = description;
	}

	public String getImageURL() {
		if(imageURL == null){
			imageURL = "";
		}
		return imageURL.trim();
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBlobKey() {
		if(blobKey == null){
			blobKey = "";
		}
		return blobKey.trim();
	}
	public void setBlobKey(String blobkey) {
		this.blobKey = blobkey;
	}
	
	
}
