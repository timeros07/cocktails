package pl.cocktails.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

public class Upload extends HttpServlet {

	private static final long serialVersionUID = 6914153987660386110L;
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

    	String imageURL;
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("ingredientFile");
        BlobKey blobKey = blobKeys.get(0);
        
        res.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = res.getWriter();
        try{
        	
        		
        	out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        	out.println("<fileInfo>");
        	
        	if(blobKey != null) {
        		
        		ImagesService imageService = ImagesServiceFactory.getImagesService();
        		ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(blobKey);
        		imageURL = imageService.getServingUrl(options);
        		
        		out.println("<blobKey>");
        		out.println(blobKey.getKeyString());
        		out.println("</blobKey>");
        	
        		out.println("<imageURL>");
        		out.println(imageURL);
        		out.println("</imageURL>");
        	}
        	else {
        		out.println("<blobKey>error</blobKey>");
        	}
        	
        	out.println("</fileInfo>");
        }
        finally{
        	out.close();
        }
               
    }
}