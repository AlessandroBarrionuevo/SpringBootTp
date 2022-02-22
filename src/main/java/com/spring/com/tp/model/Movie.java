package com.spring.com.tp.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long seriesStartYear;
    private Long seriesEndYear;
    private int numberOfEpisodes;
    private Long runningTimeInMinutes;
    private String titleType;

    public Movie( String title, Long seriesStartYear, Long seriesEndYear, int numberOfEpisodes, Long runningTimeInMinutes, String titleType) {
        this.name = title;
        this.seriesStartYear = seriesStartYear;
        this.seriesEndYear = seriesEndYear;
        this.numberOfEpisodes = numberOfEpisodes;
        this.runningTimeInMinutes = runningTimeInMinutes;
        this.titleType = titleType;
    }
}
