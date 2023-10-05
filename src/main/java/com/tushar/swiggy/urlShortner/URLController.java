package com.tushar.swiggy.urlShortner;

import com.tushar.swiggy.urlShortner.models.URLEntity;
import com.tushar.swiggy.urlShortner.services.URLService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
public class URLController {

    @Autowired
    URLService urlService;

    @PostMapping("/shortenUrl")
    public ResponseEntity<String> shortenUrl(@RequestBody URLEntity urlEntity){
        try {
            return ResponseEntity.ok(urlService.generateShortenUrl(urlEntity.getLongUrl()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/updateDestinationUrl")
    public ResponseEntity<Boolean> updateDestinationUrl(@RequestBody URLEntity urlEntity){
        try {
            return ResponseEntity.ok(urlService.updateDestinationUrl(urlEntity.getShortUrl(),urlEntity.getLongUrl()));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/getOriginalUrl/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl){
        try {
            return ResponseEntity.ok(urlService.getOriginalUrl(shortUrl));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/updateExpiry")
    public ResponseEntity<Boolean> updateExpiry(@RequestBody URLEntity urlEntity){
        try {
            return ResponseEntity.ok(urlService.updateExpiry(urlEntity.getShortUrl(), urlEntity.getDaysToExpire()));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/redirect/{shortenString}")
    public ResponseEntity<Void> redirectToFullUrl(HttpServletResponse response, @PathVariable String shortenString) {
        try {
            // get the full url from csv and use the below lines for redirection

            String originalUrl = urlService.getOriginalUrl(shortenString);
            response.sendRedirect(originalUrl);
            return ResponseEntity.status(HttpStatus.FOUND).build();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found", e);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not redirect to the full url", e);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
