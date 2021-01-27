package com.marcelodeassis.thermostasis.service.restclient.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class SpotifyRecommendation {
    @JsonProperty("tracks")
    private List<SpotifyRecommendationTrack> tracks;
}

