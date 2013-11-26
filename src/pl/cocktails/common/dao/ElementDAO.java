package pl.cocktails.common.dao;

import java.util.List;

import pl.cocktails.admin.ingredients.data.ElementData;


public interface ElementDAO {
	public List<ElementData> getAll();
	public void create(ElementData element);
	public ElementData getElementById(Long id); 
	public void modify(ElementData element);
	public void remove(Long id);
}
