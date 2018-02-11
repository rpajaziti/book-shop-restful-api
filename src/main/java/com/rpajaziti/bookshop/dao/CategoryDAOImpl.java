package com.rpajaziti.bookshop.dao;

import com.rpajaziti.bookshop.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> getCategories() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Category> theQuery =
                currentSession.createQuery("FROM Category",
                        Category.class);

        return theQuery.getResultList();
    }

    @Override
    public void saveOrUpdateCategory(Category category) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(category);
    }

    @Override
    public Category getCategory(String id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Category.class, id);
    }
}