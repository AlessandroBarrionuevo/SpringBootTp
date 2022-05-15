package com.spring.com.tp.model.Strategy.Profesiones;

import com.spring.com.tp.model.Strategy.Profesiones.Profesiones;

public class Medico extends Profesiones {
    @Override
    public Double trabajar() {
        return 1000.00;
    }
}
