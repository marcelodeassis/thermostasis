package com.marcelodeassis.thermostasis.controller;

import com.marcelodeassis.thermostasis.dto.Playlist;
import com.marcelodeassis.thermostasis.service.PlaylistService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Component
@RestController
@Api(value = "Thermostasis API")
public class ApiController {

    private final PlaylistService playlistService;

    public ApiController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping(value = {"/city/{cityName}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Playlist getPlaylist(@PathVariable String cityName){
        return playlistService.getPlaylist(cityName);
    }

    @GetMapping(value = {"/coordinates/{lat}/{lon}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Playlist getPlaylist(@PathVariable BigDecimal lat, @PathVariable BigDecimal lon){
        return playlistService.getPlaylist(lat, lon);
    }
}
