package com.amad.playlistconverter.converter.services;

import com.amad.playlistconverter.converter.model.MusicServiceUrlType;
import com.amad.playlistconverter.converter.model.StreamMusicUrls;
import com.amad.playlistconverter.musicservices.MusicService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConverterService {

    private static final String youtubeUrlPattern = "youtube";
    private static final String youtuBeUrlPattern = "youtu.be";
    private static final String spotifyUrlPattern = "spotify";
    private static final String appleUrlPattern = "apple";
    private static final String emptyString = "";
    private final MusicService youtubeService;
    private final MusicService spotifyService;
    private final MusicService appleMusicService;

    public ConverterService(@Qualifier("spotifyService") MusicService spotifyService,
                            @Qualifier("youtubeService") MusicService youtubeService,
                            @Qualifier("youtubeService") MusicService appleMusicService) { //TODO: Mocked
        this.youtubeService = youtubeService;
        this.spotifyService = spotifyService;
        this.appleMusicService = appleMusicService;
    }

    public StreamMusicUrls convertSong(String url) {
        MusicServiceUrlType musicServiceUrlType = getUrlType(url);
        Optional<String> titleOfSong = getTitleOfSong(url, musicServiceUrlType);
        return titleOfSong.map(this::findSongsInAllServices).orElseGet(StreamMusicUrls::new);
    }

    //TODO: Add actual url in future
    private StreamMusicUrls findSongsInAllServices(String titleOfSong) {
        StreamMusicUrls streamMusicUrls = new StreamMusicUrls();
        String youtubeUrl = youtubeService.getSongUrl(titleOfSong).orElse(emptyString);
        String spotifyUrl = spotifyService.getSongUrl(titleOfSong).orElse(emptyString);
        String appleUrl = appleMusicService.getSongUrl(titleOfSong).orElse(emptyString);
        streamMusicUrls.setYoutube(youtubeUrl);
        streamMusicUrls.setSpotify(spotifyUrl);
        streamMusicUrls.setAppleMusic(appleUrl);
        return streamMusicUrls;
    }

    private Optional<String> getTitleOfSong(String url, MusicServiceUrlType musicServiceUrlType) {
        switch (musicServiceUrlType) {
            case SPOTIFY:
                return spotifyService.getSongTitle(url);
            case YOUTUBE:
                return youtubeService.getSongTitle(url);
            case APPLE_MUSIC:
                return appleMusicService.getSongTitle(url);
            default:
                throw new IllegalArgumentException("Music service type not recognised");
        }
    }

    private MusicServiceUrlType getUrlType(String url) {
        if (url.contains(spotifyUrlPattern)) {
            return MusicServiceUrlType.SPOTIFY;
        } else {
            if (url.contains(appleUrlPattern)) {
                return MusicServiceUrlType.APPLE_MUSIC;
            } else if (url.contains(youtubeUrlPattern) || url.contains(youtuBeUrlPattern)) {
                return MusicServiceUrlType.YOUTUBE;
            } else {
                throw new IllegalArgumentException("Provided url is not supported");
            }
        }
    }
}
