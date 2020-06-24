package com.amad.playlistconverter.musicservices.spotify;

import com.amad.playlistconverter.musicservices.MusicService;
import com.amad.playlistconverter.musicservices.spotify.services.SpotifyApiService;
import org.springframework.stereotype.Service;

import java.util.List;


public class SpotifyService implements MusicService {
    private SpotifyApiService spotifyApiService;

    public SpotifyService(SpotifyApiService spotifyApiService) {
        this.spotifyApiService = spotifyApiService;
    }

    @Override
    public String getTitle(String url) {
        return null;
    }

    @Override
    public String getUrl(String title) {
        return null;
    }

    @Override
    public List<String> getTitlesOfPlaylist(String url) {
        return null;
    }

    @Override
    public List<String> getUrlsOfPlaylist(String url) {
        return null;
    }
}
