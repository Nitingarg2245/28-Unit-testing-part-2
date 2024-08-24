package com.example.__Unit_testing_part_2.service;

import com.example.__Unit_testing_part_2.entity.Movie;
import com.example.__Unit_testing_part_2.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieService movieService;

    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp(){
        movie1=Movie.builder().id(1).genre("Romance").name("Koi mil gya").build();
        movie2=Movie.builder().id(2).genre("action").name("bhaubhali").build();
        movieRepository.saveAll(Arrays.asList(movie1,movie2));
    }
    @AfterEach
    void TearDown(){
        movieRepository.deleteAll();
    }

    @Test
    void getMovieById() {
        when(movieRepository.findById(anyInt())).thenReturn(Optional.of(movie1));
        Movie m = movieService.getMovieById(10);
        assertNotNull(m);
        assertEquals(1,m.getId());
    }

    @Test
    void getAllMovies() {
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie1,movie2));
        List<Movie> allMovies = movieService.getAllMovies();
        assertNotNull(allMovies);
        assertEquals(2,allMovies.size());
    }

    @Test
    void addMovie() {
        when(movieRepository.save(any(Movie.class))).thenReturn(movie1);
        Movie movie = movieService.addMovie(movie2);
        assertNotNull(movie);
        assertEquals(1,movie.getId());
    }

    @Test
    void deleteMovieById() {
        doNothing().when(movieRepository).deleteById(anyInt());
        movieService.deleteMovieById(2);
        verify(movieRepository,times(1)).deleteById(2);
    }

    @Test
    void updateMovie() {
        when(movieRepository.findById(anyInt())).thenReturn(Optional.of(movie1));
        when(movieRepository.save(any(Movie.class))).thenReturn(movie1);

        movie1.setGenre("horror");
        Movie movie = movieService.updateMovie(1, movie1);
        assertNotNull(movie);
        assertEquals("horror",movie.getGenre());
    }
}