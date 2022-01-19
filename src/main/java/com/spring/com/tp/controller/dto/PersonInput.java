package com.spring.com.tp.controller.dto;
import com.spring.com.tp.model.Movie;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PersonInput{
    private String name;
    private String dni;
    private String bDay;
    private List<String> myMovies;
    private String isbn;
}
