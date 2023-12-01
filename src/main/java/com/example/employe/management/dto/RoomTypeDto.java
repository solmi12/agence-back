package com.example.employe.management.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDto {

    private Long id;
    @NotNull
    private String typeRoom;

    @NotNull
    private double priceAdulte;

    @NotNull
    private double priceChildB;
    @NotNull
    private double priceChild;
    @NotNull
    private Double viewOfMakkah;

    @NotNull
    private int maxOccupancy;
}
