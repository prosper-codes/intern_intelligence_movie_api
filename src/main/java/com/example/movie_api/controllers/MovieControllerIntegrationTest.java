package com.example.movie_api.controllers;

import com.example.movie_api.models.Movie;
import com.example.movie_api.services.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void testGetMovieById() throws Exception {
        // Arrange
        Movie movie = new Movie(1L, "Inception", "Christopher Nolan", 2010, "Sci-Fi", 8.8);
        Mockito.when(movieService.getMovieById(1L)).thenReturn(Optional.of(movie));

        // Act & Assert
        mockMvc.perform(get("/api/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Inception")))
                .andExpect(jsonPath("$.director", is("Christopher Nolan")));
    }

    @Test
    void testAddMovie() throws Exception {
        // Arrange
        Movie movie = new Movie(1L, "Interstellar", "Christopher Nolan", 2014, "Sci-Fi", 8.6);
        Mockito.when(movieService.addMovie(Mockito.any(Movie.class))).thenReturn(movie);

        String movieJson = """
                {
                    "title": "Interstellar",
                    "director": "Christopher Nolan",
                    "releaseYear": 2014,
                    "genre": "Sci-Fi",
                    "imdbRating": 8.6
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Interstellar")));
    }

    // You can add more integration tests for update and delete endpoints similarly.
}
