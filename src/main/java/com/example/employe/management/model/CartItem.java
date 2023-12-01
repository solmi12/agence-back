package com.example.employe.management.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "haj_id")
    @ManyToOne
    @JsonManagedReference
    private Haj haj;


    private int quantityAd;
    private int quantityVen;
    private String sessionIdentifier;
    @OneToOne
    @JoinColumn(name = "invoice_id")
     @JsonIgnore
    private Invoice invoice;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", haj=" + haj +
                ", invoiceId=" + (invoice != null ? invoice.getInvId() : "N/A") +
                ", quantityAd=" + quantityAd +
                ", quantityVen=" + quantityVen +
                ", sessionIdentifier='" + sessionIdentifier + '\'' +
                '}';
    }

}
