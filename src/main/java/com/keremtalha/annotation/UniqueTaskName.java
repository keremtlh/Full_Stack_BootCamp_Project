package com.keremtalha.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueTaskNameValidation.class})
public @interface UniqueTaskName {
        String message() default "{role.name.unique.constraints.NotNull.message}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}
