package com.tasks.eventtask.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tasks.eventtask.constraints.Longitude;


public class LongitudeValidator implements ConstraintValidator<Longitude, String> {

    @Override
    public boolean isValid(String lng, ConstraintValidatorContext context) {
        double lngValue;
        try {
            lngValue = Double.parseDouble(lng);
        } catch (Exception e) {
            return false;
        }
        return lngValue <= 180 && lngValue >= -180;
    }
    
}
