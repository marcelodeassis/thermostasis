package com.marcelodeassis.thermostasis.controller;

import com.marcelodeassis.thermostasis.model.Playlist;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Api(value = "Thermostasis API")
public class ApiController {

    @GetMapping(value = {"/city/{cityName}"}, produces = "application/json", consumes = "multipart/form-data")
    public Playlist getPlaylist(@PathVariable String cityName){
        System.out.println(cityName);
        return new Playlist();
    }

    @GetMapping(value = {"/coordinates/{lat}/{lon}"}, produces = "application/json", consumes = "multipart/form-data")
    public Playlist getPlaylist(@PathVariable BigDecimal lat, @PathVariable BigDecimal lon){
        System.out.println(lat.add(lon));
        return new Playlist();
    }
}
