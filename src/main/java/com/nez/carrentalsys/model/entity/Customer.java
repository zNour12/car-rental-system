package com.nez.carrentalsys.model.entity;

import com.nez.carrentalsys.model.enums.CustomerStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_first_name")
    private String firstName;

    @Column(name = "customer_last_name")
    private String lastName;

    @Column(name = "customer_email")
    private String email;

    @Column(name = "customer_phone_number")
    private String phoneNumber;

    @Column(name = "customer_driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "customer_birth_date")
    private LocalDate birthDate;

    @Column(name = "customer_status")
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;
}
