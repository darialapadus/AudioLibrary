package com.example.audiolibrary.service;

import com.example.audiolibrary.model.Song;
import com.example.audiolibrary.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AuditService auditService;

//    public Song createSong(String name, String artist, int releaseYear) {
//        if (songRepository.findByNameAndArtist(name, artist).isPresent()) {
//            throw new RuntimeException("This song is already in the library.");
//        }
//        Song song = new Song(name, artist, releaseYear);
//        return songRepository.save(song);
//    }

    //Audit
    public Song createSong(String name, String artist, int releaseYear, String username) {
        if (songRepository.findByNameAndArtist(name, artist).isPresent()) {
            throw new RuntimeException("This song is already in the library.");
        }
        Song song = new Song(name, artist, releaseYear);
        auditService.logAction("Created song: " + name, username);
        return songRepository.save(song);
    }

//    public List<Song> searchSongsByName(String name) {
//        return songRepository.findByNameContaining(name);
//    }
//
//    public List<Song> searchSongsByArtist(String artist) {
//        return songRepository.findByArtistContaining(artist);
//    }

    //Pageable
//    public Page<Song> searchSongsByName(String name, Pageable pageable) {
//        return songRepository.findByNameContaining(name, pageable);
//    }

    //Pageable+Audit
    public Page<Song> searchSongsByName(String name, Pageable pageable, String username) {
        auditService.logAction("Search for songs by name: " + name, username);
        return songRepository.findByNameContaining(name, pageable);
    }

    //Pageable
//    public Page<Song> searchSongsByArtist(String artist, Pageable pageable) {
//        return songRepository.findByArtistContaining(artist, pageable);
//    }

    //Pageable+Audit
    public Page<Song> searchSongsByArtist(String artist, Pageable pageable, String username) {
        auditService.logAction("Search for songs by artist: " + artist, username);
        return songRepository.findByArtistContaining(artist, pageable);
    }
}
