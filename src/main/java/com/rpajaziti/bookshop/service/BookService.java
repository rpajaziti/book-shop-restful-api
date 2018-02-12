package com.rpajaziti.bookshop.service;

import com.rpajaziti.bookshop.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();

    List<Book> searchBooks(String q, String isbn, String categoryId);

    Book saveOrUpdateBook(Book book);

    Book getBook(String id);
}
