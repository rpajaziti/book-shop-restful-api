package com.rpajaziti.bookshop.dao;

import com.rpajaziti.bookshop.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CartDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Cart> getCarts() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Cart> theQuery =
                currentSession.createQuery("FROM Cart",
                        Cart.class);

        return theQuery.getResultList();
    }

    @Override
    public Cart saveCart(Cart cart) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(cart);
        return cart;
    }

    @Override
    public void updateCart(Cart cart) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(cart);
    }

    @Override
    public Cart getCart(String id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Cart.class, id);
    }
}