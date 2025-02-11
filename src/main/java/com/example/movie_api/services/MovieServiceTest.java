package com.example.movie_api.services;

import com.example.movie_api.models.Movie;
import com.example.movie_api.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMovies() {
        // Arrange
        List<Movie> movies = Arrays.asList(
                new Movie(1L, "Inception", "Christopher Nolan", 2010, "Sci-Fi", 8.8)
        );
        Pageable pageable = PageRequest.of(0, 10);
        Page<Movie> moviePage = new PageImpl<>(movies);
        when(movieRepository.findAll(pageable)).thenReturn(moviePage);

        // Act
        Page<Movie> result = movieService.getMovies(0, 10);

        // Assert
        assertEquals(1, result.getTotalElements());
        verify(movieRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetMovieById() {
        // Arrange
        Movie movie = new Movie(1L, "Inception", "Christopher Nolan", 2010, "Sci-Fi", 8.8);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        // Act
        Optional<Movie> result = movieService.getMovieById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Inception", result.get().getTitle());
        verify(movieRepository, times(1)).findById(1L);
    }

    @Test
    void testAddMovie() {
        // Arrange
        Movie movieToAdd = new Movie(null, "Interstellar", "Christopher Nolan", 2014, "Sci-Fi", 8.6);
        Movie savedMovie = new Movie(2L, "Interstellar", "Christopher Nolan", 2014, "Sci-Fi", 8.6);
        when(movieRepository.save(movieToAdd)).thenReturn(savedMovie);

        // Act
        Movie result = movieService.addMovie(movieToAdd);

        // Assert
        assertNotNull(result.getId());
        assertEquals(2L, result.getId());
        verify(movieRepository, times(1)).save(movieToAdd);
    }

    // You can add more tests for updateMovie and deleteMovie similarly.
}
