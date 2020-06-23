package com.amad.playlistconverter.converter.controllers;

import com.amad.playlistconverter.converter.model.MusicUrls;
import com.amad.playlistconverter.converter.services.ConverterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("/convert")
public class ConverterController {


    private final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    //TODO: Check if null on github
    @GetMapping
    public MusicUrls convertUrl(@RequestParam(value = "url") String url){
        return converterService.convert(url);
    }
}
