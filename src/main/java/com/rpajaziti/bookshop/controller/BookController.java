
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

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id) {
        return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Book>> searchBook(@RequestParam(value = "q", required = false) String q,
                                                 @RequestParam(value = "isbn", required = false) String isbn,
                                                 @RequestParam(value = "category_id", required = false) String categoryId) {
        return new ResponseEntity<>(bookService.searchBooks(q, isbn, categoryId), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveOrUpdateBook(book), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseMessage> updateBook(@PathVariable("id") String id,
                                                      @RequestBody Book book) {
        if (book.getId() == null) {
            book.setId(id);
        }
        bookService.saveOrUpdateBook(book);

        return new ResponseEntity<>(new ResponseMessage().setMessage("Updated Successfully"), HttpStatus.OK);
    }
}
