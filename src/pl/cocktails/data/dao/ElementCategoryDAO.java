package pl.cocktails.data.dao;

import org.springframework.stereotype.Repository;

import pl.cocktails.data.ElementCategoryData;

@Repository
public class ElementCategoryDAO extends AbstractDAO<ElementCategoryData> {
	
	private ElementCategoryDAO() {
		super(ElementCategoryData.class);
	}
}
