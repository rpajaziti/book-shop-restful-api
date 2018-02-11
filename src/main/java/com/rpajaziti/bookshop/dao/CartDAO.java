package com.rpajaziti.bookshop.dao;

import com.rpajaziti.bookshop.entity.Cart;

import java.util.List;

public interface CartDAO {

    List<Cart> getCarts();

    Cart saveCart(Cart cart);

    void updateCart(Cart cart);

    Cart getCart(String id);
}
