package com.tasks.eventtask.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.tasks.eventtask.validators.LongitudeValidator;


@Documented
@Constraint(validatedBy = LongitudeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Longitude {
    String message() default "invalid longitude value";
  
    Class<?>[] groups() default {};
  
    Class<? extends Payload>[] payload() default {};
}

