package com.spring.com.tp.repository;

import com.spring.com.tp.model.Book;
import com.spring.com.tp.model.Sim;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Component
public class SimRepo {
    private Map<String, Sim> simMap = new HashMap<String, Sim>();

    public void  insertSim(Sim sim){
        log.info("Saving sim: {}", sim);
        simMap.put(sim.getDni(),sim);
    }

    public List<Sim> getAllSims(){
        List<Sim> listOfSims = simMap.values().stream().collect(Collectors.toList());
        log.info("List of Sims: {}", listOfSims);
        return listOfSims;
    }

    public Sim getSimById(String id){
        Sim sim = simMap.get(id);
        log.info("Returning sim: {}", sim);
        return sim;
    }

    public Boolean updateSim(Sim sim, Sim simOldValue){
        log.info("Sim to Update: {}", sim);
        return  simMap.replace(sim.getDni(), simOldValue, sim);
    }

    public String deleteById(Sim sim){
        log.info("Deleting Sim: {}", sim);
        simMap.remove(sim.getDni());
        return "Usuario eliminado";
    }



}
