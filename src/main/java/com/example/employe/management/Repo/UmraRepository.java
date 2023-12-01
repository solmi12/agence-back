package com.example.employe.management.Repo;

import com.example.employe.management.model.Umra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UmraRepository extends JpaRepository <Umra , Integer> {

    List<Umra> findByUmraName(String umraName);
}
