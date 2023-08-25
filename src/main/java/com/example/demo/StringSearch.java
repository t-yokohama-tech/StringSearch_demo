package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
@AllArgsConstructor
public class StringSearch implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(StringSearch.class, args);
    }

    private final ReadFile readFile;
    private final IndexOutput indexOutput;
    private final SearchIndex searchIndex;
    private final String path = "data/chumonno_oi_ryoriten.utf8.txt";
    private final File file = new File(path);



    @Override
    public void run(String... args) throws IOException {

        try( Reader reader = new BufferedReader(readFile.read(file))){
            indexOutput.output(searchIndex.search(reader, args[0]));
        }
    }
}