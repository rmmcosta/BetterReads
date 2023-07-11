package com.rmmcosta.betterreadsbootstrap;

import com.datastax.astra.sdk.AstraClient;
import com.rmmcosta.betterreadsbootstrap.author.Author;
import com.rmmcosta.betterreadsbootstrap.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class BetterReadsBootstrapApplication {

    @Autowired
    AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(BetterReadsBootstrapApplication.class, args);
    }

    @Bean
    public void bootstrapAuthors() throws IOException {
        var bufferedReader = new BufferedReader(new FileReader("src/main/resources/sample_authors.txt"));
        var gsonJsonParser = new GsonJsonParser();
        String line;
        Map<String, Object> jsonMap;
        while ((line = bufferedReader.readLine()) != null) {
            int startJsonIdx = line.indexOf("{");
            String json = line.substring(startJsonIdx);
            System.out.println(json);
            jsonMap = gsonJsonParser.parseMap(json);
            String authorKey = jsonMap.get("key").toString().replace("/authors/", "");
            String authorName = jsonMap.get("name").toString();
            authorRepository.save(new Author(authorKey, authorName));
        }
    }
}
