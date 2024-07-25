package com.example.demo.ValidationAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckEmailHostName implements ConstraintValidator<EmailHostNameValidation, String>{

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		if(email.isBlank() || email == null) {
			return true;
		}
		
		String[] words = email.split("@");
		
		String emailHostName = words[1];
		
		if(emailHostName.equals("gmail.com") || emailHostName.equals("outlook.com") || emailHostName.equals("yahoo.com")) {
			return true;
		}
		
		context.buildConstraintViolationWithTemplate("an acacdc");
		return false;
	}

}
