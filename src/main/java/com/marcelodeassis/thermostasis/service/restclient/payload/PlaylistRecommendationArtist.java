package com.marcelodeassis.thermostasis.service.restclient.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
public class PlaylistRecommendationArtist {
    @JsonProperty("name")
    private String name;

    public String toString(){
        return name;
    }

}

