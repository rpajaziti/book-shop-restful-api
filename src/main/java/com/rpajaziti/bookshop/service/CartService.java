package com.rpajaziti.bookshop.service;

import com.rpajaziti.bookshop.entity.Cart;

public interface CartService {

    //List<Cart> getCarts();

    Cart saveCart(Cart cart);

    String updateCart(Cart cart);

    Cart getCart(String theId);
}
