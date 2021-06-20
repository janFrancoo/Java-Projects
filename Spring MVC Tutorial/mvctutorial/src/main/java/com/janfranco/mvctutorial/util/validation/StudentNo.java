package com.janfranco.mvctutorial.util.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = StudentNoConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentNo {

    String value() default "0";

    String message() default "Student no must be starts with 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
