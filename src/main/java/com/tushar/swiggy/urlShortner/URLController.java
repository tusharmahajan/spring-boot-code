package com.tushar.swiggy.urlShortner;

import com.tushar.swiggy.urlShortner.models.URLEntity;
import com.tushar.swiggy.urlShortner.services.URLService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
public class URLController {

    @Autowired
    URLService urlService;

    @PostMapping("/shortenUrl")
    public String shortenUrl(@RequestBody URLEntity urlEntity){
        try {
            return urlService.generateShortenUrl(urlEntity.getLongUrl());
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/updateDestinationUrl")
    public boolean updateDestinationUrl(@RequestBody URLEntity urls){
        try {
            return urlService.updateDestinationUrl(urls.getShortUrl(),urls.getLongUrl());
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/getOriginalUrl/{shortUrl}")
    public String getOriginalUrl(@PathVariable String shortUrl){
        try {
            return urlService.getOriginalUrl(shortUrl);
        }
        catch (RuntimeException e){
            return e.getMessage();
        }
    }

//    @PostMapping("/updateExpiry")
//    public boolean updateExpiry(@RequestParam String shortUrl, @RequestParam Integer daysToExpire){
//
//    }

    @GetMapping("/redirect/{shortenString}")
    public void redirectToFullUrl(HttpServletResponse response, @PathVariable String shortenString) {
        try {
            // get the full url from csv and use the below lines for redirection

            String originalUrl = urlService.getOriginalUrl(shortenString);
            response.sendRedirect(originalUrl);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found", e);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not redirect to the full url", e);
        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }

}
