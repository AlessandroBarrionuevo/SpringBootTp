package com.spring.com.tp.services;
import com.spring.com.tp.config.exception.BadRequestException;
import com.spring.com.tp.config.exception.NotFoundException;
import com.spring.com.tp.controller.dto.SimInput;
import com.spring.com.tp.model.Book;
import com.spring.com.tp.model.Movie;
import com.spring.com.tp.model.Sim;
import com.spring.com.tp.repository.SimRepo;
import com.spring.com.tp.repository.SimsRepository;
import com.spring.com.tp.services.Utils.DateTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class SimService {
    private final BookService bookService;
    private final MovieService movieService;
    private final SimsRepository simsRepository;

    @Autowired
    public SimService(SimRepo simRepo, BookService bookService, MovieService movieService, SimsRepository simsRepository ){
        this.bookService = bookService;
        this.movieService = movieService;
        this.simsRepository = simsRepository;
    }

    public Sim createSim(SimInput simInput){
        log.info("Sim to create: {}", simInput);
        DateTransformer dateTransformer = new DateTransformer();
        Book simBook = this.bookService.createBook(simInput.getIsbn());
        List<Movie> simMovies = this.movieService.createMovieList(simInput.getMoviesName());
        Sim sim = new Sim(
                simInput.getName(),
                simInput.getDni(),
                dateTransformer.formaterDate(simInput.getBirthDay()),
                simMovies,
                simBook
        );
        simsRepository.save(sim);
        log.info("Created Sim: {}", sim);
        return sim;
    }

    public Optional<Sim> getSimById(Integer id){
        log.info("Request to get sim with id: {}", id);
        Optional<Sim> sim = this.simsRepository.findById(id);
        log.info("Service response for get sim: {}", sim);
        return Optional.ofNullable(sim.orElseThrow(() -> new NotFoundException("Sim not found")));
    }

    public List<Sim> getAllSims(){
        List<Sim> listOfSims = this.simsRepository.findAll();
        if(listOfSims.isEmpty()){
            log.info("Service response for all sims: NotFundException, does not exist sims");
            throw new NotFoundException("Sims are empty");
        }else{
            log.info("Service response for get all sims: {}", listOfSims);
            return listOfSims;
        }
    }


    public Sim updateSim(Sim sim){
        Optional<Sim> simOldValue = this.simsRepository.findById(sim.getDni());
        log.info("Sim updated: {}", sim);
        return simOldValue.orElseThrow(() -> new NotFoundException("Sim doesn't exist so you can't update it"));
    }

    public void deleteSimById(Integer id){
        log.info("Delete sim with id: {}", id);
        Optional<Sim> simToDelete = this.simsRepository.findById(id);
        simToDelete.orElseThrow(() -> new BadRequestException("Error. Sim: "+ id + " could not deleted" ));

    }

}
