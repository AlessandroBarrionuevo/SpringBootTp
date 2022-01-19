package com.spring.com.tp.client.dtoMovie;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieResponse {
    private List<Result> results;
}
