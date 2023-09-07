package com.example.demo;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockConstruction;

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

    @Test
    void test() {
        try (MockedConstruction<PatternMatcher.Match> mockResult = mockConstruction(PatternMatcher.Match.class)) {
            var result = new PatternMatcher.Match();
            doReturn(new PatternMatcher.Match()).when(result);

            assertEquals(result,target.reverseMatch("ABCDE".toCharArray()));
        }

    }



}
