package com.example.demo;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PatternMatcherTest {

    private final char[] pattern = "ABCDE".toCharArray();

    private final PatternMatcher target = new PatternMatcher(pattern);

    @ParameterizedTest
    @MethodSource
    void reverseMatchTest(char[] c, PatternMatcher.Result expected){
        var result = target.reverseMatch(c);
        assertEquals(result, expected);
    }

    static @NotNull Stream<Arguments> reverseMatchTest(){
        return Stream.of(
                arguments("ABCDE".toCharArray(), new PatternMatcher.Match()),
                arguments("ABCAD".toCharArray(), new PatternMatcher.Mismatch(4))
        );
    }
}
