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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContext;

import pl.cocktails.common.data.CocktailData;
import pl.cocktails.common.data.CocktailService;
import pl.cocktails.common.data.ElementData;
import pl.cocktails.common.data.IngredientData;

@Controller
@RequestMapping("/admin")
public class AdminIngredientsController {
	
	
	@Autowired
	private CocktailService cocktailService;
	
	@RequestMapping(value="/ingredient", method=RequestMethod.GET, params="create")
	public String initCreateIngredientForm(Model model) {
		model.addAttribute(new ElementData());
		
		return "adminIngredientCreate";
	}
	
	@RequestMapping(value="/ingredient", method=RequestMethod.POST)
	public String addIngredient(@Validated ElementData element, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "adminIngredientCreate";
		}
		cocktailService.createElement(element);
		return "redirect:/admin/ingredients";
	}
	
	@RequestMapping(value="/ingredient/{elementId}", method=RequestMethod.GET)
	public String showIngredient(@PathVariable Long elementId, Model model){
		model.addAttribute(cocktailService.getElement(elementId));
		return "adminIngredientCreate";
	}
	
	@RequestMapping(value="/ingredients", method=RequestMethod.GET)
	public String showIngredients(Model model){
		List<ElementData> elements = cocktailService.findElements();
		model.addAttribute("ingredients", elements);
		return "adminIngredients";
	}
}
