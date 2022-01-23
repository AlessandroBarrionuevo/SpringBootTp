package com.spring.com.tp.controller;
import com.spring.com.tp.controller.dto.SimInput;
import com.spring.com.tp.controller.dto.SimOutput;
import com.spring.com.tp.model.Sim;
import com.spring.com.tp.services.SimService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class PersonController {
    private SimService simService;

    @Autowired
    public PersonController(SimService simService){
      this.simService = simService;
    }

    //reglas de rest ponerlo en plural
    @PostMapping(path = "/sims")
        public ResponseEntity<SimOutput> createPerson(@RequestBody SimInput simInput){
        log.info("Estamos en el metodo post que fue solicitado desde un microservicio exterior o una peticion directa a /sims post method");
        log.info("recibimos un json con los datos a utilizar para crear una persona mediante el service PersonService.");
        Sim sim = this.simService.createPerson(simInput);
        SimOutput simOutput = new SimOutput(sim.getName(),sim.getMovies(), sim.getBook());
        log.info("La persona se creo y se va a dar como respuesta los datos de la persona, datos de la lista de peliculas y del libro relacionado con el isbn enviado");
        return ResponseEntity.ok(simOutput);
    }

}
