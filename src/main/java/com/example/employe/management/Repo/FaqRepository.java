package com.example.employe.management.Repo;

import com.example.employe.management.model.Faq;

import com.example.employe.management.model.Kontakt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Integer> {


    List<Faq> findByCategoriefaq(String category);

    List<Faq> findAllByTitleFaq(String titleFaq);
}
