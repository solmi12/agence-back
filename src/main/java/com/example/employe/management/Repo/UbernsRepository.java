package com.example.employe.management.Repo;

import com.example.employe.management.model.Uberns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbernsRepository extends JpaRepository<Uberns, Long> {
    boolean existsByFirstName(String firstName);
}
