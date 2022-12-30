package com.niit.Muzix.service;

import com.niit.Muzix.domain.Track;
import com.niit.Muzix.exception.ArtistNotFoundException;
import com.niit.Muzix.exception.MusicAlreadyExistException;
import com.niit.Muzix.exception.MusicNotFoundException;

import java.util.List;

public interface MuzixService {
    public Track addTrack(Track track) throws MusicAlreadyExistException;
    public List<Track> getAll();
    public String delete(Integer trackId) throws MusicNotFoundException;
    public List<Track> findByArtist(String artistName) throws ArtistNotFoundException;
    public List<Track> findByRating(Integer trackRating);
}
