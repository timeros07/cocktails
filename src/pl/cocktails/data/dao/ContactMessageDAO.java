package pl.cocktails.data.dao;

import org.springframework.stereotype.Repository;

import pl.cocktails.data.ContactMessageData;

@Repository
public class ContactMessageDAO extends AbstractDAO<ContactMessageData>{
	
	private ContactMessageDAO(){
		super(ContactMessageData.class);
	}
}
