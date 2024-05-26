package com.example.audiolibrary.repository;

import com.example.audiolibrary.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByArtistContaining(String artist);
    Optional<Song> findByNameAndArtist(String name, String artist);
    List<Song> findByNameContaining(String name);
}
