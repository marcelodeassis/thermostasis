package com.marcelodeassis.thermostasis.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    private String trackName;
    private String artistName;
    private String albumName;
    private String imageUrl;
}
