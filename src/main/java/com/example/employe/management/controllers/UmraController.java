package com.example.employe.management.controllers;

import com.example.employe.management.exception.UmraFoundException;
import com.example.employe.management.service.UmraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.example.employe.management.dto.UmraDto;
import com.example.employe.management.model.Umra;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/umra")
public class UmraController {
    @Autowired
    UmraService umraService;

    @PostMapping("/add")
    public ResponseEntity<?> addFaq(@RequestBody UmraDto umraDto){
        try{
            Umra newUmra = umraService.addUmra(umraDto);
            return  new ResponseEntity<>(newUmra, HttpStatus.CREATED);
        } catch (UmraFoundException ex){
            return  new ResponseEntity<>("Faq with the same title already exists",HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return new ResponseEntity<>("An error occurred while adding Faq", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
