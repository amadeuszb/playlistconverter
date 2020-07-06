package com.amad.playlistconverter.musicservices.spotify;

import com.amad.playlistconverter.musicservices.MusicService;
import com.amad.playlistconverter.musicservices.spotify.services.SpotifyApiService;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("spotifyService")
public class SpotifyService implements MusicService {

    private final static String spotifyTrackUrl = "https://open.spotify.com/track/";
    public static final String trackPattern = "/track/";
    private SpotifyApiService spotifyApiService;
    private final String spaceCharacter = " ";

    public SpotifyService(SpotifyApiService spotifyApiService) {
        this.spotifyApiService = spotifyApiService;
    }

    @Override
    public Optional<String> getSongTitle(String url) {
        return spotifyApiService
                .getTrackById(getSpotifySongId(url))
                .map(this::mapTrackToArtistAndName);
    }

    @Override
    public Optional<String> getSongUrl(String title) {
        Optional<String> songId = spotifyApiService.getSongId(title);
        return songId.map(id -> spotifyTrackUrl + id);
    }

    @Override
    public List<String> getTitlesOfPlaylist(String url) {
        return null;
    }

    @Override
    public List<String> getUrlsOfPlaylist(String url) {
        return null;
    }

    private String getSpotifySongId(String songUrl) {
        String url = UriComponentsBuilder
                .fromUriString(songUrl)
                .build()
                .getPath();
        try {
            String id = url.split(trackPattern)[1];
            return id;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Provided url is not correct, does not contain track id");
        }
    }

    private String mapTrackToArtistAndName(Track track) {
        List<ArtistSimplified> artists = Arrays.asList(track.getArtists());
        String artistsNames = artists
                .stream()
                .map(ArtistSimplified::getName)
                .collect(Collectors.joining(spaceCharacter));
        String trackName = track.getName();
        return artistsNames + spaceCharacter + trackName;
    }
}
