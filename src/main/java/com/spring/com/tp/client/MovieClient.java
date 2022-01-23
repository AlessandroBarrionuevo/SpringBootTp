package com.spring.com.tp.client;

import com.spring.com.tp.client.dtoMovie.MovieResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
public class MovieClient {
    private RestTemplate restTemplate;

    @Value("${movie.Api}")
    private String url ;
    //= "https://imdb8.p.rapidapi.com/title/find?q=";

    @Autowired
    public MovieClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public MovieResponse getMovie(String movieName){
        log.info("Get from: {}", this.url);
        HttpHeaders header = new HttpHeaders();
        header.set("x-rapidapi-host", "imdb8.p.rapidapi.com");
        header.set("x-rapidapi-key", "c42b9c3cbfmsh58412fc2b88e92bp12b98bjsna78136cbbfa2");
        HttpEntity<Void> requestEntity = new HttpEntity<Void>(header);
        ResponseEntity<MovieResponse> response = restTemplate.exchange(this.url+movieName , HttpMethod.GET, requestEntity, MovieResponse.class);
        log.info("Api response: {}", response.getBody());
        return response.getBody();
    }

}
