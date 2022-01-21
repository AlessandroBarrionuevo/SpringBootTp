package com.spring.com.tp.client.dtoMovie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private String title;
    private Long seriesStartYear;
    private Long seriesEndYear;
    private int numberOfEpisodes;
    private Long runningTimeInMinutes;
    private String titleType;
}
//Con lo que retorne la api hay que llenar el objeto
// película con los datos: seriesStartYear,
// seriesEndYear,
// numberOfEpisodes y runningTimeInMinutes