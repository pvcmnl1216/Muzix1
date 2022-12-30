package com.niit.Muzix.repository;

import com.niit.Muzix.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MuzixRepository extends MongoRepository<Track,Integer> {

    @Query("{'artist.artistName':{$in:[?0]}}")
    public List<Track> findByArtist(String artistName);
    @Query("{'trackRating':{$gte:4}}")
    public List<Track> findByRating(Integer trackRating);
}
