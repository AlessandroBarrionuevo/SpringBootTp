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
        Sim sim = this.simRepo.getSimById(id);
        if ( sim == null){
            return null;
        } else {
            return sim;
        }
    }

    public List<Sim> getAllSims(){
        List<Sim> listOfSims = this.simRepo.getAllSims();
        if(listOfSims.isEmpty()){
            return null;
        }else{
            return listOfSims;
        }
    }

    public Sim updateSim(Sim sim){
        //obtengo el sim que actualizo
        Sim updateSim = simRepo.updateSim(sim);

        //chequeo que lo devuelto sea un sim y no uun null producto de que no existia ese Sim en memoria
        if ( updateSim != null){
            return  updateSim;
        }else{
            return null;
        }
    }

    public String deleteSimById(String id){
        Sim simToDelete = this.getSimById(id);
        if (simToDelete != null){
            simRepo.deleteById(simToDelete);
            return "Sim eliminado con exito";
        }else{
            return "Error no se pudo eliminar el sim";
        }

    }









}
