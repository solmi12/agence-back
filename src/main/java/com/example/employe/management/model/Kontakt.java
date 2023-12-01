package com.example.employe.management.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kontakt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kontaktId;

    @NotNull
    private String title;


    @NotNull
    private String numTlp;
    @NotNull
    private String mail;
    @NotNull
    private String nachname;
    @NotNull
    private String description;
    @NotNull
    private Boolean isShow;

}
