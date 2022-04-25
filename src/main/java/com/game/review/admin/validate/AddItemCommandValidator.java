package com.game.review.admin.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.game.review.admin.command.AddItemCommand;

public class AddItemCommandValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AddItemCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemImg", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemPrice", "required");
	}

}
