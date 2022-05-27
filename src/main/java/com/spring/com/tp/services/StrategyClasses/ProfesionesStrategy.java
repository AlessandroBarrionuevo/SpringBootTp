package com.spring.com.tp.services.StrategyClasses;

import com.spring.com.tp.model.Strategy.ProfesionesEnum;
import com.spring.com.tp.services.StrategyClasses.Profesiones.Ingeniero;
import com.spring.com.tp.services.StrategyClasses.Profesiones.Medico;
import com.spring.com.tp.services.StrategyClasses.Profesiones.Policia;
import com.spring.com.tp.services.StrategyClasses.Profesiones.Profesiones;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProfesionesStrategy {

    Map<ProfesionesEnum, Profesiones> strategyMap = new HashMap<ProfesionesEnum, Profesiones> () {{
        put(ProfesionesEnum.POLICIA, new Policia());
        put(ProfesionesEnum.MEDICO, new Medico());
        put(ProfesionesEnum.INGENIERO, new Ingeniero());
    }};

    public Double trabajar(ProfesionesEnum profesion){
        return strategyMap.get(profesion).trabajar();
    }
}
