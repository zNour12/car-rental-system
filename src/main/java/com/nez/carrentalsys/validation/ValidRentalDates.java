package com.nez.carrentalsys.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RentalDatesValidator.class)
@Documented
public @interface ValidRentalDates {
    String message() default "Invalid rental dates";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
