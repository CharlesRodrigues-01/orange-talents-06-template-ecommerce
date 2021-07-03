package br.com.zupacademy.charles.mercadolivre.annotation;

import br.com.zupacademy.charles.mercadolivre.validation.ExistIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExistIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistId {

    String message() default "{br.com.zupacademy.charles.mercadolivre.ExistId}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> domainClass();
}
