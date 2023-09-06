package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AdvanceAmountCalculatorTest {
    
    @Test
    void SlidingAmountCalculatorTest() {
        var expectedAdvanceAmountMap = new HashMap<Character, Integer>() {
            {
                put('A', 1);
                put('B', 3);
                put('C', 2);
            }
        };
        var pattern = "ABCAD".toCharArray();
        var target = new AdvanceAmountCalculator(pattern);
        var targetMap = target.MakeAdvanceAmountMap(pattern, 5);

        assertEquals(expectedAdvanceAmountMap, targetMap);
    }
}
