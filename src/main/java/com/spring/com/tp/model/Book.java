package com.spring.com.tp.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String category;
    private String publishedDate;



    public Book(String isbn, String title, String author, String publisher, String category, String publishedDate) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.publishedDate = publishedDate;
    }
}
