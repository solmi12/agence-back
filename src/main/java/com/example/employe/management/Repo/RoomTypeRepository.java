package com.example.employe.management.Repo;

import com.example.employe.management.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    // You can define custom query methods here if needed
}
