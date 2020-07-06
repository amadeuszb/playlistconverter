package com.amad.playlistconverter.musicservices.youtube.services;

import com.amad.playlistconverter.musicservices.youtube.YoutubeServiceProperties;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class YoutubeApiService {

    private final YouTube youTube;
    private final YoutubeServiceProperties youtubeServiceProperties;
    private final String apiUrl;
    private static final String snippet = "snippet";

    public YoutubeApiService(YoutubeServiceProperties youtubeServiceProperties, YouTube youTube) {
        this.youtubeServiceProperties = youtubeServiceProperties;
        this.youTube = youTube;
        apiUrl = youtubeServiceProperties.getApiUrl();
    }

    public Optional<String> findVideoTitle(String videoId) throws IOException {
        YouTube.Videos.List request = youTube.videos()
                .list(snippet);
        VideoListResponse response = request.setId(videoId).execute();
        List<Video> videoResults = response.getItems();
        return videoResults
                .stream()
                .findFirst()
                .map(Video::getSnippet)
                .map(VideoSnippet::getTitle);
    }

    public Optional<String> findVideoId(String videoTitle) throws IOException {
        YouTube.Search.List request = youTube.search()
                .list(snippet);
        SearchListResponse response = request
                .setQ(videoTitle)
                .execute();
        List<SearchResult> searchResults = response.getItems();
        return searchResults.stream()
                .findFirst()
                .map(SearchResult::getId)
                .map(ResourceId::getVideoId);
    }
}
