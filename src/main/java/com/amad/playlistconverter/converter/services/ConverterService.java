package com.amad.playlistconverter.converter.services;

import com.amad.playlistconverter.converter.model.MusicUrls;
import com.amad.playlistconverter.converter.model.MusicUrlType;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    public MusicUrls convert(String url){
        MusicUrlType musicUrlType = getTypeOfUrl(url);
        return null;
    }
    private MusicUrlType getTypeOfUrl(String url){
        if(url.contains("spotify")){
            return MusicUrlType.SPOTIFY;
        }else if(url.contains("apple")){
            return MusicUrlType.APPLE_MUSIC;
        }else if(url.contains("youtube") || url.contains("youtu.be")){
            return MusicUrlType.YOUTUBE;
        }else {
            throw new IllegalArgumentException();
        }
    }
}
