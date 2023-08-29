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
        char[] strArray = new char[strLength];
        SlidingWindow slidingWindow = new SlidingWindow(reader, strLength);
        int i = 0;

        while(strLength>i){
            strArray[i]=str.charAt(i);
            i++;
        }
        while (!slidingWindow.eof()) {

            if(Arrays.equals(slidingWindow.toCharArray(),strArray)){
                idxList.add(slidingWindow.position());
            }
            slidingWindow.advance();
        }
        return idxList;
    }
}

