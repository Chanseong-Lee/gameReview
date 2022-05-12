package com.game.review.game.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.game.review.game.command.GameModifyCommand;

public class GameModifyCommandValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return GameModifyCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gName", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gCode", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gDev", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gDate", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gPrice", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gContent", "required");
	}

}
