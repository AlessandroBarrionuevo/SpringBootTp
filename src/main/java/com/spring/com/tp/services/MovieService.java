package com.spring.com.tp.services;

import com.spring.com.tp.client.MovieClient;
import com.spring.com.tp.client.dtoMovie.MovieResponse;
import com.spring.com.tp.client.dtoMovie.Result;
import com.spring.com.tp.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MovieService {
    private MovieClient movieClient;

    @Autowired
    public MovieService(MovieClient movieClient){
        this.movieClient = movieClient;
    }


    public Movie getMovie(String movieName){
        log.info("movieName: {}", movieName);
        MovieResponse movieResponse = this.movieClient.getMovie(movieName);
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

    public List<Movie> getMovies(List<String> movieNames){
        log.info("list Of movies names: {}", movieNames);
        //creo el array que va a devolverse con los datos que busquemos en el get
        List<Movie> myMovies = new ArrayList<Movie>();

        //por cada uno de los elementos de la lista que nos viene por parametro

        movieNames.stream().forEach((m) -> {
            //vamos a buscar los datos con ese nombre y los colocamos en la que queremos retornar
            myMovies.add(getMovie(m));
        });
        //con filter, igual estamal hay que hacerlo en un paso previo para poder filtrar bien
        //List<Movie> filterMovies = myMovies.stream().filter((movie) -> Objects.equals(movie.getTitleType(), "tvSeries")).collect(Collectors.toList());
        log.info("Movies: {}", myMovies);
        return myMovies;
    }

}
