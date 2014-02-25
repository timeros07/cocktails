package pl.cocktails.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import pl.cocktails.common.JSONResponse;
import pl.cocktails.data.CocktailData;
import pl.cocktails.data.CocktailRateData;
import pl.cocktails.data.ElementData;
import pl.cocktails.services.CocktailService;

@Controller
@RequestMapping("/")
public class CocktailsController {
	
	private List<CocktailData> cocktails;
	private CocktailData cocktail;
	
	private CocktailData searchCriteria;
	
	@Autowired
	private CocktailService cocktailService;
	
	@ModelAttribute
	public void addAttributes(WebRequest request, Model model) {
		
		List<ElementData> elements = cocktailService.findElements();
		Map<String, String> elementsMap = new HashMap<String, String>();
		for(ElementData data: elements){
			elementsMap.put(data.getId()+"", data.getName());
		}
		
		model.addAttribute("elements", elementsMap);
		
		
		searchCriteria = new CocktailData();
		model.addAttribute("searchCriteria", searchCriteria);
	}
	
	@RequestMapping(value="/cocktails", method=RequestMethod.GET)
	public String initCocktails(Model model) {
		cocktails = cocktailService.findCocktails();
		model.addAttribute("cocktails", cocktails);
		return "cocktails";
	}
	
	@RequestMapping(value="/cocktailDetails", method=RequestMethod.GET)
	public String showCocktail(@RequestParam(required= true) Long id, Model model){
		cocktail = cocktailService.getCocktail(id);
		//Integer rank = cocktailService.getUserRating(userId, cocktailId);
		
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
