package com.game.review.game.validate;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.game.review.game.command.GameRegCommand;
import com.game.review.member.validate.FileTypeByTika;

public class GameRegValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		try {
			
			GameRegCommand grc = (GameRegCommand) target;
			
			if (grc.getImgFile().isEmpty() || grc.getImgFile() == null) {
				errors.rejectValue("imgFile", "required");
			}else {
				InputStream inputStream = grc.getImgFile().getInputStream();
				boolean isImage = FileTypeByTika.validImgFile(inputStream);
				if(!isImage) {
					errors.rejectValue("imgFile", "noImage");
				}
			}
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gName", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gDate", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gCode", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gDev", "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genNum", "required");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
