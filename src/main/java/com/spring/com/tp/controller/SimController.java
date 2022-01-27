package com.spring.com.tp.controller;
import com.spring.com.tp.controller.dto.SimInput;
import com.spring.com.tp.controller.dto.SimOutput;
import com.spring.com.tp.model.Sim;
import com.spring.com.tp.services.SimService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class SimController {
    private SimService simService;

    @Autowired
    public SimController(SimService simService){
      this.simService = simService;
    }

    @PostMapping(path = "/sims")
        public ResponseEntity<SimOutput> createPerson(@RequestBody SimInput simInput){
        log.info("PostMethod recive SimInput: {} ", simInput);
        Sim sim = this.simService.createSim(simInput);
        SimOutput simOutput = new SimOutput(sim.getName(),sim.getMovies(), sim.getBook());
        log.info("Created Sim: {}", simOutput);
        return ResponseEntity.ok(simOutput);
    }

    @GetMapping(path = "/sims/{id}")
    public ResponseEntity<Sim> getSim(@PathVariable String id){
        Sim sim = this.simService.getSimById(id);
        return ResponseEntity.ok(sim);
    }

    @GetMapping(path = "/sims")
    public ResponseEntity<List<Sim>> getAllSims(){
        return ResponseEntity.ok(this.simService.getAllSims());
    }

    @PutMapping(path = "/sims")
    public ResponseEntity<Sim> putSim(@RequestBody Sim sim){
        return ResponseEntity.ok( this.simService.updateSim(sim));
    }

    @DeleteMapping(path = "/sims/{id}")
    public ResponseEntity<String> deleteSim(@PathVariable String id){
        String deleteMsj = this.simService.deleteSimById(id);
        return ResponseEntity.ok(deleteMsj);
    }


}
