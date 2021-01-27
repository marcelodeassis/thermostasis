package com.marcelodeassis.thermostasis.service.restclient.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class SpotifyRecommendationTrack {
    @JsonProperty("name")
    private String name;

    @JsonProperty("album")
    private SpotifyRecommendationAlbum album;

    @JsonProperty("artists")
    List<SpotifyRecommendationArtist> artists;

    public String getArtistsString(){
        return artists.stream().map(Object::toString).collect(Collectors.joining(", "));
        /*
        StringBuilder sb = new StringBuilder();
        for(SpotifyRecomendationArtist srArtist: artists){
            sb.append(srArtist);
        }
        return sb.toString();*/
    }

}
