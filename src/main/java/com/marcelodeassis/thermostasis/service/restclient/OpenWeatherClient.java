package com.marcelodeassis.thermostasis.service.restclient;

import com.marcelodeassis.thermostasis.service.restclient.payload.Weather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Slf4j
@Component
public class OpenWeatherClient implements WeatherClient {

    private final String apiKey;
    private final String baseUrl = "http://api.openweathermap.org/data/2.5/weather";

    public OpenWeatherClient(@Value("${api.openweathermap.key}") final String apiKey) {
        this.apiKey = apiKey;
    }

    private Weather buildWeather(String uri){
        ResponseEntity<Weather> w = WebClient.builder()
                .baseUrl(uri)
                .build()
                .get()
                .retrieve()
                .toEntity(Weather.class)
                .block();

        log.info(w.getBody().toString());
        return w.getBody();
    }

    @Override
    public Weather getWeather(String city) {
        String uri = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("q", city)
                .queryParam("units", "metric")
                .queryParam("appid", apiKey)
                .build()
                .toUriString();

        return buildWeather(uri);
    }

    @Override
    public Weather getWeather(BigDecimal lat, BigDecimal lon) {
        String uri = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", "metric")
                .queryParam("appid", apiKey)
                .build()
                .toUriString();

        return buildWeather(uri);
    }


}
