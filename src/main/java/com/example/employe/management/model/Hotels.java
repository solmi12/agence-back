package com.example.employe.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Hotels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @NotNull
    private  String hotelName;

    @NotNull
    private Integer numberOfStars;

    @NotNull
    private String adresse;
    @NotNull
    private String location;
    @NotNull
    private String priceAb;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image5;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image1;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image2;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image3;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image4;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image6;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image7;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image8;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image9;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image10;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomType> roomTypes = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationOption> reservationOptions = new ArrayList();
}
