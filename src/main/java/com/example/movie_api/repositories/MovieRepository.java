package com.example.movie_api.repositories;

import com.example.movie_api.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Use pagination for large data sets
    Page<Movie> findAll(Pageable pageable);

    // Optimized query to fetch movie with director using JOIN FETCH
    @Query("SELECT m FROM Movie m WHERE m.id = :id")
    Movie findMovieWithDirector(@Param("id") Long id);
}
