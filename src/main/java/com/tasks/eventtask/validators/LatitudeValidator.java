package com.tasks.eventtask.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tasks.eventtask.constraints.Latitude;


public class LatitudeValidator implements ConstraintValidator<Latitude, Double> {

    @Override
    public boolean isValid(Double lat, ConstraintValidatorContext context) {
        return lat <= 90 && lat >= -90;
    }
    
}
