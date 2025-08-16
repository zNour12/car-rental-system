package com.nez.carrentalsys.model.dto;

import com.nez.carrentalsys.model.enums.CustomerStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Email(message = "Must be a valid email format")
    @NotBlank
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{8,15}$")
    private String phoneNumber;

    @NotBlank(message = "Driver license is required")
    @Size(min = 5, max = 20, message = "Driver license must be between 5 and 20 characters")
    private String driverLicenseNumber;

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth is required")
    private LocalDate birthDate;

    @NotNull(message = "Status is required")
    private CustomerStatus status;
}
