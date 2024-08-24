package com.example.__Unit_testing_part_2.repository;

import com.example.__Unit_testing_part_2.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findByGenre(String genre);
}
