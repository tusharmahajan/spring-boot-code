package com.tushar.swiggy.urlShortner.services;

public interface FileParserService {

    void saveURLMapping(String longUrl, String shortUrl);
    String getOriginalUrl(String shortUrl);
    String getCachedShortUrl(String longUrl);
    boolean updateDestinationUrl(String shortUrl, String longUrl);
    boolean updateShortUrlExpiry(String shortUrl, Integer daysToExpire);
}
