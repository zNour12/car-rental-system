package com.nez.carrentalsys.validation;

import com.nez.carrentalsys.model.dto.RentalDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.temporal.ChronoUnit;

public class RentalDatesValidator implements ConstraintValidator<ValidRentalDates, RentalDTO> {

    @Override
    public boolean isValid(RentalDTO rentalDTO, ConstraintValidatorContext context) {
        if (rentalDTO.getStartDate() == null || rentalDTO.getEndDate() == null) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        boolean isValid = true;

        if (!rentalDTO.getEndDate().isAfter(rentalDTO.getStartDate())) {
            context
                    .buildConstraintViolationWithTemplate("End Date must be after Start Date")
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
            isValid = false;
        }

        long daysBetween = ChronoUnit.DAYS.between(rentalDTO.getStartDate(), rentalDTO.getEndDate());
        if (daysBetween > 30) {
            context
                    .buildConstraintViolationWithTemplate("Rental duration must exceed 30 days")
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }

}
