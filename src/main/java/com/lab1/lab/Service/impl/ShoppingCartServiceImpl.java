package com.lab1.lab.Service.impl;

import com.lab1.lab.Model.CartStatus;
import com.lab1.lab.Model.Kniga;
import com.lab1.lab.Model.ShoppingCart;
import com.lab1.lab.Model.User;
import com.lab1.lab.Model.dto.ChargeRequest;
import com.lab1.lab.Model.exceptions.*;
import com.lab1.lab.Repository.ShoppingCartRepository;
import com.lab1.lab.Service.PaymentService;
import com.lab1.lab.Service.ShoppingCartService;
import com.lab1.lab.Service.UserService;
import com.lab1.lab.Service.knigaService;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final knigaService productService;
    private final PaymentService paymentService;


    public ShoppingCartServiceImpl(UserService userService,
                                   ShoppingCartRepository shoppingCartRepository, knigaService productService, PaymentService paymentService) {
        this.userService = userService;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
        this.paymentService = paymentService;
    }

    @Override
    public ShoppingCart findActiveShoppingCartByUsername(String userId) {
        return this.shoppingCartRepository.findByUserUsernameAndStatus(userId, CartStatus.CREATED).orElseThrow(()-> new ShoppingCartIsNotActiveException(userId));
    }

    @Override
    public List<ShoppingCart> findAllByUsername(String userId) {
        return this.shoppingCartRepository.findAllByUserUsername(userId);
    }

    @Override
    public ShoppingCart createNewShoppingCart(String userId) {
        User user = this.userService.findById(userId);
        if (this.shoppingCartRepository.existsByUserUsernameAndStatus(
                user.getUsername(),
                CartStatus.CREATED
        )) {
            throw new ShoppingCartIsAlraedyCreatedd(userId);
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return this.shoppingCartRepository.save(shoppingCart);
    }



    @Override
    @Transactional
    public ShoppingCart removeProductFromShoppingCart(String userId, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);
        shoppingCart.setBooks(
                shoppingCart.getBooks()
                        .stream()
                        .filter(product -> !product.getId().equals(productId))
                        .collect(Collectors.toList())
        );
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Transactional
    public ShoppingCart addProductToShoppingCart(String userId, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);
        Kniga product = this.productService.findById(productId);
        for(Kniga k : shoppingCart.getBooks()){
            if(k.getId().equals(productId)){
                throw new BookIsAlreadyInShoppingCartException(product.getImeKniga());

            }
        }
        shoppingCart.getBooks().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }


    @Override
    public ShoppingCart getActiveShoppingCart(String userId) {
        return this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, CartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    User user = this.userService.findById(userId);
                    shoppingCart.setUser(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart cancelActiveShoppingCart(String userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));
        shoppingCart.setStatus(CartStatus.CANCELED);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart checkoutShoppingCart(String userId, ChargeRequest chargeRequest) {
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserUsernameAndStatus(userId, CartStatus.CREATED)
                .orElseThrow(() -> new ShoppingCartIsNotActiveException(userId));

        List<Kniga> products = shoppingCart.getBooks();
        float price = 0;

        for (Kniga k : products) {
            if (k.getBrojPrimeroci() <= 0) {
                throw new BookOutOfStockException(k.getImeKniga());
            }
            k.setBrojPrimeroci(k.getBrojPrimeroci() - 1);
            price += k.getPrice();
        }
        Charge charge = null;
        try {
            charge = this.paymentService.pay(chargeRequest);
        }
        catch ( CardException | APIException | AuthenticationException | APIConnectionException | InvalidRequestException e){
            throw new TransactionFailedException(userId, e.getMessage());
        }
        shoppingCart.setBooks(products);
        shoppingCart.setStatus(CartStatus.FINISHED);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
