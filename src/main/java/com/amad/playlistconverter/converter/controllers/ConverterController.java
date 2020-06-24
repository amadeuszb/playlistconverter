package com.amad.playlistconverter.converter.controllers;

import com.amad.playlistconverter.converter.model.MusicUrl;
import com.amad.playlistconverter.converter.model.StreamsMusicUrls;
import com.amad.playlistconverter.converter.services.ConverterService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;



@RestController
@RequestMapping(value = "/convert")
public class ConverterController {

    private final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StreamsMusicUrls convertUrl(@RequestBody MusicUrl url){
        return converterService.convertSong(url.getUrl());
    }
}
