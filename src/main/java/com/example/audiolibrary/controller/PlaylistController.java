package com.example.audiolibrary.controller;

import com.example.audiolibrary.model.Playlist;
import com.example.audiolibrary.model.User;
import com.example.audiolibrary.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/create")
    public Playlist createPlaylist(@RequestParam String name, @RequestBody User owner) {
        try {
            return playlistService.createPlaylist(name, owner);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/addSong")
    public String addSongToPlaylist(@RequestParam Long playlistId, @RequestParam Long songId, @RequestBody User owner) {
        try {
            playlistService.addSongToPlaylist(playlistId, songId, owner);
            return "Song added successfully to the playlist.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/list")
    public List<Playlist> listPlaylists(@RequestBody User owner) {
        return playlistService.listPlaylists(owner);
    }
}
