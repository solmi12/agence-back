package com.example.employe.management.controllers;


import com.example.employe.management.dto.FaqDto;
import com.example.employe.management.dto.KontaktDto;
import com.example.employe.management.exception.FaqFoundException;
import com.example.employe.management.exception.KontaktFoundException;
import com.example.employe.management.model.Faq;
import com.example.employe.management.model.Kontakt;
import com.example.employe.management.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/faqs")
public class FaqController {

    @Autowired
    private FaqService faqService;

    @PostMapping("/add")
    public ResponseEntity<?> addFaq(@RequestBody FaqDto faqDto){
        try{
            Faq newFaq = faqService.addFaq(faqDto);
            return  new ResponseEntity<>(newFaq, HttpStatus.CREATED);
        } catch (FaqFoundException ex){
            return  new ResponseEntity<>("Faq with the same title already exists",HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return new ResponseEntity<>("An error occurred while adding Faq", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Faq> getAllFaqs() {
        return faqService.getAllFaqs();
    }

    @GetMapping("/{id}")
    public Faq getFaqById(@PathVariable Integer id) {
        return faqService.getFaqById(id);
    }

    @GetMapping("/category/{category}")
    public List<Faq> getFaqsByCategory(@PathVariable String category) {
        return faqService.getFaqsByCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faq> updateFaq(@PathVariable Integer id, @RequestBody FaqDto updatedFaqDto) {
        Faq updated = faqService.updateFaq(id, updatedFaqDto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteFaq(@PathVariable Integer id) {
        if (faqService.deleteFaq(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
