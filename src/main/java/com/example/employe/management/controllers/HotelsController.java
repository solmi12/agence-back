package com.example.employe.management.controllers;


import com.example.employe.management.dto.HoteldDto;
import com.example.employe.management.model.Hotels;
import com.example.employe.management.service.HotelsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelsController {

    private final HotelsService hotelsService;

    @Autowired
    public HotelsController(HotelsService hotelsService) {
        this.hotelsService = hotelsService;
    }

    @GetMapping
    public ResponseEntity<List<Hotels>> getAllHotels() {
        List<Hotels> hotels = hotelsService.getAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotels> getHotelById(@PathVariable Long hotelId) {
        Optional<Hotels> hotel = hotelsService.getHotelById(hotelId);
        return hotel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping(value = "/add")
    public ResponseEntity<?> addHotel(@RequestBody HoteldDto hotelDto) {
        try {
            // Check if a hotel with the same name already exists
            if (hotelsService.doesHotelExist(hotelDto.getHotelName())) {
                // If it exists, return an error response
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hotel already exists.");
            }


            // If it doesn't exist, proceed to add the hotel
            Hotels hotel = hotelsService.addHotel(hotelDto);
            return new ResponseEntity<>(hotel, HttpStatus.CREATED);
        } catch (Exception ex) {
            // Handle the exception and send an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the hotel.");
        }
    }


    private byte[] decodeBase64Image(String base64Image) {
        if (base64Image != null && !base64Image.isEmpty()) {
            return Base64.getDecoder().decode(base64Image);
        }
        return null;
    }
    /*@PutMapping("/{hotelId}")
    public ResponseEntity<Hotels> updateHotel(@PathVariable Long hotelId, @Valid @RequestBody HoteldDto hotelDto) {
        Optional<Hotels> existingHotel = hotelsService.getHotelById(hotelId);
        if (existingHotel.isPresent()) {
            Hotels updatedHotel = new Hotels();
            updatedHotel.setName(hotelDto.getName());
            updatedHotel.setPrix(hotelDto.getPrix());
            updatedHotel.setAdresse(hotelDto.getAdresse());

            Hotels hotel = hotelsService.updateHotel(hotelId, updatedHotel);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
        hotelsService.deleteHotel(hotelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
