package pl.cocktails.admin.cocktails;

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
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

import pl.cocktails.admin.JSONResponse;
import pl.cocktails.admin.JSONUploadMessage;
import pl.cocktails.admin.cocktails.data.CocktailData;
import pl.cocktails.admin.services.CocktailService;
import pl.cocktails.admin.ingredients.data.ElementData;
import pl.cocktails.admin.cocktails.data.IngredientData;

@Controller
@RequestMapping("/admin")
public class AdminCocktailsController {
	
	@Autowired
	private CocktailService cocktailService;
	
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	private List<IngredientData> ingredients = new ArrayList<IngredientData>();
	private IngredientData ingredient = new IngredientData();
	
	@ModelAttribute
	public void addAttributes(WebRequest request, Model model) {
		model.addAttribute("ingredient", ingredient);
		model.addAttribute("ingredients", ingredients);
		
		List<ElementData> elements = cocktailService.findElements();
		Map<String, String> elementsMap = new HashMap<String, String>();
		for(ElementData data: elements){
			elementsMap.put(data.getId()+"", data.getName());
		}
		
		model.addAttribute("elements", elementsMap);
		model.addAttribute("cocktailData", cocktailData);
		
	}
	
	/******CocktialCreate*****************************************/
	
	@RequestMapping(value="/cocktailCreate", method=RequestMethod.GET)
	public String initCreateCocktailForm(Model model) {
		model.addAttribute(new CocktailData());
		
		String uploadUrl = blobstoreService.createUploadUrl("/admin/cocktailUpload");
		model.addAttribute("uploadUrl", uploadUrl);
		ingredients = new ArrayList<IngredientData>();
	
		return "adminCocktailCreate";
	}
	
	@RequestMapping(value="/cocktailCreate", method=RequestMethod.POST, params="job=CREATE")
	public @ResponseBody JSONResponse createCocktail(@Validated CocktailData cocktail, BindingResult bindingResult) {
		cocktail.setIngredients(ingredients);
		
		AdminCocktailValidator validator = new AdminCocktailValidator();
		validator.validate(cocktail, bindingResult);
	
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		cocktail.setIngredients(ingredients);
		cocktailService.createCocktail(cocktail);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "cocktails");
	}
	
	@RequestMapping(value="/cocktailCreate", method=RequestMethod.POST, params="job=ADD_INGREDIENT")
	public @ResponseBody JSONResponse addIngredient(@Validated IngredientData ingredient, BindingResult bindingResult) {
		AdminIngredientValidator validator = new AdminIngredientValidator();
		validator.validate(ingredient, bindingResult);
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		ingredient.setElement(cocktailService.getElement(ingredient.getElement().getId()));
		ingredient.setKey(KeyFactory.createKey(IngredientData.class.getSimpleName(), (-1)*(ingredients.size()+1)));
		ingredients.add(ingredient);
		ingredient = new IngredientData();
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS);
	}
	
	@RequestMapping(value="/cocktailCreate", method=RequestMethod.POST, params="job=LOAD_INGREDIENTS")
	public @ResponseBody List<IngredientData> getIngredients() {
		return ingredients;
	}
	
	/******CocktialModify*****************************************/
	
	CocktailData cocktailData = new CocktailData();
	
	@RequestMapping(value="/cocktailModify", method=RequestMethod.GET)
	public String initModify(@RequestParam(required= true) Long id, Model model){
		
		String uploadUrl = blobstoreService.createUploadUrl("/admin/cocktailUpload");
		model.addAttribute("uploadUrl", uploadUrl);
		cocktailData = cocktailService.getCocktail(id);
		model.addAttribute(cocktailData);
		ingredients = cocktailData.getIngredients();
		
		return "adminCocktailModify";
	}
	
	@RequestMapping(value="/cocktailModify", method=RequestMethod.POST, params="job=SAVE")
	public @ResponseBody JSONResponse modifyCocktail(@ModelAttribute CocktailData cocktail, BindingResult bindingResult) {
		cocktail.setIngredients(ingredients);
		
		AdminCocktailValidator validator = new AdminCocktailValidator();
		validator.validate(cocktail, bindingResult);
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
		CocktailData cocktail = cocktailService.getCocktail(id);
		model.addAttribute(cocktail);
		ingredients = cocktail.getIngredients();
		return "adminCocktailDetails";
	}
	
	@RequestMapping(value="/cocktailDetails", method=RequestMethod.POST, params="job=REMOVE")
	public @ResponseBody JSONResponse removeCocktail(@RequestParam(required= true) Long id, Model model){
		cocktailService.removeCocktail(id);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "cocktails");
	}
	
	@RequestMapping(value="/removeIngredient", method=RequestMethod.GET)
	public @ResponseBody String removeIngredient(@RequestParam Long id) {
		for(int i=0 ; i<ingredients.size() ; i++){
			if(id.equals(ingredients.get(i).getKey().getId())){
				ingredients.remove(i);
			}
		}
		
		return "success";
	}

	@RequestMapping(value="/cocktails", method=RequestMethod.GET)
	public String showCocktails(Model model){
		List<CocktailData> cocktails = cocktailService.findCocktails();
		model.addAttribute("cocktails", cocktails);
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
