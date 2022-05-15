package com.spring.com.tp.controller.dto;
import com.spring.com.tp.model.Strategy.ProfesionesEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SimInput {
    private String name;
    private String dni;
    private String birthDay;
    private List<String> moviesName;
    private List<String> listOfIsbn;
    private ProfesionesEnum profesion;
    private Double dinero;
}
