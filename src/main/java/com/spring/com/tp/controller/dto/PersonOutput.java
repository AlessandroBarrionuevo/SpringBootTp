package com.spring.com.tp.controller.dto;

import com.spring.com.tp.model.Book;
import com.spring.com.tp.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PersonOutput {
    private String name;
    private List<Movie> movies;
    private Book book;
}
