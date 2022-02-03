package com.spring.com.tp.services.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateTransformer {

    public LocalDate formaterDate(String dateToTransform){
        log.info("transforming date format: {}", dateToTransform);
        DateTimeFormatter esDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (dateToTransform.contains("-")){
            esDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        }
        LocalDate simBday = LocalDate.parse(dateToTransform, esDateFormat);
        log.info("Date format transformated: {}", simBday);
        return simBday;
    }
}
