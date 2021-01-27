package com.marcelodeassis.thermostasis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Playlist {
    private final List<Track> tracks = new ArrayList<Track>();
}
