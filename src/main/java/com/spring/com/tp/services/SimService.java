package com.spring.com.tp.services;
import com.spring.com.tp.config.exception.BadRequestException;
import com.spring.com.tp.config.exception.NotFoundException;
import com.spring.com.tp.controller.dto.SimInput;
import com.spring.com.tp.model.Book;
import com.spring.com.tp.model.Movie;
import com.spring.com.tp.model.Sim;
import com.spring.com.tp.repository.SimRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Sim createSim(SimInput simInput){
        log.info("Sim to create: {}", simInput);
        Book simBook = this.bookService.createBook(simInput.getIsbn());
        List<Movie> simMovies = this.movieService.createMovieList(simInput.getMoviesName());
        Sim sim = new Sim(
                simInput.getName(),
                simInput.getDni(),
                simInput.getBDay(),
                simMovies,
                simBook
        );
        simRepo.insertSim(sim);
        log.info("Created Sim: {}", sim);
        return sim;
    }

    public Sim getSimById(String id){
        log.info("Request to get sim with id: {}", id);
        Sim sim = this.simRepo.getSimById(id);
        if ( sim == null){
            log.info("Service response for get sim: Sim not Found");
            throw new NotFoundException("Sim not fund");
        } else {
            log.info("Service response for get sim: {}", sim);
            return sim;
        }
    }

    public List<Sim> getAllSims(){
        List<Sim> listOfSims = this.simRepo.getAllSims();
        if(listOfSims.isEmpty()){
            log.info("Service response for all sims: NotFundException, does not exist sims");
            throw new NotFoundException("Sims are empty");
        }else{
            log.info("Service response for get all sims: {}", listOfSims);
            return listOfSims;
        }
    }


    public Sim updateSim(Sim sim){
        //Al ser un put significa que voy a actulizar un Sim que ya tenia en memoria
        //Obtengo el sim anterior utilizando el mismo id que me pasan, esto es para verificar que ya existiera en memoria
        Sim simOldValue = this.simRepo.getSimById(sim.getDni());

        //verifico si son iguales los id
        if (Objects.equals(sim.getDni(), simOldValue.getDni())){
            log.info("Existing Sim: {}", simOldValue);
            //si coinciden es que existe ese sim, por ende lo actualizo
            simRepo.updateSim(sim, simOldValue);
            log.info("Sim updated: {}", sim);
            return sim;
        }else{
            //si no existe regreso un null a falta de tener excepciones implementadas
            log.info("Sim update error, not exist sim in bdd");
            throw new NotFoundException("Sim doesn't exist");
        }
    }
    public String deleteSimById(String id){
        log.info("Delete sim with id: {}", id);
        Sim simToDelete = this.getSimById(id);
        if (simToDelete != null){
            simRepo.deleteById(simToDelete);
            log.info("successfully removed");
            return "Sim eliminado con exito";
        }else{
            log.info("Error. can't remove sim");
            throw new BadRequestException("Error can't remove sim whit id: " + id);
        }

    }









}
