package com.rpajaziti.bookshop.dao;

import com.rpajaziti.bookshop.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Book> getBooks() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Book> theQuery =
                currentSession.createQuery("FROM Book ORDER BY name",
                        Book.class);

        return theQuery.getResultList();
    }

    @Override
    public void saveOrUpdateBook(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(book);
    }

    @Override
    public Book getBookById(String id) {
        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.get(Book.class, id);
    }

    @Override
    public List<Book> searchBook(String q) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Book> theQuery =
                currentSession.createQuery("FROM Book WHERE name LIKE :name\n" +
                        "OR isbn = :isbn\n" +
                        " ORDER BY name", Book.class);

        theQuery.setParameter("name", "%" + q);
        theQuery.setParameter("isbn", q);

        return theQuery.getResultList();
    }
}