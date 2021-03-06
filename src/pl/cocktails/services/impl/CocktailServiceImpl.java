package pl.cocktails.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.cocktails.common.AbstractService;
import pl.cocktails.common.ResultList;
import pl.cocktails.common.SearchCriteria;
import pl.cocktails.data.AverageRatingData;
import pl.cocktails.data.CocktailCategoryData;
import pl.cocktails.data.CocktailData;
import pl.cocktails.data.CocktailRateData;
import pl.cocktails.data.ElementCategoryData;
import pl.cocktails.data.ElementData;
import pl.cocktails.data.dao.CocktailCategoryDAO;
import pl.cocktails.data.dao.CocktailDAO;
import pl.cocktails.data.dao.CocktailRateDAO;
import pl.cocktails.data.dao.ElementCategoryDAO;
import pl.cocktails.data.dao.ElementDAO;
import pl.cocktails.services.CocktailService;


@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class CocktailServiceImpl extends AbstractService implements CocktailService{

	@Autowired
	ElementDAO elementDAO;
	
	@Autowired
	ElementCategoryDAO elementCategoryDAO;
	
	@Autowired
	CocktailCategoryDAO cocktailCategoryDAO;
	
	@Autowired
	CocktailDAO cocktailDAO;
	
	@Autowired
	CocktailRateDAO cocktailRateDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void createElement(ElementData element) {
		elementDAO.createItem(getPersistenceManager(),element);
	}

	@Override
	public ElementData getElement(Long id) {
		return elementDAO.getItem(getPersistenceManager(),id);
	}

	@Override
	public ResultList<ElementData> findElements(SearchCriteria criteria) {
		return elementDAO.getItems(getPersistenceManager(), criteria);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void createCocktail(CocktailData cocktail) {
		cocktailDAO.createItem(getPersistenceManager(),cocktail);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void modifyElement(ElementData element) {
		elementDAO.modifyItem(getPersistenceManager(),element);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeElement(Long id) {
		elementDAO.removeItem(getPersistenceManager(),id);
	}

	@Override
	public CocktailData getCocktail(Long id) {
		return cocktailDAO.getItem(getPersistenceManager(), id);
	}
	
	@Override
	public ResultList<CocktailData> findCocktails(SearchCriteria criteria) {
		return cocktailDAO.getItems(getPersistenceManager(), criteria);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void modifyCocktail(CocktailData cocktail) {
		cocktailDAO.modifyItem(getPersistenceManager(),cocktail);	
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeCocktail(Long id) {
		cocktailDAO.removeItem(getPersistenceManager(),id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void createElementCategory(ElementCategoryData category) {
		elementCategoryDAO.createItem(getPersistenceManager(), category);
	}

	@Override
	public ElementCategoryData getElementCategory(Long id) {
		return elementCategoryDAO.getItem(getPersistenceManager(),id);
	}

	@Override
	public List<ElementCategoryData> findElementCategories() {
		return elementCategoryDAO.getItems(getPersistenceManager());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void modifyElementCategory(ElementCategoryData category) {
		elementCategoryDAO.modifyItem(getPersistenceManager(),category);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeElementCategory(Long id) {
		elementCategoryDAO.removeItem(getPersistenceManager(),id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void rankCocktail(CocktailRateData rate) {
		CocktailRateData oldRate = cocktailRateDAO.getUserRating(getPersistenceManager(), rate.getUserId(), rate.getCocktailId());
		if(oldRate == null){
			cocktailRateDAO.createItem(getPersistenceManager(),rate);
		}else{
			rate.setId(oldRate.getId());
			cocktailRateDAO.modifyItem(getPersistenceManager(),rate);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void createCocktailCategory(CocktailCategoryData category) {
		cocktailCategoryDAO.createItem(getPersistenceManager(), category);
	}

	@Override
	public CocktailCategoryData getCocktailCategory(Long id) {
		return cocktailCategoryDAO.getItem(getPersistenceManager(),id);
	}

	@Override
	public List<CocktailCategoryData> findCocktailCategories() {
		return cocktailCategoryDAO.getItems(getPersistenceManager());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void modifyCocktailCategory(CocktailCategoryData category) {
		cocktailCategoryDAO.modifyItem(getPersistenceManager(),category);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeCocktailCategory(Long id) {
		cocktailCategoryDAO.removeItem(getPersistenceManager(),id);
	}
	
	@Override
	public Integer getUserRating(Long userId, Long cocktailId) {
		if(cocktailRateDAO.getUserRating(getPersistenceManager(), userId, cocktailId) != null){
			return cocktailRateDAO.getUserRating(getPersistenceManager(), userId, cocktailId).getRank();
		}
		return null;
	}

	@Override
	public AverageRatingData getCocktailRatings(Long cocktailId) {
		Double ratings = null;
		Long numberOfVotes = cocktailRateDAO.getCocktailRatingsNoOfVotes(cocktailId);
		if(numberOfVotes >0){
			ratings = cocktailRateDAO.getCocktailRatings(getPersistenceManager(), cocktailId);
		}
		return new AverageRatingData(ratings, numberOfVotes);
	}

	

}
