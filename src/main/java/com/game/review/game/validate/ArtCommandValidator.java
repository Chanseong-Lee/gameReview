package com.game.review.game.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.game.review.game.command.ArtCommand;

public class ArtCommandValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ArtCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aContent", "required");
		
	}

}
