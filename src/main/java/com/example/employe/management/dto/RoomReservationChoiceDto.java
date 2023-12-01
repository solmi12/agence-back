package com.example.employe.management.dto;

import com.example.employe.management.model.Hotels;
import com.example.employe.management.model.RoomType;
import com.example.employe.management.service.HotelsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomReservationChoiceDto {

    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private Integer numberOfB;
    private double totalpriceRes ;
    private String hotelNamee;

    private Integer numbnuit;
    private Date dateRes;

    private String sessionIdentifier;
    private boolean viewOfMakkah;
    private List<RoomTypeDto> roomTypes;
    private List<ReservationOptionDto> reservationOptions;
}
