package com.nez.carrentalsys.model.dto;

import com.nez.carrentalsys.model.enums.RentalStatus;
import com.nez.carrentalsys.validation.ValidRentalDates;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidRentalDates
public class RentalDTO {

    private Long id;

    @NotNull(message = "Car ID is required")
    private Long carId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    private LocalDate actualReturnDate;

    @DecimalMin(value = "0.0",
            inclusive = false,
            message = "Total cost must be greater than 0.0")
    private BigDecimal totalCost;

    @NotNull(message = "Rental status is required")
    private RentalStatus status;
}
