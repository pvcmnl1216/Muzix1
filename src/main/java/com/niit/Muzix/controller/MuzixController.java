/*
 * Author Name: Philip Meshach
 * Date: 29-12-2022
 * Praise The Lord
 */
package com.niit.Muzix.controller;

import com.niit.Muzix.domain.Track;
import com.niit.Muzix.exception.ArtistNotFoundException;
import com.niit.Muzix.exception.MusicAlreadyExistException;
import com.niit.Muzix.exception.MusicNotFoundException;
import com.niit.Muzix.service.MuzixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MuzixController {
    @Autowired
    MuzixService muzixService;
    @PostMapping("/Music")
    public ResponseEntity<?>add(@RequestBody Track track) throws MusicAlreadyExistException {
        return new ResponseEntity<>(muzixService.addTrack(track), HttpStatus.OK);
    }
    @GetMapping("/Music")
    public ResponseEntity<?>get(){
        return new ResponseEntity<>(muzixService.getAll(),HttpStatus.OK);
    }
    @DeleteMapping("/music/{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id) throws MusicNotFoundException {
        return new ResponseEntity<>(muzixService.delete(id),HttpStatus.OK);
    }
    @GetMapping("/artist/{artist}")
    public ResponseEntity<?>getByArtist(@PathVariable String artist) throws ArtistNotFoundException {
        return new ResponseEntity<>(muzixService.findByArtist(artist),HttpStatus.OK);
    }
    @GetMapping("/rating/{trackRating}")
    public ResponseEntity<?>getByRating(@PathVariable Integer trackRating){
        return new ResponseEntity<>(muzixService.findByRating(trackRating),HttpStatus.OK);
    }
}
