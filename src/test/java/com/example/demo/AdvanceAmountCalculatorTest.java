package com.example.demo;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AdvanceAmountCalculatorTest {

    char[] pattern = "ABCAD".toCharArray();
    private final AdvanceAmountCalculator target = new AdvanceAmountCalculator(pattern);
    @ParameterizedTest
    @MethodSource
    void getSlidingAmountTest(int position, char c, int expected) {
        var result = target.getSlidingAmount(position,c);
        assertEquals(expected, result);
    }

    static @NotNull Stream<Arguments> getSlidingAmountTest(){
        return Stream.of(
                arguments(1, 'A', 4),//advanceAmountMapに含まれる文字　かつ　patternLength - positionを返却
                arguments(4, 'B', 3),//advanceAmountMapに含まれる文字　かつ　advanceAmountMapの値を返却
                arguments(4, 'Z', 5) //advanceAmountMapに含まれない文字

        );
    }
}
