package com.rpajaziti.bookshop.controller;

import com.rpajaziti.bookshop.custom.ResponseMessage;
import com.rpajaziti.bookshop.entity.Cart;
import com.rpajaziti.bookshop.entity.Customer;
import com.rpajaziti.bookshop.service.CartService;
import com.rpajaziti.bookshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") String id) {
        return new ResponseEntity<>(cartService.getCart(id), HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<?> saveCart(@RequestBody Cart cart) {

        cart = cartService.saveCart(cart);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ResponseMessage().setMessage("Bad Request."), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public ResponseEntity<ResponseMessage> updateCart(@PathVariable("id") String id,
                                                      @RequestBody Cart cart) {
        if (cart.getId() == null) {
            cart.setId(id);
        }
        String result = cartService.updateCart(cart);
        if (result == null) {
            return new ResponseEntity<>(new ResponseMessage().setMessage("Updated Successfully."), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage().setMessage(result), HttpStatus.BAD_REQUEST);
    }
}
