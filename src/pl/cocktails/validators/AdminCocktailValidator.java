package pl.cocktails.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.cocktails.common.MessageUtils;
import pl.cocktails.data.CocktailData;

public class AdminCocktailValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CocktailData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CocktailData cocktail = (CocktailData)target;
				
		ValidationUtils.rejectIfEmpty(errors, "name", "errors.empty", new Object[]{MessageUtils.getMessage("labels.name")});
		ValidationUtils.rejectIfEmpty(errors, "description", "errors.empty", new String[]{MessageUtils.getMessage("labels.description")});
		ValidationUtils.rejectIfEmpty(errors, "status", "errors.empty", new String[]{MessageUtils.getMessage("labels.status")});
		
		if(cocktail.getIngredients().isEmpty()){
			errors.reject("errors.cocktail.ingredients.empty");
		}
		
	}

}
