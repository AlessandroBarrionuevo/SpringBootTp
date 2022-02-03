package com.spring.com.tp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Sim {
    private String name;
    private String dni;
    private LocalDate birthDay;
    private List<Movie> movies;
    private Book book;
}