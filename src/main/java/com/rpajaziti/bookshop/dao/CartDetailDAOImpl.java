package com.rpajaziti.bookshop.dao;

import com.rpajaziti.bookshop.entity.CartDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDetailDAOImpl implements CartDetailDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CartDetailDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<CartDetail> getCartDetails() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<CartDetail> theQuery =
                currentSession.createQuery("FROM CartDetail",
                        CartDetail.class);

        return theQuery.getResultList();
    }

    @Override
    public CartDetail saveOrUpdateCartDetail(CartDetail cartDetail) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(cartDetail);
        return cartDetail;
    }

    @Override
    public CartDetail getCartDetail(String id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(CartDetail.class, id);
    }
}