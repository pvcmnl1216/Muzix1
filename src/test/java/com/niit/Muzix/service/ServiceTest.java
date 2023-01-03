/*
 * Author Name: Philip Meshach
 * Date: 30-12-2022
 * Praise The Lord
 */
package com.niit.Muzix.service;

import com.niit.Muzix.domain.Artist;
import com.niit.Muzix.domain.Track;
import com.niit.Muzix.exception.MusicAlreadyExistException;
import com.niit.Muzix.exception.MusicNotFoundException;
import com.niit.Muzix.repository.MuzixRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import  org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    MuzixRepository muzixRepository;
    @InjectMocks
    MuzixServiceImpl muzixService;
    private Track track,track1;
    private Artist artist,artist1;
    List<Track>trackList;
    @BeforeEach
    public void setUp(){
        this.artist = new Artist(1,"Dui");
        this.track = new Track(1,"Due",6,this.artist);
        this.artist1 = new Artist(1,"Dui");
        this.track1 = new Track(1,"Due",6,this.artist);
        trackList = Arrays.asList(track,track1);
    }
    @AfterEach
    public void tearDown(){
        this.artist = null;
        this.track = null;
    }
    @Test
    public void givenCustomerToSaveReturnSavedCustomerSuccess() throws MusicAlreadyExistException {
        when(muzixRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
        when(muzixRepository.save(track)).thenReturn(track);
        assertEquals(track,muzixService.addTrack(track));
    }
    @Test
    public void givenCustomerToSaveReturnCustomerFailure(){
        when(muzixRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
        assertThrows(MusicAlreadyExistException.class,()->muzixService.addTrack(track));
    }
    @Test
    public void givenCustomerToDeleteShouldDeleteSuccess() throws MusicNotFoundException {
        when(muzixRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
        String t1 = muzixService.delete(track.getTrackId());
        assertEquals("Track deleted Successfully",t1);
    }
    @Test
    public void givenCustomerToDeleteShouldDeleteFailure(){
        when(muzixRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
        assertThrows(MusicNotFoundException.class,()->muzixService.delete(track.getTrackId()));
    }
}
