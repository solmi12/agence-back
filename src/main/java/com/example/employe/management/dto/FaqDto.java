package com.example.employe.management.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@AllArgsConstructor
@NoArgsConstructor
@Data
public class FaqDto {

    @NotNull
        private String titleFaq;



    @NotNull
    private String descriptionFaq;
    @NotNull
    private String categoriefaq;











}
