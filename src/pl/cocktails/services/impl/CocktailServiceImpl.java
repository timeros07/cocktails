package pl.cocktails.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.cocktails.data.AverageRatingData;
import pl.cocktails.data.CocktailData;
import pl.cocktails.data.CocktailRateData;
import pl.cocktails.data.ElementCategoryData;
import pl.cocktails.data.ElementData;
import pl.cocktails.data.dao.CocktailDAO;
import pl.cocktails.data.dao.CocktailRateDAO;
import pl.cocktails.data.dao.ElementCategoryDAO;
import pl.cocktails.data.dao.ElementDAO;
import pl.cocktails.services.CocktailService;


@Service
public class CocktailServiceImpl implements CocktailService{

	@Autowired
	ElementDAO elementDAO;
	
	@Autowired
	ElementCategoryDAO elementCategoryDAO;
	
	@Autowired
	CocktailDAO cocktailDAO;
	
	@Autowired
	CocktailRateDAO cocktailRateDAO;
	
	public void createElement(ElementData element) {
		elementDAO.createItem(element);
	}

	public ElementData getElement(Long id) {
		return elementDAO.getItem(id);
	}

	public List<ElementData> findElements() {
		return elementDAO.getItems();
	}

	public void createCocktail(CocktailData cocktail) {
		cocktailDAO.createItem(cocktail);
	}

	public void modifyElement(ElementData element) {
		elementDAO.modifyItem(element);
	}

	public void removeElement(Long id) {
		elementDAO.removeItem(id);
	}

	public CocktailData getCocktail(Long id) {
		return cocktailDAO.getItem(id);
	}
	
	public List<CocktailData> findCocktails() {
		return cocktailDAO.getItems();
	}

	public void modifyCocktail(CocktailData cocktail) {
		cocktailDAO.modifyItem(cocktail);	
	}

	public void removeCocktail(Long id) {
		cocktailDAO.removeItem(id);
	}

	@Override
	public void createElementCategory(ElementCategoryData category) {
		elementCategoryDAO.createItem(category);
	}

	@Override
	public ElementCategoryData getElementCategory(Long id) {
		return elementCategoryDAO.getItem(id);
	}

	@Override
	public List<ElementCategoryData> findElementCategories() {
		return elementCategoryDAO.getItems();
	}

	@Override
	public void modifyElementCategory(ElementCategoryData category) {
		elementCategoryDAO.modifyItem(category);
	}

	@Override
	public void removeElementCategory(Long id) {
		elementCategoryDAO.removeItem(id);
	}
	
	public void rankCocktail(CocktailRateData rate) {
		CocktailRateData oldRate = cocktailRateDAO.getUserRating(rate.getUserId(), rate.getCocktailId());
		if(oldRate == null){
			cocktailRateDAO.createItem(rate);
		}else{
			rate.setId(oldRate.getId());
			cocktailRateDAO.modifyItem(rate);
		}
	}

	@Override
	public Integer getUserRating(Long userId, Long cocktailId) {
		if(cocktailRateDAO.getUserRating(userId, cocktailId) != null){
			return cocktailRateDAO.getUserRating(userId, cocktailId).getRank();
		}
		return null;
	}

	@Override
	public AverageRatingData getCocktailRatings(Long cocktailId) {
		Double ratings = cocktailRateDAO.getCocktailRatings(cocktailId);
		Long numberOfVotes = cocktailRateDAO.getCocktailRatingsNoOfVotes(cocktailId);
		return new AverageRatingData(ratings, numberOfVotes);
	}

	

}
