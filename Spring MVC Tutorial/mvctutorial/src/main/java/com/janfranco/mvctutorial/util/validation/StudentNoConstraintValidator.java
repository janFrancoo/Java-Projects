package com.janfranco.mvctutorial.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentNoConstraintValidator implements ConstraintValidator<StudentNo, String> {

    private String studentNoPrefix;

    @Override
    public void initialize(StudentNo constraintAnnotation) {
        studentNoPrefix = constraintAnnotation.value();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.startsWith(studentNoPrefix);
    }
}
