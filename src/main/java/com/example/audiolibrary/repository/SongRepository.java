package com.example.audiolibrary.repository;

import com.example.audiolibrary.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    //Pageable
//    List<Song> findByArtistContaining(String artist);
    Page<Song> findByArtistContaining(String artist, Pageable pageable);
//    List<Song> findByNameContaining(String name);
    Page<Song> findByNameContaining(String name, Pageable pageable);
    Optional<Song> findByNameAndArtist(String name, String artist);


}
