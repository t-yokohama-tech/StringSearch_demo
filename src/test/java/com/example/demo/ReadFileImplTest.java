package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadFileImplTest {
    private final ReadFile target = new ReadFileImpl();
    private final String path = "data/String_A_File.txt";
    private final File file = new File(path);
    @Nested
    class readFile {
        @Test
        void returnReadFile() throws IOException {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            var result = target.readLine(bufferedReader);
            var expected = "1はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。";
            assertEquals(expected, result);

            var result2 = target.readLine(bufferedReader);
            var expected2 = "2はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。";
            assertEquals(expected2, result2);

        }
    }
}