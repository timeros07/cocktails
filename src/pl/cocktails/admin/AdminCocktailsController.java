package pl.cocktails.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.google.appengine.api.datastore.KeyFactory;

import pl.cocktails.common.data.CocktailData;
import pl.cocktails.common.data.CocktailService;
import pl.cocktails.common.data.ElementData;
import pl.cocktails.common.data.IngredientData;

@Controller
@RequestMapping("/admin")
public class AdminCocktailsController {
	
	@Autowired
	private CocktailService cocktailService;
	
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
		
	}
	
	@RequestMapping(value="/cocktailCreate", method=RequestMethod.GET)
	public String initCreateCocktailForm(Model model) {
		model.addAttribute(new CocktailData());

		ingredients = new ArrayList<IngredientData>();
	
		return "adminCocktailCreate";
	}
	
	@RequestMapping(value="/cocktailCreate", method=RequestMethod.POST)
	public String addCocktail(@Validated CocktailData cocktail, BindingResult bindingResult) {
		cocktail.setIngredients(ingredients);
		
		AdminCocktailValidator validator = new AdminCocktailValidator();
		validator.validate(cocktail, bindingResult);
	
		if(bindingResult.hasErrors()){
			return "adminCocktailCreate";
		}
		cocktail.setIngredients(ingredients);
		cocktailService.createCocktail(cocktail);
		return "redirect:/admin/cocktails";
	}
	
	@RequestMapping(value="/cocktailDetails", method=RequestMethod.GET)
	public String showCocktail(@RequestParam(required= true) Long id, Model model){
		CocktailData cocktail = cocktailService.getCocktail(id);
		model.addAttribute(cocktail);
		ingredients = cocktail.getIngredients();
		return "adminCocktailDetails";
	}
	
	@RequestMapping(value="/cocktailModify", method=RequestMethod.GET)
	public String initModify(@RequestParam(required= true) Long id, Model model){
		
		CocktailData cocktail = cocktailService.getCocktail(id);
		model.addAttribute(cocktail);
		ingredients = cocktail.getIngredients();
		
		return "adminCocktailModify";
	}
	
	@RequestMapping(value="/cocktailModify", method=RequestMethod.POST)
	public String modifyCocktail(@Validated CocktailData cocktail, BindingResult bindingResult) {
		cocktail.setIngredients(ingredients);
		
		AdminCocktailValidator validator = new AdminCocktailValidator();
		validator.validate(cocktail, bindingResult);
		if(bindingResult.hasErrors()){
			return "adminCocktailModify";
		}
		for(IngredientData data : cocktail.getIngredients()){
			if(data.getKey().getId() <= 0){
				data.setKey(null);
			}
		}
		cocktailService.modifyCocktail(cocktail);
		return "redirect:/admin/cocktailDetails?id=" + cocktail.getId();
	}
	
	@RequestMapping(value="/cocktails", method=RequestMethod.GET)
	public String showCocktails(Model model){
		List<CocktailData> cocktails = cocktailService.findCocktails();
		model.addAttribute("cocktails", cocktails);
		return "adminCocktails";
	}
	
	@RequestMapping(value="/cocktailRemove", method=RequestMethod.POST)
	public @ResponseBody String removeCocktail(@RequestParam(required= true) Long id, Model model){
		cocktailService.removeCocktail(id);
		return "success";
	}
	
	@RequestMapping(value="/addIngredient", method=RequestMethod.POST)
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
	
	@RequestMapping(value="/getIngredients", method=RequestMethod.POST)
	public @ResponseBody List<IngredientData> getIngredients() {
		return ingredients;
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
	

}
