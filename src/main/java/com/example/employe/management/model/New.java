package com.example.employe.management.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class New {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newId;


    @NotNull
    private String newName;
    @NotNull
    @Column(name = "new_description", columnDefinition = "TEXT")
    private String newDescription;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;

}
