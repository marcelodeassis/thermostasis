package com.marcelodeassis.thermostasis.service.restclient;

import com.marcelodeassis.thermostasis.service.restclient.payload.PlaylistRecommendation;

public interface PlaylistRecommendationClient {
    public PlaylistRecommendation getRecommendation(String genre);
    public PlaylistRecommendation getRecommendation(String genre, String country);
}
