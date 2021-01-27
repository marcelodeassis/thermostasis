package com.marcelodeassis.thermostasis.service.restclient.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PlaylistRecommendation {
    @JsonProperty("tracks")
    private List<PlaylistRecommendationTrack> tracks;
}

