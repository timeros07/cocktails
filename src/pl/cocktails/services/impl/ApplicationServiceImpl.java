package pl.cocktails.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.cocktails.common.AbstractService;
import pl.cocktails.common.SearchCriteria;
import pl.cocktails.data.CocktailData;
import pl.cocktails.data.ContactMessageData;
import pl.cocktails.data.ElementData;
import pl.cocktails.data.dao.ContactMessageDAO;
import pl.cocktails.services.ApplicationService;
import pl.cocktails.services.CocktailService;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class ApplicationServiceImpl extends AbstractService implements ApplicationService {

	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	@Autowired
	private CocktailService cocktailService;
	
	@Autowired
	private ContactMessageDAO contactMessageDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Integer removeOrphanBlobs() {
		Iterator<BlobInfo> iterator = null;
		List<BlobInfo> blobsToCheck = new LinkedList<BlobInfo>(); 
		BlobInfoFactory blobInfoFactory = new BlobInfoFactory(datastore);
		
		iterator = blobInfoFactory.queryBlobInfos();
		while(iterator.hasNext()){
			blobsToCheck.add(iterator.next());
		}
		
		List<ElementData> elements = cocktailService.findElements(new SearchCriteria());
		List<CocktailData> cocktails = cocktailService.findCocktails(new SearchCriteria());
		List<BlobKey> keysToRemove = new ArrayList<BlobKey>();
		
		for(BlobInfo info : blobsToCheck){
			boolean remove = true;
			for(ElementData element : elements){
				if(info.getBlobKey().getKeyString().equals(element.getBlobKey())){
					remove = false;
				}
			}
			
			for(CocktailData cocktail : cocktails){
				if(info.getBlobKey().getKeyString().equals(cocktail.getBlobKey())){
					remove = false;
				}
			}
			if(remove)
				keysToRemove.add(info.getBlobKey());
		}
		
		for(BlobKey key : keysToRemove){
			blobstoreService.delete(key);
		}
		
		return keysToRemove.size();

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void sendContactMessage(ContactMessageData message) {
		contactMessageDAO.createItem(getPersistenceManager(), message);
	}

	@Override
	public List<ContactMessageData> getContactMessages() {
		return contactMessageDAO.getItems(getPersistenceManager());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeContactMessage(Long id) {
		contactMessageDAO.removeItem(getPersistenceManager(), id);
	}

	@Override
	public ContactMessageData getContactMessage(Long id) {
		return contactMessageDAO.getItem(getPersistenceManager(), id);
	}

}
