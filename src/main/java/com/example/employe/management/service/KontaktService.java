package com.example.employe.management.service;


import com.example.employe.management.Repo.KontaktRepository;
import com.example.employe.management.dto.KontaktDto;
import com.example.employe.management.exception.KontaktFoundException;
import com.example.employe.management.model.Kontakt;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//@Slf4j
@Service
public class KontaktService {


    @Autowired
    private KontaktRepository kontaktRepository;

    public Kontakt addKontakt (KontaktDto kontaktDto ) throws  Exception{

        List<Kontakt> kontaktList = kontaktRepository.findAllByTitle(kontaktDto.getTitle());
        if (!kontaktList.isEmpty()) {
            throw new KontaktFoundException("Kontakt(s) with the same title already exist");
        }
        Kontakt kontakt = new Kontakt();
        BeanUtils.copyProperties(kontaktDto, kontakt);
        kontakt.setIsShow(true);

        return kontaktRepository.save(kontakt);

    }

    public Kontakt updateKontakt (Integer id , KontaktDto kontaktDto){

        Kontakt kontakt = kontaktRepository.findByKontaktId(id);
        if (kontakt == null){

            throw new EntityNotFoundException("kontakt not found");
        }

        BeanUtils.copyProperties(kontaktDto, kontakt);
        kontakt.setIsShow(true);
        return kontaktRepository.save(kontakt);
    }

    public void deleteKontakt(Integer id){

        Kontakt kontakt = kontaktRepository.findByKontaktId(id);

        if (kontakt == null){
            throw new EntityNotFoundException("kontakt not found");
        }
        kontaktRepository.delete(kontakt);
    }

    public List<Kontakt> getAllKontakts() {
        return kontaktRepository.findAll();
    }
    public Kontakt getKontaktByIsShow(Boolean isShow) {
        Kontakt kontakt = kontaktRepository.findByIsShow(isShow);
        if (kontakt == null) {
            throw new EntityNotFoundException("Kontakt not found");
        }
        return kontakt;
    }

    public Kontakt getKontaktById(Integer id) {
        Kontakt kontakt = kontaktRepository.findByKontaktId(id);
        if (kontakt == null) {
            throw new EntityNotFoundException("Kontakt not found");
        }
        return kontakt;
    }


    public List<Kontakt> getKontaktsByTitle(String title) {
        return kontaktRepository.findAllByTitle(title);
    }
}
