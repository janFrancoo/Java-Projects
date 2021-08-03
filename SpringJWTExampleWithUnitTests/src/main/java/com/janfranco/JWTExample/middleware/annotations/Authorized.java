package com.janfranco.JWTExample.middleware.annotations;

import com.janfranco.JWTExample.util.security.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorized {

    Role[] roles() default {};
}
