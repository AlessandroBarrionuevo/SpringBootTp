package com.spring.com.tp.services;

import com.spring.com.tp.client.BookClient;
import com.spring.com.tp.client.dtoBook.BookResponse;
import com.spring.com.tp.client.dtoBook.VolumeInfo;
import com.spring.com.tp.model.Book;
import com.spring.com.tp.repository.BooksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {
    private BookClient bookClient;
    private BooksRepository booksRepository;
    @Autowired
    public BookService(BookClient bookClient, BooksRepository booksRepository){
        this.bookClient = bookClient;
        this.booksRepository = booksRepository;
    }

    public Book createBook(String isbn){
        log.info("Creating book from isbn: {}", isbn);
        BookResponse bookResponse = this.bookClient.getBookFromGoogleApi(isbn);
        VolumeInfo volumeInfo = bookResponse.getItems().get(0).getVolumeInfo();
        Book book = new Book(
                isbn,
                volumeInfo.getTitle(),
                volumeInfo.getAuthors().get(0),
                volumeInfo.getPublisher(),
                volumeInfo.getCategories().get(0),
                volumeInfo.getPublishedDate()
        );
        log.info("Created Book: {}", book);
        return book;
    }

    public Book bookExits(String isbn){
        Optional<Book> book = this.booksRepository.findById(isbn);
        return book.orElse(createBook(isbn));
    }

    public List<Book> listOfBooks( List<String> books){
        return books.stream().map(this::bookExits).collect(Collectors.toList());
    }
}
