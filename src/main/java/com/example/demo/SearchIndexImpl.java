package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchIndexImpl implements SearchIndex {
    @Override
    public List<Integer> search(Reader reader, String str) {
        List<Integer> idxList = new ArrayList<>(); //文字数リスト
        int strLength = str.length();
        char[] strArray = str.toCharArray();
        SlidingWindow slidingWindow = new SlidingWindow(reader, strLength);
        while (!slidingWindow.eof()) {

            if(Arrays.equals(slidingWindow.toCharArray(),strArray)){
                idxList.add(slidingWindow.position());
            }
            slidingWindow.advance();
        }
        return idxList;
    }
}

