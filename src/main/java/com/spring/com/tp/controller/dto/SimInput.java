package com.spring.com.tp.controller.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SimInput {
    private String name;
    private Integer dni;
    private String birthDay;
    private List<String> moviesName;
    private String isbn;
}
