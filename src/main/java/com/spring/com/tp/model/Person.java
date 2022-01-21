package com.spring.com.tp.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Person {
    private String name;
    private String dni;
    private String bDay;
    private List<Movie> myMovies;
    private Book book;
}
