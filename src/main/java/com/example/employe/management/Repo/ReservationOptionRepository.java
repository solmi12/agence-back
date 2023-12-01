package com.example.employe.management.Repo;

import com.example.employe.management.model.ReservationOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationOptionRepository extends JpaRepository<ReservationOption, Long> {
    // You can define custom query methods here if needed
}
