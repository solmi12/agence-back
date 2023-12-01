package com.example.employe.management.service;


import com.example.employe.management.Repo.UbernsRepository;
import com.example.employe.management.dto.UbernsDto;

import com.example.employe.management.model.Uberns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UbernsService {



    private final UbernsRepository ubernsRepository;
    @Autowired
    public UbernsService (UbernsRepository ubernsRepository){
        this.ubernsRepository = ubernsRepository;
    }

    public List<Uberns> getAllUberns(){
        return ubernsRepository.findAll();

    }

    public boolean doesUbernsExist(String firstName){
        return ubernsRepository.existsByFirstName(firstName);
    }
    public Uberns addUberns(UbernsDto ubernsDto){

        Uberns uberns = new Uberns();
        uberns.setUberId(ubernsDto.getUberId());
        uberns.setFirstName(ubernsDto.getFirstName());
        uberns.setFirstPicture(decodeBase64Image(ubernsDto.getFirstPicture()));
        uberns.setSecondName(ubernsDto.getSecondName());
        uberns.setSecondPicture(decodeBase64Image(ubernsDto.getSecondPicture()));
        uberns.setThirdName(ubernsDto.getThirdName());
        uberns.setThirdPicture(decodeBase64Image(ubernsDto.getThirdPicture()));
        return ubernsRepository.save(uberns);
    }

    private Optional<Uberns> getUbernsById(Long uberId){
        return ubernsRepository.findById(uberId);
    }

    public Uberns updateUberns(Long uberId , Uberns updatedUberns){
        if (ubernsRepository.existsById(uberId)){
            updatedUberns.setUberId(uberId);
            return ubernsRepository.save(updatedUberns);
        }else{
            throw new IllegalArgumentException("Uberns with Id "+ uberId + "not found.");
        }
    }

    public void deleteUberns (Long uberId){

        if (ubernsRepository.existsById(uberId)){

            ubernsRepository.deleteById(uberId);
        }else {
            throw new IllegalArgumentException("Hotel with ID " + uberId + " not found.");
        }
    }
    private byte[] decodeBase64Image(String base64Image) {
        if (base64Image != null) {
            return Base64.getDecoder().decode(base64Image);
        }
        return null;
    }
}
