package pl.cocktails.common.data;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;


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

	@Override
	public CocktailData getCocktail(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
