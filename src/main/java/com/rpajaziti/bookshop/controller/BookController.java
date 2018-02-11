
package com.rpajaziti.bookshop.controller;

import com.rpajaziti.bookshop.custom.ResponseMessage;
import com.rpajaziti.bookshop.entity.Book;
import com.rpajaziti.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id) {
        return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Book>> searchBook(@RequestParam("q") String q) {
        return new ResponseEntity<>(bookService.searchBooks(q), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveOrUpdateBook(book), HttpStatus.CREATED);

    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseMessage> updateBook(@PathVariable("id") String id,
                                                      @RequestBody Book book) {
        if (book.getId() == null) {
            book.setId(id);
        }
        bookService.saveOrUpdateBook(book);

        return new ResponseEntity<>(new ResponseMessage().setMessage("Updated Successfully"), HttpStatus.OK);
    }
}
