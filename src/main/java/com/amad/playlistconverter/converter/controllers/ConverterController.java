package com.amad.playlistconverter.converter.controllers;

import com.amad.playlistconverter.converter.model.MusicUrl;
import com.amad.playlistconverter.converter.model.StreamMusicUrls;
import com.amad.playlistconverter.converter.services.ConverterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/convert")
public class ConverterController {

    private final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StreamMusicUrls convertUrl(@RequestBody MusicUrl songUrl) {
        return converterService.convertSong(songUrl.getUrl());
    }
}
