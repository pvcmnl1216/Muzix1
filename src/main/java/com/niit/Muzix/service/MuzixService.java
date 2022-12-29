package com.niit.Muzix.service;

import com.niit.Muzix.domain.Track;

import java.util.List;

public interface MuzixService {
    public Track addTrack(Track track);
    public List<Track> getAll();
    public String delete(Integer trackId);
    public List<Track> findByArtist(String artistName);
    public List<Track> findByRating(Integer trackRating);
}
