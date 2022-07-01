package com.tasks.eventtask.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tasks.eventtask.constraints.Latitude;


public class LatitudeValidator implements ConstraintValidator<Latitude, String> {

    @Override
    public boolean isValid(String lat, ConstraintValidatorContext context) {
        double latValue;
        try {
            latValue = Double.parseDouble(lat);
        } catch (Exception e) {
            return false;
        }
        return latValue <= 90 && latValue >= -90;
    }
    
}
