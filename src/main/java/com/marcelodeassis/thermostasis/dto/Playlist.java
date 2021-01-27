package com.marcelodeassis.thermostasis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Playlist {
    private final List<Track> tracks = new ArrayList<Track>();
}
