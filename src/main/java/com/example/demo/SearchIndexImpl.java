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
            BMSearch bmSearch = new BMSearch(slidingWindow,strArray,idxList);
            int j = strLength-1;

            while (j >= 0 && bmSearch.flag() && !slidingWindow.eof()) {  //Windowをずらす用のループ
                if (strArray[j] == bmSearch.sw()[strLength-1])
                    break;

                slidingWindow.advance();
                j--;
            }
        }
        return idxList;
    }
}

