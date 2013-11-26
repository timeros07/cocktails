package pl.cocktails.admin.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.cocktails.admin.cocktails.data.CocktailData;
import pl.cocktails.admin.ingredients.data.ElementData;
import pl.cocktails.admin.services.ApplicationService;
import pl.cocktails.admin.services.CocktailService;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	@Autowired
	CocktailService cocktailService;
	
	public void removeOrphanBlobs() {
		Iterator<BlobInfo> iterator = null;
		List<BlobInfo> blobsToCheck = new LinkedList<BlobInfo>(); 
		BlobInfoFactory blobInfoFactory = new BlobInfoFactory(datastore);
		
		iterator = blobInfoFactory.queryBlobInfos();
		while(iterator.hasNext()){
			blobsToCheck.add(iterator.next());
		}
		
		List<ElementData> elements = cocktailService.findElements();
		List<CocktailData> cocktails = cocktailService.findCocktails();
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

	}

}
