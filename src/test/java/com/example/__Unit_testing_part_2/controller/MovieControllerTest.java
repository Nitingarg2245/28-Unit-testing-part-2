package com.example.__Unit_testing_part_2.controller;

import com.example.__Unit_testing_part_2.entity.Movie;
import com.example.__Unit_testing_part_2.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MovieControllerTest {

    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp() {
        movie1 = Movie.builder().id(1).genre("Romance").name("Koi mil gya").build();
        movie2 = Movie.builder().id(2).genre("action").name("bhaubhali").build();
    }

    @Test
    void getMovieById() throws Exception {
        when(movieService.getMovieById(anyInt())).thenReturn(movie1);
        mockMvc.perform(get("/app/movie/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genre",is(movie1.getGenre())));

    }

    @Test
    void getAllMovies() throws Exception {
        when(movieService.getAllMovies()).thenReturn(Arrays.asList(movie1,movie2));
        mockMvc.perform(get("/app/movie"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(2)));
    }

    @Test
    void addMovie() throws Exception {
        when(movieService.addMovie(any(Movie.class))).thenReturn(movie1);
        mockMvc.perform(post("/app/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(movie1.getName())));
    }

    @Test
    void deleteMovieById() throws Exception{
        doNothing().when(movieService).deleteMovieById(anyInt());
        mockMvc.perform(delete("/app/movie/{id}",1))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateMovie() throws Exception{
        when(movieService.updateMovie(anyInt(),any(Movie.class))).thenReturn(movie1);
        mockMvc.perform(put("/app/movie/{id}",1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(movie1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(movie1.getName())));
    }
}