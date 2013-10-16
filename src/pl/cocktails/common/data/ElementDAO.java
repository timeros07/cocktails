package pl.cocktails.common.data;

import java.util.List;


public interface ElementDAO {
	public List<ElementData> getAll();
	public void create(ElementData element);
	public ElementData getElementById(Long id); 
	public void modify(ElementData element);
	public void remove(Long id);
}
