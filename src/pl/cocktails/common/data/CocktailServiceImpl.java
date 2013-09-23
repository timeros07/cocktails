package pl.cocktails.common.data;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CocktailServiceImpl implements CocktailService{

	@Autowired
	ElementDAO elementDAO;
	@Autowired
	CocktailDAO cocktailDAO;
	
	public void createElement(ElementData element) {
		elementDAO.create(element);
	}

	public ElementData getElement(Long id) {
		return elementDAO.getElementById(id);
	}

	public List<ElementData> findElements() {
		return elementDAO.getAll();
	}

	public void createCocktail(CocktailData cocktail) {
		cocktailDAO.create(cocktail);
	}

	public void modifyElement(ElementData element) {
		elementDAO.modify(element);
	}

	public void removeElement(Long id) {
		elementDAO.remove(id);
	}

	public CocktailData getCocktail(Long id) {
		return cocktailDAO.getCocktailById(id);
	}
	
	public List<CocktailData> findCocktails() {
		return cocktailDAO.getAllCocktails();
	}

	public void modifyCocktail(CocktailData cocktail) {
		cocktailDAO.modify(cocktail);	
	}

	public void removeCocktail(Long id) {
		cocktailDAO.remove(id);
	}
	
	

}
