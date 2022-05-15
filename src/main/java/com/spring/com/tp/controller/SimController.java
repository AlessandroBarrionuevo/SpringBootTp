package com.spring.com.tp.controller;
import com.spring.com.tp.config.exception.NotFoundException;
import com.spring.com.tp.controller.dto.SimInput;
import com.spring.com.tp.controller.dto.SimOutput;
import com.spring.com.tp.model.Sim;
import com.spring.com.tp.services.SimService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class SimController {
    private final SimService simService;

    @Autowired
    public SimController(SimService simService){
      this.simService = simService;
    }

    @PostMapping(path = "/sims")
        public ResponseEntity<SimOutput> createSim(@RequestBody SimInput simInput){
        log.info("PostMethod recive SimInput: {} ", simInput);
        Sim sim = this.simService.createSim(simInput);
        SimOutput simOutput = new SimOutput(sim.getName(),sim.getMovies(), sim.getBooks());
        log.info("Created Sim: {}", simOutput);
        return ResponseEntity.ok(simOutput);
    }

    @GetMapping(path = "/sims/{id}")
    public ResponseEntity<Sim> getSim(@PathVariable String id){
        log.info("Get method recive petition for Sim whith ID: {}", id);
        Sim sim = this.simService.getSimById(id);
        log.info("Giving back Sim: {}", sim);
        return ResponseEntity.ok(sim);
    }

    @GetMapping(path = "/sims")
    public ResponseEntity<List<Sim>> getAllSims(){
        log.info("Petition for all sims");
        List<Sim> sims = this.simService.getAllSims();
        log.info("Giving back list of sims: {}", sims);
        return ResponseEntity.ok(sims);
    }

    @PutMapping(path = "/sims")
    public ResponseEntity<Sim> putSim(@RequestBody Sim sim){
        log.info("Updating sim: {}", sim);
        return ResponseEntity.ok( this.simService.updateSim(sim));
    }

    @DeleteMapping(path = "/sims/{id}")
    public ResponseEntity<String> deleteSim(@PathVariable String id){
        log.info("Deleting sim whit id: {}", id);
        this.simService.deleteSimById(id);
        log.info("Sim: {} deleted", id);
        return ResponseEntity.ok("Sim : "+ id +"Deleted");
    }


    @GetMapping(path = "/sims/{id}/rutina-de-trabajo")
    public ResponseEntity<Sim> trabajar(@PathVariable String id){
        log.info("Searching sim whith id: {}", id);
        Sim sim = this.simService.getSimById(id);
        log.info("Sim id: {} working", sim.getDni());
        sim = this.simService.trabajarSim(sim);
        log.info("Sim money: {}", sim.getDinero());
        return ResponseEntity.ok(sim);

    }

}
