package com.example.employe.management.dto;



import com.example.employe.management.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data

@NoArgsConstructor
public class CartViewDTO {
    private int totalQuantityAd;
    private int totalQuantityVen;
    private double totalPriceCart;
    private List<CartItem> cartItems;

    public CartViewDTO(int totalQuantityAd, int totalQuantityVen, double totalPriceCart, List<CartItem> cartItems) {
        this.totalQuantityAd = totalQuantityAd;
        this.totalQuantityVen = totalQuantityVen;
        this.totalPriceCart = totalPriceCart;
        this.cartItems = cartItems;
    }}
