package pl.cocktails.admin.ingredients;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.cocktails.admin.MessageUtils;
import pl.cocktails.admin.ingredients.data.ElementData;



public class AdminElementValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ElementData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "errors.empty", new Object[]{MessageUtils.getMessage("labels.ingredient.name")});
		ValidationUtils.rejectIfEmpty(errors, "description", "errors.empty", new String[]{MessageUtils.getMessage("labels.ingredient.description")});
	}

}
