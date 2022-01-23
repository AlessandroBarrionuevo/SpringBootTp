package com.spring.com.tp.services;
import com.spring.com.tp.controller.dto.SimInput;
import com.spring.com.tp.model.Book;
import com.spring.com.tp.model.Movie;
import com.spring.com.tp.model.Sim;
import com.spring.com.tp.repository.SimRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SimService {
    private SimRepo simRepo;
    private BookService bookService;
    private MovieService movieService;

    @Autowired
    public SimService(SimRepo simRepo, BookService bookService, MovieService movieService){
        this.simRepo = simRepo;
        this.bookService = bookService;
        this.movieService = movieService;
    }

    public Sim createPerson(SimInput simInput){
        log.info("Sim to create: {}", simInput);
        Book personBook = this.bookService.getBook(simInput.getIsbn());
        List<Movie> personMovies = this.movieService.getMovies(simInput.getMoviesName());
        Sim sim = new Sim(
                simInput.getName(),
                simInput.getDni(),
                simInput.getBDay(),
                personMovies,
                personBook
        );
        log.info("Sim created: {}", sim);
        return sim;
    }







}
