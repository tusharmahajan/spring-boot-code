package com.tushar.swiggy.urlShortner;

import com.tushar.swiggy.urlShortner.models.URLEntity;
import com.tushar.swiggy.urlShortner.services.URLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class URLController {

    @Autowired
    URLManager urlManager;

    @PostMapping("/shortenUrl")
    public ResponseEntity<String> shortenUrl(@RequestBody URLEntity urlEntity){
        try {
            return ResponseEntity.ok(urlManager.generateShortenUrl(urlEntity.getLongUrl()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/updateDestinationUrl")
    public ResponseEntity<Boolean> updateDestinationUrl(@RequestBody URLEntity urlEntity){
        try {
            return ResponseEntity.ok(urlManager.updateDestinationUrl(urlEntity.getShortUrl(),urlEntity.getLongUrl()));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/getOriginalUrl/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl){
        try {
            return ResponseEntity.ok(urlManager.getOriginalUrl(shortUrl));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/updateExpiry")
    public ResponseEntity<Boolean> updateExpiry(@RequestBody URLEntity urlEntity){
        try {
            return ResponseEntity.ok(urlManager.updateExpiry(urlEntity.getShortUrl(), urlEntity.getDaysToExpire()));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/redirect/{shortenString}")
    public RedirectView redirectToFullUrl( @PathVariable String shortenString) {
        try {

            String originalUrl = urlManager.getOriginalUrl(shortenString);
            RedirectView redirectView = new RedirectView(originalUrl);
            redirectView.setStatusCode(HttpStatus.FOUND);

            return redirectView;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found", e);
        }
    }

    @PostMapping("/validate/shortenUrl")
    public ResponseEntity<String> validateShortenUrl(@RequestBody Map<String, Object> data, @RequestAttribute boolean valid){

        return ResponseEntity.ok().body("Success");
    }

}
