package com.spring.com.tp.services;

import com.spring.com.tp.model.Sim;
import com.spring.com.tp.services.StrategyClasses.ProfesionesStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TrabajarService {
    @Autowired
    private ProfesionesStrategy profesionesStrategy;

    public Sim trabajarSim(Sim sim){
        log.info("Sim trabajando");
        sim.setDinero(sim.getDinero() + this.profesionesStrategy.trabajar(sim.getTipoProfesion()));
        log.info("Sim working of: {}",sim.getTipoProfesion());
        log.info("Sim money: {}",sim.getDinero());
        return sim;
    }

    public Double verificarDinero(Double dineroSim){
        if(dineroSim == null || dineroSim < 0){
            return 100.00;
        }else return dineroSim;
    }
}
