package com.demo.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.demo.entities.Book;
import com.demo.demo.services.BookService;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> list = bookService.getAllBooks();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    // get single book
    @GetMapping("book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    @PostMapping("/addbook")
    public ResponseEntity<Book> createUser(@RequestBody Book book) {
        Book b = null;
        try {

            b = this.bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    // delete recoed
    @DeleteMapping("deletebook/{bid}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bid") int bid) {

        try {
            this.bookService.deleteBook(bid);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    // @PutMapping("updatebook/{bid}")
    // public ResponseEntity<Book> updateUser( @PathVariable("bid") int bid) {
    // try {
    // this.updateUser( bid);

    // return ResponseEntity.status(HttpStatus.OK).build();
    // } catch (Exception e) {
    // return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    // }
    // }
    @PutMapping("updatebook/{bid}")
    public Book updateBook(@RequestBody Book newBookDetails, @PathVariable("bookid") int id) {
        this.bookService.updateBook(newBookDetails, id);
        return newBookDetails;
    }

}
