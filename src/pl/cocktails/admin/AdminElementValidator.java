package pl.cocktails.admin;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.cocktails.common.data.ElementData;



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
