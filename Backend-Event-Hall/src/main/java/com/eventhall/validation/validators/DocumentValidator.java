package com.eventhall.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.eventhall.utils.DocumentUtils;
import com.eventhall.validation.constraints.ValidDocument;

public class DocumentValidator implements ConstraintValidator<ValidDocument, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return false;

        try {
            DocumentUtils.validateDocument(value);
            return true;
        } catch (IllegalArgumentException ex) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ex.getMessage())
                    .addConstraintViolation();
            return false;
        }
    }
}
