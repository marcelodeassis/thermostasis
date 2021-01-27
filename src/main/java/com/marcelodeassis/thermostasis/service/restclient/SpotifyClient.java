package com.marcelodeassis.thermostasis.service.restclient;

import com.marcelodeassis.thermostasis.service.restclient.payload.GithubRepo;
import com.marcelodeassis.thermostasis.service.restclient.payload.Weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Component
public class OpenWeatherClient implements WeatherClient {

    private final WebClient webClient;

    public OpenWeatherClient(@Value("${api.openweathermap.key}") final String apiKey) {
        System.out.println(apiKey);

        webClient = WebClient.builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/weather?q=London&appid=6801fe9e74c3fd9d5a5b0ea6b668d7af&units=metric")
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .build();
    }

    @Override
    public Weather getWeather(String city) {
        ResponseEntity<Weather> w = webClient.get()
                //.uri("/weather")
                .retrieve()
                .toEntity(Weather.class)
                .block();

        return w.getBody();
    }

    @Override
    public Weather getWeather(BigDecimal lat, BigDecimal lon) {
        return new Weather();
    }
}
