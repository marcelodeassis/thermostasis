package com.marcelodeassis.thermostasis.controller;

import com.marcelodeassis.thermostasis.dto.Playlist;
import com.marcelodeassis.thermostasis.dto.Track;
import com.marcelodeassis.thermostasis.service.PlaylistService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Component
@RestController
@Api(value = "Thermostasis API")
public class ApiController {

    private final PlaylistService playlistService;

    public ApiController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping(value = {"/city/{cityName}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Track> getPlaylist(@PathVariable String cityName){
        return playlistService.getPlaylist(cityName).getTracks();
    }

    @GetMapping(value = {"/coordinates/{lat}/{lon}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Track> getPlaylist(@PathVariable BigDecimal lat, @PathVariable BigDecimal lon){
        return playlistService.getPlaylist(lat, lon).getTracks();
    }
}
