package com.example.employe.management.controllers;


import com.example.employe.management.dto.KontaktDto;
import com.example.employe.management.exception.KontaktFoundException;
import com.example.employe.management.model.Kontakt;
import com.example.employe.management.service.KontaktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kontakt")
public class KontaktController {

    @Autowired
    private KontaktService kontaktService;

    @PostMapping("/add")
    public ResponseEntity<?> addKontakt(@RequestBody KontaktDto kontaktDto){
        try{
            Kontakt newKontakt = kontaktService.addKontakt(kontaktDto);
            return  new ResponseEntity<>(newKontakt, HttpStatus.CREATED);
        } catch (KontaktFoundException ex){
            return  new ResponseEntity<>("Kontakt with the same title already exists",HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return new ResponseEntity<>("An error occurred while adding kontakt", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kontakt> updateKontakt(@PathVariable Integer id, @RequestBody KontaktDto kontaktDto) {
        Kontakt updatedKontakt = kontaktService.updateKontakt(id, kontaktDto);
        return ResponseEntity.ok(updatedKontakt);
    }

    @DeleteMapping("/get/{id}")
    public ResponseEntity<Void> deleteKontakt(@PathVariable Integer id) {
        kontaktService.deleteKontakt(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Kontakt>> getAllKontakts() {
        List<Kontakt> kontaktList = kontaktService.getAllKontakts();
        return ResponseEntity.ok(kontaktList);
    }
    @GetMapping("/byIsShow/{isShow}")
    public ResponseEntity<Kontakt> getKontaktByIsShow(@PathVariable Boolean isShow) {
        Kontakt kontakt = kontaktService.getKontaktByIsShow(isShow);
        return ResponseEntity.ok(kontakt);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kontakt> getKontaktById(@PathVariable Integer id) {
        Kontakt kontakt = kontaktService.getKontaktById(id);
        return ResponseEntity.ok(kontakt);
    }
    @GetMapping("/byTitle/{title}")
    public ResponseEntity<List<Kontakt>> getKontaktsByTitle(@PathVariable String title) {
        List<Kontakt> kontaktList = kontaktService.getKontaktsByTitle(title);
        return ResponseEntity.ok(kontaktList);
    }
}
