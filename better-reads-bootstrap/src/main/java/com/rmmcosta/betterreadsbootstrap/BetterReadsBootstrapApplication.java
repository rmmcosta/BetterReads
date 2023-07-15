package com.rmmcosta.betterreadsbootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmmcosta.betterreadsbootstrap.openlibrarydeserialize.AuthorJson;
import com.rmmcosta.betterreadsbootstrap.openlibrarydeserialize.Book;
import com.rmmcosta.betterreadsbootstrap.author.Author;
import com.rmmcosta.betterreadsbootstrap.author.AuthorRepository;
import com.rmmcosta.betterreadsbootstrap.work.Work;
import com.rmmcosta.betterreadsbootstrap.work.WorkByAuthor;
import com.rmmcosta.betterreadsbootstrap.work.WorkByAuthorRepository;
import com.rmmcosta.betterreadsbootstrap.work.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@SpringBootApplication
public class BetterReadsBootstrapApplication {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    WorkRepository workRepository;
    @Autowired
    WorkByAuthorRepository workByAuthorRepository;

    static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(BetterReadsBootstrapApplication.class, args);
    }

    @Bean
    public void bootstrapAuthorsAndWorks() throws IOException {
        bootstrapAuthors();
        bootstrapWorks();
    }

    private void bootstrapAuthors() throws IOException {
        var bufferedReader = new BufferedReader(new FileReader("src/main/resources/test-authors.txt"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            int startJsonIdx = line.indexOf("{");
            String json = line.substring(startJsonIdx);
            //System.out.println(json);
            AuthorJson author = objectMapper.readValue(json, AuthorJson.class);
            authorRepository.save(new Author(author.key(), author.name()));
        }
    }

    private void bootstrapWorks() throws IOException {
        var bufferedReader = new BufferedReader(new FileReader("src/main/resources/test-works.txt"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            int startJsonIdx = line.indexOf("{");
            String workJson = line.substring(startJsonIdx);
            Book book = objectMapper.readValue(workJson, Book.class);
            String authorKey = book.authors()[0].author().key();
            Optional<Author> author = authorRepository.findById(authorKey);
            String authorName = author.isPresent() ? author.get().getName() : "";
            String bookCover = (book.covers() != null) ? String.valueOf(book.covers()[0]) : "";
            LocalDate releaseDt = LocalDate.parse(book.created().value(), DateTimeFormatter.ISO_DATE_TIME);
            workRepository.save(
                    new Work(
                            book.key(),
                            book.title(),
                            bookCover,
                            authorKey,
                            authorName
                    )
            );
            workByAuthorRepository.save(
                    new WorkByAuthor(
                            authorKey,
                            releaseDt,
                            book.key(),
                            book.title(),
                            bookCover
                    )
            );
        }
    }
}
