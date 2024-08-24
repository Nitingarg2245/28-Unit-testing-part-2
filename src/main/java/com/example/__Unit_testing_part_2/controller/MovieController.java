package com.example.__Unit_testing_part_2.controller;

import com.example.__Unit_testing_part_2.entity.Movie;
import com.example.__Unit_testing_part_2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/movie")
public class MovieController {

    @Autowired
   private MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id){
        Movie movieById = movieService.getMovieById(id);
        return new ResponseEntity<>(movieById, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> allMovies = movieService.getAllMovies();
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        Movie movie1 = movieService.addMovie(movie);
        return new ResponseEntity<>(movie1,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Integer id){
        movieService.deleteMovieById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id ,@RequestBody Movie movie){
        Movie movie1 = movieService.updateMovie(id, movie);
        return new ResponseEntity<>(movie1,HttpStatus.OK);

    }
}
