package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class BMSearchTest {

    @Test
    void BMSearchTrue() {
        var slidingWindow = mock(SlidingWindow.class);
        {
            doReturn("ああいあい".toCharArray()).when(slidingWindow).toCharArray();
        }
        var strArray = ("あいうえお").toCharArray();
        var idxList = new ArrayList<Integer>();
        var target = new BMSearch(slidingWindow, strArray, idxList);

        assertTrue(target.flag());
        assertArrayEquals(new char[]{'あ', 'あ', 'い', 'あ', 'い'}, target.sw());
    }
    @Test
    void BMSearchFalse(){
        var slidingWindow = mock(SlidingWindow.class);
        {
            doReturn("あいうえお".toCharArray()).when(slidingWindow).toCharArray();
        }
        var strArray = ("あいうえお").toCharArray();
        var idxList = new ArrayList<Integer>();
        var target = new BMSearch(slidingWindow,strArray,idxList);

        assertFalse(target.flag());
        assertArrayEquals(new char[]{'あ','い','う','え','お'},target.sw());
    }
}
