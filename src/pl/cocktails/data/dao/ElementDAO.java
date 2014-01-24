package pl.cocktails.data.dao;

import org.springframework.stereotype.Repository;

import pl.cocktails.data.ElementData;

@Repository
public class ElementDAO extends AbstractDAO<ElementData> {

	private ElementDAO() {
		super(ElementData.class);
	}
}
