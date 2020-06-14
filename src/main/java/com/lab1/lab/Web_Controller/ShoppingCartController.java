package com.lab1.lab.Web_Controller;

import com.lab1.lab.Model.ShoppingCart;
import com.lab1.lab.Service.AuthService;
import com.lab1.lab.Service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }


    @PostMapping("/{productId}/add-book")
    public String addProductToShoppingCart(@PathVariable Long productId) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.addProductToShoppingCart(this.authService.getCurrentUserId(), productId);
        } catch (RuntimeException ex) {
            return "redirect:/books?error=" + ex.getLocalizedMessage();
        }
        return "redirect:/books";
    }


    @PostMapping("/{productId}/remove-book")
    public String removeProductToShoppingCart(@PathVariable Long productId) {
        ShoppingCart shoppingCart = this.shoppingCartService.removeProductFromShoppingCart(this.authService.getCurrentUserId(), productId);
        return "redirect:/books";
    }
}
