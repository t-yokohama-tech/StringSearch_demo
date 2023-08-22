package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@AllArgsConstructor
public class StringSearch implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(StringSearch.class, args);
    }

    private final ReadFile readFile;
    private final IndexOutput indexOutput;
    private final SearchIndex searchIndex;

    private final Path path = Paths.get("data/String_A_File.txt");

    @Override
    public void run(String... args) throws IOException {
        String str = args[0];
        String stringA = readFile.read(path);
        indexOutput.output(searchIndex.search(str, stringA));
    }
}