package com.lab1.lab.Web_Controller;


import com.lab1.lab.Model.ShoppingCart;
import com.lab1.lab.Service.AuthService;
import com.lab1.lab.Service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/shopping-carts")
public class ShoppingCartRestController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartRestController(ShoppingCartService shoppingCartService,
                                      AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping
    public List<ShoppingCart> findAllByUsername() {
        return this.shoppingCartService.findAllByUsername(this.authService.getCurrentUserId());
    }

    @PostMapping
    public ShoppingCart createNewShoppingCart() {
        return this.shoppingCartService.createNewShoppingCart(this.authService.getCurrentUserId());
    }

    @PatchMapping("/{productId}/books")
    public ShoppingCart addProductToCart(@PathVariable Long productId) {
        return this.shoppingCartService.addProductToShoppingCart(
                this.authService.getCurrentUserId(),
                productId
        );

    }

    @DeleteMapping("/{productId}/books")
    public ShoppingCart removeProductFromCart(@PathVariable Long productId) {
        return this.shoppingCartService.removeProductFromShoppingCart(
                this.authService.getCurrentUserId(),
                productId
        );
    }

    @PatchMapping("/cancel")
    public ShoppingCart cancelActiveShoppingCart() {
        return this.shoppingCartService.cancelActiveShoppingCart(this.authService.getCurrentUserId());
    }

    @PatchMapping("/checkout")
    public ShoppingCart checkoutActiveShoppingCart() {
//        return this.shoppingCartService.checkoutShoppingCart(this.authService.getCurrentUserId())'
        return null;
    }



}
