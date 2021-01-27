package com.marcelodeassis.thermostasis.service.restclient.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
public class PlaylistRecommendationAlbum {
    @JsonProperty("name")
    private String name;

    private String imageUrl;

    @JsonProperty("images")
    private void unpackImageUrls(List<Map<String, String>> imageUrlsList) {
        imageUrl = imageUrlsList.get(1).get("url"); //getting medium image size
    }
}
