//package com.example.demo.Exception;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//@Constraint(validatedBy = {ValidateList.class})
//@Target({ ElementType.FIELD, ElementType.PARAMETER })
//@Retention(RetentionPolicy.RUNTIME)
//public @interface validateAnnotation {
//	
//	 String message() default "Invalid list";
//
//	    Class<?>[] groups() default {};
//
//	    Class<? extends Payload>[] payload() default {};
//
//}
