package com.nez.carrentalsys.service.Impl;

import com.nez.carrentalsys.mapper.CustomerMapper;
import com.nez.carrentalsys.model.dto.CustomerDTO;
import com.nez.carrentalsys.model.enums.CustomerStatus;
import com.nez.carrentalsys.repository.CustomerRepository;
import com.nez.carrentalsys.service.CustomeService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomeService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomers(){
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public List<CustomerDTO> getCustomerByStatus(CustomerStatus status) {
        return customerRepository
                .findByStatus(status)
                .stream().
                map(customerMapper::toDto)
                .toList();
    }

    @Override
    public Optional<CustomerDTO> getCustomerByID(Long id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::toDto);
    }

    @Override
    public Optional<CustomerDTO> getCustomerByEmail(String email) {
        return customerRepository
                .findByEmail(email)
                .map(customerMapper::toDto);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
//        Customer customer = customerMapper.toEntity(customerDTO);
//        Customer savedCustomer = customerRepository.save(customer);
//        return customerMapper.toDto(savedCustomer);
        return customerMapper.toDto(customerRepository.save(customerMapper.toEntity(customerDTO)));
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        customerDTO.setId(id);
//        Customer customer = customerMapper.toEntity(customerDTO);
//        Customer updatedCustomer = customerRepository.save(customer);
//        return customerMapper.toDto(updatedCustomer);
        return customerMapper.toDto(customerRepository.save(customerMapper.toEntity(customerDTO)));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> searchCustomerByName(String name){
        return customerRepository
                .findByFirstNameOrLastNameContainingIgnoreCase(name)
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

}
