package com.amad.playlistconverter.musicservices.spotify.services;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SpotifyApiService {

    private final SpotifyApi spotifyApi;

    public SpotifyApiService(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    public Optional<String> getSongId(String title) {
        final SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(title).build();
        List<Track> tracks;
        try {
            tracks = Arrays.asList(searchTracksRequest.execute().getItems());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace(); //TODO Logger
            return Optional.empty();
        }
        return tracks.stream().findFirst().map(Track::getId);
    }

    public Optional<Track> getTrackById(String id) {
        final GetTrackRequest trackRequest = spotifyApi.getTrack(id).build();
        try {
            return Optional.ofNullable(trackRequest.execute());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace(); //TODO Logger
            return Optional.empty();
        }
    }
}
