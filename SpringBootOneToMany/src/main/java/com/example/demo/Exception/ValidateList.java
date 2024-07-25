//package com.example.demo.Exception;
//
//import java.util.List;
//
//import com.example.demo.dto.Subject;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class ValidateList implements ConstraintValidator<validateAnnotation, List<Subject>> {
//
//	@Override
//	public boolean isValid(List<Subject> subjects, ConstraintValidatorContext context) {
//		// TODO Auto-generated method stub
//
//		boolean isValid = true;
//
//		for (int i = 0; i < subjects.size(); i++) {
//
//			Subject subject = subjects.get(i);
//
//			if (subject.getAuthor() == null || subject.getAuthor().isBlank() || subject.getAuthor().equals(null)) {
//
//				context.disableDefaultConstraintViolation();
//				context.buildConstraintViolationWithTemplate("author can not be empty , necessary to fill ")
//						.addPropertyNode("author").inIterable().atIndex(i).addConstraintViolation();
//				System.out.println(context.getDefaultConstraintMessageTemplate());
//
//				isValid = false;
//			}
//
//			if (subject.getName() == null || subject.getName().isBlank() || subject.getName().equals(null)) {
//
//				context.disableDefaultConstraintViolation();
//				context.buildConstraintViolationWithTemplate("Name can not be empty , necessary to fill ")
//						.addPropertyNode("name").inIterable().atIndex(i).addConstraintViolation();
//				System.out.println(context);
//				isValid = false;
//			}
//			
//			
//			if (subject.getSubjectId() > 0) {
//
//				context.disableDefaultConstraintViolation();
//				context.buildConstraintViolationWithTemplate("subject id must be greater than 0")
//						.addPropertyNode("subjectId").inIterable().atIndex(i).addConstraintViolation();
//
//				isValid = false;
//			}
//
//		}
//
//		return isValid;
//	}
//
//}
