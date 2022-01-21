package com.spring.com.tp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String category;
    private String publishedDate;
    //extra para probar
    private String infoLink;

    // title, author, publisher, publishedDate y category

}
