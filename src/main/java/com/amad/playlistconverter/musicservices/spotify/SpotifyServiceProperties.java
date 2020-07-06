package com.amad.playlistconverter.musicservices.spotify;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "music.service.spotify")
@Data
public class SpotifyServiceProperties {
    private String client;
    private String secret;
}
