package com.tushar.swiggy.urlShortner.services;

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
public class FileParserService {

    private final Map<String, String> shortToLongUrlMapping;
    private final Map<String, String> longToShortUrlMapping;
    private final String filePath;

    public FileParserService(){

        this.shortToLongUrlMapping = new HashMap<>();
        this.longToShortUrlMapping = new HashMap<>();
        this.filePath = "/Users/tusharmahajan/Desktop/IdeaProjects/URL-shortner-Mapping-store.csv";
        fetchExistingShortURLs();
    }

    private void fetchExistingShortURLs() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String [] urlMapping = line.split(",");
                populateMap(urlMapping);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storeShortLongUrlMapping(String longUrl, String shortUrl) {

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.write(shortUrl + ',' + longUrl);
            populateMap(new String[]{shortUrl, longUrl});
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOriginalUrl(String shortUrl) {
        String longUrl = shortToLongUrlMapping.get(shortUrl);

        if(longUrl == null){
            throw new NullPointerException("Issue while fetching long url");
        }
        return longUrl;
    }

    public Map<String, String> getShortToLongUrlMapping() {
        return shortToLongUrlMapping;
    }

    public Map<String, String> getLongToShortUrlMapping() {
        return longToShortUrlMapping;
    }

    private void populateMap(String []urlMapping) {
        shortToLongUrlMapping.put(urlMapping[0], urlMapping[1]);
        longToShortUrlMapping.put(urlMapping[1], urlMapping[0]);
    }

    public boolean updateShortToLongUrlMapping(String shortUrl, String longUrl) {

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
