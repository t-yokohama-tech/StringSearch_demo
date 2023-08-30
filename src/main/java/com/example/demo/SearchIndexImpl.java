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
        char[] strArray = str.toCharArray();
        SlidingWindow slidingWindow = new SlidingWindow(reader, strLength);
        while (!slidingWindow.eof()) {
            boolean flag = true;
            char[] sw = slidingWindow.toCharArray();
            int matchCharacterCount = 0;
            int i = strLength - 1;
            while (sw[i] == strArray[i]) {
                ++matchCharacterCount;
                if (matchCharacterCount == strLength) {
                    idxList.add(slidingWindow.position());
                    slidingWindow.advance(strLength);
                    flag = false;
                    break;
                }
                --i;
            }

            int j = strLength - 1;
            while (j >= 0 && flag && !slidingWindow.eof()) {
                if ((sw[i] == strArray[j])) {
                    break;
                }
                j--;
                slidingWindow.advance();
            }
        }
        return idxList;
    }
}

