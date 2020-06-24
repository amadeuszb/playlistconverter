package com.amad.playlistconverter.converter.services;

import com.amad.playlistconverter.converter.model.MusicServiceUrlType;
import com.amad.playlistconverter.converter.model.StreamsMusicUrls;
import com.amad.playlistconverter.musicservices.MusicService;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    private MusicService youtubeService;
    private MusicService spotifyService;
    private MusicService appleMusicService;

    public ConverterService(MusicService youtubeService, MusicService spotifyService, MusicService appleMusicService) {
        this.youtubeService = youtubeService;
        this.spotifyService = spotifyService;
        this.appleMusicService = appleMusicService;
    }

    public StreamsMusicUrls convertSong(String url) {
        MusicServiceUrlType musicServiceUrlType = getUrlType(url);
        String titleOfSong = getTitleOfSong(url, musicServiceUrlType);
        return findSongsOnAllServices(titleOfSong);
    }

    //TODO: Add actual url in future
    private StreamsMusicUrls findSongsOnAllServices(String titleOfSong){
        StreamsMusicUrls streamsMusicUrls = new StreamsMusicUrls();
        String youtubeUrl = youtubeService.getUrl(titleOfSong);
        String spotifyUrl = spotifyService.getUrl(titleOfSong);
        String appleUrl = appleMusicService.getUrl(titleOfSong);
        streamsMusicUrls.setYoutube(youtubeUrl);
        streamsMusicUrls.setSpotify(spotifyUrl);
        streamsMusicUrls.setAppleMusic(appleUrl);
        return streamsMusicUrls;
    }

    private String getTitleOfSong(String url, MusicServiceUrlType musicServiceUrlType) {
        switch (musicServiceUrlType) {
            case SPOTIFY:
                return spotifyService.getTitle(url);
            case YOUTUBE:
                return youtubeService.getTitle(url);
            case APPLE_MUSIC:
                return appleMusicService.getTitle(url);
            default:
                throw new IllegalArgumentException();
        }
    }

    private MusicServiceUrlType getUrlType(String url) {
        if (url.contains("spotify")) {
            return MusicServiceUrlType.SPOTIFY;
        } else if (url.contains("apple")) {
            return MusicServiceUrlType.APPLE_MUSIC;
        } else if (url.contains("youtube") || url.contains("youtu.be")) {
            return MusicServiceUrlType.YOUTUBE;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
