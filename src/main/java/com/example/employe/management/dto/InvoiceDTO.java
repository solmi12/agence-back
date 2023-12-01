package com.example.employe.management.dto;


import com.example.employe.management.model.PaymentMethod;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoiceDTO {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String companyName;

    @NotNull
    private String country; // This will hold the selected country code


    @NotNull
    private String street;

    private String apartment;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String codePostal;

    private  String hotelName;
    private String typeRoom;
    private boolean viewOfMakkah;
    private Integer numbnuit;

    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private Integer numberOfB;
    private Date dateRes;
    private String optionName;
    private String hajName;
    @NotNull
    private String Number;

    @NotNull
    @Email
    private String email;
    private int quantityAd;

    @NotNull
    private String paymentMethod;
    private int quantityVen;
    @NotNull
    private BigDecimal totalPrice;
}
