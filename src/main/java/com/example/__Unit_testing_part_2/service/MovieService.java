package com.example.__Unit_testing_part_2.service;

import com.example.__Unit_testing_part_2.entity.Movie;
import com.example.__Unit_testing_part_2.repository.MovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
   private MovieRepository movieRepository;

    public Movie getMovieById(Integer id){
        return movieRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public void deleteMovieById(Integer id){
        movieRepository.deleteById(id);
    }

    public Movie updateMovie(Integer id,Movie movie){
        Movie movie1 = movieRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
        BeanUtils.copyProperties(movie,movie1,"id");
       return movieRepository.save(movie1);
    }
}
