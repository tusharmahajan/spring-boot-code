package com.tushar.swiggy.urlShortner.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class URLEntity {

    private String shortUrl;
    private String longUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

}
