package com.rpajaziti.bookshop.dao;

import com.rpajaziti.bookshop.entity.CartDetail;

import java.util.List;

public interface CartDetailDAO {

    List<CartDetail> getCartDetails();

    CartDetail saveOrUpdateCartDetail(CartDetail cartDetail);

    CartDetail getCartDetail(String id);
}
