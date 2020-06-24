package com.amad.playlistconverter.musicservices.youtube;

import com.amad.playlistconverter.musicservices.MusicService;
import com.amad.playlistconverter.musicservices.youtube.services.YoutubeApiService;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Service
public class YoutubeService implements MusicService {

    private final YoutubeApiService youtubeApiService;

    public YoutubeService(YoutubeApiService youtubeApiService) {
        this.youtubeApiService = youtubeApiService;
    }

    @Override
    public String getTitle(String url) {
        String videoId = getVideoId(url);
        try {
            return youtubeApiService.findVideoTitle(videoId);
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    public String getUrl(String title) {
        String url = "youtube.pl/watch?v=";
        try {
            return url + youtubeApiService.findId(title);
        } catch (IOException e) {
            return url;
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

    private String getVideoId(String url) {
        MultiValueMap<String, String> parameters;
        parameters = UriComponentsBuilder
                .fromUriString(url)
                .build()
                .getQueryParams();
        //TODO
        return parameters.get("v").get(0);
    }
}
