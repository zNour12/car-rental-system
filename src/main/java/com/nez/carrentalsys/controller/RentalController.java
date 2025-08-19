package com.nez.carrentalsys.controller;

import com.nez.carrentalsys.model.dto.RentalDTO;
import com.nez.carrentalsys.model.entity.Rental;
import com.nez.carrentalsys.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;


    @GetMapping
    public ResponseEntity<List<RentalDTO>> getAllRentals() {
        List<RentalDTO> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("{/id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable Long id) {
        Optional<RentalDTO> rental = rentalService.getRentalById(id);
        return rental.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RentalDTO> createRental(@Valid @RequestBody RentalDTO rentalDTO) {
        RentalDTO createdRental = rentalService.createRental(rentalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);
    }
    @PutMapping("{/id}")
    public ResponseEntity<RentalDTO> updateRental(@PathVariable Long id, @Valid @RequestBody RentalDTO rentalDTO) {
        RentalDTO updatedRental = rentalService.updateRental(id, rentalDTO);
        return ResponseEntity.ok(updatedRental);
    }

    @DeleteMapping
    public ResponseEntity<Void>  deleteRentalById(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }

    // Filter/Search methods
}
