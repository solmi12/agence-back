package com.example.employe.management.controllers;


import com.example.employe.management.dto.UbernsDto;
import com.example.employe.management.model.Uberns;
import com.example.employe.management.service.UbernsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uberns")
public class UbernsController {

    private final UbernsService ubernsService;

    @Autowired
    private UbernsController(UbernsService ubernsService){
        this.ubernsService = ubernsService;

    }

    @GetMapping
    public ResponseEntity<List<Uberns>> getAllUberns(){

        List<Uberns> uberns = ubernsService.getAllUberns();
        return new ResponseEntity<>(uberns, HttpStatus.OK);
    }

    @PostMapping(value ="/add")
    public ResponseEntity<?> addUberns(@RequestBody UbernsDto ubernsDto){

        try {
            if (ubernsService.doesUbernsExist(ubernsDto.getFirstName())){

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uberns already exist.");
            }
            Uberns uberns = ubernsService.addUberns(ubernsDto);
            return new ResponseEntity<>(uberns, HttpStatus.CREATED);
        }catch (Exception ex){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the Uberns");
        }
    }


    @DeleteMapping(value = "/{uberId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long uberId){

        ubernsService.deleteUberns(uberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
