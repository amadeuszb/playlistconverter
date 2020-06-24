package com.amad.playlistconverter.musicservices.spotify;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "music.service.spotify")
@Getter
@Setter
public class SpotifyServiceProperties {
    String apiUrl;
    String clientId;
    String clientSecret;
}
