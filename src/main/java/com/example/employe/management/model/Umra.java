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
@AllArgsConstructor
@NoArgsConstructor
public class Umra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer umraId;

    @NotNull
    private String umraName;
}
