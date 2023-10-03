package com.tushar.swiggy.urlShortner.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class CustomFileParserService implements FileParserService{

    private final Map<String, String> shortToLongUrlMapping;
    private final Map<String, String> longToShortUrlMapping;
    private final String filePath;

    public CustomFileParserService(){
        this.shortToLongUrlMapping = new HashMap<>();
        this.longToShortUrlMapping = new HashMap<>();
        this.filePath = "/Users/tusharmahajan/Desktop/IdeaProjects/swiggy/src/main/resources/url-store.csv";
        fetchExistingShortURLs();
    }

    private void fetchExistingShortURLs() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String [] urlMapping = line.split(",");
                populateMap(urlMapping);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveURLMapping(String longUrl, String shortUrl) {
        try{
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
                bufferedWriter.write(shortUrl + ',' + longUrl);
                populateMap(new String[]{shortUrl, longUrl});
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    public String getOriginalUrl(String shortUrl) {
        String longUrl = shortToLongUrlMapping.get(shortUrl);
        if(longUrl == null){
            throw new NullPointerException("Long URL doesn't exist for this short URL");
        }
        return longUrl;
    }

    public String getCachedShortUrl(String longUrl) {
        return longToShortUrlMapping.get(longUrl);
    }

    private void populateMap(String []urlMapping) {
        shortToLongUrlMapping.put(urlMapping[0], urlMapping[1]);
        longToShortUrlMapping.put(urlMapping[1], urlMapping[0]);
    }

    public boolean updateDestinationUrl(String shortUrl, String longUrl) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(this.filePath), StandardCharsets.UTF_8);
            List<String> updatedLines = new ArrayList<>();
            for(String line : lines){
                String [] urlMapping = line.split(",");
                if(urlMapping[0].equals(shortUrl)){
                    updatedLines.add(shortUrl + ',' + longUrl);
                }
                else{
                    updatedLines.add(line);
                }
            }
            Files.write(Paths.get(this.filePath), updatedLines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
            populateMap(new String[]{shortUrl, longUrl});
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
