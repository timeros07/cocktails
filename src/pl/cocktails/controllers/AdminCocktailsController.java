package pl.cocktails.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

import pl.cocktails.common.JSONResponse;
import pl.cocktails.common.JSONUploadMessage;
import pl.cocktails.common.PageableResult;
import pl.cocktails.common.ResultList;
import pl.cocktails.common.SearchCriteria;
import pl.cocktails.data.CocktailData;
import pl.cocktails.data.ElementData;
import pl.cocktails.data.IngredientData;
import pl.cocktails.services.CocktailService;

@Controller
@RequestMapping("/admin")
public class AdminCocktailsController {
	
	@Autowired
	private CocktailService cocktailService;
	
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	private IngredientData ingredient = new IngredientData();
	private CocktailData cocktailData = new CocktailData();
	
	@ModelAttribute
	public void addAttributes(WebRequest request, Model model) {
		model.addAttribute("ingredient", ingredient);
		model.addAttribute("cocktailData", cocktailData);
		
		List<ElementData> elements = cocktailService.findElements(new SearchCriteria());
		Map<String, String> elementsMap = new HashMap<String, String>();
		for(ElementData data: elements){
			elementsMap.put(data.getId()+"", data.getName());
		}
		
		model.addAttribute("elements", elementsMap);
		
		Map<String, String> statusesMap = new HashMap<String, String>();
		statusesMap.put("I", "Nieaktywny");
		statusesMap.put("A", "Aktywny");
		model.addAttribute("statuses", statusesMap);
	}
	
	/******CocktialCreate*****************************************/
	
	@RequestMapping(value="/cocktailCreate", method=RequestMethod.GET)
	public String initCreateCocktailForm(Model model) {
		cocktailData = new CocktailData();
		
		String uploadUrl = blobstoreService.createUploadUrl("/admin/cocktailUpload");
		model.addAttribute("uploadUrl", uploadUrl);
		cocktailData.setIngredients(new ArrayList<IngredientData>());
	
		return "adminCocktailCreate";
	}
	
	@RequestMapping(value="/cocktailCreate", method=RequestMethod.POST, params="job=CREATE")
	public @ResponseBody JSONResponse createCocktail(@Valid CocktailData cocktail, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		cocktailService.createCocktail(cocktail);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "cocktails");
	}
	
	@RequestMapping(value="/cocktailCreate", method=RequestMethod.POST, params="job=ADD_INGREDIENT")
	public @ResponseBody JSONResponse addIngredient(@Valid IngredientData ingredient, BindingResult bindingResult) {
		if(ingredient.getElement().getId() == null){
			bindingResult.addError(new FieldError("element.id","element.id", "errors.ingredient.element.empty"));
		}
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		ingredient.setElement(cocktailService.getElement(ingredient.getElement().getId()));
		ingredient.setKey(KeyFactory.createKey(IngredientData.class.getSimpleName(), (-1)*(cocktailData.getIngredients().size()+1)));
		cocktailData.getIngredients().add(ingredient);
		ingredient = new IngredientData();
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS);
	}
	
	@RequestMapping(value="/cocktailCreate", method=RequestMethod.POST, params="job=LOAD_INGREDIENTS")
	public @ResponseBody List<IngredientData> getIngredients() {
		return cocktailData.getIngredients();
	}
	
	/******CocktialModify*****************************************/
	
	@RequestMapping(value="/cocktailModify", method=RequestMethod.GET)
	public String initModify(@RequestParam(required= true) Long id, Model model){
		
		String uploadUrl = blobstoreService.createUploadUrl("/admin/cocktailUpload");
		model.addAttribute("uploadUrl", uploadUrl);
		cocktailData = cocktailService.getCocktail(id);
	
		return "adminCocktailModify";
	}
	
	@RequestMapping(value="/cocktailModify", method=RequestMethod.POST, params="job=SAVE")
	public @ResponseBody JSONResponse modifyCocktail(@Valid CocktailData cocktail, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		for(IngredientData data : cocktail.getIngredients()){
			if(data.getKey().getId() <= 0){
				data.setKey(null);
			}
		}
		cocktailService.modifyCocktail(cocktail);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "cocktailDetails?id=" + cocktail.getId());
	}
	
	/******CocktialDetails*****************************************/
	
	@RequestMapping(value="/cocktailDetails", method=RequestMethod.GET)
	public String showCocktail(@RequestParam(required= true) Long id, Model model){
		cocktailData = cocktailService.getCocktail(id);
		return "adminCocktailDetails";
	}
	
	@RequestMapping(value="/cocktailDetails", method=RequestMethod.POST, params="job=REMOVE")
	public @ResponseBody JSONResponse removeCocktail(@RequestParam(required= true) Long id, Model model){
		cocktailService.removeCocktail(id);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "cocktails");
	}
	
	@RequestMapping(value="/removeIngredient", method=RequestMethod.GET)
	public @ResponseBody String removeIngredient(@RequestParam Long id) {
		for(int i=0 ; i<cocktailData.getIngredients().size() ; i++){
			if(id.equals(cocktailData.getIngredients().get(i).getKey().getId())){
				cocktailData.getIngredients().remove(i);
			}
		}
		
		return "success";
	}

	@RequestMapping(value="/cocktails", method=RequestMethod.GET)
	public String showCocktails(Model model, @RequestParam(required= false) Long p){
		
		SearchCriteria criteria = new SearchCriteria(p == null ? 1 : p);
		ResultList<CocktailData> cocktails = cocktailService.findCocktails(criteria);
		model.addAttribute("cocktails", cocktails);
		model.addAttribute("paging", new PageableResult(cocktails.getRealSize(), p == null ? 1 : p));
		return "adminCocktails";
	}
	
	@RequestMapping(value="/cocktailUpload", method=RequestMethod.POST)
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
