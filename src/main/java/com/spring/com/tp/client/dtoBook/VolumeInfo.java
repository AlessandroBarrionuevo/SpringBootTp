package com.spring.com.tp.client.dtoBook;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VolumeInfo {
    private List<String> authors;
    private List<String> categories;
    private String description;
    private int pageCount;
    private String publishedDate;
    private String publisher;
    private String title;
    private String infoLink;
}

