package com.spring.com.tp.repository;

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
        simMap.put(sim.getDni(),sim);
    }

    public List<Sim> getAllSims(){
        List<Sim> listOfSims = simMap.values().stream().collect(Collectors.toList());
        return listOfSims;
    }

    public Sim getSimById(String id){
        return simMap.get(id);
    }

    public Sim updateSim(Sim sim){
        log.info("Sim to Update: {}", sim);
        //Al ser un put significa que voy a actulizar un Sim que ya tenia en memoria
        //Obtengo el sim anterior utilizando el mismo id que me pasan, esto es para verificar que ya existiera en memoria
        Sim simOldValue = this.getSimById(sim.getDni());

        //verifico si son iguales los id
        if (Objects.equals(sim.getDni(), simOldValue.getDni())){
            log.info("Existing Sim: {}", simOldValue);
            //si coinciden es que existe ese sim, por ende lo actualizo
            simMap.replace(sim.getDni(), simOldValue, sim);
            log.info("Sim updated: {}", sim);
            return sim;
        }else{
            //si no existe regreso un null a falta de tener excepciones implementadas
            log.info("Sim update null, not exist sim in bdd");
            return null;
        }
    }

    public String deleteById(Sim sim){
        simMap.remove(sim.getDni());
        return "Usuario eliminado";
    }



}
