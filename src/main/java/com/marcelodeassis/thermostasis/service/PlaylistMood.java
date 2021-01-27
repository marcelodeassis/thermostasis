package com.marcelodeassis.thermostasis.service;

import lombok.Getter;

@Getter
public class PlaylistMood {
    public static final String PARTY = "party";
    public static final String POP = "pop";
    public static final String ROCK = "rock";
    public static final String CLASSICAL = "classical";

    public static String getPlaylistMood(int temperature){
        final String mood;

        if(temperature > 30){
            mood = PlaylistMood.PARTY;
        }else if(temperature >= 15){
            mood = PlaylistMood.POP;
        }else if(temperature >= 10){
            mood = PlaylistMood.ROCK;
        }else{
            mood = PlaylistMood.CLASSICAL;
        }

        return mood;
    }
}
