/*
 * Author Name: Philip Meshach
 * Date: 02-01-2023
 * Praise The Lord
 */
package com.niit.Muzix.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.Muzix.domain.Artist;
import com.niit.Muzix.domain.Track;
import com.niit.Muzix.exception.MusicAlreadyExistException;
import com.niit.Muzix.exception.MusicNotFoundException;
import com.niit.Muzix.service.MuzixServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    @Mock
    MuzixServiceImpl muzixService;
    @InjectMocks
    MuzixController muzixController;
    @Autowired
    MockMvc mockMvc;
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
        mockMvc = MockMvcBuilders.standaloneSetup(muzixController).build();
    }
    @AfterEach
    public void tearDown(){
        this.artist = null;
        this.track = null;
    }

    public static String convertJsonToString(final Object obj) throws JsonProcessingException {
        String result;
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(obj);
        result = json;
        return result;
    }
    @Test
    public void  givenTrackToSaveReturnSavedTrack() throws Exception {
        when(muzixService.addTrack(any())).thenReturn(track);
        mockMvc.perform(post("/api/v1/Music").contentType(MediaType.APPLICATION_JSON).content(convertJsonToString(track)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void givenTrackToSaveReturnSavedTrackFailure() throws Exception {
        when(muzixService.addTrack(any())).thenReturn(null);
        mockMvc.perform(post("/api/v1/Music").
                contentType(MediaType.APPLICATION_JSON).content(convertJsonToString(track))).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void givenTrackToDeleteMusic() throws Exception {

        when(muzixService.delete(anyInt())).thenReturn("Track Deleted Successfully");
        mockMvc.perform(delete("/api/v1/music/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void givenTrackToDeleteMusicFailure() throws Exception {
        when(muzixService.delete(anyInt())).thenThrow(MusicNotFoundException.class);
        mockMvc.perform(delete("/api/v1/music/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getAllTrack() throws Exception {
        when(muzixService.getAll()).thenReturn(trackList);
        mockMvc.perform(get("/api/v1/Music").contentType(MediaType.APPLICATION_JSON)
                .content(convertJsonToString(track)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTrackByArtist() throws Exception {

        when(muzixService.findByArtist("Dui")).thenReturn(trackList);
        mockMvc.perform(get("/api/v1/artist/Dui").contentType(MediaType.APPLICATION_JSON)
                        .content(convertJsonToString(track)).content(convertJsonToString(track)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllTrackByRating() throws Exception {

        when(muzixService.findByRating(6)).thenReturn(trackList);
        mockMvc.perform(get("/api/v1/rating/6").contentType(MediaType.APPLICATION_JSON)
                        .content(convertJsonToString(track)).content(convertJsonToString(track)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}

