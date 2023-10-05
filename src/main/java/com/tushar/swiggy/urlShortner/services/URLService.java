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

        String cachedShortUrl = fileParserService.getCachedShortUrl(longUrl);
        if(cachedShortUrl != null){
            return cachedShortUrl;
        }

        String shortUrl = algoService.applyAlgo(longUrl);
        fileParserService.saveURLMapping(longUrl, shortUrl);
        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        return fileParserService.getOriginalUrl(shortUrl);
    }

    public boolean updateDestinationUrl(String shortUrl, String longUrl) {
        if(shortUrl == null ) throw new IllegalArgumentException("Please pass valid short url!!");
        if(longUrl == null ) throw new IllegalArgumentException("Please pass valid long url!!");

        return fileParserService.updateDestinationUrl(shortUrl, longUrl);
    }

    public boolean updateExpiry(String shortUrl, Integer daysToExpire) {
        if(shortUrl == null ) throw new IllegalArgumentException("Please pass valid short url!!");

        return fileParserService.updateShortUrlExpiry(shortUrl, daysToExpire);
    }
}
