package com.nez.carrentalsys.repository;

import com.nez.carrentalsys.model.entity.Customer;
import com.nez.carrentalsys.model.enums.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByStatus(CustomerStatus status);

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByDriverLicenseNumber(String driverLicenseNumber);

    @Query("SELECT c FROM Customer c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Customer> findByFirstNameOrLastNameContainingIgnoreCase(@Param("name") String name);
}
