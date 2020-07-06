package com.amad.playlistconverter;

import com.amad.playlistconverter.musicservices.spotify.SpotifyServiceProperties;
import com.amad.playlistconverter.musicservices.youtube.YoutubeServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SpotifyServiceProperties.class, YoutubeServiceProperties.class})
public class PlaylistConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaylistConverterApplication.class, args);
    }

}
