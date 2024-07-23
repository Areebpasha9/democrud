package com.demo.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.demo.demo.entities.Book;

public interface BookRepository  extends  CrudRepository<Book,Integer>{
    public Book findById(int id);
}
