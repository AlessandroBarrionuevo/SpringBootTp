package com.spring.com.tp.controller.dto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SimInput {
    private String name;
    private String dni;
    private String bDay;
    private List<String> moviesName;
    private String isbn;
}
