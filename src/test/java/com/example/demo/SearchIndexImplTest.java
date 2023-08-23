package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchIndexImplTest {
    private final SearchIndex target = new SearchIndexImpl();
    @SuppressWarnings("FieldCanBeLocal")
    private final String str = "紳士";
    @SuppressWarnings("FieldCanBeLocal")
    private final String stringA = """
            1はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。
            2はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。
            3はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。""";
    @SuppressWarnings("FieldCanBeLocal")
    private final Integer count = 0;
    private final List<Integer> idxList = new ArrayList<>();
    @Nested
    class search {
        @Test
        void returnSearchResults() {
            var result = target.search(str, stringA, count, idxList);
            var expected = Arrays.asList(5, 30, 52, 77, 99, 124, 146, 171, 195, 220, 244, 269);
            assertEquals(expected, result);
        }
    }
}