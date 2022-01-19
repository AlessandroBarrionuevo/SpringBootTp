package com.spring.com.tp.services;
import com.spring.com.tp.client.BookClient;
import com.spring.com.tp.client.MovieClient;
import com.spring.com.tp.client.dtoBook.BookResponse;
import com.spring.com.tp.client.dtoBook.VolumeInfo;
import com.spring.com.tp.client.dtoMovie.MovieResponse;
import com.spring.com.tp.client.dtoMovie.Result;
import com.spring.com.tp.controller.dto.PersonInput;
import com.spring.com.tp.model.Book;
import com.spring.com.tp.model.Movie;
import com.spring.com.tp.model.Person;
import com.spring.com.tp.repository.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonService {
    private PersonRepo personRepo;
    private BookClient bookClient;
    private MovieClient movieClient;

    @Autowired
    public PersonService(PersonRepo personRepo, BookClient bookClient, MovieClient movieClient){
        this.personRepo = personRepo;
        this.bookClient = bookClient;
        this.movieClient = movieClient;
    }

    public Person createPerson(PersonInput personInput){
        log.info("Metodo para crear una persona mediante los datos recibidos. Se va a conectar con los clients correspondiente para los datos de movie y book");
        Book personBook = getBook(personInput.getIsbn());
        List<Movie> personMovies = getMovies(personInput.getMyMovies());
        return new Person(
                personInput.getName(),
                personInput.getDni(),
                personInput.getBDay(),
                personMovies,
                personBook
        );
    }

    public Book getBook(String isbn){
        log.info("obtenemos los datos del book mediante el isbn enviado");
        BookResponse bookResponse = this.bookClient.getBookFromGoogleApi(isbn);
        VolumeInfo volumeInfo = bookResponse.getItems().get(0).getVolumeInfo();
        return new Book(
                isbn,
                volumeInfo.getTitle(),
                volumeInfo.getAuthors().get(0),
                volumeInfo.getPublisher(),
                volumeInfo.getCategories().get(0),
                volumeInfo.getPublishedDate(),
                volumeInfo.getInfoLink()
        );
    }

    public Movie getMovie(String movieName){
        log.info("Obtenemos la pelicula correspondiente mediante el nombre que nos enviaron");
        MovieResponse movieResponse = this.movieClient.getMovie(movieName);
        Result results = movieResponse.getResults().get(0);
        log.info("Retornamos un objeto del tipo Movie");
        return new Movie(
                results.getTitle(),
                results.getSeriesStartYear(),
                results.getSeriesEndYear(),
                results.getNumberOfEpisodes(),
                results.getRunningTimeInMinutes(),
                results.getTitleType()
        );
    }


    public List<Movie> getMovies(List<String> movieNames){
        log.info("vamos a realizar la busqueda de todas las peliculas que se enviaron por parametro");
        //creo el array que va a devolverse con los datos que busquemos en el get
        List<Movie> myMovies = new ArrayList<Movie>();

        //por cada uno de los elementos de la lista que nos viene por parametro

        movieNames.stream().forEach((m) -> {
            //vamos a buscar los datos con ese nombre y los colocamos en la que queremos retornar
            myMovies.add(getMovie(m));
        });
        //con filter, igual estamal hay que hacerlo en un paso previo para poder filtrar bien
        //List<Movie> filterMovies = myMovies.stream().filter((movie) -> Objects.equals(movie.getTitleType(), "tvSeries")).collect(Collectors.toList());
        log.info("retornamos una lista con las peliculas y su informacion segun la busqueda realizadad");
        return myMovies;
    }



}
