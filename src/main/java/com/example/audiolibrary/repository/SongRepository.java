package com.example.audiolibrary.repository;

import com.example.audiolibrary.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

//    List<Song> findByArtistContaining(String artist);
//    List<Song> findByNameContaining(String name);

    //Pageable
    Page<Song> findByArtistContaining(String artist, Pageable pageable);
    Page<Song> findByNameContaining(String name, Pageable pageable);
    Optional<Song> findByNameAndArtist(String name, String artist);


}
