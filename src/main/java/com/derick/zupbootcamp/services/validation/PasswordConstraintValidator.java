package com.derick.zupbootcamp.services.validation;

import com.derick.zupbootcamp.controllers.exceptions.FieldMessage;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
 
    @Override
    public void initialize(ValidPassword arg0) {
    }
 
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        PasswordValidator validator = new PasswordValidator(Arrays.asList(
           new LengthRule(8, 8),
           new UppercaseCharacterRule(1),
           new DigitCharacterRule(1),
           new SpecialCharacterRule(1),
           new NumericalSequenceRule(3,false), 
           new AlphabeticalSequenceRule(3,false), 
           new QwertySequenceRule(3,false),
           new WhitespaceRule()));
 
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        for (RuleResultDetail e : result.getDetails()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("PasswordValidation")
                    .addPropertyNode(e.getErrorCode()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}