package com.game.review.admin.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.game.review.admin.command.AdminMemberUpdateCommand;

public class AdminMemberUpdateCommandValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AdminMemberUpdateCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AdminMemberUpdateCommand amuc = (AdminMemberUpdateCommand) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "point", "required");
	}

}
