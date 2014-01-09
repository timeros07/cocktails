package pl.cocktails.portal.ingredients;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.cocktails.admin.cocktails.data.CocktailData;
import pl.cocktails.admin.services.CocktailService;

@Controller
@RequestMapping("/")
public class CocktailsController {
	
	private List<CocktailData> cocktails;
	
	@Autowired
	private CocktailService cocktailService;
	
	
	@RequestMapping(value="/cocktails", method=RequestMethod.GET)
	public String initCocktails(Model model) {
		cocktails = cocktailService.findCocktails();
		model.addAttribute("cocktails", cocktails);
		return "cocktails";
	}

}
