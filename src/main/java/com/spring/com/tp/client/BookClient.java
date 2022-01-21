package com.spring.com.tp.client;


import com.spring.com.tp.client.dtoBook.BookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class BookClient {
    private RestTemplate restTemplate;
    @Value("${book.hostIsbn}")
    private String url;

    @Autowired
    public BookClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public BookResponse getBookFromGoogleApi(String isbn){
        log.info("Get de peliculas a la api https://www.googleapis.com");
        String urlAPegar = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;
        return this.restTemplate.getForObject(urlAPegar, BookResponse.class);
    }
}
