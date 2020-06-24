package com.amad.playlistconverter.musicservices.youtube.services;

import com.amad.playlistconverter.musicservices.youtube.YoutubeServiceProperties;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class YoutubeApiService {

    YouTube youTube;
    YoutubeServiceProperties youtubeServiceProperties;
    String apiUrl;
    private static final String snippet = "snippet";

    public YoutubeApiService(YoutubeServiceProperties youtubeServiceProperties, YouTube youTube) {
        this.youtubeServiceProperties = youtubeServiceProperties;
        this.youTube = youTube;
        apiUrl = youtubeServiceProperties.getApiUrl();
    }

    public String findVideoTitle(String videoId) throws IOException {
        YouTube.Videos.List request = youTube.videos()
                .list(snippet);
        VideoListResponse response = request.setId(videoId).execute();
        return response.getItems().get(0).getSnippet().getTitle();
    }
    public String findId(String videoTitle) throws IOException {
        YouTube.Search.List request = youTube.search()
                .list(snippet);
        SearchListResponse response = request
                .setQ(videoTitle)
                .execute();
        return response.getItems().get(0).getId().getVideoId();
    }
}
