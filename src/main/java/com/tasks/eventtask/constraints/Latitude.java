package com.tasks.eventtask.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import javax.validation.Payload;

import com.tasks.eventtask.validators.LatitudeValidator;

import javax.validation.Constraint;


@Documented
@Constraint(validatedBy = LatitudeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Latitude {
    String message() default "invalid latitude value";
  
    Class<?>[] groups() default {};
  
    Class<? extends Payload>[] payload() default {};
}

