package com.example.employe.management.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String companyName;

    @NotNull
    private String country; // Store the selected country as a string

    @NotNull
    private String street;

    private String apartment;
    private  String hotelName;
    private String typeRoom;
    private boolean viewOfMakkah;
    private Integer numbnuit;

    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private Integer numberOfB;
    private Date dateRes;
    private String optionName;
    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String codePostal;

    @NotNull
    private String number;

    @NotNull
    private String email;


    private String hajName;
    private int quantityAd;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private int quantityVen;
    @NotNull
    private BigDecimal totalPrice;
    @OneToOne(mappedBy = "invoice")
    @JsonIgnore
    private CartItem cartItem;
}
