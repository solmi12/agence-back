package com.example.employe.management.Repo;

import com.example.employe.management.model.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelsRepository extends JpaRepository<Hotels, Long> {
        boolean existsByHotelName(String hotelName);

}