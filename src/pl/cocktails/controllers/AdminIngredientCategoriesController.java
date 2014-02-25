package pl.cocktails.controllers;

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

import pl.cocktails.common.JSONResponse;
import pl.cocktails.data.ElementCategoryData;
import pl.cocktails.services.CocktailService;
import pl.cocktails.validators.AdminElementValidator;

@Controller
@RequestMapping("/admin")
public class AdminIngredientCategoriesController {
	
	@Autowired
	private CocktailService cocktailService;
	
	private ElementCategoryData category;
	
	@ModelAttribute
	public void addAttributes(WebRequest request, Model model) {
		Map<String, String> statusesMap = new HashMap<String, String>();
		statusesMap.put("I", "Nieaktywny");
		statusesMap.put("A", "Aktywny");
		model.addAttribute("statuses", statusesMap);
	}
	
/******IngredientCategoryCreate*****************************************/
	
	@RequestMapping(value="/ingredientCategoryCreate", method=RequestMethod.GET)
	public String initNew(Model model) {
		category = new ElementCategoryData();
		model.addAttribute("category", category);
		return "adminIngredientCategoryCreate";
	}
	
	@RequestMapping(value="/ingredientCategoryCreate", method=RequestMethod.POST, params="job=CREATE")
	public @ResponseBody JSONResponse create(@Validated ElementCategoryData category, BindingResult bindingResult, HttpServletRequest req) {
		AdminElementValidator validator = new AdminElementValidator();
		validator.validate(category, bindingResult);
	
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		cocktailService.createElementCategory(category);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "ingredientCategories");
	}
	
	/******IngredientModify*****************************************/
	
	@RequestMapping(value="/ingredientCategoryModify", method=RequestMethod.GET)
	public String initModify(@RequestParam(required= true) Long id, Model model){
		
		category = cocktailService.getElementCategory(id);
		model.addAttribute("category", category);
		return "adminIngredientCategoryModify";
	}
	
	@RequestMapping(value="/ingredientCategoryModify", method=RequestMethod.POST, params="job=SAVE")
	public @ResponseBody JSONResponse save(@ModelAttribute ElementCategoryData category, BindingResult bindingResult) {
		AdminElementValidator validator = new AdminElementValidator();
		validator.validate(category, bindingResult);
	
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		cocktailService.modifyElementCategory(category);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "ingredientCategoryDetails?id=" + category.getId());
	}
	
	/******IngredientDetails*****************************************/
	
	@RequestMapping(value="/ingredientCategoryDetails", method=RequestMethod.GET)
	public String getItem(@RequestParam(required= true) Long id, Model model){
		category = cocktailService.getElementCategory(id);
		model.addAttribute("category", category);
		return "adminIngredientCategoryDetails";
	}
	
	@RequestMapping(value="/ingredientCategoryDetails", method=RequestMethod.POST,  params="job=REMOVE")
	public @ResponseBody JSONResponse remove(@RequestParam(required= true) Long id, Model model){
		cocktailService.removeElementCategory(id);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "ingredients");
	}
	
	/******Ingredients*****************************************/
	
	@RequestMapping(value="/ingredientCategories", method=RequestMethod.GET)
	public String getItems(Model model){
		List<ElementCategoryData> categories = cocktailService.findElementCategories();
		model.addAttribute("categories", categories);
		return "adminIngredientCategories";
	}
	
}
