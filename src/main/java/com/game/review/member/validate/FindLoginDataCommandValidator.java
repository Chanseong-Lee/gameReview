package com.game.review.member.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.game.review.member.command.FindLoginDataCommand;
import com.game.review.member.command.MemberRegCommand;

public class FindLoginDataCommandValidator implements Validator{
	
	private static final String emailRegExp=
			"^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
	//정규표현식의 시작은 ^를 붙임. 마무리는$
	
	private Pattern pattern;
	//자바에서 정규표현식을 사용하기 위한 객체 ->Pattern객체,Matcher객체
	
	public FindLoginDataCommandValidator() {
		pattern = Pattern.compile(emailRegExp);
		//정규식을 사용하기위해 패턴의 컴파일메소드를 사용
		//주어진 정규표현식으로부터 패턴을 만들어냄
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return FindLoginDataCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		FindLoginDataCommand fec = (FindLoginDataCommand) target;
		
		if(!(fec.getEmail() == null) || !(fec.getEmail().trim().isEmpty())) {
			Matcher matcher = pattern.matcher(fec.getEmail());
			if(!matcher.matches()) {
				errors.rejectValue("email", "badEmailFormat");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
	}
}
