package com.spring.com.tp.services;

import com.spring.com.tp.client.BookClient;
import com.spring.com.tp.client.dtoBook.BookResponse;
import com.spring.com.tp.client.dtoBook.VolumeInfo;
import com.spring.com.tp.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookService {
    private BookClient bookClient;

    @Autowired
    public BookService(BookClient bookClient){
        this.bookClient = bookClient;
    }

    public Book getBook(String isbn){
        log.info("isbn: {}", isbn);
        BookResponse bookResponse = this.bookClient.getBookFromGoogleApi(isbn);
        VolumeInfo volumeInfo = bookResponse.getItems().get(0).getVolumeInfo();
        Book book = new Book(
                isbn,
                volumeInfo.getTitle(),
                volumeInfo.getAuthors().get(0),
                volumeInfo.getPublisher(),
                volumeInfo.getCategories().get(0),
                volumeInfo.getPublishedDate(),
                volumeInfo.getInfoLink()
        );
        log.info("Created Book: {}", book);
        return book;
    }
}
