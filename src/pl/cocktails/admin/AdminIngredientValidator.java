package pl.cocktails.admin;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.cocktails.common.data.ElementData;

public class AdminIngredientValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ElementData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ElementData element = (ElementData)target;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "errors.empty", new Object[]{"nazwa"});
		String a = MessageUtils.getMessage("ingredient.name");
		ValidationUtils.rejectIfEmpty(errors, "description", "errors.empty", new String[]{"opis"});
		
	}

}
