package com.marcelodeassis.thermostasis.service;

import com.marcelodeassis.thermostasis.domain.ApiCallLog;
import com.marcelodeassis.thermostasis.dto.Playlist;
import com.marcelodeassis.thermostasis.dto.Track;
import com.marcelodeassis.thermostasis.repository.ApiCallLogRepository;
import com.marcelodeassis.thermostasis.service.restclient.PlaylistRecommendationClient;
import com.marcelodeassis.thermostasis.service.restclient.WeatherClient;
import com.marcelodeassis.thermostasis.service.restclient.payload.PlaylistRecommendation;
import com.marcelodeassis.thermostasis.service.restclient.payload.PlaylistRecommendationTrack;
import com.marcelodeassis.thermostasis.service.restclient.payload.Weather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Component
public class PlaylistService {
    private final WeatherClient weatherClient;
    private final PlaylistRecommendationClient playlistRecommendationClient;
    private final ApiCallLogRepository apiCallLogRepository;

    public PlaylistService(ApiCallLogRepository apiCallLogRepository, WeatherClient weatherClient, PlaylistRecommendationClient playlistRecommendationClient) {
        this.weatherClient = weatherClient;
        this.playlistRecommendationClient = playlistRecommendationClient;
        this.apiCallLogRepository = apiCallLogRepository;
    }

    public Playlist getPlaylist(String cityName) {
        Weather weather = weatherClient.getWeather(cityName);
        return buildPlaylist(weather);
    }

    public Playlist getPlaylist(BigDecimal lat, BigDecimal lon){
        Weather weather = weatherClient.getWeather(lat, lon);
        return buildPlaylist(weather);
    }

    private Playlist buildPlaylist(Weather weather){

        ApiCallLog apiCallLog = ApiCallLog.builder().city(weather.getCity())
                .latitude(weather.getLatitude())
                .longitude(weather.getLongitude())
                .temperature(weather.getCelsiusTemp())
                .country(weather.getCountry())
                .createdOn(LocalDateTime.now())
                .build();

        try{
            apiCallLogRepository.save(apiCallLog);
        }catch (InvalidDataAccessResourceUsageException e){
            log.error("Tabela não existe. Provavelmente Flyway falhou");
        }

        PlaylistRecommendation sr = playlistRecommendationClient.getRecommendation(PlaylistMood.getPlaylistMood(weather.getCelsiusTemp()), weather.getCountry());

        return parseRecommendationToPlaylist(sr);
    }

    private Playlist parseRecommendationToPlaylist(PlaylistRecommendation sr){
        Playlist playlist = new Playlist();

        for(PlaylistRecommendationTrack srTrack : sr.getTracks()){
            Track track = Track.builder()
                    .trackName(srTrack.getName())
                    .albumName(srTrack.getAlbum().getName())
                    .artistName(srTrack.getArtistsString())
                    .imageUrl(srTrack.getAlbum().getImageUrl())
                    .build();
            playlist.getTracks().add(track);
        }

        return playlist;
    }


}
