package com.amad.playlistconverter.musicservices.youtube;

import com.amad.playlistconverter.musicservices.MusicService;
import com.amad.playlistconverter.musicservices.youtube.services.YoutubeApiService;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service("youtubeService")
public class YoutubeService implements MusicService {

    private final YoutubeApiService youtubeApiService;
    private final String youtubeVideoUrlParameter = "v";
    private final String youtubeVideoUrl = "youtube.pl/watch?v=";

    public YoutubeService(YoutubeApiService youtubeApiService) {
        this.youtubeApiService = youtubeApiService;
    }

    @Override
    public Optional<String> getSongTitle(String url) {
        String videoId = getVideoId(url).orElseThrow(() -> new IllegalArgumentException("Provided url does not contain video id"));
        try {
            return youtubeApiService.findVideoTitle(videoId).map(this::cutParenthesisInSongTitle);
        } catch (IOException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> getSongUrl(String title) {
        try {
            Optional<String> videoId = youtubeApiService.findVideoId(title);
            return videoId.map(s -> youtubeVideoUrl + s);
        } catch (IOException ex) {
            //TODO: Add logger
            return Optional.empty();
        }
    }

    @Override
    public List<String> getTitlesOfPlaylist(String url) {
        return null;
    }

    @Override
    public List<String> getUrlsOfPlaylist(String url) {
        return null;
    }

    private Optional<String> getVideoId(String videoUrl) {
        MultiValueMap<String, String> parameters;
        parameters = UriComponentsBuilder
                .fromUriString(videoUrl)
                .build()
                .getQueryParams();
        return parameters.get(youtubeVideoUrlParameter).stream().findFirst();
    }

    private String cutParenthesisInSongTitle(String songTitle) {
        return songTitle.replaceAll("\\([^()]*\\)", "")
                .replaceAll("\\[[^()]*\\]", "");
    }
}
