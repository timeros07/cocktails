package pl.cocktails.controllers;

import java.util.ArrayList;
import java.util.HashMap;
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

import pl.cocktails.common.JSONResponse;
import pl.cocktails.common.JSONUploadMessage;
import pl.cocktails.common.PageableResult;
import pl.cocktails.common.ResultList;
import pl.cocktails.common.SearchCriteria;
import pl.cocktails.data.ElementCategoryData;
import pl.cocktails.data.ElementData;
import pl.cocktails.data.dao.ElementDAO;
import pl.cocktails.services.CocktailService;
import pl.cocktails.validators.AdminElementValidator;

@Controller
@RequestMapping("/admin")
public class AdminIngredientsController {
	
	
	@Autowired
	private CocktailService cocktailService;
	
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	private ElementData element;
	private List<ElementCategoryData> categories = new ArrayList<ElementCategoryData>();
	
	/******IngredientCreate*****************************************/
	
	@RequestMapping(value="/ingredientCreate", method=RequestMethod.GET)
	public String initCreateIngredientForm(Model model) {
		element = new ElementData();
		categories = cocktailService.findElementCategories();
		String uploadUrl = blobstoreService.createUploadUrl("/admin/ingredientUpload");
		model.addAttribute("uploadUrl", uploadUrl);
		model.addAttribute(element);
		return "adminIngredientCreate";
	}
	
	@RequestMapping(value="/ingredientCreate", method=RequestMethod.POST, params="job=CREATE")
	public @ResponseBody JSONResponse save(@Validated ElementData element, BindingResult bindingResult, HttpServletRequest req) {
		AdminElementValidator validator = new AdminElementValidator();
		validator.validate(element, bindingResult);
	
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		element.setCategory(cocktailService.getElementCategory(element.getCategory().getId()));
		cocktailService.createElement(element);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "ingredients");
	}
	
	/******IngredientModify*****************************************/
	
	@ModelAttribute
	public void addAttributes(WebRequest request, Model model) {
		model.addAttribute("elementData", element);
		
		Map<String, String> statusesMap = new HashMap<String, String>();
		statusesMap.put("I", "Nieaktywny");
		statusesMap.put("A", "Aktywny");
		model.addAttribute("statuses", statusesMap);
		
		Map<Long, String> categoriesMap = new HashMap<Long, String>();
		for(ElementCategoryData category : categories){
			categoriesMap.put(category.getId(), category.getName());
		}
		
		model.addAttribute("categories", categoriesMap);
	}
	
	@RequestMapping(value="/ingredientModify", method=RequestMethod.GET)
	public String initModify(@RequestParam(required= true) Long id, Model model){
		
		element = cocktailService.getElement(id);
		model.addAttribute(element);
		String uploadUrl = blobstoreService.createUploadUrl("/admin/ingredientUpload");
		model.addAttribute("uploadUrl", uploadUrl);
		categories = cocktailService.findElementCategories();
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
		categories = cocktailService.findElementCategories();
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
	public String showIngredients(Model model, @RequestParam(required= false) Long p){
			
		SearchCriteria criteria = new SearchCriteria(p == null ? 1 : p);
		ResultList<ElementData> elements = cocktailService.findElements(criteria);
		model.addAttribute("ingredients", elements);
		model.addAttribute("paging", new PageableResult(elements.getRealSize(), p == null ? 1 : p));
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
