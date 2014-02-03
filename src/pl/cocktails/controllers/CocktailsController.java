package pl.cocktails.controllers;


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

import pl.cocktails.common.JSONResponse;
import pl.cocktails.data.CocktailData;
import pl.cocktails.data.CocktailRateData;
import pl.cocktails.services.CocktailService;

@Controller
@RequestMapping("/")
public class CocktailsController {
	
	private List<CocktailData> cocktails;
	private CocktailData cocktail;
	
	@Autowired
	private CocktailService cocktailService;
	
	@RequestMapping(value="/cocktails", method=RequestMethod.GET)
	public String initCocktails(Model model) {
		cocktails = cocktailService.findCocktails();
		model.addAttribute("cocktails", cocktails);
		return "cocktails";
	}
	
	@RequestMapping(value="/cocktailDetails", method=RequestMethod.GET)
	public String showCocktail(@RequestParam(required= true) Long id, Model model){
		cocktail = cocktailService.getCocktail(id);
		model.addAttribute(cocktail);
		return "cocktailDetails";
	}
	
	@RequestMapping(value="/cocktailDetails", method=RequestMethod.POST, params="job=RATE")
	public @ResponseBody JSONResponse rateCocktail(Integer rate, Long userId, Model model) {
		
		CocktailRateData cocktailRate = new CocktailRateData();
		cocktailRate.setCocktailId(cocktail.getId());
		cocktailRate.setRank(rate);
		cocktailRate.setUserId(userId);
		cocktailService.rankCocktail(cocktailRate);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS);
	}
	
}
