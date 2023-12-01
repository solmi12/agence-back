package com.example.employe.management.dto;



import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewDto {



    private String imageData;


    @NotNull
    private String newName;

    private String newDescription;
}
