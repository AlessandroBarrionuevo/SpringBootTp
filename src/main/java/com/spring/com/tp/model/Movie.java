package com.spring.com.tp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Movie {
    private String name;
    private Long seriesStartYear;
    private Long seriesEndYear;
    private int numberOfEpisodes;
    private Long runningTimeInMinutes;
    private String titleType;
}
