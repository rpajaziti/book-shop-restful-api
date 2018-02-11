package com.rpajaziti.bookshop.service;

import com.rpajaziti.bookshop.dao.BookDAO;
import com.rpajaziti.bookshop.dao.CartDetailDAO;
import com.rpajaziti.bookshop.entity.Book;
import com.rpajaziti.bookshop.entity.CartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    private final CartDetailDAO cartDetailDAO;
    private final BookDAO bookDAO;

    @Autowired
    public CartDetailServiceImpl(CartDetailDAO cartDetailDAO, BookDAO bookDAO) {
        this.cartDetailDAO = cartDetailDAO;
        this.bookDAO = bookDAO;
    }

    /*@Override
    public List<Cart> getCarts() {
        return cartDAO.getCarts();
    }*/

    @Override
    @Transactional
    public List<CartDetail> getCartDetails() {
        return cartDetailDAO.getCartDetails();
    }

    @Override
    @Transactional
    public CartDetail saveOrUpdateCartDetail(CartDetail cartDetail) {
        Book book = bookDAO.getBookById(cartDetail.getBookId());

        if (book.getQuantity() - cartDetail.getQuantity() <= 0) {
            return null;
        }

        if (cartDetail.getPrice() == null) {
            cartDetail.setPrice(book.getPrice());
            cartDetail.setTotal(book.getPrice() * cartDetail.getQuantity());
        }

        cartDetail = cartDetailDAO.saveOrUpdateCartDetail(cartDetail);

        return cartDetail;
    }

    @Override
    @Transactional
    public CartDetail getCartDetail(String id) {
        return cartDetailDAO.getCartDetail(id);
    }
}