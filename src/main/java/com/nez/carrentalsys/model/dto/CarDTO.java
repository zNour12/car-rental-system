package com.nez.carrentalsys.model.dto;

import com.nez.carrentalsys.model.enums.CarStatus;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private Long id;

    @NotBlank(message = "Brand is required")
    @Size(min = 2, max = 50, message = "Brand must be between 2 and 50 characters")
    private String brand;

    @NotBlank(message = "Model is required")
    @Size(min = 1, max = 50, message = "Model must be between 1 and 50 characters")
    private String model;

    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    @Max(value = 2030, message = "Year must be less than  2030")
    private int year;

    @NotBlank(message = "License plate is required")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "License plate must only contain uppercase letters and numbers")
    private String licensePlate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "10000.0", message = "Price cannot exceed 10,000.0")
    private double pricePerDay;

    @NotNull(message = "Status is required")
    private CarStatus status;
}
