package com.example.employe.management.dto;



import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoteldDto {
    @NotNull
    private  String hotelName;


    @NotNull
    private Integer numberOfStars;

    private String image5;  // Use a String for Base64-encoded image
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image6;
    private String image7;
    private String image8;
    private String image9;
    private String image10;
    @NotNull
    private String location;
    @NotNull
    private String priceAb;

    @NotNull
    private String adresse;
    private List<RoomTypeDto> roomTypes = new ArrayList<>();

    private List<ReservationOptionDto> reservationOptions = new ArrayList<>();
}
