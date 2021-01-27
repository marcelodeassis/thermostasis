package com.marcelodeassis.thermostasis.service.restclient;

import com.marcelodeassis.thermostasis.service.restclient.payload.Weather;

import java.math.BigDecimal;

public interface WeatherClient {

    public Weather getWeather(String city);

    public Weather getWeather(BigDecimal lat, BigDecimal lon);
}
