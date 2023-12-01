package com.example.employe.management.Repo;

import com.example.employe.management.model.RoomReservationChoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<RoomReservationChoice, Long> {
    List<RoomReservationChoice> findBySessionIdentifier(String sessionIdentifier);
    void deleteBySessionIdentifier(String sessionIdentifier);
}