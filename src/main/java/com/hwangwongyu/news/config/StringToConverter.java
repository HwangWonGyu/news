package com.hwangwongyu.news.config;

import com.hwangwongyu.news.user.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class StringToConverter {

    @Bean
    public Converter<String, UserDTO.Sex> stringToSexConverter() {
        return new Converter<String, UserDTO.Sex>()
        {
            @Override
            public UserDTO.Sex convert(String source) {
                return UserDTO.Sex.valueOf(source.toUpperCase());
            }
        };
    }

}