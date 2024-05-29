package com.example.audiolibrary.controller;

import com.example.audiolibrary.model.Song;
import com.example.audiolibrary.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/create")
    public Song createSong(@RequestParam String name, @RequestParam String artist, @RequestParam int releaseYear) {
        try {
            return songService.createSong(name, artist, releaseYear);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/searchByName")
    public List<Song> searchSongsByName(@RequestParam String name) {
        return songService.searchSongsByName(name);
    }

    @GetMapping("/searchByArtist")
    public List<Song> searchSongsByArtist(@RequestParam String artist) {
        return songService.searchSongsByArtist(artist);
    }

}
