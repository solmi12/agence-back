package com.example.employe.management.controllers;

import com.example.employe.management.Repo.CartItemRepository;
import com.example.employe.management.dto.CartItemDTO;
import com.example.employe.management.dto.CartViewDTO;
import com.example.employe.management.model.CartItem;
import com.example.employe.management.model.CartView;
import com.example.employe.management.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;


import java.util.Collections;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CartController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addToCart(
            @RequestBody CartItemDTO cartItemDTO
    ) {
        try {
            HttpSession session = request.getSession();
            String sessionIdentifier = session.getId();

            // Ensure that cartItemDTO has a valid hajId
            Long hajId = cartItemDTO.getHaj().getHajId();
            if (hajId == null) {
                // Handle the case where hajId is missing or invalid
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Invalid hajId");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            // Set the sessionIdentifier in the cartItemDTO
            cartItemDTO.setSessionIdentifier(sessionIdentifier);

            // Add the item to the cart using the service method
            shoppingCartService.addToCart(cartItemDTO);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Product added to cart");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "An error occurred."));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CartViewDTO> deleteCartItem(@PathVariable Long id) {
        try {
            // Call the service method to delete the cart item
            shoppingCartService.deleteCartItem(id);

            // Get the updated cart view and return it
            HttpSession session = request.getSession();
            String sessionIdentifier = session.getId();
            List<CartItem> cartItems = shoppingCartService.getCartItemsBySessionIdentifier(sessionIdentifier);
            CartView cartSummary = shoppingCartService.calculateCartView(cartItems);
            cartSummary.setCartItems(cartItems);
            CartViewDTO cartSummaryDTO = new CartViewDTO(
                    cartSummary.getTotalQuantityAd(),
                    cartSummary.getTotalQuantityVen(),
                    cartSummary.getTotalPriceCart(),
                    cartItems
            );
            cartSummaryDTO.setCartItems(cartItems);

            return ResponseEntity.ok(cartSummaryDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Handle errors here
        }
    }

    @GetMapping("/view")
    public ResponseEntity<CartViewDTO> getCartSummary() {
        HttpSession session = request.getSession();
        String sessionIdentifier = session.getId();

        List<CartItem> cartItems = shoppingCartService.getCartItemsBySessionIdentifier(sessionIdentifier);
        for (CartItem cartItem : cartItems) {
            System.out.println("CartItem: " + cartItem);
        }

        CartView cartSummary = shoppingCartService.calculateCartView(cartItems);

        // Set the cartItems list in the CartView and CartViewDTO
        cartSummary.setCartItems(cartItems);

        CartViewDTO cartSummaryDTO = new CartViewDTO(
                cartSummary.getTotalQuantityAd(),
                cartSummary.getTotalQuantityVen(),
                cartSummary.getTotalPriceCart(),
                cartItems
        );
        cartSummaryDTO.setCartItems(cartItems);

        return ResponseEntity.ok(cartSummaryDTO);
    }



    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateCartItem(
            @RequestBody CartItemDTO updatedCartItemDTO
    ) {
        try {
            HttpSession session = request.getSession();
            // Retrieve the user's session identifier
            String sessionIdentifier = session.getId();

            // Update the cart item using the service method and pass the sessionIdentifier
            shoppingCartService.updateCartItem(updatedCartItemDTO, sessionIdentifier);

            Map<String, String> response = new HashMap<>();
            response.put("message", "CartItem updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "An error occurred."));
        }
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("cartItems");
        return ResponseEntity.ok("Cart cleared");
    }
}
