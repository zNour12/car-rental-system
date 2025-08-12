package com.nez.carrentalsys.repository;

import com.nez.carrentalsys.model.entity.Customer;
import com.nez.carrentalsys.model.enums.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
    List<Customer> findByLastNameContainingIgnoreCase(String lastName);

    List<Customer> findByStatus(CustomerStatus status);

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByDriverLicenseNumber(String driverLicenseNumber);
}
