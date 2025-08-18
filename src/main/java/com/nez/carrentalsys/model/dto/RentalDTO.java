package com.nez.carrentalsys.model.dto;

import com.nez.carrentalsys.model.enums.RentalStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    private LocalDate returnDate;

    @DecimalMin(value = "0.0",
            inclusive = false,
            message = "Total cost must be greater than 0.0")
    private BigDecimal totalPrice;

    @NotNull(message = "Rental status is required")
    private RentalStatus status;
}
