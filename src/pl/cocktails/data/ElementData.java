package pl.cocktails.data;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.appengine.datanucleus.annotations.Unowned;

import pl.cocktails.common.Blobkey;


@PersistenceCapable
public class ElementData implements Serializable{
	
	private static final long serialVersionUID = -187399049842911717L;

	public interface Status{
		public static final String ACTIVE = "A";
		public static final String INACTIVE = "I";
	}
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	@NotBlank(message="errors.name.empty")
	private String name;
	
	@Persistent
	@NotBlank(message="errors.description.empty")
	private String description;
	
	@Blobkey(getterName = "getBlobKey")
	@Persistent	
	private String blobKey;
	
	@Persistent
	@NotBlank(message="errors.status.empty")
	private String status;
	
	@Persistent
	@Unowned
	private ElementCategoryData category;
	
	public ElementCategoryData getCategory() {
		return category;
	}

	public void setCategory(ElementCategoryData category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ElementData(){}
	
	public ElementData(String name, String description){
		this.name = name;
		this.description = description;
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
