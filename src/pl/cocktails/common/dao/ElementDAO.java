package pl.cocktails.common.dao;

import org.springframework.stereotype.Repository;

import pl.cocktails.admin.ingredients.data.ElementData;

@Repository
public class ElementDAO extends AbstractDAO<ElementData> {

	private ElementDAO() {
		super(ElementData.class);
	}
}
