package com.lugd.album.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lugd.album.request.AlbumRequest;

public class LogValidator implements Validator{
		
	@Override
    public boolean supports(Class<?> aClass) {
        return AlbumRequest.class.equals(aClass);
    }
	
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "level", "", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "appName", "" , "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "action", "", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createBy", "", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "", "NotEmpty");
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "note", "", "Not empty");
		 */
        

        AlbumRequest form = (AlbumRequest) o;
        
		/*if(!errors.hasFieldErrors("level")) {
			if (form.getLevel() < 1 ||  form.getLevel() > 5 ) {
				errors.rejectValue("level", "", "level.log.invalid");
			}
		}*/
	
    }

}
