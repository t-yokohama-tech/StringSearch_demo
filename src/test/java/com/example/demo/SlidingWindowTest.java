package com.example.demo;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowTest {

    @Test
    void exampleUsage(){
        var reader = new StringReader("ABCDE");
        var windowSize = 3;
        var target = new SlidingWindow( reader, windowSize );


        // expects "ABC"
        assertFalse( target.eof() );
        assertEquals( 0, target.position() );
        assertArrayEquals(new char[]{'A', 'B', 'C'}, target.toCharArray() );


        target.advance();
        // expects "BCD"
        assertFalse( target.eof() );
        assertEquals( 1, target.position() );
        assertArrayEquals(new char[]{'B', 'C', 'D'}, target.toCharArray() );


        target.advance();
        // expects "CDE"
        assertFalse( target.eof() );
        assertEquals( 2, target.position() );
        assertArrayEquals(new char[]{'C', 'D', 'E'}, target.toCharArray() );


        target.advance();
        // expects EOF
        assertTrue( target.eof() );
        assertThrows( RuntimeException.class, target::toCharArray);
        assertThrows( RuntimeException.class, target::advance);
    }

}