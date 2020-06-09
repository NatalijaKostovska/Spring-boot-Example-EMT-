package com.lab1.lab.Web_Controller;

import com.lab1.lab.Model.Kniga;
import com.lab1.lab.Model.ShoppingCart;
import com.lab1.lab.Model.dto.ChargeRequest;
import com.lab1.lab.Service.AuthService;
import com.lab1.lab.Service.ShoppingCartService;
import com.stripe.exception.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")

public class PaymentController {


    @Value("${STRIPE_P_KEY}")
    public String publickey;
    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;
    public PaymentController(ShoppingCartService shoppingCartService, AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping("/charge")
    public String getCheckoutPage(Model model) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.findActiveShoppingCartByUsername(this.authService.getCurrentUserId());
            model.addAttribute("shoppingCart", shoppingCart);
            model.addAttribute("currency", "eur");
            model.addAttribute("amount", (int)(shoppingCart.getBooks().stream().mapToDouble(Kniga::getPrice).sum() * 100));
            model.addAttribute("stripePublicKey", this.publickey);
            return "checkout";
        } catch (RuntimeException ex) {
            return "redirect:/books?error=" + ex.getLocalizedMessage();
        }
    }


    @PostMapping("/charge")
    public String checkout(ChargeRequest chargeRequest, Model model) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartService.checkoutShoppingCart(this.authService.getCurrentUserId(), chargeRequest);
            return "redirect:/products?message=Successful Payment";
        } catch (RuntimeException ex) {
            return "redirect:/payments/charge?error=" + ex.getLocalizedMessage();

        }
    }




}
