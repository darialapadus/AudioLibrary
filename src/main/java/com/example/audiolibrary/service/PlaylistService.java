package com.example.audiolibrary.service;

import com.example.audiolibrary.model.Playlist;
import com.example.audiolibrary.model.Song;
import com.example.audiolibrary.model.User;
import com.example.audiolibrary.repository.PlaylistRepository;
import com.example.audiolibrary.repository.SongRepository;
import com.example.audiolibrary.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private UserRepository userRepository;

    public Playlist createPlaylist(String name, User owner) throws Exception {
        Optional<Playlist> existingPlaylist = playlistRepository.findByNameAndOwner(name, owner);
        if (existingPlaylist.isPresent()) {
            throw new Exception("You already have a playlist named " + name);
        }
        Playlist newPlaylist = new Playlist(name, owner);
        return playlistRepository.save(newPlaylist);
    }

    public void addSongToPlaylist(Long playlistId, Long songId, Long ownerId) throws Exception {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new Exception("User not found"));
        Playlist playlist = playlistRepository.findByIdAndOwner(playlistId, owner)
                .orElseThrow(() -> new Exception("Playlist not found or does not belong to you"));
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new Exception("Song not found"));

        if (playlist.getSongs().contains(song)) {
            throw new Exception("This song is already in the playlist");
        }

        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
    }

//    public List<Playlist> listPlaylists(User owner) {
//        return playlistRepository.findByOwner(owner);
//    }

    //Pageable
    public Page<Playlist> listPlaylists(User owner, Pageable pageable) {
        return playlistRepository.findByOwner(owner, pageable);
    }

    public File exportPlaylist(Long playlistId, Long userId, String format) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found!"));
        Playlist playlist = playlistRepository.findByIdAndOwner(playlistId, user)
                .orElseThrow(() -> new Exception("Playlist not found or does not belong to you"));

        String fileName = "export_" + user.getUsername() + "_" + playlist.getName() + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "." + format;

        if ("csv".equalsIgnoreCase(format)) {
            return exportPlaylistToCSV(playlist, fileName);
        } else if ("json".equalsIgnoreCase(format)) {
            return exportPlaylistToJSON(playlist, fileName);
        } else {
            throw new Exception("Unsupported file format");
        }
    }

    private File exportPlaylistToCSV(Playlist playlist, String fileName) throws IOException {
        File csvFile = new File(fileName);
        try (PrintWriter writer = new PrintWriter(csvFile)) {
            writer.println("Name,Artist,Release Year");
            for (Song song : playlist.getSongs()) {
                writer.println(song.getName() + "," + song.getArtist() + "," + song.getReleaseYear());
            }
        }
        return csvFile;
    }

    private File exportPlaylistToJSON(Playlist playlist, String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(fileName);
        mapper.writeValue(jsonFile, playlist.getSongs());
        return jsonFile;
    }
}
