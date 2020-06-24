package com.amad.playlistconverter.converter.services;

import com.amad.playlistconverter.musicservices.MusicService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class ConverterServiceTest {

    @Mock
    MusicService youtubeService;
    @Mock
    MusicService spotifyService;
    @Mock
    MusicService appleMusicService;

    ConverterService converterService;

    @BeforeAll
    public void init() {
        spotifyService = Mockito.mock(MusicService.class);
        appleMusicService = Mockito.mock(MusicService.class);
        youtubeService = Mockito.mock(MusicService.class);
        converterService = new ConverterService(youtubeService, spotifyService, appleMusicService);
    }

}