package com.example.employe.management.dto;


import com.example.employe.management.model.HajCategorie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HajDto {

    private Long hajId;
    private String hajName;
    private  String hajDescription;
    private String departureAirport;
    private String retrnAirport;
    private String going;
    private Integer reservationNumber;
    private String coming ;
    private  String airline;
    private String price;
    private  String offre1;
    private  String offre2;
    private  String offre3;
    private  String offre4;
    private  String offre5;
    private  String offre6;
    private  String offre7;
    private  String offre8;
    private  String offre9;
    private  String offre10;
    private String distanceMakka;
    private boolean showNow;
    private String distanceMadina;
    private boolean airfare;
    private boolean localTransportation;
    private boolean tourGuide;
    private boolean accommodation;
    private boolean entranceFees;
    private  boolean lunch;
    private boolean dinner;
    private String imageData;
    private String nbrDays;
    private String typeRoom;
    private Integer priceAd;
    private Integer priceVen;
    private Integer numb;
    private boolean guideGratuity;
    private  String hajDescription2;
    private HajCategorie hajCategorie;
}
