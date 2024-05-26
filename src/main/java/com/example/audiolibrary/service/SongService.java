package com.example.audiolibrary.service;

import com.example.audiolibrary.model.Song;
import com.example.audiolibrary.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song createSong(String name, String artist, int releaseYear) {
        if (songRepository.findByNameAndArtist(name, artist).isPresent()) {
            throw new RuntimeException("This song is already in the library.");
        }
        Song song = new Song(name, artist, releaseYear);
        return songRepository.save(song);
    }

    public List<Song> searchSongsByName(String name) {
        return songRepository.findByNameContaining(name);
    }

    public List<Song> searchSongsByArtist(String artist) {
        return songRepository.findByArtistContaining(artist);
    }
}
