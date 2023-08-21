package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class StringSearch {
    public static void main(String[] args) throws IOException {

        Path p1 = Paths.get("");
        Path p2 = p1.toAbsolutePath();

        System.out.println("実装側　"+p2.toString());

        String str = args[0];

        String stringA = getFilePath();

        int idx = stringA.indexOf(str);
        while (idx != -1) {
            System.out.println(idx + "文字目");
            idx = stringA.indexOf(str, idx + 1);
        }
    }

    public static String getFilePath() throws IOException {
        Path path = Paths.get("data/String_A_File.txt");
        return Files.readString(path);
    }
}