package pl.cocktails.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.cocktails.common.data.CocktailService;
import pl.cocktails.common.data.ElementData;

@Controller
@RequestMapping("/admin")
public class AdminIngredientsController {
	
	
	@Autowired
	private CocktailService cocktailService;
	
	/******IngredientCreate*****************************************/
	
	@RequestMapping(value="/ingredientCreate", method=RequestMethod.GET)
	public String initCreateIngredientForm(Model model) {
		model.addAttribute(new ElementData());
		
		return "adminIngredientCreate";
	}
	
	@RequestMapping(value="/ingredientCreate", method=RequestMethod.POST, params="job=CREATE")
	public JSONResponse addIngredient(@Validated ElementData element, BindingResult bindingResult) {
		AdminElementValidator validator = new AdminElementValidator();
		validator.validate(element, bindingResult);
	
		if(bindingResult.hasErrors()){
			return new JSONResponse(false, bindingResult.getAllErrors());
		}
		cocktailService.createElement(element);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS, "ingredients");
	}
	
	/******IngredientModify*****************************************/
	
	@RequestMapping(value="/ingredientModify", method=RequestMethod.GET)
	public String initModify(@RequestParam(required= true) Long id, Model model){
		
		model.addAttribute(cocktailService.getElement(id));
		return "adminIngredientModify";
	}
	
	@RequestMapping(value="/ingredientModify", method=RequestMethod.POST, params="job=SAVE")
	public JSONResponse modifyIngredient(@Validated ElementData element, BindingResult bindingResult) {
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
	

}
