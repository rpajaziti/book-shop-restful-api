package com.rpajaziti.bookshop.dao;

import com.rpajaziti.bookshop.entity.Book;

import java.util.List;

public interface BookDAO {

    List<Book> getBooks();

    void saveOrUpdateBook(Book book);

    Book getBookById(String id);

    List<Book> searchBook(String name, String isbn, String categoryId);
}
