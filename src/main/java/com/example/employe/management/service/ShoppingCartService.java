package com.example.employe.management.service;

import com.example.employe.management.Repo.CartItemRepository;
import com.example.employe.management.dto.CartItemDTO;
import com.example.employe.management.dto.CartViewDTO;
import com.example.employe.management.dto.HajDto;
import com.example.employe.management.model.CartItem;
import com.example.employe.management.model.CartView;
import com.example.employe.management.model.Haj;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;
    private final HajService hajService;

    @Autowired
    public ShoppingCartService(CartItemRepository cartItemRepository, ModelMapper modelMapper, HajService hajService) {
        this.cartItemRepository = cartItemRepository;
        this.modelMapper = modelMapper;
        this.hajService = hajService;
    }

    public void addToCart(CartItemDTO cartItemDTO) {
        // Map CartItemDTO to CartItem entity
        CartItem cartItem = modelMapper.map(cartItemDTO, CartItem.class);

        // Retrieve the session identifier from the CartItemDTO
        String sessionIdentifier = cartItemDTO.getSessionIdentifier();

        // Fetch the CartItem by hajId and session identifier
        CartItem existingCartItem = cartItemRepository.findByHaj_hajIdAndSessionIdentifier(cartItem.getHaj().getHajId(), sessionIdentifier);

        if (existingCartItem != null) {
            // Update the quantity if the item already exists in the cart
            existingCartItem.setQuantityAd(cartItem.getQuantityAd());
            existingCartItem.setQuantityVen(cartItem.getQuantityVen());
            cartItemRepository.save(existingCartItem);
        } else {
            // Create a new cart item if it doesn't exist
            cartItemRepository.save(cartItem);
        }
    }

    @Transactional
    public void updateCartItem(CartItemDTO cartItemDTO, String sessionIdentifier) {
        // Map CartItemDTO to CartItem entity
        CartItem cartItem = modelMapper.map(cartItemDTO, CartItem.class);

        // Fetch the CartItem by hajId and the session identifier passed as a parameter
        CartItem existingCartItem = cartItemRepository.findByHaj_hajIdAndSessionIdentifier(cartItem.getHaj().getHajId(), sessionIdentifier);

        if (existingCartItem != null) {
            // Update the quantities
            existingCartItem.setQuantityAd(cartItem.getQuantityAd());
            existingCartItem.setQuantityVen(cartItem.getQuantityVen());

            // Set the session identifier
            existingCartItem.setSessionIdentifier(sessionIdentifier);

            System.out.println("Received Cart Item: " + cartItemDTO);
            System.out.println("Updating CartItem for hajId: " + cartItem.getHaj().getHajId() + " and sessionIdentifier: " + sessionIdentifier);

            // Save the changes to the database
            cartItemRepository.save(existingCartItem);
        }
    }


    public List<CartItem> getCartItemsBySessionIdentifier(String sessionIdentifier) {
        return cartItemRepository.findBySessionIdentifier(sessionIdentifier);
    }
    public CartView calculateCartView(List<CartItem> cartItems) {
        int totalQuantityAd = cartItems.stream().mapToInt(CartItem::getQuantityAd).sum();
        int totalQuantityVen = cartItems.stream().mapToInt(CartItem::getQuantityVen).sum();

        double totalPrice = cartItems.stream()
                .mapToDouble(item ->
                        (item.getHaj().getPriceAd() * item.getQuantityAd()) +
                                (item.getHaj().getPriceVen() * item.getQuantityVen())
                )
                .sum();

        return new CartView(totalQuantityAd, totalQuantityVen, totalPrice, cartItems);

    }


    public void deleteCartItem(Long id) {
        // Use the cartItemRepository to delete the CartItem by its ID
        cartItemRepository.deleteById(id);
    }

    public void deleteCartItemsByHajId(Long hajId) {
        cartItemRepository.deleteByHajId(hajId);
    }
    public void clearCart(HttpSession session) {
        // Retrieve the session identifier
        String sessionIdentifier = getSessionIdentifier(session);

        // Remove all cart items associated with the session identifier
        List<CartItem> cartItems = cartItemRepository.findBySessionIdentifier(sessionIdentifier);
        cartItemRepository.deleteAll(cartItems);
    }

    private String getSessionIdentifier(HttpSession session) {
        String sessionIdentifier = (String) session.getAttribute("sessionIdentifier");

        if (sessionIdentifier == null) {
            // Generate a new session identifier if it doesn't exist
            sessionIdentifier = UUID.randomUUID().toString();
            session.setAttribute("sessionIdentifier", sessionIdentifier);
        }

        return sessionIdentifier;
    }
}
