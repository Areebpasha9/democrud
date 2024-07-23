package com.demo.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.demo.dao.BookRepository;
import com.demo.demo.entities.Book;

@Component
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public java.util.List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }
    // get single book by id

    public Book getBookById(int id) {
        Book book = null;
        try {
            book = this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;

    }

    // adding book
    public Book addBook(Book b) {

        Book result = bookRepository.save(b);
        return result;

    }

    // Delete book
    public void deleteBook(int bid) {
        bookRepository.deleteById(bid);
    }
//Update a book
    public void updateBook(Book nbook, int bookId) {
     list = list.stream().map(b->{
        if(b.getId()==bookId){
            b.setName(nbook.getName());
            b.setEmail(nbook.getEmail());
        }
        return b;
      }).collect(Collectors.toList());


    }
    // public Optional<Book> updateBook(Integer id, Book newBookDetails) {
    //     return bookRepository.findById(id).map(book -> {
    //         book.setName(newBookDetails.getName());
    //         book.setEmail(newBookDetails.getEmail());
    //         return bookRepository.save(book);
    //     });
    // }

}
