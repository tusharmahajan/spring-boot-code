package com.tushar.swiggy.urlShortner.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
@Primary
public class CustomAlgo implements AlgoService{

    private static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE62_LENGTH = BASE62_CHARS.length();
    private static final int SHORT_URL_LENGTH = 6;
    Random random = new Random();

    public String applyAlgo(String longUrl) {
        return generateRandomCode();
    }

    private String generateRandomCode() {

        StringBuilder code = new StringBuilder();

        for(int i = 0;i<SHORT_URL_LENGTH;i++){
            int randomIdx = random.nextInt(BASE62_LENGTH);
            char randomChar = BASE62_CHARS.charAt(randomIdx);
            code.append(randomChar);
        }
        return code.toString();
    }
}
