package com.amad.playlistconverter.musicservices;

import java.util.List;

public interface MusicService {
    String getTitle(String url);
    String getUrl(String title);
    List<String> getTitlesOfPlaylist(String url);
    List<String> getUrlsOfPlaylist(String url);
}
