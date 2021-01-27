package com.marcelodeassis.thermostasis.service.restclient;

import com.marcelodeassis.thermostasis.service.restclient.payload.SpotifyAccessToken;
import com.marcelodeassis.thermostasis.service.restclient.payload.PlaylistRecommendation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Base64;

@Log4j2
@Component
public class SpotifyClient implements PlaylistRecommendationClient {

    private final String clientid;
    private final String secret;
    private SpotifyAccessToken accessToken;
    private LocalDateTime nextTokenRequestTime;

    public SpotifyClient(@Value("${api.spotify.clientid}") String clientid, @Value("${api.spotify.secret}") String secret) {
        this.clientid = clientid;
        this.secret = secret;
    }

    public PlaylistRecommendation getRecommendation(String genre) {
        return getRecommendation(genre, "US");
    }
    public PlaylistRecommendation getRecommendation(String genre, String market) {
        accessToken = getAccessToken();

        ResponseEntity<PlaylistRecommendation> o = WebClient.builder()
                .baseUrl("https://api.spotify.com/v1/recommendations")
                .defaultHeaders(header -> header.setBearerAuth(accessToken.getAccessToken()))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build().get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("limit", 20)
                        .queryParam("market", market)
                        .queryParam("seed_genres", genre)
                        .build())
                .retrieve()
                .toEntity(PlaylistRecommendation.class)
                .block();

        log.info(o.getBody().toString());

        return o.getBody();
    }

    private SpotifyAccessToken getAccessToken(){
        if (accessToken != null && nextTokenRequestTime.compareTo(LocalDateTime.now()) > 0){
            return accessToken;
        }

        final String encodedAuthString = Base64.getEncoder().encodeToString((clientid + ":" + secret).getBytes());

        ResponseEntity<SpotifyAccessToken> o = WebClient.builder()
                .baseUrl("https://accounts.spotify.com/api/token")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(header -> header.setBasicAuth(encodedAuthString))
                .build().post()
                .bodyValue("grant_type=client_credentials")
                .retrieve()
                .toEntity(SpotifyAccessToken.class)
                .block();

        log.info(o.getBody().toString());

        nextTokenRequestTime = LocalDateTime.now().plusSeconds(o.getBody().getExpiresIn());

        return o.getBody();
    }

}

