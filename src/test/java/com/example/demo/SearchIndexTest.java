package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchIndexTest {
    private final SearchIndex target =
            new SearchIndexImpl();
    @SuppressWarnings("FieldCanBeLocal")
    private final String str = "紳士";
    @SuppressWarnings("FieldCanBeLocal")
    private final String stringA = "はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。";

    @Nested
    class search {
        @Test
        void returnSearchResult() {
            var result = target.search(str, stringA);
            var expected = "4文字目\n29文字目\n";

            assertEquals(expected, result);
        }
    }
}