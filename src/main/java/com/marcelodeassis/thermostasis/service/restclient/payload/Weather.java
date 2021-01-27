package com.marcelodeassis.thermostasis.service.restclient.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@ToString
public class Weather {

    private BigDecimal latitude;
    private BigDecimal longitude;
    private int celsiusTemp;
    private String country;

    @JsonProperty("name")
    private String city;

    @JsonProperty("coord")
    private void unpackCoords(Map<String, String> o) {
        latitude = NumberUtils.parseNumber(o.get("lat"), BigDecimal.class);
        longitude = NumberUtils.parseNumber(o.get("lon"), BigDecimal.class);
    }

    @JsonProperty("main")
    private void unpackMain(Map<String, String> o) {
        celsiusTemp = NumberUtils.parseNumber(o.get("temp"), Double.class).intValue();
    }

    @JsonProperty("sys")
    private void unpackSys(Map<String, String> o) {
        country = o.get("country");
    }
}