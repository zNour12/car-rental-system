package com.nez.carrentalsys.service;

import com.nez.carrentalsys.model.dto.CustomerDTO;
import com.nez.carrentalsys.model.enums.CustomerStatus;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    List<CustomerDTO> getCustomerByStatus(CustomerStatus status);

    Optional<CustomerDTO> getCustomerByID(Long id);
    Optional<CustomerDTO> getCustomerByEmail(String email);
    Optional<CustomerDTO> getCustomerByDriverLicenseNumber(String driverLicenseNumber);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomer(Long id);

    List<CustomerDTO> searchCustomerByName(String name);
}
