package com.example.demo;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class AdvanceAmountCalculatorTest {

    @Test
    void getSlidingAmountTest() {

        var pattern = "ABCAD".toCharArray();
        var target = new AdvanceAmountCalculator(pattern);
        var slideAmount_A = target.getSlidingAmount(1, 'A');
        var slideAmount_B = target.getSlidingAmount(2, 'B');
        var slideAmount_C = target.getSlidingAmount(3, 'C');


        assertEquals(4, slideAmount_A);
        assertEquals(3, slideAmount_B);
        assertEquals(2, slideAmount_C);
    }
}
