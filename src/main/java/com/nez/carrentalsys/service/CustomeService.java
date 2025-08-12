package com.nez.carrentalsys.service;

import com.nez.carrentalsys.model.dto.CustomerDTO;
import com.nez.carrentalsys.model.enums.CustomerStatus;

import java.util.List;
import java.util.Optional;

public interface CustomeService {
    List<CustomerDTO> getAllCustomers();
    List<CustomerDTO> getCustomerByStatus(CustomerStatus status);

    Optional<CustomerDTO> getCustomerByID(Long id);
    Optional<CustomerDTO> getCustomerByEmail(String email);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long id);
}
