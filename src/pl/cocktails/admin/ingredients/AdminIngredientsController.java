package pl.cocktails.admin.ingredients;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

import pl.cocktails.admin.JSONResponse;
import pl.cocktails.admin.JSONUploadMessage;
import pl.cocktails.admin.services.CocktailService;
import pl.cocktails.admin.ingredients.data.ElementData;

@Controller
@RequestMapping("/admin")
public class AdminIngredientsController {
	
	
	@Autowired
	private CocktailService cocktailService;
	
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	/******IngredientCreate*****************************************/
	
	@RequestMapping(value="/ingredientCreate", method=RequestMethod.GET)
	public String initCreateIngredientForm(Model model) {
		element = new ElementData();
		String uploadUrl = blobstoreService.createUploadUrl("/admin/ingredientUpload");
		model.addAttribute("uploadUrl", uploadUrl);
		model.addAttribute(element);
		return "adminIngredientCreate";
	}
	
	@RequestMapping(value="/ingredientCreate", method=RequestMethod.POST, params="job=CREATE")
	public @ResponseBody JSONResponse addIngredient(@Validated ElementData element, BindingResult bindingResult, HttpServletRequest req) {
		AdminElementValidator validator = new AdminElementValidator();
		validator.validate(element, bindingResult);
	
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		cocktailService.createElement(element);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "ingredients");
	}
	
	/******IngredientModify*****************************************/
	
	private ElementData element = new ElementData();
	
	@ModelAttribute
	public void addAttributes(WebRequest request, Model model) {
		model.addAttribute("elementData", element);
	}
	
	@RequestMapping(value="/ingredientModify", method=RequestMethod.GET)
	public String initModify(@RequestParam(required= true) Long id, Model model){
		
		element = cocktailService.getElement(id);
		model.addAttribute(element);
		String uploadUrl = blobstoreService.createUploadUrl("/admin/ingredientUpload");
		model.addAttribute("uploadUrl", uploadUrl);
		
		return "adminIngredientModify";
	}
	
	@RequestMapping(value="/ingredientModify", method=RequestMethod.POST, params="job=SAVE")
	public @ResponseBody JSONResponse modifyIngredient(@ModelAttribute ElementData element, BindingResult bindingResult) {
		AdminElementValidator validator = new AdminElementValidator();
		validator.validate(element, bindingResult);
	
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		cocktailService.modifyElement(element);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "ingredientDetails?id=" + element.getId());
	}
	
	/******IngredientDetails*****************************************/
	
	@RequestMapping(value="/ingredientDetails", method=RequestMethod.GET)
	public String showIngredient(@RequestParam(required= true) Long id, Model model){
		model.addAttribute(cocktailService.getElement(id));
		return "adminIngredientDetails";
	}
	
	@RequestMapping(value="/ingredientDetails", method=RequestMethod.POST,  params="job=REMOVE")
	public @ResponseBody JSONResponse removeIngredient(@RequestParam(required= true) Long id, Model model){
		cocktailService.removeElement(id);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "ingredients");
	}
	
	/******Ingredients*****************************************/
	
	@RequestMapping(value="/ingredients", method=RequestMethod.GET)
	public String showIngredients(Model model){
		List<ElementData> elements = cocktailService.findElements();
		model.addAttribute("ingredients", elements);
		return "adminIngredients";
	}
	
	@RequestMapping(value="/ingredientUpload", method=RequestMethod.POST)
	public @ResponseBody JSONUploadMessage uploadImage(HttpServletRequest req, Model model){
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
	    List<BlobKey> blobKeys = blobs.get("uploadFile");
	    BlobKey blobKey = blobKeys.get(0);
	    
	    ImagesService imageService = ImagesServiceFactory.getImagesService();
    	ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(blobKey);
    	String imageURL = imageService.getServingUrl(options);
	    
		return new JSONUploadMessage(true,JSONUploadMessage.UPLOAD_SUCCESS, blobKey.getKeyString(), imageURL);
	}
	
}
