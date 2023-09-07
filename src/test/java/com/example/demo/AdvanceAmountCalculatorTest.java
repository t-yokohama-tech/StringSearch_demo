package com.example.demo;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AdvanceAmountCalculatorTest {

    private final char[] pattern = "ABCAD".toCharArray();
    private final AdvanceAmountCalculator target = new AdvanceAmountCalculator(pattern);
    @ParameterizedTest
    @MethodSource
    void getSlidingAmountTest(int position, char c, int expected) {
        var result = target.getSlidingAmount(position,c);
        assertEquals(expected, result);
    }

    static @NotNull Stream<Arguments> getSlidingAmountTest(){
        return Stream.of(
                arguments(2, 'A', 2),//advanceAmountMapに含まれる文字　かつ　advanceAmountMap.get(c)-patternLength + position + 1を返却
                arguments(1, 'C', 1),//advanceAmountMapに含まれる文字　かつ　1の値を返却
                arguments(4, 'Z', 5) //advanceAmountMapに含まれない文字

        );
    }
}
