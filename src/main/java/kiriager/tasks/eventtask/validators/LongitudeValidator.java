package kiriager.tasks.eventtask.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import kiriager.tasks.eventtask.constraints.Longitude;

public class LongitudeValidator implements ConstraintValidator<Longitude, Double> {

    @Override
    public boolean isValid(Double lng, ConstraintValidatorContext context) {
        return lng <= 180 && lng >= -180;
    }
    
}
