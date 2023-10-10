package com.tushar.swiggy.urlShortner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class URLManager {

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

    public boolean updateDestinationUrl(String shortUrl, String newLongUrl) {
        if(shortUrl == null ) throw new IllegalArgumentException("Please pass valid short url!!");
        if(newLongUrl == null ) throw new IllegalArgumentException("Please pass valid long url!!");

        return fileParserService.updateDestinationUrl(shortUrl, newLongUrl);
    }

    public boolean updateExpiry(String shortUrl, Integer daysToExpire) {
        if(shortUrl == null ) throw new IllegalArgumentException("Please pass valid short url!!");

        return fileParserService.updateShortUrlExpiry(shortUrl, daysToExpire);
    }

    @Scheduled(cron = "0 2/10 0 9 10 ?")
    public void removeExpiredLinks(){
        fileParserService.removeExpiredLinks();
    }
}
