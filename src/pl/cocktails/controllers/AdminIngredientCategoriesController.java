package pl.cocktails.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import pl.cocktails.common.JSONResponse;
import pl.cocktails.common.XEditableForm;
import pl.cocktails.data.ElementCategoryData;
import pl.cocktails.services.CocktailService;

@Controller
@RequestMapping("/admin")
public class AdminIngredientCategoriesController {
	
	@Autowired
	private CocktailService cocktailService;
	
	@ModelAttribute
	public void addAttributes(WebRequest request, Model model) {
		Map<String, String> statusesMap = new HashMap<String, String>();
		statusesMap.put("I", "Nieaktywny");
		statusesMap.put("A", "Aktywny");
		model.addAttribute("statuses", statusesMap);
	}
	
	/******Ingredients*****************************************/
	
	@RequestMapping(value="/ingredientCategories", method=RequestMethod.GET)
	public String getItems(Model model){
		List<ElementCategoryData> categories = cocktailService.findElementCategories();
		
		
		model.addAttribute("categories", categories);
		
		return "adminIngredientCategories";
	}
	
	@RequestMapping(value="/ingredientCategories", method=RequestMethod.POST, params="job=MODIFY")
	public @ResponseBody JSONResponse modifyCategory(@ModelAttribute XEditableForm form, BindingResult bindingResult) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	
		ElementCategoryData categoryToModify = cocktailService.getElementCategory(form.getPk());
		PropertyUtils.setSimpleProperty(categoryToModify, form.getName(), form.getValue());
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ElementCategoryData>> constraintViolations = validator.validateProperty(categoryToModify, form.getName());
		if(!constraintViolations.isEmpty())
		bindingResult.addError(new ObjectError(constraintViolations.iterator().next().getMessageTemplate(), constraintViolations.iterator().next().getMessage()));
		
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		
		cocktailService.modifyElementCategory(categoryToModify);
		
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS);
	}
	
	/******IngredientCategoryCreate*****************************************/
	
	@RequestMapping(value="/ingredientCategoryCreate", method=RequestMethod.GET)
	public String initCreateIngredientForm(Model model) {
		ElementCategoryData category = new ElementCategoryData();
		model.addAttribute("category", category);;
		return "adminIngredientCategoryCreate";
	}
	
	@RequestMapping(value="/ingredientCategoryCreate", method=RequestMethod.POST, params="job=CREATE")
	public @ResponseBody JSONResponse save(@Valid ElementCategoryData category, BindingResult bindingResult, HttpServletRequest req) {
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		cocktailService.createElementCategory(category);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "ingredientCategories");
	}
	
}
