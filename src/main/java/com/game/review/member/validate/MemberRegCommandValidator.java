package com.game.review.member.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.game.review.member.command.MemberRegCommand;

public class MemberRegCommandValidator implements Validator{
	
	private static final String emailRegExp=
			"^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
	//정규표현식의 시작은 ^를 붙임. 마무리는$
	
	private Pattern pattern;
	//자바에서 정규표현식을 사용하기 위한 객체 ->Pattern객체,Matcher객체
	
	public MemberRegCommandValidator() {
		pattern = Pattern.compile(emailRegExp);
		//정규식을 사용하기위해 패턴의 컴파일메소드를 사용
		//주어진 정규표현식으로부터 패턴을 만들어냄
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberRegCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		MemberRegCommand mrc = (MemberRegCommand) target;
		
		if(!(mrc.getEmail() == null) || !(mrc.getEmail().trim().isEmpty())) {
			Matcher matcher = pattern.matcher(mrc.getEmail());
			if(!matcher.matches()) {
				errors.rejectValue("email", "badEmailFormat");
			}
		}
		
		if(!(mrc.getPassword() == null) && !(mrc.getConfirmPassword() == null)) {
			if(!mrc.getPassword().equals(mrc.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "passwordNotMatch");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
	}
}
