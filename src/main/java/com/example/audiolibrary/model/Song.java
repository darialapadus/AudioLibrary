package com.example.audiolibrary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "artist"})
})
public class Song {

    @Id
    @SequenceGenerator(
            name="song_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "song_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private int releaseYear;

    public Song(String name, String artist, int releaseYear) {
        this.name = name;
        this.artist = artist;
        this.releaseYear = releaseYear;
    }
}
