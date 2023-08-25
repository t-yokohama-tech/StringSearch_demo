package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchIndexImplTest {
    private final SearchIndex target = new SearchIndexImpl();
    @SuppressWarnings("FieldCanBeLocal")
    private final String str = "紳士";
    @SuppressWarnings("FieldCanBeLocal")
    private final String path = "data/String_A_File.txt";
    private final Reader reader;
    {
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Nested
    class search {
        @Test
        void returnSearchResults() throws IOException {
            var result = target.search(reader,str);
            var expected = Arrays.asList(5, 30, 52, 77, 124, 146, 171, 195, 220, 244, 269, 295);
            assertEquals(expected, result);
        }
    }
}