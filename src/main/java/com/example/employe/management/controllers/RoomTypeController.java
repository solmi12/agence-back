package com.example.employe.management.controllers;


import com.example.employe.management.dto.RoomTypeDto;
import com.example.employe.management.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room-types")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/{roomTypeId}")
    public ResponseEntity<Object> getRoomTypeById(@PathVariable Long roomTypeId) {
        RoomTypeDto roomTypeDto = roomTypeService.getRoomTypeById(roomTypeId);

        if (roomTypeDto != null) {
            return new ResponseEntity<>(roomTypeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Room type not found", HttpStatus.NOT_FOUND);
        }
    }

}
