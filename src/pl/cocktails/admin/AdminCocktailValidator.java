package pl.cocktails.admin;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.cocktails.common.data.CocktailData;

public class AdminCocktailValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CocktailData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CocktailData cocktail = (CocktailData)target;
		
			
		ValidationUtils.rejectIfEmpty(errors, "name", "errors.empty", new Object[]{"nazwa"});
		ValidationUtils.rejectIfEmpty(errors, "description", "errors.empty", new String[]{"opis"});
		
		if(cocktail.getIngredients().isEmpty()){
			errors.reject("errors.cocktail.ingredients.empty");
		}
		
	}

}
