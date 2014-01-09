package pl.cocktails.admin.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.cocktails.admin.cocktails.data.CocktailData;
import pl.cocktails.admin.ingredients.data.ElementData;
import pl.cocktails.admin.services.CocktailService;
import pl.cocktails.common.dao.CocktailDAO;
import pl.cocktails.common.dao.ElementDAO;


@Service
public class CocktailServiceImpl implements CocktailService{

	@Autowired
	ElementDAO elementDAO;
	
	@Autowired
	CocktailDAO cocktailDAO;
	
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
	
	

}
