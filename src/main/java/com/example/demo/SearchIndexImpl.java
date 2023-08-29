package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchIndexImpl implements SearchIndex {
    @Override
    public List<Integer> search(Reader reader, String str) {
        List<Integer> idxList = new ArrayList<>(); //文字数リスト
        int strLength = str.length();
        int count = 0;
        SlidingWindow slidingWindow = new SlidingWindow(reader, strLength);
        while (!slidingWindow.eof()) {
            if (String.valueOf(slidingWindow.toCharArray()).equals(str)) {
                idxList.add(slidingWindow.position() + count);
            }
            slidingWindow.advance();
        }
        return idxList;
    }
}

