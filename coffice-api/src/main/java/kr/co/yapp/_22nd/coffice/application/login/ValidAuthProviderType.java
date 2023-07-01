package kr.co.yapp._22nd.coffice.application.login;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthProviderTypeValidator.class)
public @interface ValidAuthProviderType {
    String message() default "Invalid authProviderType";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
