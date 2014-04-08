package pl.cocktails.data;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@PersistenceCapable
public class ContactMessageData implements Serializable {

	private static final long serialVersionUID = 1112630706177606290L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	@NotBlank(message="errors.contact.topic.empty")
	private String topic;
	
	@Persistent
	@NotBlank(message="errors.contact.content.empty")
	private String content;
	
	@Persistent
	@Email(message="errors.contact.email.invalid")
	@NotBlank(message="errors.contact.email.empty")
	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
