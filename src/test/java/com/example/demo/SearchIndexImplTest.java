package com.example.demo;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class SearchIndexImplTest {
    private final SearchIndex target = new SearchIndexImpl();

    @Nested
    class search {

        @ParameterizedTest
        @MethodSource
        void returnSearchResults(Reader reader, String str, List<Integer> expected) throws IOException {
            var result = target.search(reader, str);
            assertEquals(expected, result);
        }


        static @NotNull Stream<Arguments> returnSearchResults() {
            return Stream.of(
                    arguments(
                            new StringReader("はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。"),
                            "紳士",
                            List.of(4, 29)
                    ),
                    arguments(
                            new StringReader("検索対象の文字列が先頭にあるパターンを検証"),
                            "検索",
                            List.of(0)
                    ),
                    arguments(
                            new StringReader("検証対象の文字が最後にある文字列を検索"),
                            "検索",
                            List.of(17)
                    ),
                    arguments(
                             new StringReader("対象ファイル"),
                            "検索対象ファイル",
                            List.of()
                    ),
                    arguments(
                            new StringReader(
                            """
                                    はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。
                                    はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。
                                    はじめ紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。
                                    はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。
                                    はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。
                                    """),
                            "紳士",
                            List.of(4, 29, 52, 77, 99, 124, 147, 172, 195, 220)
                    )
            );
        }
    }
}