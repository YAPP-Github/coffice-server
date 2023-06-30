package kr.co.yapp._22nd.coffice.application.login;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;

public class AuthProviderTypeValidator implements ConstraintValidator<ValidAuthProviderType, String> {

    @Override
    public void initialize(ValidAuthProviderType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            AuthProviderType.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
