package com.example.movie_api.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "movies", indexes = {
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_director", columnList = "director")
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String director;

    private int releaseYear;
    private String genre;

    @Column(nullable = false)
    private double imdbRating;
}
