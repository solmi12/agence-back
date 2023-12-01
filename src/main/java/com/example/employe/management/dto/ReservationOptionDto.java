package com.example.employe.management.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationOptionDto {
    private Long id;
    @NotNull
    private String optionName;

    @NotNull
    private double price;
}
