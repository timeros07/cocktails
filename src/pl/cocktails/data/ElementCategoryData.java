package pl.cocktails.data;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@PersistenceCapable
public class ElementCategoryData implements Serializable {

	private static final long serialVersionUID = 7259707537800597744L;
	
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
	
	@Persistent
	@NotBlank(message="errors.status.empty")
	private String status;

	public Long getId() {
		return id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
