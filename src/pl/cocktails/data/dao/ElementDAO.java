package pl.cocktails.data.dao;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Repository;

import pl.cocktails.data.ElementData;

@Repository
public class ElementDAO extends AbstractDAO<ElementData> {

	private ElementDAO() {
		super(ElementData.class);
	}
	
	@Override
	public ElementData getItem(PersistenceManager manager, Long id) {
		ElementData element = super.getItem(manager, id);
		element.getCategory();
		return element;
	}
}
