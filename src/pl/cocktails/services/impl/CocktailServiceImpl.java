package pl.cocktails.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.cocktails.data.CocktailData;
import pl.cocktails.data.CocktailRateData;
import pl.cocktails.data.ElementData;
import pl.cocktails.data.dao.CocktailDAO;
import pl.cocktails.data.dao.CocktailRateDAO;
import pl.cocktails.data.dao.ElementDAO;
import pl.cocktails.services.CocktailService;


@Service
public class CocktailServiceImpl implements CocktailService{

	@Autowired
	ElementDAO elementDAO;
	
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

	public void rankCocktail(CocktailRateData rate) {
		cocktailRateDAO.createItem(rate);
	}
	
	

}
