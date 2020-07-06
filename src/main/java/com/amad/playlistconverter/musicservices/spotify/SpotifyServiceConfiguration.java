package com.amad.playlistconverter.musicservices.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SpotifyServiceConfiguration {

    private final SpotifyServiceProperties spotifyServiceProperties;

    public SpotifyServiceConfiguration(SpotifyServiceProperties spotifyServiceProperties) {
        this.spotifyServiceProperties = spotifyServiceProperties;
    }

    @Bean
    public SpotifyApi spotifyApi() {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(spotifyServiceProperties.getClient())
                .setClientSecret(spotifyServiceProperties.getSecret())
                .build();
        try {
            setAccessToken(spotifyApi);
        } catch (Exception e) {//TODO: exceptions
            System.out.println("Error: " + e.getMessage());
        }
        return spotifyApi;
    }
    private void setAccessToken(SpotifyApi spotifyApi) throws ParseException, SpotifyWebApiException, IOException {
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi
                .clientCredentials()
                .build();
        final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        //TODO: Logger
        System.out.println("Expires in: " + clientCredentials.getExpiresIn());
    }
}
