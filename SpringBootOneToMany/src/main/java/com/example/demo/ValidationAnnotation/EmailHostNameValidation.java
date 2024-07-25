package com.example.demo.ValidationAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = { CheckEmailHostName.class })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailHostNameValidation {

	String message() default "CHOOSE A VALID HOSTNAME";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
