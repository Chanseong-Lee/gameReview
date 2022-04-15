package com.game.review.member.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.game.review.member.command.LoginCommand;
import com.game.review.member.command.MemberUpdateCommand;

public class MemberUpdateCommandValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberUpdateCommandValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberUpdateCommand lc = (MemberUpdateCommand) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "required");
	}
}
