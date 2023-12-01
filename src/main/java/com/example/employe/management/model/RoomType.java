package com.example.employe.management.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String typeRoom; // Updated to be a String to describe room type

    @NotNull
    private double priceAdulte;

    @NotNull
    private double priceChild;


    @NotNull
    private double priceChildB;

    @NotNull
    private Double viewOfMakkah;
    @NotNull
    private int maxOccupancy; // Maximum number of guests in the room

    @ManyToOne
    @JsonIgnore
    private Hotels hotel;
}
