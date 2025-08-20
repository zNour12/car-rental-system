package com.nez.carrentalsys.controller;

import com.nez.carrentalsys.model.dto.RentalDTO;
import com.nez.carrentalsys.model.enums.RentalStatus;
import com.nez.carrentalsys.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;


    @GetMapping
    public ResponseEntity<List<RentalDTO>> getAllRentals() {
        List<RentalDTO> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable Long id) {
        Optional<RentalDTO> rental = rentalService.getRentalById(id);
        return rental.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RentalDTO> createRental(@Valid @RequestBody RentalDTO rentalDTO) {
        RentalDTO createdRental = rentalService.createRental(rentalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);
    }
    @PutMapping("/{id}")
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

    @GetMapping("/status/{status}")
    public ResponseEntity<List<RentalDTO>> getRentalStatus(@PathVariable RentalStatus status) {
        List<RentalDTO> rentals = rentalService.getRentalByStatus(status);
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<RentalDTO>> getRentalsByCarId(@PathVariable Long carId) {
        List<RentalDTO> rentals = rentalService.getRentalByCarId(carId);
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<RentalDTO>> getRentalsByCustomerId(@PathVariable Long customerId) {
        List<RentalDTO> rentals = rentalService.getRentalByCustomerId(customerId);
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<RentalDTO>> getOverdueRentals() {
        List<RentalDTO> overdueRentals = rentalService.getOverDueRentals();
        return ResponseEntity.ok(overdueRentals);
    }

    // Business related methods

    @GetMapping("/availability")
    public ResponseEntity<Boolean> checkCarAvailability(
                                                        @PathVariable Long carId,
                                                        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        boolean isAvailable = rentalService.isCarAvailable(carId, startDate, endDate);
        return ResponseEntity.ok(isAvailable);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<RentalDTO> returnCar(@PathVariable Long id) {
        RentalDTO completedRental = rentalService.returnCar(id);
        return ResponseEntity.ok(completedRental);
    }
}
