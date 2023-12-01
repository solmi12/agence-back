package com.example.employe.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@NoArgsConstructor
public class CartView {
    private int totalQuantityAd;
    private int totalQuantityVen;
    private double totalPriceCart;
    private List<CartItem> cartItems;
    public CartView(int totalQuantityAd, int totalQuantityVen, double totalPriceCart, List<CartItem> cartItems) {
        this.totalQuantityAd = totalQuantityAd;
        this.totalQuantityVen = totalQuantityVen;
        this.totalPriceCart = totalPriceCart;
        this.cartItems = cartItems;
    }
}

