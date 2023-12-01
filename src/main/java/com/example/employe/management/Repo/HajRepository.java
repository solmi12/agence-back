package com.example.employe.management.Repo;

import com.example.employe.management.model.Haj;
import com.example.employe.management.model.HajCategorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface HajRepository extends JpaRepository<Haj , Long> {
    List<Haj> findByHajCategorie(HajCategorie hajCategorie);
    List<Haj> findByHajName(java.lang.String hajName);
    Optional<Haj> findByHajIdAndReservationNumberGreaterThan (Integer id, int reservationNumber);
    Optional<Haj> findByHajId(Long hajId);
    List<Haj> findByShowNow(boolean showNow);
    List<Haj> findByShowNowAndNumbNotNullOrderByNumb(boolean showNow);
}
