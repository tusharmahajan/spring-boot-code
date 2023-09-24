package com.tushar.swiggy.urlShortner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class URLService {

    @Autowired
    FileParserService fileParserService;

    @Autowired
    AlgoService algoService;

    public String generateShortenUrl(String longUrl) {
        if(longUrl == null) throw new NullPointerException("No long url specified");
        String checkAndGetShortUrl = fileParserService.getLongToShortUrlMapping().get(longUrl);
        if(checkAndGetShortUrl != null){
            return checkAndGetShortUrl;
        }

        String shortUrl = algoService.applyAlgo(longUrl);
        fileParserService.storeShortLongUrlMapping(longUrl, shortUrl);
        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        return fileParserService.getOriginalUrl(shortUrl);
    }

    public boolean updateShortUrlToNewDestinationUrl(String shortUrl, String longUrl) {
        if(shortUrl == null || longUrl == null) throw new IllegalArgumentException("Please pass valid urls");
        return fileParserService.updateShortToLongUrlMapping(shortUrl, longUrl);
    }
}
