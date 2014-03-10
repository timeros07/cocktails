package pl.cocktails.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import pl.cocktails.common.JSONResponse;
import pl.cocktails.common.PageableResult;
import pl.cocktails.common.ResultList;
import pl.cocktails.common.SearchCriteria;
import pl.cocktails.common.UserContext;
import pl.cocktails.data.AverageRatingData;
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
		
		List<ElementData> elements = cocktailService.findElements(new SearchCriteria());
		Map<String, String> elementsMap = new HashMap<String, String>();
		for(ElementData data: elements){
			elementsMap.put(data.getId()+"", data.getName());
		}
		
		model.addAttribute("elements", elementsMap);
		
		
		searchCriteria = new CocktailData();
		model.addAttribute("searchCriteria", searchCriteria);
	}
	
	@RequestMapping(value="/cocktails", method=RequestMethod.GET)
	public String initCocktails(Model model, @RequestParam(required= false) Long p) {
		SearchCriteria criteria = new SearchCriteria(p == null ? 1 : p);
		ResultList<CocktailData> cocktails = cocktailService.findCocktails(criteria);
		model.addAttribute("cocktails", cocktails);
		model.addAttribute("paging", new PageableResult(cocktails.getRealSize(), p == null ? 1 : p));
		return "cocktails";
	}
	
	@RequestMapping(value="/cocktailDetails", method=RequestMethod.GET)
	public String showCocktail(@RequestParam(required= true) Long id, Model model, @ModelAttribute(CommonController.USER_CONTEXT) UserContext context){
		cocktail = cocktailService.getCocktail(id);
		
		AverageRatingData ratings = cocktailService.getCocktailRatings(id);
		model.addAttribute("cocktailRatings",ratings);
		
		if(context != null && context.getUser() != null){
			Integer userRank = cocktailService.getUserRating(context.getUser().getId(), id);
			model.addAttribute("userRate",userRank);
		}
		model.addAttribute(cocktail);
		return "cocktailDetails";
	}
	
	@RequestMapping(value="/cocktailDetails", method=RequestMethod.POST, params="job=RATE")
	public @ResponseBody JSONResponse rateCocktail(Integer rate, Model model, @ModelAttribute(CommonController.USER_CONTEXT) UserContext context) {
		
		CocktailRateData cocktailRate = new CocktailRateData();
		cocktailRate.setCocktailId(cocktail.getId());
		cocktailRate.setRank(rate);
		cocktailRate.setUserId(context.getUser().getId());
		cocktailService.rankCocktail(cocktailRate);
		return new JSONResponse(true, JSONResponse.OPERATION_SUCCESS);
	}
	
}
