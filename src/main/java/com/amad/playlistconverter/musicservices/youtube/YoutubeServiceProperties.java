package com.amad.playlistconverter.musicservices.youtube;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "music.service.youtube")
@Getter
@Setter
public class YoutubeServiceProperties {
    private String apiUrl;
}
