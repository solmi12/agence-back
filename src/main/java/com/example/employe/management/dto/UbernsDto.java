package com.example.employe.management.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbernsDto {

    private Long uberId;
    private String firstName;
    private String  firstPicture;
    private String secondName;
    private String secondPicture;
    private String thirdName;
    private String thirdPicture;
}
