package pl.cocktails.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import pl.cocktails.common.data.CocktailData;
import pl.cocktails.common.data.CocktailService;
import pl.cocktails.common.data.ElementData;
import pl.cocktails.common.data.IngredientData;

@Controller
@RequestMapping("/admin")
public class AdminIngredientsController {
	
	
	@Autowired
	private CocktailService cocktailService;
	
	@RequestMapping(value="/ingredientCreate", method=RequestMethod.GET)
	public String initCreateIngredientForm(Model model) {
		model.addAttribute(new ElementData());
		
		return "adminIngredientCreate";
	}
	
	@RequestMapping(value="/ingredientCreate", method=RequestMethod.POST)
	public String addIngredient(@Validated ElementData element, BindingResult bindingResult) {
		AdminIngredientValidator validator = new AdminIngredientValidator();
		validator.validate(element, bindingResult);
	
		if(bindingResult.hasErrors()){
			return "adminIngredientCreate";
		}
		cocktailService.createElement(element);
		return "redirect:/admin/ingredients";
	}
	
	@RequestMapping(value="/ingredientDetails", method=RequestMethod.GET)
	public String showIngredient(@RequestParam(required= true) Long id, Model model){
		model.addAttribute(cocktailService.getElement(id));
		return "adminIngredientDetails";
	}
	
	@RequestMapping(value="/ingredientModify", method=RequestMethod.GET)
	public String initModify(@RequestParam(required= true) Long id, Model model){
		
		model.addAttribute(cocktailService.getElement(id));
		return "adminIngredientModify";
	}
	
	@RequestMapping(value="/ingredientModify", method=RequestMethod.POST)
	public String modifyIngredient(@Validated ElementData element, BindingResult bindingResult) {
		AdminIngredientValidator validator = new AdminIngredientValidator();
		validator.validate(element, bindingResult);
	
		if(bindingResult.hasErrors()){
			return "adminIngredientModify";
		}
		cocktailService.modifyElement(element);
		return "redirect:/admin/ingredientDetails?id=" + element.getId();
	}
	
	@RequestMapping(value="/ingredients", method=RequestMethod.GET)
	public String showIngredients(Model model){
		List<ElementData> elements = cocktailService.findElements();
		model.addAttribute("ingredients", elements);
		return "adminIngredients";
	}
	
	@RequestMapping(value="/ingredientRemove", method=RequestMethod.POST)
	public @ResponseBody String removeIngredient(@RequestParam(required= true) Long id, Model model){
		cocktailService.removeElement(id);
		return "success";
	}
}
