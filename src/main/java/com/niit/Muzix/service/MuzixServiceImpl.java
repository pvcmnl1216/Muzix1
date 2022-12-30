/*
 * Author Name: Philip Meshach
 * Date: 29-12-2022
 * Praise The Lord
 */
package com.niit.Muzix.service;

import com.niit.Muzix.domain.Track;
import com.niit.Muzix.exception.ArtistNotFoundException;
import com.niit.Muzix.exception.MusicAlreadyExistException;
import com.niit.Muzix.exception.MusicNotFoundException;
import com.niit.Muzix.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuzixServiceImpl implements MuzixService {
    @Autowired
    MuzixRepository muzixRepository;

    @Override
    public Track addTrack(Track track) throws MusicAlreadyExistException {
        if (muzixRepository.findById(track.getTrackId()).isEmpty()) {
            Track track1 = muzixRepository.save(track);
            return track1;
        }
        throw new MusicAlreadyExistException();
    }

    @Override
    public List<Track> getAll() {
        return muzixRepository.findAll();
    }

    @Override
    public String delete(Integer trackId) throws MusicNotFoundException {
        if (muzixRepository.findById(trackId).isEmpty()) {
            throw new MusicNotFoundException();
        }
        muzixRepository.deleteById(trackId);
        return "Track deleted Successfully";
    }

    @Override
    public List<Track> findByArtist(String artistName) throws ArtistNotFoundException {
        return muzixRepository.findByArtist(artistName);
    }

    @Override
    public List<Track> findByRating(Integer trackRating) {
        return muzixRepository.findByRating(trackRating);
    }
}
