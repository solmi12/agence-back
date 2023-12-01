package com.example.employe.management.Repo;


import com.example.employe.management.model.Kontakt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KontaktRepository extends JpaRepository<Kontakt , Integer> {


    Kontakt findByKontaktId(Integer kontaktId);
    Kontakt findByTitle(String title);
    List<Kontakt> findAllByTitle(String title);
    Kontakt findByIsShow(Boolean isShow);

}
