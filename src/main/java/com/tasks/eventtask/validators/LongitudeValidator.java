package com.tasks.eventtask.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tasks.eventtask.constraints.Longitude;


public class LongitudeValidator implements ConstraintValidator<Longitude, Double> {

    @Override
    public boolean isValid(Double lng, ConstraintValidatorContext context) {
        if (lng == null) {
            return true;
        } else {
            return lng <= 180 && lng >= -180;
        }
    }
    
}
