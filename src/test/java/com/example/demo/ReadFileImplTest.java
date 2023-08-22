package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadFileImplTest {
    private final ReadFile target = new ReadFileImpl();
    private final Path path = Paths.get("data/String_A_File.txt");

    @Nested
    class readFile {
        @Test
        void returnReadFile() throws IOException {
            var result = target.read(path);
            var expected = "はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。";

            assertEquals(expected, result);
        }
    }
}