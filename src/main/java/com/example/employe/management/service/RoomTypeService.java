package com.example.employe.management.service;


import com.example.employe.management.Repo.RoomTypeRepository;
import com.example.employe.management.dto.RoomTypeDto;
import com.example.employe.management.exception.ResourceNotFoundException;
import com.example.employe.management.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;


    public RoomTypeDto getRoomTypeById(Long roomTypeId) {
        Optional<RoomType> roomTypeOptional = roomTypeRepository.findById(roomTypeId);

        if (roomTypeOptional.isPresent()) {
            RoomType roomType = roomTypeOptional.get();
            // You can use a DTO mapper or constructor to convert RoomType to RoomTypeDto.
            RoomTypeDto roomTypeDto = new RoomTypeDto();
            roomTypeDto.setId(roomType.getId());
            roomTypeDto.setTypeRoom(roomType.getTypeRoom());
            roomTypeDto.setPriceAdulte(roomType.getPriceAdulte());
            roomTypeDto.setPriceChildB(roomType.getPriceChildB());
            roomTypeDto.setPriceChild(roomType.getPriceChild());
            roomTypeDto.setViewOfMakkah(roomType.getViewOfMakkah());
            roomTypeDto.setMaxOccupancy(roomType.getMaxOccupancy());

            return roomTypeDto;
        } else {
            throw new ResourceNotFoundException("Room type with ID " + roomTypeId + " not found");

        }
    }
}
