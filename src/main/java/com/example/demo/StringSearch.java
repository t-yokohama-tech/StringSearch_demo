package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class StringSearch implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(StringSearch.class, args);
    }

    private final ReadFile readFile;
    private final IndexOutput indexOutput;
    private final SearchIndex searchIndex;

    private final String path = "data/String_A_File.txt";
    private final File file = new File(path);



    @Override
    public void run(String... args) throws IOException {
        String str = args[0];
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int count = 0; //全文通しての文字数カウント
        String stringA;
        List<Integer> idxList = new ArrayList<>(); //全文通しの文字数リスト
        while ((stringA = readFile.readLine(bufferedReader)) != null) {
            searchIndex.search(str, stringA, count, idxList);
            count = count + stringA.length();
        }
        indexOutput.output(idxList);
    }
}