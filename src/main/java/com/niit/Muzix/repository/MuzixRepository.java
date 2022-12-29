package com.niit.Muzix.repository;

import com.niit.Muzix.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MuzixRepository extends MongoRepository<Track,Integer> {

    @Query("{'artist.artistName':{$in:[?0]}}")
    public List<Track> findByArtist(String artistName);
    public List<Track> findByRating(Integer trackRating);
}
