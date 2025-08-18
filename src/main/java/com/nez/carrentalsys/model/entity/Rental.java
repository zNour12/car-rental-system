package com.nez.carrentalsys.model.entity;

import com.nez.carrentalsys.model.enums.RentalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rental_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "rental_end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "rental_total_cost", precision = 10, scale = 2)
    private BigDecimal totalCost;

    @Column(name = "rental_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customrt_id", nullable = false)
    private Customer customer;
}
