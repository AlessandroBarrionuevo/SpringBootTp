package com.spring.com.tp.controller;
import com.spring.com.tp.controller.dto.PersonInput;
import com.spring.com.tp.controller.dto.PersonOutput;
import com.spring.com.tp.model.Person;
import com.spring.com.tp.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
      this.personService = personService;
    }

    //reglas de rest ponerlo en plural
    @PostMapping(path = "/sims")
        public ResponseEntity<PersonOutput> createPerson(@RequestBody PersonInput personInput){
        log.info("Estamos en el metodo post que fue solicitado desde un microservicio exterior o una peticion directa a /sims post method");
        log.info("recibimos un json con los datos a utilizar para crear una persona mediante el service PersonService.");
        Person person = this.personService.createPerson(personInput);
        PersonOutput response = new PersonOutput(person.getName(),person.getMyMovies(), person.getBook());
        log.info("La persona se creo y se va a dar como respuesta los datos de la persona, datos de la lista de peliculas y del libro relacionado con el isbn enviado");
        return ResponseEntity.ok(response);
    }

}
