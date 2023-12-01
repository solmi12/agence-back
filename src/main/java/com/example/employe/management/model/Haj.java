package com.example.employe.management.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Haj implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hajId;
    @Column(name = "haj_name")
    private String hajName;
    @Column(name = "haj_description", columnDefinition = "TEXT")
    private String hajDescription;
    @Column(name = "departure_airport")
    private String departureAirport;
    @Column(name = "retrn_airport")
    private String retrnAirport;
    @Column(name = "going")
    private String going;
    @Column(name = "reservation_number")
    private Integer reservationNumber;
    @Column(name = "coming")
    private String coming ;
    @Column(name = "airline")
    private  String airline;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;
    @Column(name = "nbr_days")
    private String nbrDays;
    @Column(name = "price_ven")
    private Integer priceVen;
    @Column(name = "distance_makka")
    private String distanceMakka;
    @Column(name = "distance_madina")
    private String distanceMadina;
    @Column(name = "type_room")
    private String typeRoom;
    @Column(name = "price_ad")
    private Integer priceAd;
    @Column(name = "price")
    private String price;
    @Column(name = "airfare")
    private boolean airfare;
    @Column(name = "local_transportation")
    private boolean localTransportation;
    @Column(name = "tour_guide")
    private boolean tourGuide;
    @Column(name = "accommadation")
    private boolean accommodation;
    @Column(name = "entrance_fees")
    private boolean entranceFees;
    @Column(name = "lunch")
    private  boolean lunch;
    @Column(name = "dinner")
    private boolean dinner;
    @Column(name = "show_now")
    private boolean showNow;
    @Column(name = "offre1")
    private  String offre1;
    @Column(name = "offre2")
    private  String offre2;
    @Column(name = "offre3")
    private  String offre3;
    @Column(name = "offre4")
    private  String offre4;
    @Column(name = "offre5")
    private  String offre5;
    @Column(name = "offre6")
    private  String offre6;
    @Column(name = "offre7")
    private  String offre7;
    @Column(name = "offre8")
    private  String offre8;
    @Column(name = "offre9")
    private  String offre9;
    @Column(name = "offre10")
    private  String offre10;
    @Column(name = "guide_gratuity")
    private boolean guideGratuity;
    @Column(name = "haj_description2", columnDefinition = "TEXT")
    private String hajDescription2;
    @Enumerated(EnumType.STRING)
    @Column(name = "haj_categorie")
    private HajCategorie hajCategorie;
    @Column(name = "numb")
    private Integer numb;

    @OneToMany(mappedBy = "haj", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonManagedReference
    private List<CartItem> cartItems;
    @Override
    public String toString() {
        return "Haj{" +
                "hajId=" + hajId +
                ", hajName='" + hajName + '\'' +
                ", hajDescription='" + hajDescription + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", retrnAirport='" + retrnAirport + '\'' +
                ", going='" + going + '\'' +
                ", reservationNumber=" + reservationNumber +
                ", numb=" + numb +
                ", coming='" + coming + '\'' +
                ", airline='" + airline + '\'' +
                ", image=" + Arrays.toString(image) +
                ", nbrDays='" + nbrDays + '\'' +
                ", priceVen=" + priceVen +
                ", distanceMakka='" + distanceMakka + '\'' +
                ", distanceMadina='" + distanceMadina + '\'' +
                ", typeRoom='" + typeRoom + '\'' +
                ", priceAd=" + priceAd +
                ", price='" + price + '\'' +
                ", airfare=" + airfare +
                ", localTransportation=" + localTransportation +
                ", tourGuide=" + tourGuide +
                ", accommodation=" + accommodation +
                ", entranceFees=" + entranceFees +
                ", lunch=" + lunch +
                ", dinner=" + dinner +
                ", showNow=" + showNow +
                ", offre1='" + offre1 + '\'' +
                ", offre2='" + offre2 + '\'' +
                ", offre3='" + offre3 + '\'' +
                ", offre4='" + offre4 + '\'' +
                ", offre5='" + offre5 + '\'' +
                ", offre6='" + offre6 + '\'' +
                ", offre7='" + offre7 + '\'' +
                ", offre8='" + offre8 + '\'' +
                ", offre9='" + offre9 + '\'' +
                ", offre10='" + offre10 + '\'' +
                ", guideGratuity=" + guideGratuity +
                ", hajDescription2='" + hajDescription2 + '\'' +
                ", hajCategorie=" + hajCategorie +
                '}';}

}
