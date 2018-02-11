package com.rpajaziti.bookshop.service;

import com.rpajaziti.bookshop.dao.BookDAO;
import com.rpajaziti.bookshop.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    @Transactional
    public List<Book> searchBooks(String q) {
        return bookDAO.searchBook(q);
    }

    @Override
    @Transactional
    public Book saveOrUpdateBook(Book book) {
        bookDAO.saveOrUpdateBook(book);
        return book;
    }

    @Override
    @Transactional
    public Book getBook(String id) {
        return bookDAO.getBookById(id);
    }
}
