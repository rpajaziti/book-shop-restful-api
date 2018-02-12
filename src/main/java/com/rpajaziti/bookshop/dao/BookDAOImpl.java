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
    public List<Book> searchBook(String q, String isbn, String categoryId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Book> theQuery =
                currentSession.createQuery("FROM Book WHERE ((:name IS NULL OR name LIKE :name) OR author LIKE :name)\n" +
                        "AND (:isbn IS NULL OR isbn = :isbn)\n" +
                        "AND (:category_id IS NULL OR category_id = :category_id)\n" +
                        " ORDER BY name", Book.class);
        if (q == null) {
            theQuery.setParameter("name", null);
        } else {
            theQuery.setParameter("name", "%" + q + "%");
        }

        theQuery.setParameter("isbn", isbn);
        theQuery.setParameter("category_id", categoryId);

        return theQuery.getResultList();
    }
}