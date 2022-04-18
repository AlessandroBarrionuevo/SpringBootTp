package com.spring.com.tp.repository;

import com.spring.com.tp.model.Book;
import com.spring.com.tp.model.Sim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository  extends JpaRepository<Book, String> {
}
