package com.lab1.lab.Service;

import com.lab1.lab.Model.ShoppingCart;
import com.lab1.lab.Model.dto.ChargeRequest;
import com.stripe.exception.*;
import com.stripe.model.Charge;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart findActiveShoppingCartByUsername(String userId);

    List<ShoppingCart> findAllByUsername(String userId);

    ShoppingCart createNewShoppingCart(String userId);

    ShoppingCart removeProductFromShoppingCart(String userId,
                                               Long productId);
    ShoppingCart addProductToShoppingCart(String userId,
                                          Long productId);

    ShoppingCart getActiveShoppingCart(String userId);

    ShoppingCart cancelActiveShoppingCart(String userId);

    ShoppingCart checkoutShoppingCart(String userId, ChargeRequest chargeRequest);
}
