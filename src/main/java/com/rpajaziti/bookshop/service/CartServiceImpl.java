package com.rpajaziti.bookshop.service;

import com.rpajaziti.bookshop.dao.BookDAO;
import com.rpajaziti.bookshop.dao.CartDAO;
import com.rpajaziti.bookshop.entity.Book;
import com.rpajaziti.bookshop.entity.Cart;
import com.rpajaziti.bookshop.entity.CartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO;
    private final BookDAO bookDAO;

    @Autowired
    public CartServiceImpl(CartDAO cartDAO, BookDAO bookDAO) {
        this.cartDAO = cartDAO;
        this.bookDAO = bookDAO;
    }

    /*@Override
    public List<Cart> getCarts() {
        return cartDAO.getCarts();
    }*/

    @Override
    @Transactional
    public Cart saveCart(Cart cart) {
        double totalPrice = 0;
        for (CartDetail cartDetail : cart.getCartDetails()) {
            Book book = bookDAO.getBookById(cartDetail.getBookId());

            if (cartDetail.getPrice() == null) {
                cartDetail.setPrice(book.getPrice());
                cartDetail.setTotal(book.getPrice() * cartDetail.getQuantity());
            }

            totalPrice += cartDetail.getPrice();

            if (book.getQuantity() - cartDetail.getQuantity() < 0) {
                return null;
            }

            if (cart.getStatus().equals("COMPLETED")) {
                book.setQuantity(book.getQuantity() - cartDetail.getQuantity());
                bookDAO.saveOrUpdateBook(book);
            }

            cart.setCreatedAt(System.currentTimeMillis() / 1000L);
        }

        if (cart.getTotalPrice() == null) {
            cart.setTotalPrice(totalPrice);
        }

        cartDAO.saveCart(cart);

        return cart;
    }

    @Override
    @Transactional
    public String updateCart(Cart cart) {
        double totalPrice = 0;
        for (CartDetail cartDetail : cart.getCartDetails()) {
            Book book = bookDAO.getBookById(cartDetail.getBookId());

            if (book.getQuantity() - cartDetail.getQuantity() < 0) {
                return book.getQuantity() + " Books are on stock.";
            }

            if (cartDetail.getPrice() == null) {
                cartDetail.setPrice(book.getPrice());
                cartDetail.setTotal(book.getPrice() * cartDetail.getQuantity());
            }

            totalPrice += cartDetail.getPrice();

            if (cart.getStatus().equals("COMPLETED")) {
                book.setQuantity(book.getQuantity() - cartDetail.getQuantity());
                bookDAO.saveOrUpdateBook(book);
            }
        }

        if (cart.getTotalPrice() == null) {
            cart.setTotalPrice(totalPrice);
        }

        cartDAO.updateCart(cart);

        return null;
    }

    @Override
    @Transactional
    public Cart getCart(String id) {
        return cartDAO.getCart(id);
    }
}