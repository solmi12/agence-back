package com.example.employe.management.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Uberns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uberId;


    @NotNull
    private String firstName;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] firstPicture;

    @NotNull
    private String secondName;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] secondPicture;

    @NotNull
    private String thirdName;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] thirdPicture;
}
