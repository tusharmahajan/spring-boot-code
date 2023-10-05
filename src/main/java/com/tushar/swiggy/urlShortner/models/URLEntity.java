package com.tushar.swiggy.urlShortner.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class URLEntity {

    private String shortUrl;
    private String longUrl;
    @JsonProperty("extendExpiryByDays")
    private Integer daysToExpire;

    public Integer getDaysToExpire() {
        return daysToExpire;
    }

    public void setDaysToExpire(Integer daysToExpire) {
        this.daysToExpire = daysToExpire;
    }

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
