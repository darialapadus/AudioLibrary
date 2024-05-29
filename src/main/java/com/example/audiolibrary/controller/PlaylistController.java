package com.example.audiolibrary.controller;

import com.example.audiolibrary.model.Playlist;
import com.example.audiolibrary.model.User;
import com.example.audiolibrary.repository.UserRepository;
import com.example.audiolibrary.service.PlaylistService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ContentDisposition;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private UserRepository userRepository;
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
   public String addSongToPlaylist(@RequestParam Long playlistId, @RequestParam Long songId, @RequestParam Long ownerId) {
       try {
           playlistService.addSongToPlaylist(playlistId, songId, ownerId);
           return "Song added successfully to the playlist.";
       } catch (Exception e) {
           return "Error: " + e.getMessage();
       }
   }

    @GetMapping("/list")
    public List<Playlist> listPlaylists(@RequestParam Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return playlistService.listPlaylists(owner);
    }

    @GetMapping("/export/{playlistId}/{format}")
    public ResponseEntity<Resource> exportPlaylist(@PathVariable Long playlistId, @RequestParam Long userId, @PathVariable String format) {
        try {
            File file = playlistService.exportPlaylist(playlistId, userId, format);
            HttpHeaders headers = new HttpHeaders();

            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                    .filename(file.getName())
                    .build();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());

            MediaType mediaType = format.equalsIgnoreCase("csv") ? MediaType.TEXT_PLAIN : MediaType.APPLICATION_JSON;

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(mediaType)
                    .body((Resource) new InputStreamResource(new FileInputStream(file)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

}
