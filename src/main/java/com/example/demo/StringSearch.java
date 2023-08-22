package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class StringSearch implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(StringSearch.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        String str = args[0];
        Path path = Paths.get("data/String_A_File.txt");
        String stringA = Files.readString(path);

        int idx = stringA.indexOf(str);
        while (idx != -1) {
            System.out.println(idx + "文字目");
            idx = stringA.indexOf(str, idx + 1);
        }
    }
}