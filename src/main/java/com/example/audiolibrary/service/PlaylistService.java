package com.example.audiolibrary.service;

import com.example.audiolibrary.model.Playlist;
import com.example.audiolibrary.model.Song;
import com.example.audiolibrary.model.User;
import com.example.audiolibrary.repository.PlaylistRepository;
import com.example.audiolibrary.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private SongRepository songRepository;

    public Playlist createPlaylist(String name, User owner) throws Exception {
        Optional<Playlist> existingPlaylist = playlistRepository.findByNameAndOwner(name, owner);
        if (existingPlaylist.isPresent()) {
            throw new Exception("You already have a playlist named " + name);
        }
        Playlist newPlaylist = new Playlist(name, owner);
        return playlistRepository.save(newPlaylist);
    }

    public void addSongToPlaylist(Long playlistId, Long songId, User owner) throws Exception {
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

    public List<Playlist> listPlaylists(User owner) {
        return playlistRepository.findByOwner(owner);
    }
}
