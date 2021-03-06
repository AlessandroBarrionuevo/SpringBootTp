package com.spring.com.tp.services;
import com.spring.com.tp.config.exception.BadRequestException;
import com.spring.com.tp.config.exception.NotFoundException;
import com.spring.com.tp.controller.dto.SimInput;
import com.spring.com.tp.model.Book;
import com.spring.com.tp.model.Movie;
import com.spring.com.tp.model.Sim;
import com.spring.com.tp.repository.SimsRepository;
import com.spring.com.tp.services.StrategyClasses.ProfesionesStrategy;
import com.spring.com.tp.services.Utils.DateTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SimService {
    private final BookService bookService;
    private final MovieService movieService;
    private final SimsRepository simsRepository;
    private ProfesionesStrategy profesionesStrategy;
    private TrabajarService trabajarService;

    @Autowired
    public SimService(BookService bookService, MovieService movieService,
                      SimsRepository simsRepository, ProfesionesStrategy profesionesStrategy,
                      TrabajarService trabajarService
    ){
        this.bookService = bookService;
        this.movieService = movieService;
        this.simsRepository = simsRepository;
        this.profesionesStrategy = profesionesStrategy;
        this.trabajarService = trabajarService;
    }

    public Sim createSim(SimInput simInput){
        log.info("Sim to create: {}", simInput);
        DateTransformer dateTransformer = new DateTransformer();
        List<Book> simBooks = this.bookService.listOfBooks(simInput.getListOfIsbn());
        List<Movie> simMovies = this.movieService.createMovieList(simInput.getMoviesName());
        Sim sim = new Sim(
                simInput.getName(),
                simInput.getDni(),
                dateTransformer.formaterDate(simInput.getBirthDay()),
                simMovies,
                simBooks,
                simInput.getProfesion(),
                trabajarService.verificarDinero(simInput.getDinero())
        );
        log.info("Created Sim: {}", sim);
        return simsRepository.save(sim);
    }

    public Sim getSimById(String id){
        log.info("Request to get sim with id: {}", id);
        Optional<Sim> sim = this.simsRepository.findById(id);
        sim.orElseThrow(() -> new NotFoundException("Sim id: "+ id + " not found"));
        log.info("Service response for get sim: {}", sim);
        return sim.get();
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
        Optional<Sim> simToUpdate = this.simsRepository.findById(sim.getDni());
        simToUpdate.ifPresent(s -> this.simsRepository.save(sim));
        log.info("Sim updated: {}", sim);
        return simToUpdate.orElseThrow(() -> new NotFoundException("Sim doesn't exist, to create sim use post method."));
    }

    public void deleteSimById(String id){
        log.info("Delete sim with id: {}", id);
        Optional<Sim> simToDelete = this.simsRepository.findById(id);
        simToDelete.ifPresent(sim -> this.simsRepository.deleteById(sim.getDni()));
        simToDelete.orElseThrow(() -> new BadRequestException("Error. Sim: "+ id + " could not deleted" ));
    }



}
