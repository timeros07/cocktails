package pl.cocktails.services;

import java.util.List;

import pl.cocktails.data.ContactMessageData;

public interface ApplicationService {
	
	public Integer removeOrphanBlobs();
	
	public void sendContactMessage(ContactMessageData message);
	
	public List<ContactMessageData> getContactMessages();
	
	public void removeContactMessage(Long id);

	public ContactMessageData getContactMessage(Long id);

}
