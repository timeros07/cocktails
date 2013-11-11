package pl.cocktails.admin.cocktails;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.cocktails.admin.MessageUtils;
import pl.cocktails.common.data.CocktailData;

public class AdminCocktailValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CocktailData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CocktailData cocktail = (CocktailData)target;
				
		ValidationUtils.rejectIfEmpty(errors, "name", "errors.empty", new Object[]{MessageUtils.getMessage("labels.cocktail.name")});
		ValidationUtils.rejectIfEmpty(errors, "description", "errors.empty", new String[]{MessageUtils.getMessage("labels.cocktail.description")});
		
		if(cocktail.getIngredients().isEmpty()){
			errors.reject("errors.cocktail.ingredients.empty");
		}
		
	}

}
