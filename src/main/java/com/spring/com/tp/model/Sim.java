package com.spring.com.tp.model;

import com.spring.com.tp.model.Strategy.ProfesionesEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Sim {

    private String name;

    @Id
    private String dni;

    private LocalDate birthDay;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sim_id")
    private List<Movie> movies;

    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable(
            name = "sim_book",
            joinColumns = { @JoinColumn(name = "sim_id")},
            inverseJoinColumns = { @JoinColumn(name = "book_id")}
    )
    private List<Book> books;
    private ProfesionesEnum tipoProfesion;
    private Double dinero;
}
