package com.spring.com.tp.services;

import com.spring.com.tp.client.MovieClient;
import com.spring.com.tp.client.dtoMovie.MovieResponse;
import com.spring.com.tp.client.dtoMovie.Result;
import com.spring.com.tp.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MovieService {
    private final MovieClient movieClient;


    @Autowired
    public MovieService(MovieClient movieClient){
        this.movieClient = movieClient;
    }


    public Movie createMovie(String movieName){
        log.info("Creating movie with name: {}", movieName);
        MovieResponse movieResponse = this.movieClient.getMovie(movieName.replace(" ", ""));
        Result results = movieResponse.getResults().get(0);
        Movie movie = new Movie(
                results.getTitle(),
                results.getSeriesStartYear(),
                results.getSeriesEndYear(),
                results.getNumberOfEpisodes(),
                results.getRunningTimeInMinutes(),
                results.getTitleType()
        );
        log.info("Created Movie: {}", movie);
        return movie;
    }

    public List<Movie> createMovieList(List<String> movieNames){
        log.info("list Of movies names: {}", movieNames);
        List<Movie> myMovies =  movieNames.stream().map(this::createMovie).collect(Collectors.toList());
        log.info("Created Movies: {}", myMovies);
        return myMovies;
    }

}
