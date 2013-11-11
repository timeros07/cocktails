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
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Upload extends HttpServlet {

	private static final long serialVersionUID = 6914153987660386110L;
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

    	String imageURL;
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("uploadFile");
        BlobKey blobKey = blobKeys.get(0);
        
        JSONObject obj = new JSONObject();
        
        res.setContentType("application/json;charset=UTF-8");
        
        PrintWriter out = res.getWriter();
        try{
        	ImagesService imageService = ImagesServiceFactory.getImagesService();
        	ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(blobKey);
        	imageURL = imageService.getServingUrl(options);
       	
        	obj.put("success", "true");
        	obj.put("blobkey", blobKey.getKeyString());
        	obj.put("imageUrl", imageURL);
        	
        	out.print(obj);
        	out.flush();
        } catch (JSONException e) {
			e.printStackTrace();
		}
        finally{
        	out.close();
        }
               
    }
}