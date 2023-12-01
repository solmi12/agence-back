package com.example.employe.management.service;


import com.example.employe.management.Repo.UmraRepository;
import com.example.employe.management.dto.UmraDto;
import com.example.employe.management.exception.UmraFoundException;
import com.example.employe.management.model.Umra;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Slf4j
@Service
public class UmraService {
    @Autowired
    UmraRepository umraRepository;

    public Umra addUmra(UmraDto umraDto) throws Exception {


        List<Umra> umraList = umraRepository.findByUmraName(umraDto.getUmraName());
        if (!umraList.isEmpty()) {
            throw new UmraFoundException("Kontakt(s) with the same title already exist");
        }
        Umra umra = new Umra();
        BeanUtils.copyProperties(umraDto, umra);


        return umraRepository.save(umra);

    }

}