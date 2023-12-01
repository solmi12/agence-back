package com.example.employe.management.dto;



import com.example.employe.management.model.Haj;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long id;
    private Haj haj;
    @JsonIgnore
    private Long invoiceId;
    private int quantityAd;
    private int quantityVen;
    private String sessionIdentifier;
}
