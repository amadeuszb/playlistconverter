package com.amad.playlistconverter.musicservices;

import java.util.List;
import java.util.Optional;

public interface MusicService {

    Optional<String> getSongTitle(String url);

    Optional<String> getSongUrl(String title);

    List<String> getTitlesOfPlaylist(String url);

    List<String> getUrlsOfPlaylist(String url);
}
