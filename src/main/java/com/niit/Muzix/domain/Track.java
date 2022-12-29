/*
 * Author Name: Philip Meshach
 * Date: 29-12-2022
 * Praise The Lord
 */
package com.niit.Muzix.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Track {
    @Id
    private int trackId;
    private String trackName;
    private int trackRating;
    private Artist artist;
}
