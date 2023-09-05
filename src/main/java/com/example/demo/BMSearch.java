package com.example.demo;

import org.springframework.lang.NonNull;

import java.util.List;

public class BMSearch {
    private boolean flag = true;
    @NonNull
    private final char[] sw;

    public BMSearch(SlidingWindow slidingWindow, char[] strArray, List<Integer> idxList) {
        sw = slidingWindow.toCharArray();
        int matchCharacterCount = 0;
        int strLength = strArray.length;
        int i = strLength - 1;

        while (sw[i] == strArray[i]) {  //文字検索用ループ
            ++matchCharacterCount;
            if (matchCharacterCount == strLength) {
                idxList.add(slidingWindow.position());
                slidingWindow.advance(strLength);
                flag = false;
                break;
            }
            --i;
        }
    }

    public boolean flag() {
        return flag;
    }

    public char[] sw() {
        return sw;
    }
}
