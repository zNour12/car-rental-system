package com.nez.carrentalsys.controller;

import com.nez.carrentalsys.model.dto.CustomerDTO;
import com.nez.carrentalsys.model.enums.CustomerStatus;
import com.nez.carrentalsys.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerSerice;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerSerice.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Optional<CustomerDTO> customer = customerSerice.getCustomerByID(id);
        return customer
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CustomerDTO>> getCustomerByStatus(@PathVariable CustomerStatus status) {
        List<CustomerDTO> customers = customerSerice.getCustomerByStatus(status);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDTO> getCustomerByEmail(@PathVariable String email) {
        Optional<CustomerDTO> customer = customerSerice.getCustomerByEmail(email);
        return customer
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dlicense/{licenseNumber}")
    public ResponseEntity<CustomerDTO> getCustomerByLicenseNumber(@PathVariable String licenseNumber) {
        Optional<CustomerDTO> customer = customerSerice.getCustomerByDriverLicenseNumber(licenseNumber);
        return customer
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> searchCustomers(@RequestParam String name) {
        List<CustomerDTO> customers = customerSerice.searchCustomerByName(name);
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomer = customerSerice.saveCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id,@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerSerice.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable Long id) {
        customerSerice.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
