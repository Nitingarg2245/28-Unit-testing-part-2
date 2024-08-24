package com.example.__Unit_testing_part_2.repository;

import com.example.__Unit_testing_part_2.entity.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp(){
        movie1=Movie.builder().genre("Romance").name("Koi mil gya").build();
        movie2=Movie.builder().genre("action").name("bhaubhali").build();
    }
    @AfterEach
    void TearDown(){
        movieRepository.deleteAll();
    }

    @Test
    void TestFindByGenre(){
        movieRepository.saveAll(Arrays.asList(movie1,movie2));
        List<Movie> action = movieRepository.findByGenre("action");
        assertEquals(1,action.size());
    }
}
