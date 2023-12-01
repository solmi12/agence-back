package com.example.employe.management.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomReservationChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resId;

    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private Integer numberOfB;
    private boolean viewOfMakkah;
    @NotNull
    private String sessionIdentifier;
    @NotNull
    private Integer numbnuit;
    @NotNull
    private Date dateRes;
    private String hotelNamee;


    private double totalpriceRes ;

    @ManyToMany
    @JoinTable(
            name = "room_reservation_choice_room_type",
            joinColumns = @JoinColumn(name = "res_id"),
            inverseJoinColumns = @JoinColumn(name = "room_type_id")
    )
    private List<RoomType> roomTypes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "room_reservation_choice_reservation_option",
            joinColumns = @JoinColumn(name = "res_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_option_id")
    )
    private List<ReservationOption> reservationOptions = new ArrayList<>();


}
