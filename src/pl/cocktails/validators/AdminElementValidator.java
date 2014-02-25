package pl.cocktails.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.cocktails.common.MessageUtils;
import pl.cocktails.data.ElementData;



public class AdminElementValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ElementData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "errors.empty", new Object[]{MessageUtils.getMessage("labels.name")});
		ValidationUtils.rejectIfEmpty(errors, "description", "errors.empty", new String[]{MessageUtils.getMessage("labels.description")});
		ValidationUtils.rejectIfEmpty(errors, "status", "errors.empty", new String[]{MessageUtils.getMessage("labels.status")});
	}

}
