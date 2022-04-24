package com.example.assessmentksp.helpers;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class DateFormatter {

    public LocalDate trxDateFormatter(String date, Boolean isUsingValidation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate trxDate = LocalDate.parse(date, formatter);

        if (isUsingValidation) {
            if (trxDate.isAfter(LocalDate.now())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "trx_date cannot exceed the current date");
            }
        }

        return trxDate;
    }
}
