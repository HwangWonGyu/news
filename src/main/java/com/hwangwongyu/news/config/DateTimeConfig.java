package com.hwangwongyu.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class DateTimeConfig {

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String text, Locale locale) {
                return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyyMMdd", locale));
            }

            @Override
            public String print(LocalDate object, Locale locale) {
                return DateTimeFormatter.ISO_DATE.format(object);
            }
        };
    }


    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {
            @Override
            public LocalDateTime parse(String text, Locale locale) {
                return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyyMMddHHmmss", locale));
            }

            @Override
            public String print(LocalDateTime object, Locale locale) {
                return DateTimeFormatter.ISO_DATE_TIME.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalTime> DateFormatter() {
        return new Formatter<LocalTime>() {
            @Override
            public LocalTime parse(String text, Locale locale) throws ParseException {
                return LocalTime.parse(text, DateTimeFormatter.ofPattern("HHmmss", locale));
            }

            @Override
            public String print(LocalTime object, Locale locale) {
                return new SimpleDateFormat("HH:mm:ss").format(object);
            }
        };
    }
}