package com.spring.com.tp.client.dtoBook;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BookResponse {
    private Integer totalItems;
    private List<Item> items;

}
