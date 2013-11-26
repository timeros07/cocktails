package pl.cocktails.admin.cocktails;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.cocktails.admin.MessageUtils;
import pl.cocktails.admin.cocktails.data.IngredientData;

public class AdminIngredientValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return IngredientData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "element.id", "errors.empty", new Object[]{MessageUtils.getMessage("labels.cocktail.ingredient.element")});
		ValidationUtils.rejectIfEmpty(errors, "count", "errors.empty", new String[]{MessageUtils.getMessage("labels.cocktail.ingredient.count")});
	}

}
