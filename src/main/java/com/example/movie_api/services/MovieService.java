package com.example.movie_api.services;

import com.example.movie_api.models.Movie;
import com.example.movie_api.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    // Get all movies with pagination
    public Page<Movie> getMovies(int page, int size) {
        return movieRepository.findAll(PageRequest.of(page, size));
    }

    // Fetch a movie by ID with caching
    @Cacheable(value = "movies", key = "#id")
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    // Add a new movie
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Update a movie
    public Optional<Movie> updateMovie(Long id, Movie updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setDirector(updatedMovie.getDirector());
            movie.setReleaseYear(updatedMovie.getReleaseYear());
            movie.setGenre(updatedMovie.getGenre());
            movie.setImdbRating(updatedMovie.getImdbRating());
            return movieRepository.save(movie);
        });
    }

    // Delete a movie
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
