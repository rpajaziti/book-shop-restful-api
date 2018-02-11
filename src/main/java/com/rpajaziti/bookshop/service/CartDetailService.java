package com.rpajaziti.bookshop.service;

import com.rpajaziti.bookshop.entity.Cart;
import com.rpajaziti.bookshop.entity.CartDetail;

import java.util.List;

public interface CartDetailService {
    List<CartDetail> getCartDetails();

    CartDetail saveOrUpdateCartDetail(CartDetail cart);

    CartDetail getCartDetail(String id);
}
