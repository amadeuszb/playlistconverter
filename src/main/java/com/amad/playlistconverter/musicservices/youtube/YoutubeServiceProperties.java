package com.amad.playlistconverter.musicservices.youtube;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "music.service.youtube")
@Data
public class YoutubeServiceProperties {
    private String apiUrl;
    private String secret;
    private String scope;
}
